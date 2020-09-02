package com.mediscreen.rapport.manager;

import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.repository.TriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TriggerManagerImpl implements TriggerManager {
    @Autowired
    TriggerRepository triggerRepository;

    @Override
    public Trigger save(Trigger trigger) {
        if(trigger.getId() == 0)
            trigger.setId(findTopByOrderByIdDesc() != null ? findTopByOrderByIdDesc().getId() + 1 : 1);
        if(findByLibelle(trigger.getLibelle()) == null)
            trigger = triggerRepository.save(trigger);
        return trigger;
    }

    @Override
    public Trigger find(int id) {
        return triggerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        triggerRepository.delete(find(id));
    }

    @Override
    public List<Trigger> list() {
        return triggerRepository.findAll();
    }

    @Override
    public Trigger findTopByOrderByIdDesc() {
        return triggerRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Trigger findByLibelle(String libelle) {
        return triggerRepository.findTopByLibelle(libelle);
    }
}
