package org.glsc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.glsc.function.FindItFunction;
import org.glsc.function.LoginFunction;
import org.glsc.function.RemoteIPAndRequestURIFunction;
import org.glsc.function.URLFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FullLog {

    private static final Logger logger = LoggerFactory.getLogger(FullLog.class);

    public static void main(String[] args) {
//        fullLog();
        long start = System.currentTimeMillis();
        findIt(args[0], args[1]);
//        findIt("D:\\total.log", "997003");
        logger.info("Took " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findIt(String bizFile, String words) {
        SparkConf conf = new SparkConf().setAppName("Find it!");
////        conf.set("spark.executor.memory", "4g");
        /** 这里配置driver memory 没用*/
//        conf.set("spark.driver.memory", "2g");
        conf.setSparkHome("D:\\temp");
        conf.setMaster("local[4]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        File logFiles = new File(bizFile);
        long times = 0;

        List<String> countList = new ArrayList<>();
//        List<Tuple2<String, Tuple2<String, Integer>>> allList = new ArrayList<>();
        if (logFiles.isDirectory()) {
            File[] listFiles = logFiles.listFiles();

            for (File file : listFiles) {
                JavaRDD<String> cache =
                        sc.textFile(file.getAbsolutePath()).cache();
                long tmp = cache.filter(new FindItFunction(words)).count();
                times += tmp;

                logger.info("FILE: {} finds {}.", file.getAbsolutePath() , tmp);
                countList.add(file.getAbsolutePath() + ":" + times);
            }
        } else {
                JavaRDD<String> cache =
                    sc.textFile(logFiles.getAbsolutePath()).cache();
            long tmp = cache.filter(new FindItFunction(words)).count();
            times += tmp;

            logger.info("FILE: {} finds {}.", logFiles.getAbsolutePath() , tmp);
            countList.add(logFiles.getAbsolutePath() + ":" + times);

        }
        logger.info("list: {}.", countList.size());
        logger.info("Occur times: {}.", times);
        sc.close();
    }

    private static void fullLog() {
        String bizFile = "D:\\spark_data";
        SparkConf conf = new SparkConf().setAppName("Log Analysis");
        conf.setSparkHome("D:\\App\\spark-1.6.0");
        conf.setMaster("local[4]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        File logFiles = new File(bizFile);
        long pageRequestTimes = 0;
        long loginTimes = 0;
        long accountTimes = 0;
        int longInt = 1_000_000;
        long exception = 0;

        List<String> countList = new ArrayList<>();
        long total = 0;
        long allCount = 0;
        List<Tuple2<String, Tuple2<String, Integer>>> allList = new ArrayList<>();
        if (logFiles.isDirectory()) {
            File[] listFiles = logFiles.listFiles();
            for (File file : listFiles) {
                JavaRDD<String> cache =
                        sc.textFile(file.getAbsolutePath()).cache();
                pageRequestTimes += cache.filter(new
                        RemoteIPAndRequestURIFunction()).count();
                loginTimes += cache.filter(new LoginFunction()).count();
                accountTimes += cache.filter(new
                        URLFunction("/newwt/account/account.html")).count();

                long count = cache.filter(new LoginFunction()).count();
                total += count;
                logger.info("FILE: " + file.getAbsolutePath() + " has" + count + ".");
                if (count > 0) {
                    countList.add(file.getAbsolutePath() + ":" + count);
                }
            }
        }
        logger.info("ALL: " + allCount);
        logger.info("list: " + allList.size());
        logger.info("login times: " + loginTimes);
        logger.info("account times: " + accountTimes);
        sc.close();
    }

}
