package com.example.promoevaluator.model.momo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    @JsonProperty("DATAFLAG")
    public String dataFlag;
    @JsonProperty("CUST_NO")
    public String custNo;
    @JsonProperty("ECM_PROMO_NO")
    public String ecmPromoNo;
    @JsonProperty("START_DATE")
    public Date startDate;
    @JsonProperty("END_DATE")
    public Date endDate;
    @JsonProperty("M_PROMO_NO")
    public String mPromoNo;
    @JsonProperty("REMOVE_FLAG")
    public String removeFlag;
    @JsonProperty("ORDER_NO")
    public String orderNo;
    @JsonProperty("FULL_ORDERNO")
    public String fullOrderno;
    @JsonProperty("ORDER_DATE")
    public Date orderDate;
    @JsonProperty("SET_YN")
    public String setYn;
    @JsonProperty("GOODS_CODE")
    public String goodsCode;
    @JsonProperty("GOODSDT_CODE")
    public String goodsdtCode;
    @JsonProperty("GOODS_NAME")
    public String goodsName;
    @JsonProperty("GOODSDT_INFO")
    public String goodsdtInfo;
    @JsonProperty("RSALE_AMT")
    public Integer rsaleAmt;
    @JsonProperty("QTY")
    public Integer qty;
}
