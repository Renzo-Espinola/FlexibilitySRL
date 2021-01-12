package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.models.Person;
import com.flexibilitysrl.services.SellerServiceImpl;
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
@WebMvcTest(controllers = SellerController.class)
class SellerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SellerServiceImpl vendedorService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<SellerEntity> sellerEntityList;

    @BeforeEach
    void setUp() {
        this.sellerEntityList = new ArrayList<>();
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2L);
        sellerEntityList.add(sellerEntity);
    }


    @Test
    void saveVendedor() throws  Exception{
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 3L);
        given(vendedorService.save(sellerEntity)).willAnswer((invocation)-> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Seller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sellerEntity)))
                .andExpect(status().isCreated());

    }

    @Test
    void findAll() throws  Exception{
        given(vendedorService.findAll()).willReturn(this.sellerEntityList);
        this.mockMvc.perform(get("/v1/Seller/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(sellerEntityList.size())));
    }

    @Test
    void findById() throws  Exception{
        Long idVendedor = 1L;
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2l);
        given(vendedorService.findBy(idVendedor)).willReturn(sellerEntity);

        this.mockMvc.perform(get("/v1/Seller/{id}",idVendedor))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws  Exception {
        Long idVendedor = 1L;
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2L);
        given(vendedorService.findBy(idVendedor)).willReturn(sellerEntity);
        doNothing().when(vendedorService).deleteBy(sellerEntity.getIdVendedor());

        this.mockMvc.perform(delete("/v1/Seller/{id}", sellerEntity.getIdVendedor()))
                .andExpect(status().isOk());
    }
}