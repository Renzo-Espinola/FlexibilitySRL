package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.models.Persona;
import com.flexibilitysrl.services.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteServiceImpl clienteService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<ClientesEntity> clientesEntityList;

    @BeforeEach
    void setUp() {
        this.clientesEntityList = new ArrayList<>();
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        ClientesEntity clientesEntity = new ClientesEntity(1L, "CONSUMIDOR FINAL");
        ClientesEntity clientesEntity2 = new ClientesEntity(2L, "IVA EXENTO");
        clientesEntityList.add(clientesEntity);
        clientesEntityList.add(clientesEntity2);
    }

    @Test
    void findAll() throws Exception {
        given(clienteService.findAll()).willReturn(this.clientesEntityList);
        this.mockMvc.perform(get("/v1/Cliente/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(clientesEntityList.size())));
    }

    @Test
    void saveCliente() throws Exception {
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        ClientesEntity clientesEntity = new ClientesEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.save(clientesEntity)).willAnswer((invocation)-> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientesEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    void findById() throws Exception {
        Long idCliente = 1L;
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        ClientesEntity clientesEntity = new ClientesEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.findBy(idCliente)).willReturn(Optional.of(clientesEntity));

        this.mockMvc.perform(get("/v1/Cliente/{id}",idCliente))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        Long idCliente = 1L;
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        ClientesEntity clientesEntity = new ClientesEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.findBy(idCliente)).willReturn(Optional.of(clientesEntity));
        doNothing().when(clienteService).deleteBy(clientesEntity.getIdCliente());

        this.mockMvc.perform(delete("/v1/Cliente/{id}",clientesEntity.getIdCliente()))
                .andExpect(status().isOk());
    }
}