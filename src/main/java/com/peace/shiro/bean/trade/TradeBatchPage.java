package com.peace.shiro.bean.trade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.List;

/**
 * @author peace
 * @desc shiro权限控制之用户实体类(分页)
 * @date 2017/10/25 16:35
 */
@Data
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class TradeBatchPage {
    protected Integer code = 0;
    protected String msg;
    protected Integer count = 0;
    protected List<TmTradeBatchInfo> data;
}
