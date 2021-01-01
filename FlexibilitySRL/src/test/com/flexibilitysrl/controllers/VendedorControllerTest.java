package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.models.Persona;
import com.flexibilitysrl.services.VendedorServiceImpl;
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
@WebMvcTest(controllers = VendedorController.class)
class VendedorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VendedorServiceImpl vendedorService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<VendedorEntity> vendedorEntityList;

    @BeforeEach
    void setUp() {
        this.vendedorEntityList = new ArrayList<>();
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        VendedorEntity vendedorEntity = new VendedorEntity(1L, 2L);
        vendedorEntityList.add(vendedorEntity);
    }


    @Test
    void saveVendedor() throws  Exception{
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        VendedorEntity vendedorEntity = new VendedorEntity(1L, 3L);
        given(vendedorService.save(vendedorEntity)).willAnswer((invocation)-> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Vendedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorEntity)))
                .andExpect(status().isCreated());

    }

    @Test
    void findAll() throws  Exception{
        given(vendedorService.findAll()).willReturn(this.vendedorEntityList);
        this.mockMvc.perform(get("/v1/Vendedor/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(vendedorEntityList.size())));
    }

    @Test
    void findById() throws  Exception{
        Long idVendedor = 1L;
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        VendedorEntity vendedorEntity = new VendedorEntity(1L, 2l);
        given(vendedorService.findBy(idVendedor)).willReturn(Optional.of(vendedorEntity));

        this.mockMvc.perform(get("/v1/Vendedor/{id}",idVendedor))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws  Exception {
        Long idVendedor = 1L;
        Persona persona = new Persona(33074277L, "Renzo", "Espinola");
        VendedorEntity vendedorEntity = new VendedorEntity(1L, 2L);
        given(vendedorService.findBy(idVendedor)).willReturn(Optional.of(vendedorEntity));
        doNothing().when(vendedorService).deleteBy(vendedorEntity.getIdVendedor());

        this.mockMvc.perform(delete("/v1/Vendedor/{id}",vendedorEntity.getIdVendedor()))
                .andExpect(status().isOk());
    }
}