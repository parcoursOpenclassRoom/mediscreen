package com.mediscreen.patient;

import com.mediscreen.patient.entity.Patient;
import com.mediscreen.patient.manager.PatientManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientITTest {
    @Autowired
    PatientManager patientManager;

    @Test
    public void PatientManagerTest() {
        Patient patient = new Patient(1, "TOTO", "TITI", "M", new Date(), "Massy Palaiseau", "08874689" );

        // Save
        patient = patientManager.save(patient);
        assertNotNull(patient.getId());
        assertEquals(patient.getFirstName(), "TOTO", "TOTO");

        // Update
        patient.setPhone("07983467");
        patient = patientManager.save(patient);
        assertEquals(patient.getPhone(), "07983467", "07983467");

        // Find
        List<Patient> listResult = patientManager.list();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = patient.getId();
        patientManager.delete(id);
        patient = patientManager.find(id);
        assertNull(patient);
    }
}
