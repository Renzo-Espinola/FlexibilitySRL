package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.services.ProductServiceImpl;
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
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productoService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductEntity> productEntityList;

    @BeforeEach
    void setUp() {
        this.productEntityList = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        ProductEntity productEntity2 = new ProductEntity("2", "FACT", 60, 100L, "COM");
        productEntityList.add(productEntity);
        productEntityList.add(productEntity2);
    }

    @Test
    void save_producto_iscreated_test() throws Exception {
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        given(productoService.save(productEntity)).willAnswer((invocation) -> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    void find_all_products_isok_test() throws Exception {
        given(productoService.findAll()).willReturn(this.productEntityList);
        this.mockMvc.perform(get("/v1/Producto/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productEntityList.size())));
    }

    @Test
    void find_byid_product_isok_test() throws Exception {
        Long idProducto = 1L;
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        given(productoService.findById(String.valueOf(idProducto))).willReturn(productEntity);

        this.mockMvc.perform(get("/v1/Producto/{id}", idProducto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto", is(productEntity.getNombreProducto())));
    }

    @Test
    void deletebyid_product_isok_test() throws Exception {
        Long idProducto = 1L;
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        given(productoService.findById(String.valueOf(idProducto))).willReturn(productEntity);
        doNothing().when(productoService).deleteBy(Long.valueOf(productEntity.getIdProducto()));

        this.mockMvc.perform(delete("/v1/Producto/{id}", productEntity.getIdProducto()))
                .andExpect(status().isOk());
    }
}