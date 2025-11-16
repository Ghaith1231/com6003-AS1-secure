package uk.ac.ltu.hms.net;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class AppointmentsRepo {
    private final AppointmentsApi api;

    public AppointmentsRepo(AppointmentsApi api) {
        this.api = api;
    }

    /** Never returns null. Empty list means error/empty payload. */
    public List<Appointment> today() {
        try {
            Response<AppointmentsResponse> r = api.today().execute();
            if (!r.isSuccessful()) return Collections.emptyList();

            AppointmentsResponse body = r.body();
            if (body == null || body.appointments == null) return Collections.emptyList();

            return body.appointments;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
