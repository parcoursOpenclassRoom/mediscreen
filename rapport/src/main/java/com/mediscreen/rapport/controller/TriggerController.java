package com.mediscreen.rapport.controller;

import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.manager.TriggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trigger")
public class TriggerController {
    @Autowired
    TriggerManager triggerManager;

    @GetMapping
    public List<Trigger> list(){
        return triggerManager.list();
    }

    @GetMapping("/{id}")
    public Trigger find(@PathVariable int id){
        return triggerManager.find(id);
    }

    @PostMapping
    public Trigger create(@RequestBody Trigger trigger){
        return triggerManager.save(trigger);
    }

    @PutMapping
    public Trigger edit(@RequestBody Trigger trigger){
        return triggerManager.save(trigger);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestBody int id){
        triggerManager.delete(id);
    }

}
