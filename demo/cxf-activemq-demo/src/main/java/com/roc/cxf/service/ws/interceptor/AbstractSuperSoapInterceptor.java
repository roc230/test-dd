package com.roc.cxf.service.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作为CXF拦截器的超级类使用，所有拦截器公用的配置方法都在这个抽象类中设置
 * @author roc
 *
 */
public abstract class AbstractSuperSoapInterceptor extends AbstractSoapInterceptor{
	
	/**
	 * 日志记录器
	 */
	protected Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public AbstractSuperSoapInterceptor(String phase){
		super(phase);
	}
	
	/**
     * 通过SOAP消息获取请求对象
     * @param msg
     * @return
     */
    protected HttpServletRequest getRequest(SoapMessage msg){
    	return (HttpServletRequest)msg.get(AbstractHTTPDestination.HTTP_REQUEST);
    }
    /**
     * 通过SOAP消息获取回应对象
     * @param msg
     * @return
     */
    protected HttpServletResponse getResponse(SoapMessage msg){
    	return (HttpServletResponse)msg.get(AbstractHTTPDestination.HTTP_RESPONSE);
    }
    /**
     * 获取服务实例名称
     * @param message
     * @return
     */
    protected String getServiceName(SoapMessage message){
    	String service = message.get(SoapMessage.WSDL_SERVICE).toString();
    	if(StringUtils.isNotEmpty(service) && service.contains("}")){
    		int index = service.indexOf("}");
    		return service.substring(index+1);
    	}
    	return null;
    }
    /**
     * 获取本次调用的服务方法名称
     * @param message
     * @return
     */
    protected String getMethodName(SoapMessage message){
    	String method = message.get(SoapMessage.WSDL_OPERATION).toString();
    	if(StringUtils.isNotEmpty(method) && method.contains("}")){
    		int index = method.indexOf("}");
    		return method.substring(index+1);
    	}
    	return null;
    }
    /**
     * 获取项目的运行类路径
     * @return
     */
    protected String getClassesPath(){
    	return this.getClass().getClassLoader().getResource("").getPath();
    }
    
    /**
     * 输出SoapMessage对象中的信息
     * @param message
     */
    protected void printSoapMessageInfo(SoapMessage message){
    	logger.info("ACCEPT_CONTENT_TYPE:" + message.get(SoapMessage.ACCEPT_CONTENT_TYPE));
    	logger.info("ATTACHMENTS:" + message.get(SoapMessage.ATTACHMENTS));
    	logger.info("ASYNC_POST_RESPONSE_DISPATCH:" + message.get(SoapMessage.ASYNC_POST_RESPONSE_DISPATCH));
		logger.info("DECOUPLED_CHANNEL_MESSAGE:" + message.get(SoapMessage.DECOUPLED_CHANNEL_MESSAGE));
		logger.info("HTTP_REQUEST_METHOD:" + message.get(SoapMessage.HTTP_REQUEST_METHOD));
		logger.info("BASE_PATH:" + message.get(SoapMessage.BASE_PATH));
		logger.info("CONTENT_TYPE:" + message.get(SoapMessage.CONTENT_TYPE));
		logger.info("CONNECTION_TIMEOUT:" + message.get(SoapMessage.CONNECTION_TIMEOUT));
		logger.info("ENCODING:" + message.get(SoapMessage.ENCODING));
		logger.info("ENDPOINT_ADDRESS:" + message.get(SoapMessage.ENDPOINT_ADDRESS));
		logger.info("IN_INTERCEPTORS:" + message.get(SoapMessage.IN_INTERCEPTORS));
		logger.info("INBOUND_MESSAGE:" + message.get(SoapMessage.INBOUND_MESSAGE));
		logger.info("INTERCEPTOR_PROVIDERS:" + message.get(SoapMessage.INTERCEPTOR_PROVIDERS));
		logger.info("INVOCATION_CONTEXT:" + message.get(SoapMessage.INVOCATION_CONTEXT));
		logger.info("MAINTAIN_SESSION:" + message.get(SoapMessage.MAINTAIN_SESSION));
		logger.info("MIME_HEADERS:" + message.get(SoapMessage.MIME_HEADERS));
		logger.info("MTOM_ENABLED:" + message.get(SoapMessage.MTOM_ENABLED));
		logger.info("MTOM_THRESHOLD:" + message.get(SoapMessage.MTOM_THRESHOLD));
		logger.info("ONE_WAY_REQUEST:" + message.get(SoapMessage.ONE_WAY_REQUEST));
		logger.info("OUT_INTERCEPTORS:" + message.get(SoapMessage.OUT_INTERCEPTORS));
		logger.info("PARTIAL_RESPONSE_MESSAGE:" + message.get(SoapMessage.PARTIAL_RESPONSE_MESSAGE));
		logger.info("PATH_INFO:" + message.get(SoapMessage.PATH_INFO));
		logger.info("PROCESS_ONEWAY_RESPONSE:" + message.get(SoapMessage.PROCESS_ONEWAY_RESPONSE));
		logger.info("PROTOCOL_HEADERS:" + message.get(SoapMessage.PROTOCOL_HEADERS));
		logger.info("QUERY_STRING:" + message.get(SoapMessage.QUERY_STRING));
		logger.info("RECEIVE_TIMEOUT:" + message.get(SoapMessage.RECEIVE_TIMEOUT));
		logger.info("REQUEST_URI:" + message.get(SoapMessage.REQUEST_URI));
		logger.info("REQUEST_URL:" + message.get(SoapMessage.REQUEST_URL));
		logger.info("REQUESTOR_ROLE:" + message.get(SoapMessage.REQUESTOR_ROLE));
		logger.info("RESPONSE_CODE:" + message.get(SoapMessage.RESPONSE_CODE));
		logger.info("REST_MESSAGE:" + message.get(SoapMessage.REST_MESSAGE));
		logger.info("ROBUST_ONEWAY:" + message.get(SoapMessage.ROBUST_ONEWAY));
		logger.info("SCHEMA_VALIDATION_ENABLED:" + message.get(SoapMessage.SCHEMA_VALIDATION_ENABLED));
		logger.info("TRANSPORT:" + message.get(SoapMessage.TRANSPORT));
		logger.info("WSDL_DESCRIPTION:" + message.get(SoapMessage.WSDL_DESCRIPTION));
		logger.info("WSDL_INTERFACE:" + message.get(SoapMessage.WSDL_INTERFACE));
		logger.info("WSDL_OPERATION:" + message.get(SoapMessage.WSDL_OPERATION));
		logger.info("WSDL_PORT:" + message.get(SoapMessage.WSDL_PORT));
		logger.info("WSDL_SERVICE:" + message.get(SoapMessage.WSDL_SERVICE));
    }
}
