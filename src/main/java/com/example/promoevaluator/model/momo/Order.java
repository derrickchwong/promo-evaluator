package com.example.promoevaluator.model.momo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
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
    public String dataFlag;
    public String custNo;
    public String ecmPromoNo;
    public Date startDate;
    public Date endDate;
    public String mPromoNo;
    public String removeFlag;
    public String orderNo;
    public String fullOrderno;
    public Date orderDate;
    public String setYn;
    public String goodsCode;
    public String goodsdtCode;
    public String goodsName;
    public String goodsdtInfo;
    public Integer rsaleAmt;
    public Integer qty;
}
