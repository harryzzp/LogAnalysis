package org.glsc.function;

import org.apache.spark.api.java.function.Function;

public class MobileFunction implements Function<String, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656438013214075135L;

	public Boolean call(String s) throws Exception {
		return s.contains("910006") && s.contains("mobile_tel");
	}

}
