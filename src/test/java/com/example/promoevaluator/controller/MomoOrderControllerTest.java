package com.example.promoevaluator.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Base64;

import com.example.promoevaluator.controller.Body.Message;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.service.MomoPromoCalculator;
import com.example.promoevaluator.service.PubsubMessageDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MomoOrderController.class)
public class MomoOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MomoPromoCalculator momoPromoCalculator;

    @MockBean
    private PubsubMessageDecoder pubsubMessageDecoder;

    @Test
    public void testReceiveMessage() throws Exception {
        // Create an Order object.
        Order order = new Order();
        
        // {
        //     "DATAFLAG": "1",
        //     "CUST_NO": "201902501293",
        //     "ECM_PROMO_NO": "20240105142141577",
        //     "START_DATE": "2024/01/05 15:00:00",
        //     "END_DATE": "2024/01/30 15:01:00",
        //     "M_PROMO_NO": "M24010500124",
        //     "REMOVE_FLAG": "1",
        //     "ORDER_NO": "24010561108493",
        //     "FULL_ORDERNO": "24010561108493-001-001-001",
        //     "ORDER_DATE": "2024/01/05 15:32:02",
        //     "SET_YN": "0",
        //     "GOODS_CODE": "5770284",
        //     "GOODSDT_CODE": "001",
        //     "GOODS_NAME": "【ZOJIRUSHI 象印】SLiT不鏽鋼真空保溫瓶保溫杯820ml(SJ-JS08)",
        //     "GOODSDT_INFO": "紅色(RA) 820cc",
        //     "RSALE_AMT": 1290,
        //     "QTY": 1
        // }

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss");

        order.setDataFlag("1");
        order.setCustNo("201902501293");
        order.setEcmPromoNo("20240105142141577");
        order.setStartDate(sdf.parse("2024/01/05 15:00:00"));
        order.setEndDate(sdf.parse("2024/01/30 15:01:00"));
        order.setMPromoNo("M24010500124");
        order.setRemoveFlag("1");
        order.setOrderNo("24010561108493");
        order.setFullOrderno("24010561108493-001-001-001");
        order.setOrderDate(sdf.parse("2024/01/05 15:32:02"));
        order.setSetYn("0");
        order.setGoodsCode("5770284");
        order.setGoodsdtCode("001");
        order.setGoodsName("【ZOJIRUSHI 象印】SLiT不鏽鋼真空保溫瓶保溫杯820ml(SJ-JS08)");
        order.setGoodsdtInfo("紅色(RA) 820cc");
        order.setRsaleAmt(1290);
        order.setQty(1);

        // Convert the Order object to JSON.
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);
        String base64 = Base64.getEncoder().encodeToString(json.getBytes());

        // Create a mock request.
        Body body = new Body();
        Message message = body.new Message();
        message.setData(base64);
        body.setMessage(message);
        
        String bodyJson = objectMapper.writeValueAsString(body);

        when(pubsubMessageDecoder.decode(body, Order.class))
            .thenReturn(order);

        // Create a mock request.
        mockMvc.perform(post("/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(bodyJson))
            // Execute the request and verify the response.
            .andExpect(status().isOk());
    }
}

