package org.glsc.function;

import org.apache.spark.api.java.function.Function;
import org.glsc.entity.BusinessAnalysisMeta;

public class BusinessAnalysisSummary implements Function<BusinessAnalysisMeta, Boolean> {
	

	public Boolean call(BusinessAnalysisMeta meta) throws Exception {
		String businessDimension = meta.getBusinessDimension();
		String timeDimension = meta.getTimeDimension();
		return null;
	}

}
