package spark.examples;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SimpleApp {
    private static final Logger log = LoggerFactory.getLogger(SimpleApp.class);

    public static void main(String[] args) throws IOException {
        String sparkHome = getSparkHome();
        log.info("Using SPARK_HOME = {}", sparkHome);

        String readmeFile = sparkHome + "/README.md";
        try (SparkSession spark = SparkSession.builder().appName("SimpleApp").getOrCreate()) {
            Dataset<String> logData = spark.read().textFile(readmeFile).cache();
            long numAs = countLinesContaining(logData, "a");
            long numBs = countLinesContaining(logData, "b");
            log.info("Lines with a: {}, lines with b: {}", numAs, numBs);
        }
    }

    private static String getSparkHome() {
        String sparkHome = System.getenv("SPARK_HOME");
        if (sparkHome == null || sparkHome.isEmpty()) {
            log.error("SPARK_HOME environment variable is not set.");
            System.exit(1);
        }
        return sparkHome;
    }

    private static long countLinesContaining(Dataset<String> data, String substring) {
        return data.filter((FilterFunction<String>) s -> s.contains(substring)).count();
    }
}