package com.mediscreen.patient.repository;

import com.mediscreen.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient save(Patient patient);
    Patient findById(int id);
    void delete(Patient patient);
    List findAll();
    Patient findTopByOrderByIdDesc();
    Patient findTopByName(String name);
}
