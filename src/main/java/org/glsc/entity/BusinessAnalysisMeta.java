package org.glsc.entity;

public class BusinessAnalysisMeta implements Comparable<BusinessAnalysisMeta> {

	private String businessDimension;
	
	private String timeDimension;
	
	private long businessCount;

	public String getBusinessDimension() {
		return businessDimension;
	}

	public void setBusinessDimension(String businessDimension) {
		this.businessDimension = businessDimension;
	}

	public String getTimeDimension() {
		return timeDimension;
	}

	public void setTimeDimension(String timeDimension) {
		this.timeDimension = timeDimension;
	}

	public long getBusinessCount() {
		return businessCount;
	}

	public void setBusinessCount(long businessCount) {
		this.businessCount = businessCount;
	}

	@Override
	public String toString() {
		return "BusinessAnalysisMeta [businessDimension=" + businessDimension + ", timeDimension=" + timeDimension
				+ ", businessCount=" + businessCount + "]";
	}

	public int compareTo(BusinessAnalysisMeta o) {
//		int i = Integer.parseInt(this.getTimeDimension().substring(5)) 
//				- Integer.parseInt(o.getTimeDimension().substring(5));
		int i = this.getBusinessDimension().compareTo(o.getBusinessDimension());
		return i;
	}
	
}
