package org.glsc.function;

import org.apache.spark.api.java.function.Function;

public class ClientIDFunction implements Function<String, Boolean> {

	public Boolean call(String v1) throws Exception {
		
		if (v1.contains("12229121") && v1.contains("mfinancing")) {
			System.out.println(v1);
			return true;
		} else {
			return false;
		}
	}

}
