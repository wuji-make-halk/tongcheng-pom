package com.micro.pmo.moudle.upload;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.qiniuyun.UploadUtils;

@Validated
@RestController
@RequestMapping(value = {"api-app/upload","api-pc/upload"})
public class UploadApi {

	 @Autowired
	 private UploadUtils uploadUtils;
	
	 /***
	  * 上传图片
	  * @param multipartFile
	  * @return
	  */
	@PostMapping("img")
	public Result uploadFile(@RequestParam("file") @NotNull(message = "图片不能为空") 
								MultipartFile multipartFile){
		String imgPath = uploadUtils.uploadFile(multipartFile, true);
		return Result.success(imgPath);
	}
	
	
}
