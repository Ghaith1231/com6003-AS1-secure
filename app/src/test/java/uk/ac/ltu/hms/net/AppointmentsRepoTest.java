// app/src/test/java/uk/ac/ltu/hms/net/AppointmentsRepoTest.java
package uk.ac.ltu.hms.net;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

public class AppointmentsRepoTest {
    private MockWebServer server;
    private AppointmentsRepo repo;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        String baseUrl = server.url("/").toString();       // http://127.0.0.1:xxxx/
        RetrofitFactory rf = new RetrofitFactory(baseUrl); // <-- instance, not static
        repo = new AppointmentsRepo(rf.api());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void today_returnsList() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"appointments\":[{\"id\":1,\"patient\":\"Alex\"},{\"id\":2,\"patient\":\"Sam\"}]}")
                .addHeader("Content-Type", "application/json"));

        List<Appointment> list = repo.today();             // <-- no arguments
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("Alex", list.get(0).patient);

        // verify the HTTP path hit by Retrofit
        assertEquals("/appointments/today", server.takeRequest().getPath());
    }

    @Test
    public void today_http500_returnsEmpty() {
        server.enqueue(new MockResponse().setResponseCode(500));
        List<Appointment> list = repo.today();
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void today_badJson_returnsEmpty() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}")
                .addHeader("Content-Type", "application/json"));
        List<Appointment> list = repo.today();
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }
}
