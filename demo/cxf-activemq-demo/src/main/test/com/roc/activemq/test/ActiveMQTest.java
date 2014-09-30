package com.roc.activemq.test;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.roc.activemq.producer.service.IProducerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContext.xml"})
public class ActiveMQTest {
	
	@Resource
	private IProducerService producerService;
	@Resource(name="queueDestination")
	private Destination destination;
	
	@Test
	public void test_send(){
		for (int i=0; i<2; i++) {  
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));  
        }  
	}
}
