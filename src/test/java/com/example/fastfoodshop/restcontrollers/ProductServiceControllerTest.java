package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductServiceControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    //TODO test product has to be deleted after delete method created
    @Test
    public void createProduct() throws Exception {
        String uri = "/products";
        Product product = new Product();
        product.setPrice(BigDecimal.ONE);
        product.setName("testProduct");
        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
