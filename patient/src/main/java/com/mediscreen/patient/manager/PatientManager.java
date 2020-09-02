package com.mediscreen.patient.manager;

import com.mediscreen.patient.entity.Patient;

import java.util.List;

/**
 * Patient Action Manager
 */
public interface PatientManager {
    /**
     * save and edit patient
     * @param patient
     * @return
     */
    Patient save(Patient patient);

    /**
     * find patient from id
     * @param id
     * @return
     */
    Patient find(int id);

    /**
     * delete patient from id
     * @param id
     */
    void delete(int id);

    /**
     * get all patient
     * @return
     */
    List<Patient> list();

    /**
     * get last record patient
     * @return
     */
    Patient findTopByOrderByIdDesc();

    Patient findByName(String familyName);
}
