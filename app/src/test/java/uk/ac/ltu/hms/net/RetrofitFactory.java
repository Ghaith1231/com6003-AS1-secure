package uk.ac.ltu.hms.net;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitFactory {
    private final Retrofit retrofit;

    public RetrofitFactory(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public AppointmentsApi api() {
        return retrofit.create(AppointmentsApi.class);
    }
}


//commiot