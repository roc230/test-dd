package com.roc.cxf.service.ws;

import javax.jws.WebService;

import com.roc.cxf.domain.Work;

@WebService
public interface IWorkService {
	
	public String execute(String info);
	
	public Work get(int id);
}
