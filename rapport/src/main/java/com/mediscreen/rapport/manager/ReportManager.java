package com.mediscreen.rapport.manager;

import com.mediscreen.rapport.entity.Report;

import java.util.List;

/**
 * Report Action Manager
 */
public interface ReportManager {
    /**
     * save and edit report
     * @param report
     * @return
     */
    Report save(Report report);

    /**
     * find report from id
     * @param id
     * @return
     */
    Report find(int id);

    /**
     * delete report from id
     * @param id
     */
    void delete(int id);

    /**
     * get all report
     * @return
     */
    List<Report> list();

    /**
     * get last record report
     * @return
     */
    Report findTopByOrderByIdDesc();

    /**
     * get last record report
     * @return
     */
    Report reportPatient(int idPatient);

    Report reportPatient(String familyName);
}
