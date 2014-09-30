package com.roc.cxf.service.ws.impl;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

import org.springframework.stereotype.Service;

import com.roc.cxf.domain.FileData;
import com.roc.cxf.service.ws.IFileDataService;

@Service("fileDataService")
@MTOM  //开启CXF文件流传输支持组件MTOM支持
@WebService(serviceName="fileDataService", endpointInterface="com.roc.cxf.service.ws.IFileDataService")
public class FileDataService implements IFileDataService{

	@Override
	public FileData getFileData() {
		FileData fileData = new FileData();
		fileData.setName("test");
		DataSource source = new FileDataSource(new File("d:/bbb.txt"));
		DataHandler data = new DataHandler(source);
		fileData.setData(data);
		return fileData;
	}

}
