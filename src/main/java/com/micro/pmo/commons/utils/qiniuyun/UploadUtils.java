package com.micro.pmo.commons.utils.qiniuyun;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.commons.utils.ResultState;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Component
public class UploadUtils {

    @Value("${qiniuyun.access_key}")
    private String accessKey;
    @Value("${qiniuyun.secret_key}")
    private String secretKey;
    @Value("${qiniuyun.bucket_name}")
    private String bucketName;
    @Value("${qiniuyun.img_url}")
    private String imgUrl;
    
	private  String[] SUPPORT_PHOTO_TYPES = new String[]{
			"jpg", "jpeg", "gif", "png", "bmp"
		};
	
	
	private  boolean isSupportPhotoType(String type) {
		for (String t : SUPPORT_PHOTO_TYPES) {
			if (type.equalsIgnoreCase(t)) {
				return true;
			}
		}
			return false;
	}
    
   /***
    * 上传文件
    * @param multipartFile
    * @return
    */
    public String uploadFile(MultipartFile multipartFile,Boolean image){
		if (multipartFile == null || multipartFile.getSize() < 1) {
			throw new PmoException(ResultState.FILE_ERROR, "未选择上传文件");
		}
		if (multipartFile.getSize() > 5 * 1024 * 1024) {
			throw new PmoException(ResultState.FILE_ERROR, "文件尺寸不能超过5M");
		} 
		String filename = multipartFile.getOriginalFilename();
		String type = filename.substring(filename.lastIndexOf(".") + 1);
    	//如果是图片就要校验格式
    	if(image){
    		if (!isSupportPhotoType(type)) {
    			throw new PmoException(ResultState.FILE_ERROR, 
    					"仅支持以下图片类型:"+ StringUtils.join(SUPPORT_PHOTO_TYPES, "|"));
    		}
    	}
    	//开始处理
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.autoZone());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String format = DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
        String randomNumber = RandomStringUtils.randomAlphanumeric(10);
        //修改文件名
        StringBuffer key = new StringBuffer(format);
        key.append(randomNumber).append(".").append(type);
        String keyStr = key.toString();
       
        try {
        	 //获取字节码
        	byte[] uploadBytes = multipartFile.getBytes();
        	//字节输入流
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            //开始上传文件包含状态码
        	Response response = uploadManager.put(byteInputStream,keyStr,upToken,null, null);
        	//状态码的处理
        	
        	//TODO
        	DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        	return String.format("%s%s",imgUrl,putRet.key);
        } catch (QiniuException ex) {
            System.err.println("七牛异常状态码：" + ex.response.statusCode +" 错误描述" + ex.response.error);
		}catch (IOException e) {
			e.printStackTrace();
		}
        throw new PmoException(ResultState.FILE_ERROR);
    }
	
	
}
