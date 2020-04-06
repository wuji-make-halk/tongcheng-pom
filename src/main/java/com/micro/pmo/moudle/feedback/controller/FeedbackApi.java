package com.micro.pmo.moudle.feedback.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.feedback.service.FeedbackService;

/***
 * 反馈
 * @author raoBo
 *
 */
@RestController
@RequestMapping("api-app/feedback")
public class FeedbackApi {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping()
	public Result saveFeedback(@RequestBody Map<String,String> map){
		String feedbackContent = map.get("feedbackContent");
		if(feedbackContent == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[feedbackContent] 反馈内容不能为空");
		}
		if(feedbackContent.length() >500){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[feedbackContent] 反馈内容不能超过500字");
		}
		feedbackService.saveFeedback(feedbackContent);
		return Result.success();
	}
	
}
