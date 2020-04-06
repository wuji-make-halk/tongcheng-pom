package com.micro.pmo.moudle.console.admin.vo;

import java.math.BigDecimal;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
public class AdminConsoleVO {
	
	/**
	 * 注册用户总数
	 */
	private Integer cusTotalNum;
	
	/**
	 * 本月注册总数 
	 */
	private Integer cusMonthNum;
	
	/**
	 * 车辆发布总数 平台
	 */
	private Integer carPlatformTotalNum;
	
	/**
	 * 本月车辆发布总数 平台
	 */
	private Integer carPlatformMonthNum;
	
	/**
	 *成交总数
	 */
	private Integer dealTotalNum;
	
	/**
	 * 本月成交总数
	 */
	private Integer dealMonthNum;
	
	/**
	 * 成交总金额
	 */
	private Integer dealTotalMoney;

	/**
	 * 本月成交金额
	 */
	private Integer dealMonthMoney;
	
	/**
	 * vip购买总人数
	 */
	private Integer vipTotalNum = 1;
	/**
	 * vip本月购买次数
	 */
	private Integer vipMonthNum = 1;
	
	/**
	 * 本月定金支付金额
	 */
	private BigDecimal monthDepositMoney;
	/**
	 * 定金支付总金额
	 */
	private BigDecimal totalDepositMoney;
	
	/**
	 * vip购买总金额
	 */
	private BigDecimal totalVipMoney;
	/**
	 * vip 本月购买总金额
	 */
	private BigDecimal monthVipMoney;
	/**
	 *  查成交价购买总金额
	 */
	private BigDecimal totalDealMoney;
	/**
	 * 查成交价本月购买总金额
	 */
	private BigDecimal monthDealMoney;
	/**
	 * 推广购买总金额
	 */
	private BigDecimal totalExtensionMoney;
	/**
	 * 本月推广总金额
	 */
	private BigDecimal monthExtensionMoney;
	
	/**
	 * 刷新购买总金额
	 */
	private BigDecimal totalFlushMoney;
	/**
	 * 本月刷新购买总金额
	 */
	private BigDecimal monthFlushMoney;
	
	/**
	 * 购买预定收车总金额
	 */
	private BigDecimal totalCollectCarMoney;
	/**
	 * 本月购买收车总金额
	 */
	private BigDecimal monthCollectCarMoney;
 
	/**
	 * 本月充值金额
	 */
	private BigDecimal monthTopupMoney;
	
	/**
	 * 充值总金额
	 */
	private BigDecimal totalTopupMoney;
	
	
	
	public BigDecimal getOtherOrderTotalMoney() {
		BigDecimal total = BigDecimal.ZERO;
	 
		total = total.add(getTotalDealMoney());
		
		total = total.add(getTotalExtensionMoney());
		
		total = total.add(getTotalFlushMoney());
		
		total = total.add(getTotalCollectCarMoney());
		
		total = total.add(getTotalTopupMoney());
		
		return total;
	}
	
	public BigDecimal getOtherOrderMonthMoney() {
		BigDecimal total = BigDecimal.ZERO;
		 
		total = total.add(getMonthDealMoney());
		
		total = total.add(getMonthExtensionMoney());
		
		total = total.add(getMonthFlushMoney());
		
		total = total.add(getMonthCollectCarMoney());
		
		total = total.add(getMonthTopupMoney());
		
		return total;
	}
	
	public BigDecimal getMonthTopupMoney() {
		return monthTopupMoney == null ? BigDecimal.ZERO : monthTopupMoney;
	}

	public void setMonthTopupMoney(BigDecimal monthTopupMoney) {
		this.monthTopupMoney = monthTopupMoney;
	}

	public BigDecimal getTotalTopupMoney() {
		return totalTopupMoney == null ? BigDecimal.ZERO : totalTopupMoney;
	}

	public void setTotalTopupMoney(BigDecimal totalTopupMoney) {
		this.totalTopupMoney = totalTopupMoney;
	}

	public BigDecimal getTotalVipMoney() {
		return totalVipMoney;
	}

	public void setTotalVipMoney(BigDecimal totalVipMoney) {
		this.totalVipMoney = totalVipMoney;
	}

	public BigDecimal getMonthVipMoney() {
		return monthVipMoney;
	}

	public void setMonthVipMoney(BigDecimal monthVipMoney) {
		this.monthVipMoney = monthVipMoney;
	}

	public BigDecimal getTotalDealMoney() {
		return totalDealMoney == null ? BigDecimal.ZERO : totalDealMoney;
	}

	public void setTotalDealMoney(BigDecimal totalDealMoney) {
		this.totalDealMoney = totalDealMoney;
	}

