package com.peace.shiro.bean.trade;

import java.io.Serializable;

public class TmTradeBatchInfo implements Serializable {
    private Long id;

    private String tradeNo;

    private Long tradeId;

    private Long lot;

    private static final long serialVersionUID = 1L;

    public TmTradeBatchInfo(){

    }

    public TmTradeBatchInfo(TmTradeInfo tmTradeInfo){
        this.tradeId = tmTradeInfo.getId();
        this.tradeNo = tmTradeInfo.getTradeNo();
    }
    public TmTradeBatchInfo(String tradeNo,Long lot){
        this.tradeNo = tradeNo;
        this.lot= lot;
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

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getLot() {
        return lot;
    }

    public void setLot(Long lot) {
        this.lot = lot;
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
        TmTradeBatchInfo other = (TmTradeBatchInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getTradeId() == null ? other.getTradeId() == null : this.getTradeId().equals(other.getTradeId()))
            && (this.getLot() == null ? other.getLot() == null : this.getLot().equals(other.getLot()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getTradeId() == null) ? 0 : getTradeId().hashCode());
        result = prime * result + ((getLot() == null) ? 0 : getLot().hashCode());
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
        sb.append(", tradeId=").append(tradeId);
        sb.append(", lot=").append(lot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}