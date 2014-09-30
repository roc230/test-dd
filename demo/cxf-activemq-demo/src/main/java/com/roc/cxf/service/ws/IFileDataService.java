package com.roc.cxf.service.ws;

import javax.jws.WebService;

import com.roc.cxf.domain.FileData;

@WebService
public interface IFileDataService {
	
	public FileData getFileData();

}