	public BigDecimal getMonthDealMoney() {
		return monthDealMoney == null ? BigDecimal.ZERO : monthDealMoney;
	}

	public void setMonthDealMoney(BigDecimal monthDealMoney) {
		this.monthDealMoney = monthDealMoney;
	}

	public BigDecimal getTotalExtensionMoney() {
		return totalExtensionMoney == null ? BigDecimal.ZERO : totalExtensionMoney;
	}

	public void setTotalExtensionMoney(BigDecimal totalExtensionMoney) {
		this.totalExtensionMoney = totalExtensionMoney;
	}

	public BigDecimal getMonthExtensionMoney() {
		return monthExtensionMoney == null ? BigDecimal.ZERO : monthExtensionMoney;
	}

	public void setMonthExtensionMoney(BigDecimal monthExtensionMoney) {
		this.monthExtensionMoney = monthExtensionMoney;
	}

	public BigDecimal getTotalFlushMoney() {
		return totalFlushMoney == null ? BigDecimal.ZERO : totalFlushMoney;
	}

	public void setTotalFlushMoney(BigDecimal totalFlushMoney) {
		this.totalFlushMoney = totalFlushMoney;
	}

	public BigDecimal getMonthFlushMoney() {
		return monthFlushMoney == null ? BigDecimal.ZERO : monthFlushMoney;
	}

	public void setMonthFlushMoney(BigDecimal monthFlushMoney) {
		this.monthFlushMoney = monthFlushMoney;
	}

	public BigDecimal getTotalCollectCarMoney() {
		return totalCollectCarMoney == null ? BigDecimal.ZERO : totalCollectCarMoney;
	}

	public void setTotalCollectCarMoney(BigDecimal totalCollectCarMoney) {
		this.totalCollectCarMoney = totalCollectCarMoney;
	}

	public BigDecimal getMonthCollectCarMoney() {
		return monthCollectCarMoney == null ? BigDecimal.ZERO : monthCollectCarMoney;
	}

	public void setMonthCollectCarMoney(BigDecimal monthCollectCarMoney) {
		this.monthCollectCarMoney = monthCollectCarMoney;
	}

	public BigDecimal getMonthDepositMoney() {
		return monthDepositMoney;
	}

	public void setMonthDepositMoney(BigDecimal monthDepositMoney) {
		this.monthDepositMoney = monthDepositMoney;
	}

	public BigDecimal getTotalDepositMoney() {
		return totalDepositMoney;
	}

	public void setTotalDepositMoney(BigDecimal totalDepositMoney) {
		this.totalDepositMoney = totalDepositMoney;
	}

	public Integer getVipTotalNum() {
		return vipTotalNum;
	}

	public void setVipTotalNum(Integer vipTotalNum) {
		this.vipTotalNum = vipTotalNum;
	}

	public Integer getVipMonthNum() {
		return vipMonthNum;
	}

	public void setVipMonthNum(Integer vipMonthNum) {
		this.vipMonthNum = vipMonthNum;
	}

	public Integer getDealMonthMoney() {
		return dealMonthMoney;
	}

	public void setDealMonthMoney(Integer dealMonthMoney) {
		this.dealMonthMoney = dealMonthMoney;
	}

	public Integer getCusTotalNum() {
		return cusTotalNum;
	}

	public void setCusTotalNum(Integer cusTotalNum) {
		this.cusTotalNum = cusTotalNum;
	}
	
	public Integer getCarPlatformTotalNum() {
		return carPlatformTotalNum;
	}

	public void setCarPlatformTotalNum(Integer carPlatformTotalNum) {
		this.carPlatformTotalNum = carPlatformTotalNum;
	}

	public Integer getCarPlatformMonthNum() {
		return carPlatformMonthNum;
	}

	public void setCarPlatformMonthNum(Integer carPlatformMonthNum) {
		this.carPlatformMonthNum = carPlatformMonthNum;
	}

	public Integer getDealTotalNum() {
		return dealTotalNum;
	}

	public void setDealTotalNum(Integer dealTotalNum) {
		this.dealTotalNum = dealTotalNum;
	}

	public Integer getDealTotalMoney() {
		return dealTotalMoney;
	}

	public void setDealTotalMoney(Integer dealTotalMoney) {
		this.dealTotalMoney = dealTotalMoney;
	}

	public Integer getCusMonthNum() {
		return cusMonthNum;
	}

	public void setCusMonthNum(Integer cusMonthNum) {
		this.cusMonthNum = cusMonthNum;
	}

	public Integer getDealMonthNum() {
		return dealMonthNum;
	}

	public void setDealMonthNum(Integer dealMonthNum) {
		this.dealMonthNum = dealMonthNum;
	}
	
}
