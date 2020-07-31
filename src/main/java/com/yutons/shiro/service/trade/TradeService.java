package com.yutons.shiro.service.trade;

import com.yutons.shiro.bean.trade.TmTradeBatchInfo;
import com.yutons.shiro.bean.trade.TmTradeInfo;
import com.yutons.shiro.bean.trade.TradeBatchPage;
import com.yutons.shiro.bean.trade.TradePage;
import com.yutons.shiro.dao.trade.TmTradeInfoExample;

import java.util.List;

public interface TradeService {


    public TradePage queryProductList(TmTradeInfoExample tmTradeInfoExample) ;

    public TradeBatchPage queryBatchList(TmTradeInfo tmTradeInfo) ;

    public TmTradeInfo findTradeById(Long id);

    public List<TmTradeInfo> findByExample();

    public int add(TmTradeInfo tmTradeInfo)throws Exception;

    public int update(TmTradeInfo tmTradeInfo)throws Exception;

    public int deleteById(Long tmTradeInfoId)throws Exception;

    public int splitTrade(TmTradeInfo tmTradeInfo) throws Exception;

    public int mergeTrade(List<TmTradeBatchInfo> tmTradeBatchInfos) throws Exception;


}
