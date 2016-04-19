package org.glsc.function;

import org.apache.spark.api.java.function.Function;

public class RemoteIPAndRequestURIFunction implements Function<String, Boolean> {
	private static final long serialVersionUID = 5948492023535024045L;

	public Boolean call(String s) {
		if (s.contains("handler/remote ip address:")) {
			String remoteIPAddressAndRequestURI = s.substring(
					s.indexOf("handler/remote ip address:"),
					s.length());
			String[] split = remoteIPAddressAndRequestURI.split(",");
			String remoteIPAddress = split[0].substring(split[0].indexOf(":") + 1);
			String requestURI = split[1].substring(split[1].indexOf(":") + 1);
			
			
			System.out.println(remoteIPAddress + " | " + requestURI);
		}
		return s.contains("handler/remote ip address:");
	}
}
