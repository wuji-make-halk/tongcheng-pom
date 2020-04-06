package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;
import java.util.Date;

public class BidVo {

    /**
     * 买车id
     */
    private Integer bidId;

    /**
     * 车辆id
     */
    private Integer carId;


    /**
     * 车辆类型
     */
    private String carType;

    /***
     * 品牌车型
     */
    private String brandSeries;

    /**
     * 车辆历程
     */
    private String carMileage;

    /**
     * 车辆颜色
     */
    private String carColor;

    /**
     * 出价
     */
    private BigDecimal binPrice;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 初次上牌时间
     */
    private Date carOldBoadTime;


    /**
     * 车辆所在地
     */
    private String provinceLocation;

    /**
     * 车辆所在城市
     */
    private String cityLocation;

    /**
     * 燃油类型
     */
    private String carDischarge;

    /**
     * 车辆图片
     */
    private String carImg1;

    /**
     * 成本价
     */
    private Integer costPrice;
    
    /**
     * 网络标价
     * @return
     */
    private Integer interPrice;

    public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getInterPrice() {
		return interPrice;
	}

	public void setInterPrice(Integer interPrice) {
		this.interPrice = interPrice;
	}

	public String getCarImg1() {
		return carImg1;
	}

	public void setCarImg1(String carImg1) {
		this.carImg1 = carImg1;
	}

	public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getBrandSeries() {
        return brandSeries;
    }

    public void setBrandSeries(String brandSeries) {
        this.brandSeries = brandSeries;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public BigDecimal getBinPrice() {
        return binPrice;
    }

    public void setBinPrice(BigDecimal binPrice) {
        this.binPrice = binPrice;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCarOldBoadTime() {
        return carOldBoadTime;
    }

    public void setCarOldBoadTime(Date carOldBoadTime) {
        this.carOldBoadTime = carOldBoadTime;
    }

    public String getProvinceLocation() {
        return provinceLocation;
    }

    public void setProvinceLocation(String provinceLocation) {
        this.provinceLocation = provinceLocation;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarDischarge() {
        return carDischarge;
    }

    public void setCarDischarge(String carDischarge) {
        this.carDischarge = carDischarge;
    }
}
