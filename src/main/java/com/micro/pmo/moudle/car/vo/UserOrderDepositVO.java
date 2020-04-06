package com.micro.pmo.moudle.car.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.micro.pmo.moudle.car.enu.OrderDepositStatusEnum;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年8月6日
 */
public class UserOrderDepositVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/**
	 * 卖家用户昵称
	 */
	private String cusName;

	/**
	 * 卖车用户头像
	 */
	private String cusHeadImg;

	/**
	 * 车辆id
	 */
	private Integer carId;

	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 车辆照片
	 */
	private String carImg;

	/**
	 * 品牌车型
	 */
	private String brandSeries;

	/**
	 * 出厂时间
	 */
	private Date carFactoryTime;

	/**
	 * 车辆里程
	 */
	private BigDecimal carMileage;

	/**
	 * 车辆所在市
	 */
	private String cityLocation;

	/**
	 * 网络标价
	 */
	private BigDecimal interPrice;
	
	/**
	 * 成本价
	 */
	private BigDecimal costPrice;

	/**
	 * 已支付订金
	 */
	private BigDecimal depositPrice;

	/**
	 * 确认状态 0为正在交易， 1 确认交易 ，2为交易异常 ,3为未支付 ， 4为等待卖家支付，5 交易失败已退款 ，6 交易完成，7
	 * 申请退款成功，8取消交易
	 */
	private Integer dealStatus;

	/**
	 * 0 为买家订金 1为卖家定金
	 */
	private Integer type;

	/**
	 * 买家订单
	 */
	private String buyerOrderId;

	private Date gmtCreate;
	
	/**
	 * 通用订单ID
	 */
	private String commonOrderId;

	/**
	 * 首次上牌时间
	 */
	private Date carOldBoadTime;
	
	/**
	 * 状态字符串
	 */
	private String dealStatusStr;
	
	public Date getCarOldBoadTime() {
		return carOldBoadTime;
	}
	
	

	public BigDecimal getCostPrice() {
		return costPrice;
	}



	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}



	public void setCarOldBoadTime(Date carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}

	public String getCarOldBoadTimeFmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if(getCarOldBoadTime() == null) {
			return null;
		}
		return sdf.format(getCarOldBoadTime());
	}
	
	public String getCommonOrderId() {
		return commonOrderId;
	}

	public void setCommonOrderId(String commonOrderId) {
		this.commonOrderId = commonOrderId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getBuyerOrderId() {
		return buyerOrderId;
	}

	public void setBuyerOrderId(String buyerOrderId) {
		this.buyerOrderId = buyerOrderId;
	}

	public String getDealStatusStr() {
		if(dealStatusStr != null ) {
			return dealStatusStr;
		}
		OrderDepositStatusEnum status = OrderDepositStatusEnum.getByMode(getDealStatus());
		return status == null ? null : status.getRemark();
	}

	public void setDealStatusStr(String dealStatusStr) {
		this.dealStatusStr = dealStatusStr;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusHeadImg() {
		return cusHeadImg;
	}

	public void setCusHeadImg(String cusHeadImg) {
		this.cusHeadImg = cusHeadImg;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCarImg() {
		return carImg;
	}

	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public Date getCarFactoryTime() {
		return carFactoryTime;
	}

	public void setCarFactoryTime(Date carFactoryTime) {
		this.carFactoryTime = carFactoryTime;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

	public BigDecimal getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(BigDecimal carMileage) {
		this.carMileage = carMileage;
	}

	public BigDecimal getInterPrice() {
		return interPrice;
	}

	public void setInterPrice(BigDecimal interPrice) {
		this.interPrice = interPrice;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	/**
	 * 确认状态 0为正在交易， 1 确认交易 ，2为交易异常 ,3为未支付 ， 4为等待卖家支付，5 交易失败已退款 ，6 交易完成，7
	 * 申请退款成功，8取消交易
	 * 
	 * @return
	 */
	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
