package com.roc.cxf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.topway.framework.web.basic.BaseJsonActionImpl;

@Controller
@ParentPackage("json-default")
@Namespace("/my")
public class MyAction extends BaseJsonActionImpl{
	
	@Action("execute")
	public String execute(){
		return NONE;
	}
}
