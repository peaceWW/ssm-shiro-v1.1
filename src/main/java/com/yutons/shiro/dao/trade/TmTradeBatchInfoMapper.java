package com.yutons.shiro.dao.trade;


import com.yutons.shiro.bean.trade.TmTradeBatchInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmTradeBatchInfoMapper {
    Integer countByExample(TmTradeBatchInfoExample example);

    int deleteByExample(TmTradeBatchInfoExample example);

    int deleteByPrimaryKey(Long id);

    List<TmTradeBatchInfo> selectByTradeNo(String tradeNo);

    int insert(TmTradeBatchInfo record);

    int insertSelective(TmTradeBatchInfo record);

    List<TmTradeBatchInfo> selectByExample(TmTradeBatchInfoExample example);

    TmTradeBatchInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmTradeBatchInfo record, @Param("example") TmTradeBatchInfoExample example);

    int updateByExample(@Param("record") TmTradeBatchInfo record, @Param("example") TmTradeBatchInfoExample example);

    int updateByPrimaryKeySelective(TmTradeBatchInfo record);

    int updateByPrimaryKey(TmTradeBatchInfo record);
}