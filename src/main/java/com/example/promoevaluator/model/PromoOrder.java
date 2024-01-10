package com.example.promoevaluator.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class PromoOrder {
    // Let's say you have below Json structure and composite ID (promoId and customerId).
    // You may use @Id and @jsonUnwrapped annotations together to make it work for Spring Data MongoDb
    // and align with the Json structure.  
    // {
    //     "promoId": "20240105142141577",
    //     "customerId": "customer000001",
    //     "orders": [{
    //         "DATAFLAG": "1",
    //         "CUST_NO": "201902501293",
    //         "ECM_PROMO_NO": "20240105142141577",
    //         "START_DATE": "2024/01/05 15:00:00",
    //         "END_DATE": "2024/01/30 15:01:00",
    //         "M_PROMO_NO": "M24010500124",
    //         "REMOVE_FLAG": "1",
    //         "ORDER_NO": "24010561108493",
    //         "FULL_ORDERNO": "24010561108493-001-001-001",
    //         "ORDER_DATE": "2024/01/05 15:32:02",
    //         "SET_YN": "0",
    //         "GOODS_CODE": "5770284",
    //         "GOODSDT_CODE": "001",
    //         "GOODS_NAME": "【ZOJIRUSHI 象印】SLiT不鏽鋼真空保溫瓶保溫杯820ml(SJ-JS08)",
    //         "GOODSDT_INFO": "紅色(RA) 820cc",
    //         "RSALE_AMT": 1290,
    //         "QTY": 1
    //     }]
    // }

    @Id
    @JsonUnwrapped
    private PromoOrderId id; 
    private List<Order> orders;


    

}
