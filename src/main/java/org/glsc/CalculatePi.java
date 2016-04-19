package org.glsc;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatePi {

	private static final Logger logger = LoggerFactory.getLogger(CalculatePi.class);

	public static void main(String[] args) {

	    SparkConf sparkConf = new SparkConf().setAppName("JavaSparkPi");
	    sparkConf.setSparkHome("D:\\App\\spark-1.6.0");
	    sparkConf.setMaster("local[4]");
	    JavaSparkContext jsc = new JavaSparkContext(sparkConf);

	    int slices = (args.length == 1) ? Integer.parseInt(args[0]) : 2;
	    int n = 100000 * slices;
	    List<Integer> l = new ArrayList<>(n);
	    for (int i = 0; i < n; i++) {
	      l.add(i);
	    }

	    JavaRDD<Integer> dataSet = jsc.parallelize(l, slices);

	    int count = dataSet.map(new Function<Integer, Integer>() {
	      public Integer call(Integer integer) {
	        double x = Math.random() * 2 - 1;
	        double y = Math.random() * 2 - 1;
	        return (x * x + y * y < 1) ? 1 : 0;
	      }
	    }).reduce(new Function2<Integer, Integer, Integer>() {
	      public Integer call(Integer integer, Integer integer2) {
	        return integer + integer2;
	      }
	    });

	    logger.info("...Pi is roughly " + 4.0 * count / n);

	    jsc.close();
	  
	}
}
