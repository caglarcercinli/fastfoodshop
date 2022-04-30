package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Product;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void postProductAndDeleteProductTest() throws Exception {
        // a product is to be posted
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

        //all products are to be get and turned to a json array
        String uriAll = "/products";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForAll = resultActions.andReturn();
        String data = mvcResultForAll.getResponse().getContentAsString();
        JSONArray jsonArr = new JSONArray(data);

        //the last added test product is to be deleted
        String uriDelete = "/products/".concat(String.valueOf(jsonArr.getJSONObject(jsonArr.length()-1).getInt("id")));
        ResultActions resultActionsDelete = mvc.perform(MockMvcRequestBuilders.delete(uriDelete)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForDelete = resultActionsDelete.andReturn();
        int statusForDelete = mvcResultForDelete.getResponse().getStatus();
        assertEquals(200, statusForDelete);
    }
    @Test
    public void getProductTest() throws Exception {
        String uri = "/products/1";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void nonExistingGetProductTest() throws Exception {
        String uri = "/products/-1";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void getProductsTest() throws Exception {
        String uri = "/products";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResult = resultActions.andReturn();
        String data = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArr = new JSONArray(data);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void nonExistingDeleteProductTest() throws Exception {
        String uri = "/products/-1";
        ResultActions resultActionsDelete = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForDelete = resultActionsDelete.andReturn();
        int statusForDelete = mvcResultForDelete.getResponse().getStatus();
        assertEquals(404, statusForDelete);
    }
}
