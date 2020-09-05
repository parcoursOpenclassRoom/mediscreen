package com.mediscreen.patient.manager;

import com.mediscreen.patient.entity.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientManagerImpl implements PatientManager {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient find(int id) {
        return patientRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        patientRepository.delete((find(id)));
    }

    @Override
    public List<Patient> list() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findTopByOrderByIdDesc() {
        return patientRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Patient findByName(String familyName) {
        return patientRepository.findTopByName(familyName);
    }
}
