package com.topway.framework.web.basic;

import java.util.HashMap;

public class BaseJsonActionImpl extends BaseActionImpl{
	
	protected final static String SUCCESS = "success";
	protected final static String GETFTL = "getFtl";
	private JsonMap<String, Object> jsonMap = new JsonMap<String, Object>();
	
	/**
	 * 重写LinkedHashMap<br>
	 * 提供连续put值方法
	 * @author roc
	 *
	 * @param <K>
	 * @param <V>
	 */
	public class JsonMap<K,V> extends HashMap<K, V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public JsonMap<K,V> putValue(K key,V value){
			super.put(key, value);
			return this;
		}
		
	}
	
	public JsonMap<String, Object> createJsonMap(){
		this.jsonMap = new JsonMap<String, Object>();
		return this.jsonMap;
	}
	
	/**
	 * 获取根目录的绝对路径
	 * @return
	 */
	public String getRealPath(){
		return getServletContext().getRealPath("");
	}

	
	//getter and setter
	public JsonMap<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(JsonMap<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
}
