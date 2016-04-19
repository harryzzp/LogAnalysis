package org.glsc;

import java.io.File;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.catalyst.analysis.Analyzer;
import org.glsc.entity.BusinessAnalysisMeta;
import org.glsc.entity.BusinessRecord;
import org.glsc.function.BusinessAnalysisSummary;
import org.glsc.function.LoginFunction;
import org.glsc.function.RemoteIPAndRequestURIFunction;
import org.glsc.function.URLFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static List<String> getBusinessList() {
		List<String> list = new ArrayList<String>();
		list.add("变更存管银行");
		list.add("创业板权限申请");
		list.add("存管账户开户");
		list.add("风险警示协议签署");
		list.add("风险警示协议取消");
		list.add("股东账户开户");
		list.add("股东账户开户(B股)");
		list.add("股东账户注销");
		list.add("沪港通开户");
		list.add("开通委托方式修改");
		list.add("客户风险评测");
		list.add("客户授信变更");
		list.add("客户信息修改");
		list.add("上海股东账户指定");
		list.add("数字证书申请");
		list.add("债券专业投资者登记");
		list.add("退市整理协议签署");
		list.add("小融宝开通");
		list.add("征信资料录入");
		list.add("中新宝开通");

		return list;
	}

	public static void main(String[] args) {
		// String logFile = "D:\\logss\\newwt";
		String bizFile = "D:\\data";
		SparkConf conf = new SparkConf().setAppName("Log Analysis");
		conf.setSparkHome("D:\\App\\spark-1.6.0");
		conf.setMaster("local[4]");
		JavaSparkContext sc = new JavaSparkContext(conf);

		// JavaSparkContext sc;
		// JavaRDD<String> data = sc.textFile("path/input.csv");
		//
		// JavaRDD<BusinessRecord> rdd_records = sc.textFile(data).map(
		// new Function<String, BusinessRecord>() {
		// public BusinessRecord call(String line) throws Exception {
		// // Here you can use JSON
		// // Gson gson = new Gson();
		// // gson.fromJson(line, Record.class);
		// String[] fields = line.split(",");
		// BusinessRecord sd = new BusinessRecord(fields[0], fields[1],
		// fields[2].trim(), fields[3]);
		// return sd;
		// }
		// });

		File logFiles = new File(bizFile);
		long pageRequestTimes = 0;
		long loginTimes = 0;
		long accountTimes = 0;

		List<String> countList = new ArrayList<String>();
		long total = 0;
		long allCount = 0;
		List<Tuple2<String, Tuple2<String, Integer>>> allList = new ArrayList<Tuple2<String, Tuple2<String, Integer>>>();
		if (logFiles.isDirectory()) {
			File[] listFiles = logFiles.listFiles();
			for (File file : listFiles) {

				JavaRDD<BusinessRecord> rdd_records = sc.textFile(file.getAbsolutePath())
						.map(new Function<String, BusinessRecord>() {
							private static final long serialVersionUID = 2712671552369785669L;

							public BusinessRecord call(String line) throws Exception {
								// Here you can use JSON
								// Gson gson = new Gson();
								// gson.fromJson(line, Record.class);
								String[] fields = line.split(",");
								BusinessRecord sd = new BusinessRecord(fields[0], fields[1], fields[2].trim(),
										fields[3], fields[4], fields[5], fields[6], fields[7].trim(), fields[8],
										fields[9], fields[10], fields[11], fields[12].trim(), fields[13], fields[14],
										fields[15], fields[16], fields[17].trim(), fields[18], fields[19], fields[20],
										fields[21], fields[22].trim(), fields[23], fields[24], fields[25]);
								return sd;
							}
						});

				// Mapping using a composite key: Department,Designation,State
				JavaPairRDD<String, Tuple2<String, Integer>> records_JPRDD = rdd_records
						.mapToPair(new PairFunction<BusinessRecord, String, Tuple2<String, Integer>>() {
							private static final long serialVersionUID = -1926204191378072475L;

							public Tuple2<String, Tuple2<String, Integer>> call(BusinessRecord record) {
								String assigneeTimeStr = record.getAssigneeTime();
								   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
								   ParsePosition pos = new ParsePosition(0);
								   Date assigneeTime = formatter.parse(assigneeTimeStr, pos);
								   SimpleDateFormat fixedformatter = new SimpleDateFormat("yyyy-MM");
								   String fixTimeStr = fixedformatter.format(assigneeTime);
								   

								Tuple2<String, Tuple2<String, Integer>> t2 = new Tuple2<String, Tuple2<String, Integer>>(
										// record.getBusinessName()+record.getClientName(),
//										record.getBusinessName() + ":" + fixTimeStr,
										record.getBusinessName() + ":2015-00",
//										" :" + fixTimeStr,
										// record.Department +
										// record.Designation + record.State,
										new Tuple2<String, Integer>(fixTimeStr, 1));
								return t2;
							}
						});

				// reduceByKey using the composite key, summing costToCompany
				// column, and accumulating the number of records by key
				JavaPairRDD<String, Tuple2<String, Integer>> final_rdd_records = records_JPRDD.reduceByKey(
						new Function2<Tuple2<String, Integer>, Tuple2<String, Integer>, Tuple2<String, Integer>>() {
							public Tuple2<String, Integer> call(Tuple2<String, Integer> v1, Tuple2<String, Integer> v2)
									throws Exception {
								return new Tuple2<String, Integer>(v1._1, v1._2 + v2._2);
							}
						});
				List<Tuple2<String, Tuple2<String, Integer>>> collect = final_rdd_records.collect();
				allList.addAll(collect);

				// Map<String, Tuple2<Long, Integer>> collectAsMap =
				// final_rdd_records.collectAsMap();
				// for (Map m : collectAsMap) {
				//
				// }

				// logger.info(string);
				// table.registerAsTable("record_table");
				// table.printSchema();
				// HashMap<String, String> options = new HashMap<String,
				// String>();
				// options.put("header", "true");
				// options.put("path", "cars.csv");
				// sqlContext.lo
				//// DataFrame df = sqlContext.load("com.databricks.spark.csv",
				// options);
				//// df.select("year", "model").save("newcars.csv",
				// "com.databricks.spark.csv");

				// JavaRDD<String> cache =
				// sc.textFile(file.getAbsolutePath()).cache();
				// pageRequestTimes += cache.filter(new
				// RemoteIPAndRequestURIFunction()).count();
				// loginTimes += cache.filter(new LoginFunction()).count();
				// accountTimes += cache.filter(new
				// URLFunction("/newwt/account/account.html")).count();
				//
				// long count = cache.filter(new LoginFunction()).count();
				// total += count;
				// logger.info("FILE: " + file.getAbsolutePath() + " has
				// " + count + ".");
				// if (count > 0) {
				// countList.add(file.getAbsolutePath() + ":"+count);
				// }
			}
		}
		List<BusinessAnalysisMeta> metaList = new ArrayList<BusinessAnalysisMeta>();
		for (Tuple2<String, Tuple2<String, Integer>> t : allList) {
			String[] _1 = t._1.split(":");
			Tuple2<String, Integer> _2 = t._2;
			allCount += _2._2;
			logger.info("-----");
			BusinessAnalysisMeta meta = new BusinessAnalysisMeta();
			meta.setBusinessDimension(_1[0]);
			meta.setTimeDimension(_1[1]);
			meta.setBusinessCount(_2._2);
			metaList.add(meta);
		}
		logger.info("ALL: " + allCount);
		logger.info("list: " + allList.size());
		sc.close();
		
		Collections.sort(metaList);
		
		for (BusinessAnalysisMeta meta : metaList) {
			logger.info(meta.toString());
		}

		for (String businessName : getBusinessList()) {
			List<Long> countOfBusiness = new ArrayList<Long>();
			for (BusinessAnalysisMeta meta : metaList) {
				if (businessName.equals(meta.getBusinessDimension())) {
					countOfBusiness.add(meta.getBusinessCount());
				}
			}
			if (countOfBusiness != null && countOfBusiness.size() > 0) {
				for (Long l : countOfBusiness) {
					logger.info(l + ",");
				}
			}
		}
		// for (String a : countList) {
		// logger.info(a);
		// }
		// logger.info("total: " + total);

	}
}
