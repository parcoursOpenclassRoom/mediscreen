package com.mediscreen.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mediscreen.note.entity.Note;
import com.mediscreen.note.manager.NoteManager;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteE2ETest {
    @Autowired
    NoteManager noteManager;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
    String uri = "patHistory/";

    @Test
    @Order(1)
    public void createTest() throws Exception {
        Note note = new Note(1, "Test Note", 1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(note);
        MvcResult result = mockMvc.perform(post(createURLWithPort(uri + "add")).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(note.getIdPatient(), jsonObject.get("idPatient"));
    }

    @Test
    @Order(2)
    public void updateTest() throws Exception {
        Note note = noteManager.find(1);
        note.setIdPatient(2);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(note);
        MvcResult result = mockMvc.perform(put(createURLWithPort(uri + "edit")).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(note.getIdPatient(), jsonObject.get("idPatient"));
        assertEquals(note.getId(), jsonObject.get("id"));
    }

    @Test
    @Order(3)
    public void deleteTest() throws Exception {
        int id = 1;
        mockMvc.perform(delete(createURLWithPort(uri+"/"+id)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
