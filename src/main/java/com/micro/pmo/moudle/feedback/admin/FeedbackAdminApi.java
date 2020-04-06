package com.micro.pmo.moudle.feedback.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.feedback.entity.Feedback;
import com.micro.pmo.moudle.feedback.service.FeedbackService;
import com.micro.pmo.moudle.feedback.vo.FeedbackVo;

@RestController
@RequestMapping("api-pc/feedback")
public class FeedbackAdminApi {

	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping("page")
	public Result feedbackPage(FeedbackVo feedbackVo){
		PageInfo<Feedback> pageInfo = feedbackService.feedbackPage(feedbackVo);
		return Result.success(pageInfo);
	}
	
}
