package org.glsc.function;

import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Skywalker on 2016/3/8.
 */
public class FindItFunction implements Function<String, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(FindItFunction.class);

    private String words;

    public FindItFunction(String words) {
        this.words = words;
    }

    @Override
    public Boolean call(String s) throws Exception {
        boolean result = s.contains(words);
//        if (result) {
//            logger.info(s);
//        }
        return result;
    }
}
