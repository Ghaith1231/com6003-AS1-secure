package uk.ac.ltu.hms.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class PatientDaoTest {

    private HmsDatabase db;
    private uk.ac.ltu.hms.data.PatientDao dao;

    @Before
    public void setup() {
        Context ctx = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(ctx, HmsDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.patientDao();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void insertAndFindByNhs() {
        Patient p = new Patient();
        p.fullName = "Alex Patient";
        p.nhsNumber = "9434765919"; // valid example

        long id = dao.insert(p);
        assertTrue(id > 0);

        Patient got = dao.findByNhs("9434765919");
        assertNotNull(got);
        assertEquals("Alex Patient", got.fullName);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void nhsMustBeUnique() {
        Patient a = new Patient(); a.fullName = "A"; a.nhsNumber = "9434765919";
        Patient b = new Patient(); b.fullName = "B"; b.nhsNumber = "9434765919";
        dao.insert(a);
        dao.insert(b); // second insert should violate the UNIQUE index
    }
}
