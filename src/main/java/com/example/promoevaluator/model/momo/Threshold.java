package com.example.promoevaluator.model.momo;

public class Threshold {
    // {
    //     "giftGood": "7470656",
    //     "giftName": "momo幣11元",
    //     "giftImgPath": "https://i1.momoshop.com.tw/20240109/goodsimg/0007/470/656/7470656_L_m.webp",
    //     "orderType": "10",
    //     "regThreshold": "001",
    //     "thresholdTag": "",
    //     "thresholdRule": "滿額1,000元",
    //     "leftCnt": "0名",
    //     "giftCnt": "1名",
    //     "regCnt": "",
    //     "winningTime": "預計09/02",
    //     "sendTime": "預計09/03",
    //     "ord_amt": "1000",
    //     "regStatus": "2",
    //     "regStatusTitle": "過期",
    //     "custShortOrdAmt": "1000",
    //     "m_promo_no": "A23081800003",
    //     "dt_promo_no": "D23081800001",
    //     "exclusively": "0",
    //     "moYn": "1",
    //     "giftTime": "預計09/03",
    //     "giftType": "1",
    //     "isRealityGiftGoods": false,
    //     "hasRegTime": "0",
    //     "promoMechGiftInfo": [
    //         {
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
    //     ],
    //     "promoDtInfo": {
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
    // }
    private String giftGood;
    private String giftName;
    private String giftImgPath;
    private String orderType;
    private String regThreshold;
    private String thresholdTag;
    private String thresholdRule;
    private String leftCnt;
    private String giftCnt;
    private String regCnt;
    private String winningTime;
    private String sendTime;
    private String ordAmt;
    private String regStatus;
    private String regStatusTitle;
    private String custShortOrdAmt;
    private String mPromoNo;
    private String dtPromoNo;
    private String exclusively;
    private String moYn;
    private String giftTime;
    private String giftType;
    private boolean isRealityGiftGoods;
    private String hasRegTime;
    private PromoMechGiftInfo[] promoMechGiftInfo;
    private PromoDtInfo promoDtInfo;
    private String regSdate;
    private String regEdate;
    private String regStime;
    private String regEtime;
}
