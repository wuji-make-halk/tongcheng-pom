package com.micro.pmo.moudle.third.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.moudle.third.enu.ThirdTypeEnum;

/**
 * ThirdApiResult 
 * @author wenhaofan 
 * 
 */
public class ThirdApiResult  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*
	*/
	private Integer  resultId;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*车架号
	*/
	private String  carVin;
	/**
	*响应数据
	*/
	private String  result;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	/**
	*0：车300 保险
	1：查博士 4s维修
	*/
	private Integer  resultType;
	/**
	*订单ID
	*/
	private String  orderId;
 
	/**
	*购买订单ID
	*/
	private String  payOrderId;
	/**
	*响应状态码
	*/
	private Integer  resCode;
	/**
	*0 为查询成功，1为查询失败，2为等待响应 ,3为没有数据
	*/
	private Integer  resStatus;
 
	/**
	 * 是否失效
	 */
	private Boolean isExpired;
	
	private Integer maxDay;
 
	/**
	 * 0 为购买 1位使用赠送次数
	 */
	private Integer buyType;
 
	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
	}

	
	
	public ThirdApiResult() {
		super();
	}

	public ThirdApiResult(Integer cusId, String carVin, Integer resultType, Integer maxDay,Integer resStatus) {
		super();
		this.cusId = cusId;
		this.carVin = carVin;
		this.resultType = resultType;
		this.resStatus = resStatus;
	}

 
	public ThirdApiResult( Integer cusId, String carVin, String result, Date gmtCreate,
			ThirdTypeEnum resultType, String orderId, String payOrderId, Integer resCode, Integer resStatus , Integer buyType) {
		super();
		this.cusId = cusId;
		this.carVin = carVin;
		this.result = result;
		this.gmtCreate = gmtCreate;
		this.resultType = resultType.getCode();
		this.orderId = orderId;
		this.payOrderId = payOrderId;
		this.resCode = resCode;
		this.resStatus = resStatus;
		this.buyType = buyType;
	}
	
	public Boolean getIsExpired() {
		
		if(isExpired != null ) return isExpired;
		
		ThirdTypeEnum typeEnum  = ThirdTypeEnum.getByCode(getResultType());
		
		if(typeEnum == null ) {
			return null;
		}
		
		Date endDate = DateUtil.datePlusDays(gmtCreate, typeEnum.getMaxDay());
		
		return endDate.before(new Date());
	}

	public ThirdApiResult setResultId(Integer resultId) {
		this.resultId=resultId;
		return this;
	}

	public Integer getResultId() {
		return this.resultId;
	}

	public ThirdApiResult setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public ThirdApiResult setCarVin(String carVin) {
		this.carVin=carVin;
		return this;
	}

	public String getCarVin() {
		return this.carVin;
	}

	public ThirdApiResult setResult(String result) {
		this.result=result;
		return this;
	}

	public String getResult() {
		return this.result;
	}

	public ThirdApiResult setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public String getGmtCreateFmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		if (getGmtCreate() ==null) {
			return null;
		}
		return sdf.format(getGmtCreate());
	}
	
	public ThirdApiResult setResultType(Integer resultType) {
		this.resultType=resultType;
		return this;
	}

	public Integer getResultType() {
		return this.resultType;
	}

	public ThirdApiResult setOrderId(String orderId) {
		this.orderId=orderId;
		return this;
	}

	public String getOrderId() {
		return this.orderId;
	}
 

	public ThirdApiResult setPayOrderId(String payOrderId) {
		this.payOrderId=payOrderId;
		return this;
	}

	public String getPayOrderId() {
		return this.payOrderId;
	}

	public Integer getResCode() {
		return resCode;
	}

	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}

	public Integer getResStatus() {
		return resStatus;
	}

	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}

	public String getCodeMsg() {
		return ResCodeMsg.getMsg(getResultType(), getResCode());
	}
	 
}

class ResCodeMsg{
	
	private static final Map<Integer,String>[] msgArr = new HashMap[2];
	
	static {
		msgArr[0] = new HashMap() {{
			put(2000, "查询成功"); 
			put(2001,"报告查询中");
			put(2002,"操作成功无数据");					 
			put(4004,"暂不支持这个品牌");
			put(4006,"无效 VIN 码");
		}};
		
		msgArr[1] = new HashMap() {{
			put(1201, "无效 vin 码");
			put(12011, "无效 vin 码");
			put(1100, " 暂不支持这个品牌");
		}};
	}
	
	public static String getMsg(Integer type,Integer code){
		if(type == null || code == null ) {
			return null;
		}
		
		return msgArr[type].get(code);
	}
}