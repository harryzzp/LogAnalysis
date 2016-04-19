package org.glsc.function;

import org.apache.spark.api.java.function.Function;

public class URLFunction implements Function<String, Boolean> {

	private static final long serialVersionUID = 6696670008138399440L;
	
	public String url;
	
	public URLFunction(String url) {
		this.url = url;
	}

	public Boolean call(String s) throws Exception {
		if (url == null){
			throw new NullPointerException();
		}
		return s.contains("request uri:"+ url);
	}

}
