package com.example.promoevaluator.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromoMechGiftInfo {
    // {
        //             "M_PROMO_NO": "A23081800003",
        //             "DT_PROMO_NO": "D23081800001",
        //             "GIFT_CODE": "Gift001",
        //             "GIFT_NAME": "獎品001",
        //             "GIFT_TYPE": "13",
        //             "GIFT_VAL": "0",
        //             "LOTTERY_PROC": "00",
        //             "GIFT": "register",
        //             "GIFT_NUM": 0,
        //             "IDX_DATE": "2023/08/18",
        //             "AWARD_DATE": null,
        //             "LOTTERY_DATE": "2023/09/02"
        //         }
        @JsonProperty("M_PROMO_NO")
        public String mPromoNo;
        @JsonProperty("DT_PROMO_NO")
        public String dtPromoNo;
        @JsonProperty("GIFT_CODE")
        public String giftCode;
        @JsonProperty("GIFT_NAME")
        public String giftName;
        @JsonProperty("GIFT_TYPE")
        public String giftType;
        @JsonProperty("GIFT_VAL")
        public String giftVal;
        @JsonProperty("LOTTERY_PROC")
        public String lotteryProc;
        @JsonProperty("GIFT")
        public String gift;
        @JsonProperty("GIFT_NUM")
        public int giftNum;
        @JsonProperty("IDX_DATE")
        public Date idxDate;
        @JsonProperty("AWARD_DATE")
        public String awardDate;
        @JsonProperty("LOTTERY_DATE")
        public Date lotteryDate;

}
