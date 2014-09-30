package com.roc.activemq.producer.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.roc.activemq.producer.service.IProducerService;

@Service
public class ProducerService implements IProducerService{
	
	@Resource
	private JmsTemplate jmsTemplate;

	@Override
	public void sendMessage(Destination destination, final String message) {
		 System.out.println("---------------生产者发送消息-----------------");  
	     System.out.println("---------------生产者发了一个消息：" + message);  
	     jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
