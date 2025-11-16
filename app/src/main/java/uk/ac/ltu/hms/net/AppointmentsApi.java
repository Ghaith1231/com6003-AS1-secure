package uk.ac.ltu.hms.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppointmentsApi {
    @GET("appointments/today")
    Call<AppointmentsResponse> today();
}
