package com.micro.pmo.moudle.feedback.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.mapper.FeedbackMapper;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.feedback.entity.Feedback;
import com.micro.pmo.moudle.feedback.service.FeedbackService;
import com.micro.pmo.moudle.feedback.vo.FeedbackVo;


@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	@Transactional
	public void saveFeedback(String feedbackContent) {
		Integer cusId = CusUtils.getCusId();
		Feedback feedback = new Feedback(cusId, feedbackContent, new Date(), 0);
		feedbackMapper.insertFeedback(feedback);
	}

	
	@Override
	public PageInfo<Feedback> feedbackPage(FeedbackVo feedbackVo) {
		PageHelper.startPage(feedbackVo.getPageNumKey(), feedbackVo.getPageSizeKey());
		List<Feedback> feedback = feedbackMapper.findFeedbackAll(feedbackVo);
		PageInfo<Feedback> info = new PageInfo<>(feedback);
		return info;
	}

	
}
