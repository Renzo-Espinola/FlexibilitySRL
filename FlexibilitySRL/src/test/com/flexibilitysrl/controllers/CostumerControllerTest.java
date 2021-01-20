package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.models.Person;
import com.flexibilitysrl.services.CostumerServiceImpl;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CostumerController.class)
class CostumerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CostumerServiceImpl clienteService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<CostumerEntity> costumerEntityList;

    @BeforeEach
    void setUp() {
        this.costumerEntityList = new ArrayList<>();
        Person person = new Person(33074277L, "Renzo", "Espinola");
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        CostumerEntity costumerEntity2 = new CostumerEntity(2L, "IVA EXENTO");
        costumerEntityList.add(costumerEntity);
        costumerEntityList.add(costumerEntity2);
    }

    @Test
    void find_all_costumers_isok_test() throws Exception {
        given(clienteService.findAll()).willReturn(this.costumerEntityList);
        this.mockMvc.perform(get("/v1/Costumer/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(costumerEntityList.size())));
    }

    @Test
    void save_costumer_iscreated_test() throws Exception {
        Person person = new Person(33074277L, "Renzo", "Espinola");
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.save(costumerEntity)).willAnswer((invocation)-> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Costumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(costumerEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    void find_by_id_costumer_isok_test() throws Exception {
        Long idCliente = 1L;
        Person person = new Person(33074277L, "Renzo", "Espinola");
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.findBy(idCliente)).willReturn(costumerEntity);

        this.mockMvc.perform(get("/v1/Costumer/{id}",idCliente))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(costumerEntity.getNombre())));
    }

    @Test
    void delete_by_id_costumer_isok_test() throws Exception {
        Long idCliente = 1L;
        Person person = new Person(33074277L, "Renzo", "Espinola");
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        given(clienteService.findBy(idCliente)).willReturn(costumerEntity);
        doNothing().when(clienteService).deleteBy(costumerEntity.getIdCliente());

        this.mockMvc.perform(delete("/v1/Costumer/{id}", costumerEntity.getIdCliente()))
                .andExpect(status().isOk());
    }
}