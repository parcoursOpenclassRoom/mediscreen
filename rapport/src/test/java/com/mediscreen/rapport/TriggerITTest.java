package com.mediscreen.rapport;

import com.mediscreen.rapport.entity.Trigger;
import com.mediscreen.rapport.manager.TriggerManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TriggerITTest {
    @Autowired
    TriggerManager triggerManager;

    @Test
    public void NoteManagerTest() {
        Trigger trigger = new Trigger("trigger");

        // Save
        trigger = triggerManager.save(trigger);
        assertNotNull(trigger.getId());
        assertEquals(trigger.getLibelle(), "trigger", "trigger");

        // Update
        trigger.setLibelle("triggerEdit");
        trigger = triggerManager.save(trigger);
        assertEquals(trigger.getLibelle(), "triggerEdit", "triggerEdit");

        // Find
        List<Trigger> listResult = triggerManager.list();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = trigger.getId();
        triggerManager.delete(id);
        trigger = triggerManager.find(id);
        assertNull(trigger);
    }
}
