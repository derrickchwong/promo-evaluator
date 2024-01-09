package com.example.promoevaluator.model.momo;

import java.util.Date;

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
    private String mPromoNo;
    private String dtPromoNo;
    private String giftCode;
    private String giftName;
    private String giftType;
    private String giftVal;
    private String lotteryProc;
    private String gift;
    private int giftNum;
    private Date idxDate;
    private String awardDate;
    private Date lotteryDate;

}
