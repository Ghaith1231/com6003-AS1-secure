# COM6003 – Lab 3

## Screens Implemented

- Main menu (MainActivity) entry point to EHR features.
- EHR summary (EhrSummaryActivity)  displays basic patient details.
- Edit EHR (EditEhrActivity)  allows editing patient information.
- Vitals entry (VitalsActivity)  records patient vitals.
- Appointments list (AppointmentsActivity) shows appointments and clinic filter.

## Limitations

- Barcode scanning is not implemented in this version; patient lookup is via manual navigation.
- Some data (appointments, EHR content) is dummy rather than from a live backend.

## Unit Tests

- 8 unit tests executed successfully 
- Tests cover NHS validation, repository behaviour, and related logic.

## Static Testing

- Critical errors fixed in EHR, vitals, and appointments components.
- Remaining warnings are minor