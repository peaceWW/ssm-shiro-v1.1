package com.peace.shiro.service.trade;

import com.peace.shiro.bean.trade.TmTradeBatchInfo;
import com.peace.shiro.bean.trade.TmTradeInfo;
import com.peace.shiro.bean.trade.TradeBatchPage;
import com.peace.shiro.bean.trade.TradePage;
import com.peace.shiro.dao.trade.TmTradeInfoExample;

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
