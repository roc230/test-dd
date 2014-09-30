package com.roc.cxf.client.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.roc.cxf.domain.FileData;
import com.roc.cxf.domain.Work;
import com.roc.cxf.service.ws.IFileDataService;
import com.roc.cxf.service.ws.IHelloWorldService;
import com.roc.cxf.service.ws.IWorkService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContext.xml"})
public class CxfClient{
	
	@Resource(name="helloWebService")
	private IHelloWorldService helloWorldService;
	@Resource(name="helloWebService2")
	private IHelloWorldService helloWebService2;
	
	@Resource(name="workWebService")
	private IWorkService workService;
	
	@Resource(name="fileDataWebService")
	private IFileDataService fileDataService;
	
	@Test
	public void testClient(){
		Assert.notNull(helloWorldService);
		String result = helloWorldService.sayHi("welcome to FoShan");
		System.out.println(result);
	}
	
	@Test
	public void testClient2(){
		String result = helloWebService2.sayHi("welcome to GuangZhou");
		System.out.println(result);
	}
	
	@Test
	public void testClient3(){
		String result = workService.execute("aaa");
		System.out.println(result);
		
		Work work = workService.get(1);
		System.out.println(work.toString());
	}
	
	@Test
	public void test_getFileData(){
		
		FileData data = fileDataService.getFileData();
		System.out.println(data.getName());
		try {
			InputStream is = data.getData().getInputStream();
			OutputStream os = new FileOutputStream(new File("d:\\test11.txt"));
			byte[] b = new byte[100000];  
			int bytesRead = 0;  
			while ((bytesRead = is.read(b)) != -1) {  
			    os.write(b, 0, bytesRead);  
			}  
			os.flush();  
			os.close();  
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
