package com.yutons.shiro.dao.trade;


import com.yutons.shiro.bean.trade.TmTradeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmTradeInfoMapper {
    Integer countByExample(TmTradeInfoExample example);

    int deleteByExample(TmTradeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmTradeInfo record);

    int insertSelective(TmTradeInfo record);

    List<TmTradeInfo> selectByExample(TmTradeInfoExample example);

    TmTradeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmTradeInfo record, @Param("example") TmTradeInfoExample example);

    int updateByExample(@Param("record") TmTradeInfo record, @Param("example") TmTradeInfoExample example);

    int updateByPrimaryKeySelective(TmTradeInfo record);

    int updateByPrimaryKey(TmTradeInfo record);

}