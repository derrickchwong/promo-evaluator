package com.example.promoevaluator.model.momo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromoDtInfo {
    //    {
    //         "M_PROMO_NO": "A23081800003",
    //         "DT_PROMO_NO": "D23081800001",
    //         "DT_PROMO_NAME": "門檻：1000元",
    //         "ACT_TYPE": "14",
    //         "START_DATE": "2023/08/18 14:00:00",
    //         "END_DATE": "2023/08/18 17:08:32",
    //         "WEEK": "0",
    //         "CNTLIMIT_YN": "1",
    //         "CNTLIMIT_INTERVAL": "3",
    //         "CNTLIMIT": "1",
    //         "NUMLIMIT_YN": "0",
    //         "NUMLIMIT_INTERVAL": "0",
    //         "NUMLIMIT": "0",
    //         "AWARDLIMIT_YN": null,
    //         "AWARDLIMIT": null,
    //         "EXCLUSIVELY": "0",
    //         "USER_AGENT": "0",
    //         "ORD_YN": "1",
    //         "ORD_INTERVAL": "3",
    //         "ORD_CNT_TYPE": "1",
    //         "ORD_NUM": "1",
    //         "ORD_AMT": "1000",
    //         "NEW_CUST": "0",
    //         "FIRST_BUY": "0",
    //         "POINT_YN": "0",
    //         "POINT": "0",
    //         "PAY_METH": "00",
    //         "CARD_FILE_NO": null,
    //         "INSERT_ID": "07213",
    //         "INSERT_DATE": "2023/08/18 13:56:54",
    //         "MODIFY_ID": "07213",
    //         "MODIFY_DATE": "2023/08/18 17:08:32",
    //         "CART_TYPE": "00",
    //         "REG_STIME": "00:00:00",
    //         "REG_ETIME": "23:59:59",
    //         "ORDER_TYPE": "10",
    //         "LOTTERY_QUOTA": "1",
    //         "REG_SDATE": null,
    //         "REG_EDATE": null,
    //         "GIFT_VAL": "0",
    //         "REPEAT_YN": "0",
    //         "SOURCE_ID": "MIDDLE"
    //     }

    @JsonProperty("M_PROMO_NO")
    public String mPromoNo;
    @JsonProperty("DT_PROMO_NO")
    public String dtPromoNo;
    @JsonProperty("DT_PROMO_NAME")
    public String dtPromoName;
    @JsonProperty("ACT_TYPE")
    public String actType;
    @JsonProperty("START_DATE")
    public Date startDate;
    @JsonProperty("END_DATE")
    public Date endDate;
    @JsonProperty("WEEK")
    public String week;
    @JsonProperty("CNTLIMIT_YN")
    public String cntlimitYn;
    @JsonProperty("CNTLIMIT_INTERVAL")
    public String cntlimitInterval;
    @JsonProperty("CNTLIMIT")
    public String cntlimit;
    @JsonProperty("NUMLIMIT_YN")
    public String numlimitYn;
    @JsonProperty("NUMLIMIT_INTERVAL")
    public String numlimitInterval;
    @JsonProperty("NUMLIMIT")
    public String numlimit;
    @JsonProperty("AWARDLIMIT_YN")
    public String awardlimitYn;
    @JsonProperty("AWARDLIMIT")
    public String awardlimit;
    @JsonProperty("EXCLUSIVELY")
    public String exclusively;
    @JsonProperty("USER_AGENT")
    public String userAgent;
    @JsonProperty("ORD_YN")
    public String ordYn;
    @JsonProperty("ORD_INTERVAL")
    public String ordInterval;
    @JsonProperty("ORD_CNT_TYPE")
    public String ordCntType;
    @JsonProperty("ORD_NUM")
    public String ordNum;
    @JsonProperty("ORD_AMT")
    public String ordAmt;
    @JsonProperty("NEW_CUST")
    public String newCust;
    @JsonProperty("FIRST_BUY")
    public String firstBuy;
    @JsonProperty("POINT_YN")
    public String pointYn;
    @JsonProperty("POINT")
    public String point;
    @JsonProperty("PAY_METH")
    public String payMeth;
    @JsonProperty("CARD_FILE_NO")
    public String cardFileNo;
    @JsonProperty("INSERT_ID")
    public String insertId;
    @JsonProperty("INSERT_DATE")
    public String insertDate;
    @JsonProperty("MODIFY_ID")
    public String modifyId;
    @JsonProperty("MODIFY_DATE")
    public String modifyDate;
    @JsonProperty("CART_TYPE")
    public String cartType;
    @JsonProperty("REG_STIME")
    public String regStime;
    @JsonProperty("REG_ETIME")
    public String regEtime;
    @JsonProperty("ORDER_TYPE")
    public String orderType;
    @JsonProperty("LOTTERY_QUOTA")
    public String lotteryQuota;
    @JsonProperty("REG_SDATE")
    public String regSdate;
    @JsonProperty("REG_EDATE")
    public String regEdate;
    @JsonProperty("GIFT_VAL")
    public String giftVal;
    @JsonProperty("REPEAT_YN")
    public String repeatYn;
    @JsonProperty("SOURCE_ID")
    public String sourceId;
}
