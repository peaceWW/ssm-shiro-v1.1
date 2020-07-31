package com.yutons.shiro.service.trade;

import org.springframework.stereotype.Component;

/**
 * @ClassName : TradeProxy  //类名
 * @Description : 贸易的代理类  //描述
 * @Author : peaceWW  //作者
 * @Date: 2020-07-21 16:45  //时间
 */
@Component
public class TradeProxy {

    private TradeService tradeService;

    public TradeProxy (){ }
    public TradeProxy (TradeService tradeService){
        this.tradeService = tradeService;
    }




}
