package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.VentasEntity;
import com.flexibilitysrl.services.VentasServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VentasController.class)
class VentasControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VentasServiceImpl ventasService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<VentasEntity> ventasEntityList;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveVenta() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}