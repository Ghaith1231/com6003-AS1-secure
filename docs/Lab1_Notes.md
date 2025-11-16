# Lab 1 – Room DAO & Unit Tests
- Entities/DAO: Patient (unique index on nhsNumber), PatientDao (insert, findByNhs), HmsDatabase.
- Tests: insertAndFindByNhs(), nhsMustBeUnique() using Robolectric (sdk 34) + inMemoryDatabaseBuilder.
- Key decisions: Room classes live in main/ so annotationProcessor can generate HmsDatabase_Impl.
