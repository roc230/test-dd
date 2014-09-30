package com.roc.cxf.service.ws.restful.impl;

import com.roc.cxf.service.ws.restful.IBasicJsonWebService;
import com.roc.cxf.service.ws.vo.PaginationQueryParam;
import com.roc.cxf.service.ws.vo.ResultVO;

public abstract class BasicJsonWebService2<T> implements IBasicJsonWebService<T>{
	
	public static <T> ResultVO<T> SUCCESS(T resultObj){
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setFlag("success");
		resultVO.setResult(resultObj);
		resultVO.setUrl("");
		resultVO.setException("");
		return resultVO;
	}

	@Override
	public ResultVO<T> add(T vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO<T> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO<T> update(T vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO<T> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO<T> getPagination(PaginationQueryParam queryParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO<T> getPaginationByName(String ClumnName,
			PaginationQueryParam queryParam) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
