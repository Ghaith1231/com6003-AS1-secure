package uk.ac.ltu.hms.net;

import java.util.List;

public class AppointmentsResponse {
    // MUST match the JSON key: {"appointments":[ ... ]}
    public List<Appointment> appointments;
}
