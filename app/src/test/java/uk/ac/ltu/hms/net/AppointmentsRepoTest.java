package uk.ac.ltu.hms.net;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class AppointmentsRepoTest {

    @Test
    public void today_returnsTwoAppointments() {
        AppointmentsRepo repo = new AppointmentsRepo();

        List<Appointment> list = repo.today();

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("Alex", list.get(0).patient);
        assertEquals("Sam", list.get(1).patient);
    }

    @Test
    public void patient_aliasCallsToday() {
        AppointmentsRepo repo = new AppointmentsRepo();

        List<Appointment> list1 = repo.today();
        List<Appointment> list2 = repo.patient();

        assertEquals(list1.size(), list2.size());
        assertEquals(list1.get(0).patient, list2.get(0).patient);
    }
}
