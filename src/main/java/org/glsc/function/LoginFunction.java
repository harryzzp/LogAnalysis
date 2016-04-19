package org.glsc.function;

import org.apache.spark.api.java.function.Function;

public class LoginFunction implements Function<String, Boolean> {
	private static final long serialVersionUID = -9011217527113911055L;

	public Boolean call(String s) {
//		return s.contains("命令[911050],结果返回");
		return s.contains("命令[912004],结果返回") && s.contains("{\"name\":\"error_no\"},\"value\":\"0\"}");
	}
}
