package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 车辆信息Entity
 */
@Table(name = "car_info")
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	
	/**
	 * 车辆类型
	 */
	@NotNull(message = "车辆类型不能为空")
	private String carType;
	
	/**
	 * 品牌车型
	 */
	@NotNull(message = "品牌车型不能为空")
	private String brandSeries;
	
	/***
	 * 排放标准
	 */
	private String carDischarge;
	
	/***
	 * 变速箱
	 */
	private String carGearbox;
	
	
	/**
	 * 车辆颜色
	 */
	//@NotNull(message = "车身颜色不能为空")
	private String carColor;
	
	/**
	 * 车辆里程
	 */
	@NotNull(message = "里程不能为空")
	private BigDecimal carMileage;
	
	/**
	 * 初次上牌时间
	 */
	@NotNull(message = "初次上牌时间不能为空")
	//@DateTimeFormat(pattern = "yyyy-MM")
	@JSONField(format = "yyyy-MM")
	private Date carOldBoadTime;
	
	/**
	 * 车牌号
	 */
	@Length(max = 10,message = "车牌号10位以内")
	private String carLicense;
	
	/**
	 * 使用性质
	 */
	@NotNull(message = "使用性质不能为空")
	private String carNature;
	
	/**
	 * 出厂日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date carFactoryTime;
	
	/**
	 * 车检到期日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date carMaturityTime;
	
	/**
	 * 车钥匙数量
	 */
	private Integer keySum;
	
	/**
	 * 车辆所在省
	 */
	private String provinceLocation;
	
	/**
	 * 车辆归属市
	 */
	//@NotNull(message = "车辆归属市不能为空")
	private String cityLocation;
	
	/**
	 * 车辆归属省
	 */
	private String provinceAttribution;
	
	/**
	 * 车辆归属市
	 */
	//@NotNull(message = "车辆所在市不能为空")
	private String cityAttribution;
	
	/**
	 * 车辆成本价
	 */
	@NotNull(message = "成本价不能为空")
	private BigDecimal costPrice;
	
	/**
	 * 批发价格
	 */
	@NotNull(message = "批发价格不能为空")
	private BigDecimal wholesalePrice;
	
	/**
	 * 网络标价
	 */
	private BigDecimal interPrice;
	
	
	/**
	 * 微店:1 发布 2 不发布
	 */
	@NotNull(message = "微店发布状态不能为空")
	private Integer microShop;
	
	/***
	 * 平台：1 发布 2 不发布
	 */
	@NotNull(message = "平台状态不能为空")
	private Integer platform;
	
	/**
	 * 车辆发布状态:0 未上架  1在售 2已售 3 下架 系统维护
	 */
	private Integer carStatus;
	
	/***
	 * 燃油类型
	 */
	@NotNull(message = "燃油类型不能为空")
	private String fuelType;
	
	/***
	 * 车辆描述
	 */
	private String carDescribe;
	
	/***
	 * 图片url地址
	 */
	private List<String> carImgs;
	/**
	 * 车辆照片
	 */
	//@NotNull(message = "车辆照片不能为空")
	private String carImg1;
	
	/**
	 * 证件照片
	 */
	//@NotNull(message = "证件照片不能为空")
	private String carImg2;
	
	/**
	 * 车辆照片
	 */
	//@NotNull(message = "其它照片不能为空")
	private String carImg3;
	
	/**
	 * 购置税:1表示有2表示无
	 */
	private Integer purchaseTax;
	
	/**
	 * 行驶证:1表示有2表示无
	 */
	private Integer carDrlicense;
	
	/**
	 * 登记证:1表示有2表示无
	 */
	private Integer regCertificate;
	
	/**
	 * 原车主身份证:1表示有2表示无
	 */
	private Integer oldIdcard;
	
	/**
	 * 原车牌照:1表示有2表示无
	 */
	private Integer oldCarLicense;
	
	/**
	 * 新车发票:1表示有2表示无
	 */
	private Integer ncInvoice;
	
	/**
	 * 新车质保:1表示有2表示无
	 */
	private Integer ncWarranty;
	
	/**
	 * 新车保养手册:1表示有2表示无
	 */
	private Integer ncmManual;
	
	/**
	 * 车辆说明书:1表示有2表示无
	 */
	private Integer carManual;
	
	/**
	 * 交强险:1表示有2表示无
	 */
	private Integer nciManual;
	
	/**
	 * 商业险:1表示有2表示无
	 */
	private Integer calInsurance;
	
	/**
	 * 车船税证:1表示有2表示无
	 */
	private Integer cbTax;
	
	/**
	 * 是否推广1是2否
	 */
	private Integer isPromotion;
	
	/**
	 * 下架价格
	 */
	private BigDecimal lowerPrice;
	
	/***
	 * 车龄
	 */
	private Integer carAge;
	
	/**
	 * 刷新时间
	 */
	private Date updateTime;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/***
	 * 是否删除
	 */
	private Integer delFlag;
	
	/***
	 * 处理人
	 */
	private Integer handler;
	
	/**
	 * 是否发布 保存还是发布
	 */
	@NotNull(message = "是否发布 不能为空")
	private Integer operation;
	
	/**
	 * 车架号
	 */
	private String carVin;
	/**
	 * 引擎号
	 */
	private String engineNumber;
	
	/***
	 * 昵称
	 */
	private String cusNick;
	
	/**
	 * 是否已交定金
	 */
	private boolean isOrder;
	
	public Car(){
		super();
	}

	/**
	 * 车辆轮播图列表
	 * @return
	 */
	public List<String> getCarImgList(){
		List<String> list = new ArrayList<String>();
		list.add(carImg1);
		list.add(carImg2);
		list.add(carImg3);
		return list;
	}
	
	public String getCarOldBoadTimeFmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if(getCarOldBoadTime() == null) {
			return null;
		}
		return sdf.format(getCarOldBoadTime());
	}
	
	public String getCarVin() {
		return carVin;
	}

	public boolean getIsOrder() {
		return isOrder;
	}


	public void setIsOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}


	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}




	public String getEngineNumber() {
		return engineNumber;
	}




	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}




	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getCarId() {
		return carId;
	}
	
	/**
	 * 设置
	 * 
	 * @param carId 
	 */
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	/**
	 * 获取车辆类型
	 * 
	 * @return String 车辆类型
	 */
	public String getCarType() {
		return carType;
	}
	
	/**
	 * 设置车辆类型
	 * 
	 * @param carType 车辆类型
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	/**
	 * 获取车辆颜色
	 * 
	 * @return String 车辆颜色
	 */
	public String getCarColor() {
		return carColor;
	}
	
	/**
	 * 设置车辆颜色
	 * 
	 * @param carColor 车辆颜色
	 */
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	
	/**
	 * 获取车辆里程
	 * 
	 * @return String 车辆里程
	 */
	public BigDecimal getCarMileage() {
		return carMileage;
	}
	
	/**
	 * 设置车辆里程
	 * 
	 * @param carMileage 车辆里程
	 */
	public void setCarMileage(BigDecimal carMileage) {
		this.carMileage = carMileage;
	}
	
	/**
	 * 获取初次上牌时间
	 * 
	 * @return Date 初次上牌时间
	 */
	public Date getCarOldBoadTime() {
		return carOldBoadTime;
	}
	
	/**
	 * 设置初次上牌时间
	 * 
	 * @param carOldBoadTime 初次上牌时间
	 */
	public void setCarOldBoadTime(Date carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}
	
	/**
	 * 获取车牌号
	 * 
	 * @return String 车牌号
	 */
	public String getCarLicense() {
		return carLicense;
	}
	
	/**
	 * 设置车牌号
	 * 
	 * @param carLicense 车牌号
	 */
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	
	/**
	 * 获取使用性质
	 * 
	 * @return String 使用性质
	 */
	public String getCarNature() {
		return carNature;
	}
	
	/**
	 * 设置使用性质
	 * 
	 * @param carNature 使用性质
	 */
	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}
	
	/**
	 * 获取出厂日期
	 * 
	 * @return Date 出厂日期
	 */
	public Date getCarFactoryTime() {
		return carFactoryTime;
	}
	
	/**
	 * 设置出厂日期
	 * 
	 * @param carFactoryTime 出厂日期
	 */
	public void setCarFactoryTime(Date carFactoryTime) {
		this.carFactoryTime = carFactoryTime;
	}
	
	/**
	 * 获取车检到期日期
	 * 
	 * @return Date 车检到期日期
	 */
	public Date getCarMaturityTime() {
		return carMaturityTime;
	}
	
	/**
	 * 设置车检到期日期
	 * 
	 * @param carMaturityTime 车检到期日期
	 */
	public void setCarMaturityTime(Date carMaturityTime) {
		this.carMaturityTime = carMaturityTime;
	}
	
	/**
	 * 获取车钥匙数量
	 * 
	 * @return Integer 车钥匙数量
	 */
	public Integer getKeySum() {
		return keySum;
	}
	
	/**
	 * 设置车钥匙数量
	 * 
	 * @param keySum 车钥匙数量
	 */
	public void setKeySum(Integer keySum) {
		this.keySum = keySum;
	}
	
	/**
	 * 获取车辆所在地
	 * 
	 * @return String 车辆所在地
	 */
	public String getProvinceLocation() {
		return provinceLocation;
	}
	
	/**
	 * 设置车辆所在地
	 * 
	 * @param provinceLocation 车辆所在地
	 */
	public void setProvinceLocation(String provinceLocation) {
		this.provinceLocation = provinceLocation;
	}
	
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getCityLocation() {
		return cityLocation;
	}
	
	/**
	 * 设置
	 * 
	 * @param cityLocation 
	 */
	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}
	
	/**
	 * 获取车辆归属地
	 * 
	 * @return String 车辆归属地
	 */
	public String getProvinceAttribution() {
		return provinceAttribution;
	}
	
	/**
	 * 设置车辆归属地
	 * 
	 * @param provinceAttribution 车辆归属地
	 */
	public void setProvinceAttribution(String provinceAttribution) {
		this.provinceAttribution = provinceAttribution;
	}
	
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getCityAttribution() {
		return cityAttribution;
	}
	
	/**
	 * 设置
	 * 
	 * @param cityAttribution 
	 */
	public void setCityAttribution(String cityAttribution) {
		this.cityAttribution = cityAttribution;
	}
	
	/**
	 * 获取车辆成本价
	 * 
	 * @return BigDecimal 车辆成本价
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	
	/**
	 * 设置车辆成本价
	 * 
	 * @param costPrice 车辆成本价
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	
	/**
	 * 获取批发价格
	 * 
	 * @return BigDecimal 批发价格
	 */
	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}
	
	/**
	 * 设置批发价格
	 * 
	 * @param wholesalePrice 批发价格
	 */
	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	
	/**
	 * 获取网络标价
	 * 
	 * @return BigDecimal 网络标价
	 */
	public BigDecimal getInterPrice() {
		return interPrice;
	}
	
	/**
	 * 设置网络标价
	 * 
	 * @param interPrice 网络标价
	 */
	public void setInterPrice(BigDecimal interPrice) {
		this.interPrice = interPrice;
	}
	/**
	 * 获取车辆发布状态:1在售2已售3其他
	 * 
	 * @return Integer 车辆发布状态:1在售2已售3其他
	 */
	public Integer getCarStatus() {
		return carStatus;
	}
	
	/**
	 * 设置车辆发布状态:1在售2已售3其他
	 * 
	 * @param carStatus 车辆发布状态:1在售2已售3其他
	 */
	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg1() {
		return carImg1;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg1 车辆照片
	 */
	public void setCarImg1(String carImg1) {
		this.carImg1 = carImg1;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg2() {
		return carImg2;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg2 车辆照片
	 */
	public void setCarImg2(String carImg2) {
		this.carImg2 = carImg2;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg3() {
		return carImg3;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg3 车辆照片
	 */
	public void setCarImg3(String carImg3) {
		this.carImg3 = carImg3;
	}
	
	/**
	 * 获取购置税:1表示有2表示无
	 * 
	 * @return Integer 购置税:1表示有2表示无
	 */
	public Integer getPurchaseTax() {
		return purchaseTax;
	}
	
	/**
	 * 设置购置税:1表示有2表示无
	 * 
	 * @param purchaseTax 购置税:1表示有2表示无
	 */
	public void setPurchaseTax(Integer purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	
	/**
	 * 获取行驶证:1表示有2表示无
	 * 
	 * @return Integer 行驶证:1表示有2表示无
	 */
	public Integer getCarDrlicense() {
		return carDrlicense;
	}
	
	/**
	 * 设置行驶证:1表示有2表示无
	 * 
	 * @param carDrlicense 行驶证:1表示有2表示无
	 */
	public void setCarDrlicense(Integer carDrlicense) {
		this.carDrlicense = carDrlicense;
	}
	
	/**
	 * 获取登记证:1表示有2表示无
	 * 
	 * @return Integer 登记证:1表示有2表示无
	 */
	public Integer getRegCertificate() {
		return regCertificate;
	}
	
	/**
	 * 设置登记证:1表示有2表示无
	 * 
	 * @param regCertificate 登记证:1表示有2表示无
	 */
	public void setRegCertificate(Integer regCertificate) {
		this.regCertificate = regCertificate;
	}
	
	/**
	 * 获取原车主身份证:1表示有2表示无
	 * 
	 * @return Integer 原车主身份证:1表示有2表示无
	 */
	public Integer getOldIdcard() {
		return oldIdcard;
	}
	
	/**
	 * 设置原车主身份证:1表示有2表示无
	 * 
	 * @param oldIdcard 原车主身份证:1表示有2表示无
	 */
	public void setOldIdcard(Integer oldIdcard) {
		this.oldIdcard = oldIdcard;
	}
	
	/**
	 * 获取原车牌照:1表示有2表示无
	 * 
	 * @return Integer 原车牌照:1表示有2表示无
	 */
	public Integer getOldCarLicense() {
		return oldCarLicense;
	}
	
	/**
	 * 设置原车牌照:1表示有2表示无
	 * 
	 * @param oldCarLicense 原车牌照:1表示有2表示无
	 */
	public void setOldCarLicense(Integer oldCarLicense) {
		this.oldCarLicense = oldCarLicense;
	}
	

	
	public Integer getNcInvoice() {
		return ncInvoice;
	}




	public void setNcInvoice(Integer ncInvoice) {
		this.ncInvoice = ncInvoice;
	}




	public Integer getNcWarranty() {
		return ncWarranty;
	}




	public void setNcWarranty(Integer ncWarranty) {
		this.ncWarranty = ncWarranty;
	}




	public Integer getNcmManual() {
		return ncmManual;
	}




	public void setNcmManual(Integer ncmManual) {
		this.ncmManual = ncmManual;
	}




	public Integer getCarManual() {
		return carManual;
	}




	public void setCarManual(Integer carManual) {
		this.carManual = carManual;
	}




	public Integer getNciManual() {
		return nciManual;
	}




	public void setNciManual(Integer nciManual) {
		this.nciManual = nciManual;
	}




	public Integer getCalInsurance() {
		return calInsurance;
	}




	public void setCalInsurance(Integer calInsurance) {
		this.calInsurance = calInsurance;
	}




	public Integer getCbTax() {
		return cbTax;
	}




	public void setCbTax(Integer cbTax) {
		this.cbTax = cbTax;
	}




	public Integer getIsPromotion() {
		return isPromotion;
	}




	public void setIsPromotion(Integer isPromotion) {
		this.isPromotion = isPromotion;
	}




	/**
	 * 获取下架价格
	 * 
	 * @return BigDecimal 下架价格
	 */
	public BigDecimal getLowerPrice() {
		return lowerPrice;
	}
	
	/**
	 * 设置下架价格
	 * 
	 * @param lowerPrice 下架价格
	 */
	public void setLowerPrice(BigDecimal lowerPrice) {
		this.lowerPrice = lowerPrice;
	}
	
	/**
	 * 获取刷新时间
	 * 
	 * @return Date 刷新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置刷新时间
	 * 
	 * @param updateTime 刷新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取创建人
	 * 
	 * @return Integer 创建人
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建人
	 * 
	 * @param creator 创建人
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	/**
	 * 获取创建时间
	 * 
	 * @return Date 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * 
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCarDischarge() {
		return carDischarge;
	}


	public void setCarDischarge(String carDischarge) {
		this.carDischarge = carDischarge;
	}

	public String getCarGearbox() {
		return carGearbox;
	}

	public void setCarGearbox(String carGearbox) {
		this.carGearbox = carGearbox;
	}

	public Integer getMicroShop() {
		return microShop;
	}

	public void setMicroShop(Integer microShop) {
		this.microShop = microShop;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getHandler() {
		return handler;
	}

	public void setHandler(Integer handler) {
		this.handler = handler;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getCarDescribe() {
		return carDescribe;
	}

	public void setCarDescribe(String carDescribe) {
		this.carDescribe = carDescribe;
	}

	public Integer getOperation() {
		return operation;
	}


	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Integer getCarAge() {
		return carAge;
	}
	public void setCarAge(Integer carAge) {
		this.carAge = carAge;
	}
	
	/***
	 * 是否可以操作
	 */
	private Boolean isHandle;

	public Boolean getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}
	
	/***以下是成交价**/
	/**
	 * 车辆成交价
	 */
	private BigDecimal transactionPrice;
	/***
	 * 卖出类型：1 零售 2 批发
	 */
	private Integer sellType;
	
	/**
	 * 用户名称
	 */
	private String name;
	
	/**
	 * 手机号
	 */
	private String phone;

	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public List<String> getCarImgs() {
		return carImgs;
	}

	public void setCarImgs(List<String> carImgs) {
		this.carImgs = carImgs;
	}
	
	
}