package uk.ac.ltu.hms.net;

import java.util.ArrayList;
import java.util.List;


public class AppointmentsRepo {

    public List<Appointment> today() {
        // In the real app you would call Retrofit here.
        // For the lab we just return some dummy data.
        List<Appointment> list = new ArrayList<>();

        Appointment a1 = new Appointment();
        a1.id = 1;
        a1.patient = "Alex";
        a1.clinic = "NHS Clinic A";
        a1.time = "10:00";
        list.add(a1);

        Appointment a2 = new Appointment();
        a2.id = 2;
        a2.patient = "Sam";
        a2.clinic = "NHS Clinic B";
        a2.time = "11:00";
        list.add(a2);

        return list;
    }

    // alias so your old test name "patient()" still works if you want it
    public List<Appointment> patient() {
        return today();
    }
}
