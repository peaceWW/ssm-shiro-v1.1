package com.peace.shiro.bean.trade;

import java.io.Serializable;
import java.util.List;

public class TmTradeInfo implements Serializable {
    private Long id;

    private String tradeNo;

    private Long tradeAmount;

    private Long tradeBatchId;

    private List<TmTradeBatchInfo> tmTradeBatchInfoList;

    private static final long serialVersionUID = 1L;


    public TmTradeInfo(){}
    public TmTradeInfo(Long tradeAmount,String tradeNo){
        this.tradeAmount = tradeAmount;
        this.tradeNo = tradeNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Long getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Long tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Long getTradeBatchId() {
        return tradeBatchId;
    }

    public void setTradeBatchId(Long tradeBatchId) {
        this.tradeBatchId = tradeBatchId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TmTradeInfo other = (TmTradeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getTradeAmount() == null ? other.getTradeAmount() == null : this.getTradeAmount().equals(other.getTradeAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getTradeAmount() == null) ? 0 : getTradeAmount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", tradeAmount=").append(tradeAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public List<TmTradeBatchInfo> getTmTradeBatchInfoList() {
        return tmTradeBatchInfoList;
    }

    public void setTmTradeBatchInfoList(List<TmTradeBatchInfo> tmTradeBatchInfoList) {
        this.tmTradeBatchInfoList = tmTradeBatchInfoList;
    }

    public TmTradeBatchInfo createBatch(){
        TmTradeBatchInfo tradeBatchInfo = new TmTradeBatchInfo();
        tradeBatchInfo.setLot(this.tradeAmount);
        tradeBatchInfo.setTradeNo(this.tradeNo);

        return tradeBatchInfo;
    }
}