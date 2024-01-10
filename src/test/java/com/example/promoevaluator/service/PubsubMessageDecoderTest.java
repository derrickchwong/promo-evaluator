package com.example.promoevaluator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.promoevaluator.controller.Body;
import com.example.promoevaluator.controller.Body.Message;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.service.PubsubMessageDecoder.UnableToDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
public class PubsubMessageDecoderTest {


    @Test
    public void testDecode_withValidMessage() throws UnableToDecodeException, ParseException {
        // Create a PubsubMessageDecoder instance.
        PubsubMessageDecoder decoder = new PubsubMessageDecoder(new ObjectMapper()
            .registerModule(new JavaTimeModule())
        );

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

        String json = "{\"DATAFLAG\":\"1\",\"CUST_NO\":\"201902501293\",\"ECM_PROMO_NO\":\"20240105142141577\",\"START_DATE\":\"2024/01/05 15:00:00\",\"END_DATE\":\"2024/01/30 15:01:00\",\"M_PROMO_NO\":\"M24010500124\",\"REMOVE_FLAG\":\"1\",\"ORDER_NO\":\"24010561108493\",\"FULL_ORDERNO\":\"24010561108493-001-001-001\",\"ORDER_DATE\":\"2024/01/05 15:32:02\",\"SET_YN\":\"0\",\"GOODS_CODE\":\"5770284\",\"GOODSDT_CODE\":\"001\",\"GOODS_NAME\":\"【ZOJIRUSHI 象印】SLiT不鏽鋼真空保溫瓶保溫杯820ml(SJ-JS08)\",\"GOODSDT_INFO\":\"紅色(RA) 820cc\",\"RSALE_AMT\":1290,\"QTY\":1}";

        // Create a Pub/Sub message.
        Body body = new Body();
        Message message = body.new Message();
        message.setData(Base64.getEncoder().encodeToString(json.getBytes()));
        // Create a Body object.
        body.setMessage(message);
        
        Order expected = new Order();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("HKT"));
        expected.setDataFlag("1");
        expected.setCustNo("201902501293");
        expected.setEcmPromoNo("20240105142141577");
        expected.setStartDate(sdf.parse("2024/01/05 15:00:00"));
        expected.setEndDate(sdf.parse("2024/01/30 15:01:00"));
        expected.setMPromoNo("M24010500124");
        expected.setRemoveFlag("1");
        expected.setOrderNo("24010561108493");
        expected.setFullOrderno("24010561108493-001-001-001");
        expected.setOrderDate(sdf.parse("2024/01/05 15:32:02"));
        expected.setSetYn("0");
        expected.setGoodsCode("5770284");
        expected.setGoodsdtCode("001");
        expected.setGoodsName("【ZOJIRUSHI 象印】SLiT不鏽鋼真空保溫瓶保溫杯820ml(SJ-JS08)");
        expected.setGoodsdtInfo("紅色(RA) 820cc");
        expected.setRsaleAmt(1290);
        expected.setQty(1);

        assertEquals(expected, decoder.decode(body, Order.class));
    }

    
}
