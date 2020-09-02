package com.mediscreen.rapport.repository;

import com.mediscreen.rapport.entity.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-note", url = "localhost:8082")
public interface NoteRepository {

    @GetMapping("/patHistory/patient/{id}")
    List<Note> findNoteByPatient(@PathVariable int id);
}
