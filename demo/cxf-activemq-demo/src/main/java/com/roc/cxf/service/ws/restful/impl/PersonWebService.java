package com.roc.cxf.service.ws.restful.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.roc.cxf.service.ws.restful.IPersonWebService;
import com.roc.cxf.service.ws.vo.PaginationQeuryResult;
import com.roc.cxf.service.ws.vo.PaginationQueryParam;
import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;

@Service
public class PersonWebService extends BasicJsonWebService implements IPersonWebService{

	public String add(UserVO vo) {
		
		return SUCCESS(vo);
	}

	@Override
	public String getPagination(Integer pageNum, Integer pageSize) {
		
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		list.add(vo);
		list.add(vo);
		
		PaginationQeuryResult<UserVO> result = new PaginationQeuryResult<UserVO>();
		result.setDefaultPageSize(pageSize);
		result.setPageNum(pageNum);
		result.setList(list);
		result.setTotalCount(2);
		
		return SUCCESS(result);
	}
	
	@Override
	public PaginationQeuryResult<UserVO> getPaginationJson(Integer pageNum,
			Integer pageSize) {
		List<UserVO> list = new ArrayList<UserVO>();
		
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		list.add(vo);
		list.add(vo);
		
		PaginationQeuryResult<UserVO> result = new PaginationQeuryResult<UserVO>();
		result.setDefaultPageSize(pageSize);
		result.setPageNum(pageNum);
		result.setList(list);
		result.setTotalCount(2);
		
		return result;
	}

	@Override
	public String get(String id) {
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		if("1".equals(id)){
			return JsonUtils.toJsonString(vo);
		}
		return null;
	}

	@Override
	public String getPaginationByColumnName(Integer pageNum, Integer pageSize,
			String clomnName, String equalsOrLike, String ascOrDesc) {
		
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		list.add(vo);
		list.add(vo);
		
		PaginationQeuryResult<UserVO> pResult = new PaginationQeuryResult<UserVO>();
		pResult.setDefaultPageSize(pageSize);
		pResult.setPageNum(pageNum);
		pResult.setList(list);
		pResult.setTotalCount(2);
		pResult.setAscOrDesc("desc");
		pResult.setColumnName("name");
		pResult.setEqualsOrLike("equals");
		
		return SUCCESS(pResult);
	}

	@Override
	public PaginationQeuryResult<UserVO> getPagination(PaginationQueryParam paginationQueryParam) {
		System.out.println(JsonUtils.toJsonString(paginationQueryParam));
		return null;
	}

	@Override
	public UserVO getJson(String id) {
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		return vo;
	}

	@Override
	public UserVO getXml(String id) {
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		return vo;
	}

	@Override
	public ResultVO<PaginationQeuryResult<UserVO>> getPaginationResult(
			PaginationQueryParam param) {
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		list.add(vo);
		list.add(vo);
		
		PaginationQeuryResult<UserVO> result = new PaginationQeuryResult<UserVO>();
		result.setDefaultPageSize(param.getPageSize());
		result.setPageNum(param.getPageNum());
		result.setList(list);
		result.setTotalCount(2);
		
		ResultVO<PaginationQeuryResult<UserVO>> resultVO = new ResultVO<PaginationQeuryResult<UserVO>>();
		resultVO.setFlag("success");
		resultVO.setResult(result);
		return resultVO;
	}

	
}
