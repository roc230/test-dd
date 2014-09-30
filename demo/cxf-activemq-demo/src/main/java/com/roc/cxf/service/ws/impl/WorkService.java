package com.roc.cxf.service.ws.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.roc.cxf.domain.Work;
import com.roc.cxf.service.ws.IWorkService;

@Service("workService")
//使用自动注解方式发布webservice，必须指定唯一性的serviceName
//如果配置了自动前缀为“/”，则访问地址为"/"+"serviceName",即为“/workService”
@WebService(serviceName="workService", endpointInterface="com.roc.cxf.service.ws.IWorkService")
public class WorkService implements IWorkService{

	@Override
	public String execute(String info) {
		return (info += " is execute!");
	}

	@Override
	public Work get(int id) {
		return new Work(id, "roc");
	}

}
