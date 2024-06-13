package com.vehicle.vehicle_information.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vehicle.vehicle_information.model.VehicleDto;
import com.vehicle.vehicle_information.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VehicleController.class)
 class VehicleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    VehicleService vehicleService;
    @Test
    void getVehicleByPathVariableTest() throws Exception {
        VehicleDto vehicleDto=VehicleDto.builder().regNo(1).build();
        Mockito.when(vehicleService.findVehicle(1)).thenReturn(vehicleDto);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/vehicle/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        assertEquals(302,result.getResponse().getStatus());
        String exp = "{\"message\":\"Found data\",\"statusCode\":302,\"vehicles\":null,\"data\":{\"regNo\":1,\"createdOn\":null,\"updatedOn\":null,\"createdBy\":null,\"updatedBy\":null,\"isDeleted\":0,\"owner\":null,\"brand\":null,\"colours\":null,\"rto\":null}}";
        assertEquals(exp,result.getResponse().getContentAsString());
    }

    @Test
    void getVehicleByPathVariableTest2() throws Exception {
        VehicleDto vehicleDto=VehicleDto.builder().regNo(1).build();
        Mockito.when(vehicleService.findVehicle(1)).thenReturn(vehicleDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.regNo").value(1));

    }
    @Test
    void saveVehicleInfoTest() throws Exception {
        VehicleDto vehicleDto=VehicleDto.builder().regNo(1).build();
        Mockito.when(vehicleService.saveVehicle(any())).thenReturn(vehicleDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle")
                        .content(objectToJsonString(VehicleDto.builder().rto("Test").brand("TestCase").build()))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.regNo").isNumber());;
    }

    String objectToJsonString(VehicleDto vehicleDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(vehicleDto);
    }



}
