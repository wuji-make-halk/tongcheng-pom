package com.micro.pmo.mapper;

import com.micro.pmo.moudle.car.entity.ReserveCar;
import com.micro.pmo.moudle.car.vo.FindCarVO;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 预订收车Mapper接口
 */
public interface ReserveCarMapper{


    /**
     * 插入预定收车
     * @param reserveCar
     * @return
     */
    public int insertReserve(ReserveCar reserveCar);
    /***
     * 修改预订收车信息
     * @param reserveCar
     * @return
     */
    public int updateReserveCar(ReserveCar reserveCar);
    
    /***
     * 获取详情，用于修改
     * @param creatorId
     * @return
     */
    public ReserveCar getReserveCarById(@Param("reserveId") Integer reserveId);

    /**
     * 根据创建人ID获取list数据
     * @param creatorId
     * @return
     */
    public List<ReserveCar> getReserveList(@Param("creatorId") Integer creatorId,@Param("reserveStatus") Integer reserveStatus);

    /**
     * 根据reserveId更新状态和时间
     * @param reserveId
     * @param reserveStatus
     * @param finishTime
     * @return
     */
    public void updateReserveStatus(@Param("reserveId") Integer reserveId,@Param("reserveStatus") Integer reserveStatus,@Param("finishTime") Date finishTime);


    /**
     * 根据用户ID获取心愿条数
     * @param creatorId
     * @return
     */
    public int getReserverByCusId(@Param("creatorId") Integer creatorId,@Param("reserveStatus") Integer reserveStatus);


    /**
     * 返回收车信息
     * @param reserveId
     * @return
     */
    public ReserveCar getReserver(@Param("reserveId") Integer reserveId);
    
    /***
     * 预订收车匹配车辆
     * @param reserveCar
     * @return
     */
    public List<FindCarVO> reserveMatchingCar(ReserveCar reserveCar);

}
