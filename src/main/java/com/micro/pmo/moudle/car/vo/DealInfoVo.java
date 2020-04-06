package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 成交价查询出参
 * @author raoBo
 *
 */
public class DealInfoVo extends DealVo{


    /**
     * 车辆成交id
     */
    private Integer dealId;
    
    /**
     * 车辆成交价
     */
    private BigDecimal transactionPrice;

    /**
     * 创建时间：成交时间
     */
    private Date createTime;
    
    /***
     * 使用性质
     */
    private String carNature;
    
    /***
     * 上牌时间
     */
    private Date carBoadTime;
    
    

    public DealInfoVo(){
        super();
    }


    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }


	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}


	public String getCarNature() {
		return carNature;
	}


	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}


	public Date getCarBoadTime() {
		return carBoadTime;
	}


	public void setCarBoadTime(Date carBoadTime) {
		this.carBoadTime = carBoadTime;
	}
    
}
