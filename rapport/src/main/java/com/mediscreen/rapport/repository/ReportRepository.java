package com.mediscreen.rapport.repository;

import com.mediscreen.rapport.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, Integer> {
    Report save(Report report);
    Report findById(int id);
    void delete(Report report);
    List findAll();
    Report findTopByOrderByIdDesc();
}
