package com.mediscreen.rapport.controller;

import com.mediscreen.rapport.entity.Report;
import com.mediscreen.rapport.manager.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assess")
public class ReportController {
@Autowired
    ReportManager reportManager;

    @PostMapping("/id")
    public Report ReportById(@RequestParam("patId") int patId){
        return reportManager.reportPatient(patId);
    }

    @PostMapping("/familyName")
    public Report ReportByFamily(@RequestParam("familyName") String familyName){
        return reportManager.reportPatient(familyName);
    }
}
