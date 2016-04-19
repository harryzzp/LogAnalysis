# Log Analysis

##Run Command:

        Run application locally on 4 cores
        spark-submit --class org.glsc.FullLog --master local[4] --driver-memory 8g --conf spark.eventLog.enabled=false --conf spark.serializer=org.apache.spark.serializer.KryoSerializer --conf spark.executor.extraJavaOptions="-verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseGCOverheadLimit" target/LogAnalysis-1.0-SNAPSHOT.jar "D:\\total.log" "997003"

spark-submit --class org.glsc.FullLog --master local[4] --driver-memory 8g --conf spark.eventLog.enabled=false --conf spark.serializer=org.apache.spark.serializer.KryoSerializer --conf spark.executor.extraJavaOptions="-XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseGCOverheadLimit -XX:CMSInitiatingOccupancyFraction=10" target/LogAnalysis-1.0-SNAPSHOT.jar "D:\\total.log" "997003"