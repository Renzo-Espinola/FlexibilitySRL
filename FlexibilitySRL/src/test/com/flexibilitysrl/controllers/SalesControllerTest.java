package com.flexibilitysrl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexibilitysrl.entity.*;
import com.flexibilitysrl.models.RequestsSells;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SalesController.class)
class SalesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SellsServiceImpl salesService;
    @MockBean
    private ProductServiceImpl productService;
    @MockBean
    private CostumerServiceImpl costumerService;
    @MockBean
    private SellerServiceImpl sellerService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<SalesEntity> salesEntityList;

    private Date fechaCompra;

    private CostumerEntity costumerEntity;

    private SellerEntity sellerEntity;
    private List<ProductEntity> productEntityList;
    private SalesEntity salesEntity;
    private List<String> productidList;
    private RequestsSells requestsSells;


    @BeforeEach
    void setUp() {
        this.fechaCompra = new Date();
        sellerEntity = new SellerEntity(1L, 33074277L, "Renzo", "Espinola", 3L);
        costumerEntity = new CostumerEntity(1L, 33074278L, "Renzon", "Espinolas", "CONS FIN");
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        salesEntityList = new ArrayList<>();
        salesEntity = new SalesEntity(1L, 2, 5L, fechaCompra, productEntityList, costumerEntity, sellerEntity);
        salesEntityList.add(salesEntity);
        productidList = new ArrayList<>();
        productidList.add("1");
        requestsSells = new RequestsSells(salesEntity, 1l, 1L, productidList);

    }

    @Test
    void save_sell_iscreated_test() throws Exception {
        this.fechaCompra = new Date();
        given(salesService.saveVentas(salesEntity, productEntityList, costumerEntity, sellerEntity)).willAnswer((invocation) -> invocation.getArguments());
        given(sellerService.findBy(any())).willReturn(sellerEntity);
        given(costumerService.findBy(any())).willReturn(costumerEntity);

        this.mockMvc.perform(post("/v1/Sales/newsells")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString((requestsSells))))
                .andExpect(status().isCreated());

    }

    @Test
    void find_by_id_sells_isok_test() throws Exception {
        Long idVentas = 1L;
        given(salesService.saveVentas(salesEntity, productEntityList, costumerEntity, sellerEntity)).willAnswer((invocation) -> invocation.getArguments());
        given(sellerService.findBy(any())).willReturn(sellerEntity);
        given(costumerService.findBy(any())).willReturn(costumerEntity);
        given(salesService.findByIdVenta((idVentas))).willReturn(salesEntity);
        int idVentaRec= Math.toIntExact(salesEntity.getIdVentas());

        this.mockMvc.perform(get("/v1/Sales/{id}",idVentas))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVentas", is(idVentaRec)));

    }

    @Test
    void find_all_sells_isok_test() throws Exception {
        this.fechaCompra = new Date();
        PersonEntity person = new PersonEntity(33074277L, "Renzo", "Espinola");
        SellerEntity sellerEntity = new SellerEntity(1L, 2L);
        CostumerEntity costumerEntity = new CostumerEntity(1L, "CONSUMIDOR FINAL");
        ProductEntity productEntity = new ProductEntity("1", "PAN", 50, 100L, "COM");
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        List<SalesEntity> salesEntityList = new ArrayList<>();
        SalesEntity salesEntity = new SalesEntity(1L, 2, 5L, fechaCompra, productEntityList, costumerEntity, sellerEntity);
        salesEntityList.add(salesEntity);

        given(salesService.findAllVentas()).willReturn(salesEntityList);
        this.mockMvc.perform(get("/v1/Sales/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(salesEntityList.size())));
    }

    @Test
    void delete_by_id_sells_isok() throws Exception {
        Long idVenta = 1L;
        given(salesService.saveVentas(salesEntity, productEntityList, costumerEntity, sellerEntity)).willAnswer((invocation) -> invocation.getArguments());
        given(sellerService.findBy(any())).willReturn(sellerEntity);
        given(costumerService.findBy(any())).willReturn(costumerEntity);

        doNothing().when(salesService).deleteVentas(Long.valueOf(salesEntity.getIdVentas()));

        this.mockMvc.perform(delete("/v1/Sales/{id}", salesEntity.getIdVentas()))
                .andExpect(status().isOk());
    }
}