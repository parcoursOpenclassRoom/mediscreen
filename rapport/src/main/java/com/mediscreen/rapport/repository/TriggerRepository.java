package com.mediscreen.rapport.repository;

import com.mediscreen.rapport.entity.Trigger;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TriggerRepository extends MongoRepository<Trigger, Integer> {
    Trigger save(Trigger trigger);
    Trigger findById(int id);
    void delete(Trigger trigger);
    List findAll();
    Trigger findTopByOrderByIdDesc();
    Trigger findTopByLibelle(String libelle);
}
