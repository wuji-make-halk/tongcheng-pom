package com.micro.pmo.moudle.feedback.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.feedback.entity.Feedback;
import com.micro.pmo.moudle.feedback.vo.FeedbackVo;

/**
 * 意见反馈Service
 */
public interface FeedbackService{
	
	/***
	 * 保存反馈意见
	 * @param feedbackContent
	 */
	public void saveFeedback(String feedbackContent);
	
	/**
	 * 反馈列表
	 * @param feedbackVo
	 * @return
	 */
	public PageInfo<Feedback> feedbackPage(FeedbackVo feedbackVo);
}
