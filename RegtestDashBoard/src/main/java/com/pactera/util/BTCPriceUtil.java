package com.pactera.util;

import com.alibaba.fastjson.JSONObject;
import com.pactera.common.HttpUtil;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/1/25 - 17:23
 */
@Component
public class BTCPriceUtil {
    /**
     * Call the url interface to query the last of bitcoin price in real time
     *
     * @return BigDecimal $dollar
     */
    //这边注入失败的问题解决
//    @Value("${custom.HttpUtilUrl.url_1}")
    private String url_1 = "https://www.okcoin.com";
//    @Value("${custom.HttpUtilUrl.url_2}")
    private String url_2 = "/api/v1/ticker.do?";
//    @Value("${custom.HttpUtilUrl.url_3}")
    private String url_3 = "symbol=btc_usd";

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private String bitcoinLastValue;

    @Transactional
    public String getSearchCurrentPrice() {
        System.out.println(url_1 + " " + url_2 + " " + url_3);
        HttpUtil httpUtil = HttpUtil.getInstance();
        String result = null;
        try {
            result = httpUtil.requestHttpGet(url_1, url_2, url_3);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        bitcoinLastValue = jsonObject.getJSONObject("ticker").getString("last");
//        System.out.println(bitcoinLastValue);

        return bitcoinLastValue;
    }
}
