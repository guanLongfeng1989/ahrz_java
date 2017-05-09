package com.cn.riwise.general.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cn.riwise.general.common.BaseResponseData;
import com.cn.riwise.general.common.ImageBase;
import com.cn.riwise.general.common.WebResponseCode;
/**
 * 图片上传
 * @author guanlongfeng
 * @date   2017年5月8日 下午1:09:11
 *
 */
@Controller
@RequestMapping("/Image")
public  class ImageController extends ImageBase{

/**
 * 图片上传
 * @param id
 * @param utype
 * @param file
 * @param request
 * @param response
 * @return
 */
@RequestMapping("/uploadHeadPic")
@ResponseBody
 public BaseResponseData uploadHeadPic(@RequestParam(required=false,value="id") String id,@RequestParam(required=false,value="usertype") String utype,@RequestParam(value="file")CommonsMultipartFile  file,HttpServletRequest request,HttpServletResponse response){
	BaseResponseData data = new BaseResponseData();
	try {
				super.upload(id,utype,file,request);
				String filename=super.getFileName();
				data.setCode(WebResponseCode.SUCCESS);
				data.setMessage("获取成功！");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("filename", filename);
				data.setResponseData(resData);
				return data;
			} catch (Exception e) {
				e.printStackTrace();
				data.setCode(WebResponseCode.ERROR);
				data.setMessage("文件上传失败！");
				HashMap<String, Object> resData = new HashMap<String, Object>();
				resData.put("filename", "");
				data.setResponseData(resData);
				return data;
			}
	   }
  }
