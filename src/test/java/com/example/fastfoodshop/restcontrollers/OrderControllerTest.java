package com.example.fastfoodshop.restcontrollers;

import com.example.fastfoodshop.domain.Order;
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

public class OrderControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getOrderTest() throws Exception {
        String uri = "/orders/1";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void nonExistingGetOrderTest() throws Exception {
        String uri = "/orders/-1";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void getOrdersTest() throws Exception {
        String uri = "/orders";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResult = resultActions.andReturn();
        String data = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArr = new JSONArray(data);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void nonExistingDeleteOrderTest() throws Exception {
        String uri = "/orders/-1";
        ResultActions resultActionsDelete = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForDelete = resultActionsDelete.andReturn();
        int statusForDelete = mvcResultForDelete.getResponse().getStatus();
        assertEquals(404, statusForDelete);
    }

    @Test
    public void postProductAndDeleteOrderTest() throws Exception {
        // an order is to be posted
        String uri = "/orders";
        Order order = new Order();
        order.setUser_id(1L);
        String inputJson = super.mapToJson(order);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        //all orders are to be get and turned to a json array
        String uriAll = "/orders";
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForAll = resultActions.andReturn();
        String data = mvcResultForAll.getResponse().getContentAsString();
        JSONArray jsonArr = new JSONArray(data);

        //the last added test order is to be deleted
        String uriDelete = "/orders/".concat(String.valueOf(jsonArr.getJSONObject(jsonArr.length()-1).getInt("id")));
        ResultActions resultActionsDelete = mvc.perform(MockMvcRequestBuilders.delete(uriDelete)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResultForDelete = resultActionsDelete.andReturn();
        int statusForDelete = mvcResultForDelete.getResponse().getStatus();
        assertEquals(200, statusForDelete);
    }
}
