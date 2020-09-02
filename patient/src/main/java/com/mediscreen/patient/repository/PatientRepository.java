package com.mediscreen.patient.repository;

import com.mediscreen.patient.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, Integer> {
    Patient save(Patient patient);
    Patient findById(int id);
    void delete(Patient patient);
    List findAll();
    Patient findTopByOrderByIdDesc();
    Patient findTopByName(String name);
}
