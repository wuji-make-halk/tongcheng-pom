package com.micro.pmo.moudle.car.service;

import java.util.List;
import java.util.Map;

import com.micro.pmo.moudle.car.entity.ReserveCar;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * 预订收车Service
 */
public interface ReserveCarService{

	/***
	 * 预订收车使用时间
	 * @return
	 */
	public Map<String,Object> reserveUseTime();
	
    /**
     * 添加预定收车心愿
     * @param reserveCar
     * @return
     */
    public void insertReserveCar(ReserveCar reserveCar);


    /**
     * 根据收车id查询列表
     * @param reserveId
     * @param reserveStatus
     * @return
     */
    public List<ReserveCar> findReserveByIdList();

    /**
     * 根据收车id和状态更新状态并且完成时间更新
     * @param reserveId
     * @return
     */
    public void updateReserveStatus(Integer reserveId);
    
    /***
     * 预订收车详情，用于修改
     * @param reserveId
     * @return
     */
    public ReserveCar reserveCarInfo(Integer reserveId);
    
    /***
     * 预订收车修改
     * @param reserveCar
     */
    public void updateReserveCar(ReserveCar reserveCar);
    
    /***
     * 预订收车详情页
     * @param reserveId
     * @return
     */
    public Map<String,Object> reserveMatchedInfo(Integer reserveId);
    
    /**
     * 购买预定收车
     * @param configId
     * @param num
     * @return
     */
    PayInfoVO buyReserve(InputCusCounterBuyVO input);
 }
