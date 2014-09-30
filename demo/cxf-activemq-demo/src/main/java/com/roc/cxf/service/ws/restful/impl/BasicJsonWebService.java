package com.roc.cxf.service.ws.restful.impl;

import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.utils.JsonUtils;

public class BasicJsonWebService{
	
	public static String SUCCESS(){
		ResultVO resultVO = new ResultVO();
		resultVO.setFlag("success");
		resultVO.setResult(null);
		resultVO.setUrl("");
		resultVO.setException("");
		return JsonUtils.toJsonString(resultVO);
	}
	
	public static <T> String SUCCESS(T resultObj){
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setFlag("success");
		resultVO.setResult(resultObj);
		resultVO.setUrl("");
		resultVO.setException("");
		return JsonUtils.toJsonString(resultVO);
	}
	
	public static String SUCCESS(String url){
		ResultVO resultVO = new ResultVO();
		resultVO.setFlag("success");
		resultVO.setResult(null);
		resultVO.setUrl(url);
		resultVO.setException("");
		return JsonUtils.toJsonString(resultVO);
	}
	
	public static String FAILED(String exception){
		ResultVO resultVO = new ResultVO();
		resultVO.setFlag("failed");
		resultVO.setResult(null);
		resultVO.setUrl("");
		resultVO.setException(exception);
		return JsonUtils.toJsonString(resultVO);
	}
}
