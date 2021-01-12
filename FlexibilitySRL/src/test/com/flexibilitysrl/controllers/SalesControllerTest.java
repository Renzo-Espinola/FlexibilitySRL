package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.entity.SellsEntity;
import com.flexibilitysrl.models.Person;
import com.flexibilitysrl.services.CostumerServiceImpl;
import com.flexibilitysrl.services.ProductServiceImpl;
import com.flexibilitysrl.services.SellerServiceImpl;
import com.flexibilitysrl.services.SellsServiceImpl;
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
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SalesController.class)
class SalesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SellsServiceImpl ventasService;
    @MockBean
    private ProductServiceImpl productoService;
    @MockBean
    private CostumerServiceImpl clienteService;
    @MockBean
    private SellerServiceImpl vendedorService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<SellsEntity> sellsEntityList;

    private Date fechaCompra;

    @BeforeEach
    void setUp() {
        this.fechaCompra = new Date();
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2L);
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        List<SellsEntity> sellsEntityList = new ArrayList<>();
        SellsEntity sellsEntity = new SellsEntity(1L, 2, 5L, fechaCompra, productEntityList, costumerEntity, sellerEntity);
        sellsEntityList.add(sellsEntity);

    }

    @Test
    void saveVenta() throws Exception {
        Person person = new Person(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2L);
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        SellsEntity sellsEntity = new SellsEntity(1L, 2, 5L, fechaCompra, productEntityList, costumerEntity, sellerEntity);

        given(ventasService.saveVentas(sellsEntity, productEntityList, costumerEntity, sellerEntity)).willAnswer((invocation) -> invocation.getArguments());

        this.mockMvc.perform(post("/v1/Ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sellsEntity)))
                .andExpect(status().isCreated());
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