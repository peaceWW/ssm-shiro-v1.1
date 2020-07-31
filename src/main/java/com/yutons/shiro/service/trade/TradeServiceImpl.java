package com.yutons.shiro.service.trade;

import com.yutons.shiro.bean.trade.TmTradeBatchInfo;
import com.yutons.shiro.bean.trade.TmTradeInfo;
import com.yutons.shiro.bean.trade.TradeBatchPage;
import com.yutons.shiro.bean.trade.TradePage;
import com.yutons.shiro.dao.trade.TmTradeBatchInfoExample;
import com.yutons.shiro.dao.trade.TmTradeBatchInfoMapper;
import com.yutons.shiro.dao.trade.TmTradeInfoExample;
import com.yutons.shiro.dao.trade.TmTradeInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName : TradeServiceImpl  //类名
 * @Description : 贸易运货实现  //描述
 * @Author : peaceWW  //作者
 * @Date: 2020-07-21 16:41  //时间
 */
@Service
public class TradeServiceImpl implements TradeService{

    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);
    @Resource
    private TmTradeInfoMapper tradeDao;


    @Resource
    private TmTradeBatchInfoMapper tradeBatchDao;

    @Override
    public TradePage queryProductList(TmTradeInfoExample tmTradeInfoExample){
        List<TmTradeInfo> data = tradeDao.selectByExample(tmTradeInfoExample);
        Integer count = tradeDao.countByExample(tmTradeInfoExample);
        TradePage tradePage = new TradePage();
        tradePage.setCount(count);
        tradePage.setData(data);
        return tradePage;
    }

    @Override
    public TmTradeInfo findTradeById(Long id) {
        return tradeDao.selectByPrimaryKey(id);
    }

    @Override
    public List<TmTradeInfo> findByExample() {
        return null;
    }


    @Override
    @Transactional
    public int add(TmTradeInfo tmTradeInfo) {
        UUID tradeNo = UUID.randomUUID();
        tmTradeInfo.setTradeNo(tradeNo.toString());
        TmTradeBatchInfo tradeBatchInfo = tmTradeInfo.createBatch();
        tradeBatchDao.insert(tradeBatchInfo);
        return tradeDao.insert(tmTradeInfo);
    }
    @Override
    @Transactional
    public int update(TmTradeInfo tmTradeInfo) {
        Long tradeAmount = tradeDao.selectByPrimaryKey(tmTradeInfo.getId()).getTradeAmount();
        List<TmTradeBatchInfo> datas = tradeBatchDao.selectByTradeNo(tmTradeInfo.getTradeNo());
        tradeDao.updateByPrimaryKey(tmTradeInfo);
        BigDecimal b1 = new BigDecimal(tradeAmount);


        for(TmTradeBatchInfo data:datas){
            BigDecimal b2 = new BigDecimal(data.getLot());

            MathContext mc = new MathContext(8, RoundingMode.HALF_UP);
            BigDecimal result =  b2.divide(b1, mc);
            data.setLot(result.multiply(new BigDecimal(tmTradeInfo.getTradeAmount())).longValue());
            tradeBatchDao.updateByPrimaryKey(data);
        }
        return 1;
    }

    @Override
    public int deleteById(Long tmTradeInfoId) {
        List<String> tradeNos = new ArrayList<>();
        tradeNos.add(tradeDao.selectByPrimaryKey(tmTradeInfoId).getTradeNo());
        TmTradeBatchInfoExample example = new TmTradeBatchInfoExample();
        TmTradeBatchInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTradeNoIn(tradeNos);
        tradeBatchDao.deleteByExample(example);
        return tradeDao.deleteByPrimaryKey(tmTradeInfoId);
    }


    @Override
    public TradeBatchPage queryBatchList(TmTradeInfo tmTradeInfo){
        List<String> tradeNos = new ArrayList<>();
        tradeNos.add(tmTradeInfo.getTradeNo());
        TmTradeBatchInfoExample example = new TmTradeBatchInfoExample();
        TmTradeBatchInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTradeNoIn(tradeNos);
        TmTradeBatchInfo batch = new TmTradeBatchInfo(tmTradeInfo);
        List<TmTradeBatchInfo> data = tradeBatchDao.selectByExample(example);
        Integer count = tradeBatchDao.countByExample(example);
        TradeBatchPage tradeBatchPage = new TradeBatchPage();
        tradeBatchPage.setCount(count);
        tradeBatchPage.setData(data);
        return tradeBatchPage;
    }


    @Override
    @Transactional
    public int splitTrade(TmTradeInfo tmTradeInfo) throws Exception {
        TmTradeBatchInfo  b = tradeBatchDao.selectByPrimaryKey(tmTradeInfo.getTradeBatchId());


        Long lots=0L;
        Integer i=0;
        for (TmTradeBatchInfo batch:tmTradeInfo.getTmTradeBatchInfoList()) {
            lots+=batch.getLot();
        }
        if(b.getLot().longValue()!=lots.longValue()){
            throw  new Exception("拆分的数量和被拆分对象不相等");
        }
        tradeBatchDao.deleteByPrimaryKey(tmTradeInfo.getTradeBatchId());
        for (TmTradeBatchInfo batch:tmTradeInfo.getTmTradeBatchInfoList()) {
            i+= tradeBatchDao.insert(batch);
        }
        return i;
    }

    @Override
    @Transactional
    public int mergeTrade(List<TmTradeBatchInfo> tmTradeBatchInfos) {
        Long lot = 0l;
        for (TmTradeBatchInfo batch:tmTradeBatchInfos) {
            lot+= batch.getLot();
            tradeBatchDao.deleteByPrimaryKey(batch.getId());
        }
        TmTradeBatchInfo b = new TmTradeBatchInfo();
        b.setLot(lot);
        b.setTradeNo(tmTradeBatchInfos.get(0).getTradeNo());
        tradeBatchDao.insert(b);
        return 0;
    }
}
