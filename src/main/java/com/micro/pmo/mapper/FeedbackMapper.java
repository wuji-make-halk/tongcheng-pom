package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.feedback.entity.Feedback;
import com.micro.pmo.moudle.feedback.vo.FeedbackVo;

/**
 * 意见反馈Mapper接口
 */
public interface FeedbackMapper{
	
	/***
	 * 反馈信息写入
	 * @param feedback
	 * @return
	 */
	public int insertFeedback(Feedback feedback);
	
	/**
	 * 反馈列表
	 * @param feedbackVo
	 * @return
	 */
	public List<Feedback> findFeedbackAll(FeedbackVo feedbackVo);
}
