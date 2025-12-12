package com.github.rusichpt.patient.ms.services;

import com.github.rusichpt.patient.ms.entities.Patient;

import java.sql.Date;

public interface PatientService {

    Patient insert(Patient patient);

    Patient select(int id);

    Patient select(String firstName, String midName, String lastName, Date birthday);

    Patient update(int id, Patient patient);

    void delete(int id);

}
