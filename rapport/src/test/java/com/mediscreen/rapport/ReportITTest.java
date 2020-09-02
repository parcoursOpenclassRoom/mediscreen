package com.mediscreen.rapport;

import com.mediscreen.rapport.entity.Report;
import com.mediscreen.rapport.manager.ReportManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReportITTest {
    @Autowired
    ReportManager reportManager;

    @Test
    public void NoteManagerTest() {
        Report report = new Report(1, 2, "Test risk", "Description");

        // Save
        report = reportManager.save(report);
        assertNotNull(report.getId());
        assertEquals(report.getRisk(), "Test risk", "Test risk");

        // Update
        report.setIdPatient(2);
        report = reportManager.save(report);
        assertEquals(report.getIdPatient(), 2, 2);

        // Find
        List<Report> listResult = reportManager.list();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = report.getId();
        reportManager.delete(id);
        report = reportManager.find(id);
        assertNull(report);
    }
}
