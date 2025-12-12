package com.github.rusichpt.patient.ms.service;

import com.github.rusichpt.patient.ms.entity.Patient;
import com.github.rusichpt.patient.ms.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    public PatientMapper patientMapper;
    @Override
    public Patient insert(Patient patient) {
        patientMapper.insert(patient);
        return patientMapper.selectCurrentPatient();
    }

    @Override
    public Patient select(int id) {
        return patientMapper.selectById(id);
    }

    @Override
    public Patient select(String firstName, String midName, String lastName, Date birthday) {
        return patientMapper.selectByName(firstName, midName, lastName, birthday);
    }

    @Override
    public Patient update(int id, Patient newPatient) {
        Patient oldPatient = patientMapper.selectById(id);
        newPatient.setId(id);

        if (oldPatient == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else if (newPatient.equals(oldPatient))
        {
            return oldPatient;
        }

        patientMapper.update(newPatient);

        return select(id);
    }

    @Override
    public void delete(int id) {
        patientMapper.delete(id);
    }
}
