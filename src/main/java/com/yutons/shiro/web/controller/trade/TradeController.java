package com.yutons.shiro.web.controller.trade;

import com.yutons.shiro.bean.trade.TmTradeBatchInfo;
import com.yutons.shiro.bean.trade.TmTradeInfo;
import com.yutons.shiro.bean.trade.TradeBatchPage;
import com.yutons.shiro.bean.trade.TradePage;
import com.yutons.shiro.common.AjaxObject;
import com.yutons.shiro.common.ResultCode;
import com.yutons.shiro.dao.trade.TmTradeInfoExample;
import com.yutons.shiro.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : TradeController  //类名
 * @Description : 贸易控制器  //描述
 * @Author : peaceWW  //作者
 * @Date: 2020-07-21 16:30  //时间
 */
@Controller
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;




    /*
     * 功能描述 初始列表页面
     * @author peaceWW
     * @date 2020/7/21
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list() {
        return "trade/index";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TradePage list(TmTradeInfoExample tmTradeInfoExample) {

        return tradeService.queryProductList(tmTradeInfoExample);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addIndex() {
        return "trade/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addIndex(TmTradeInfo tmTradeInfo) {
        try {
            int i = tradeService.add(tmTradeInfo);
            if(i>0){
                return  "redirect:/trade/index?msg=success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "trade/add";
    }


    @RequestMapping(value = "/delete/{tradeId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject deleteIndex(@PathVariable("tradeId") Long tradeId) {

        AjaxObject result = new AjaxObject(ResultCode.SUCCESS.toString());
        try {
            int i = tradeService.deleteById(tradeId);
        } catch (Exception e) {
            result.setCode(ResultCode.ERROR.toString());
            result.setMessage(e.getMessage());
        }
        return result;

    }

    /**
     * 功能描述 订单详情
     * @author peaceWW
     * @date 2020/7/21
     * @param tradeId
     * @param model
     * @return java.lang.String
     */
    @RequestMapping(value = "/update/{tradeId}", method = RequestMethod.GET)
    public String updateInex(@PathVariable("tradeId") Long tradeId, Model model) {
        TmTradeInfo tmTradeInfo = null;
        try {
            tmTradeInfo = tradeService.findTradeById(tradeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("tmTradeInfo", tmTradeInfo);
        return "trade/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateInex(TmTradeInfo tmTradeInfo, Model model) {
        int i = 0;
        try {
            i = tradeService.update(tmTradeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i>0){
            return  "redirect:/trade/index?msg=success";
        }
        return "trade/update";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Model model,TmTradeInfo TmTradeInfo) {
        model.addAttribute("tradeInfo", TmTradeInfo);
        return "trade/tradeBatch/index";
    }

    @ResponseBody
    @RequestMapping(value = "/batch/list", method = RequestMethod.GET)
    public TradeBatchPage batchlist(TmTradeInfo TmTradeInfo) {
        return tradeService.queryBatchList(TmTradeInfo);
    }


    /**
     * 功能描述 拆分订单
     * @author peaceWW
     * @date 2020/7/21
     * @return java.lang.String
     */
    @RequestMapping(value = "/splitTrade", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject splitTrade(@RequestBody TmTradeInfo TmTradeInfo) {
        AjaxObject result = new AjaxObject(ResultCode.SUCCESS.toString());
        try {
            tradeService.splitTrade(TmTradeInfo);
        } catch (Exception e) {
            result.setCode(ResultCode.ERROR.toString());
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 功能描述 合并订单
     * @author peaceWW
     * @date 2020/7/21
     * @param tmTradeBatchInfoList
     * @return java.lang.String
     */
    @RequestMapping(value = "/mergeTrade", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject mergeTrade(@RequestBody List<TmTradeBatchInfo> tmTradeBatchInfoList) {
        AjaxObject result = new AjaxObject(ResultCode.SUCCESS.toString());
        try {
            tradeService.mergeTrade(tmTradeBatchInfoList);
        } catch (Exception e) {
            result.setCode(ResultCode.ERROR.toString());
            result.setMessage(e.getMessage());
        }

        return result;
    }


}
