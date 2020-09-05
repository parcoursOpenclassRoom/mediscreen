package com.mediscreen.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mediscreen.patient.entity.Patient;
import com.mediscreen.patient.manager.PatientManager;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientE2ETest {
    @Autowired
    PatientManager patientManager;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
    String uri = "patient";

    @Test
    @Order(1)
    public void createTest() throws Exception {
        Patient patient = new Patient("TOTO", "TITI", "M", new Date(), "Massy Palaiseau", "08874689" );
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(patient);
        MvcResult result = mockMvc.perform(post(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(patient.getFirstName(), jsonObject.get("firstName"));
    }

    @Test
    public void addTest() throws Exception {
        String req = "/add?family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333";
        MvcResult result = mockMvc.perform(post(createURLWithPort(uri+req)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals("Test", jsonObject.get("firstName"));
        assertEquals("TestNone", jsonObject.get("name"));
    }

    @Test
    @Order(3)
    public void findAllTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(get(createURLWithPort(uri)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        List<Patient> patients = mapper.readValue(content, List.class);
        assertTrue(patients.size() > 0);
    }

    @Test
    @Order(4)
    public void updateTest() throws Exception {
        Patient patient = patientManager.findTopByOrderByIdDesc();
        patient.setPhone("07070707");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(patient);
        MvcResult result = mockMvc.perform(put(createURLWithPort(uri)).contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertNotNull(jsonObject);
        assertEquals(patient.getPhone(), jsonObject.get("phone"));
        assertEquals(patient.getId(), jsonObject.get("id"));
    }

    @Test
    @Order(5)
    public void deleteTest() throws Exception {
        Patient patient = patientManager.findTopByOrderByIdDesc();
        int id = patient.getId();
        mockMvc.perform(delete(createURLWithPort(uri+"/"+id)).contentType(APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
