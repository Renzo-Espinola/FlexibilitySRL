package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.services.ProductoServiceImpl;
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
@WebMvcTest(controllers = ProductoController.class)
class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductoServiceImpl productoService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductoEntity> productoEntityList;

    @BeforeEach
    void setUp() {
        this.productoEntityList = new ArrayList<>();
        ProductoEntity productoEntity = new ProductoEntity(1L,"PAN",50,100,"COM");
        ProductoEntity productoEntity2 = new ProductoEntity(2L,"FACT",60,100,"COM");
        productoEntityList.add(productoEntity);
        productoEntityList.add(productoEntity2);
    }

    @Test
    void saveProducto() throws Exception{
        ProductoEntity productoEntity = new ProductoEntity(1L,"PAN",50,100,"COM");
        given(productoService.save(productoEntity)).willAnswer((invocation)-> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        given(productoService.findAll()).willReturn(this.productoEntityList);
        this.mockMvc.perform(get("/v1/Producto/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productoEntityList.size())));
    }

    @Test
    void findById() throws Exception{
        Long idProducto = 1L;
        ProductoEntity productoEntity = new ProductoEntity(1L,"PAN",50,100,"COM");
        given(productoService.findById(idProducto)).willReturn(productoEntity);

        this.mockMvc.perform(get("/v1/Producto/{id}",idProducto))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        Long idProducto = 1L;
        ProductoEntity productoEntity = new ProductoEntity(1L,"PAN",50,100,"COM");
        given(productoService.findById(idProducto)).willReturn(productoEntity);
        doNothing().when(productoService).deleteBy(productoEntity.getIdProducto());

        this.mockMvc.perform(delete("/v1/Producto/{id}",productoEntity.getIdProducto()))
                .andExpect(status().isOk());
    }
}