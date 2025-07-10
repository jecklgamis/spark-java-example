package spark.examples;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SparkConnectApp {
    private static final Logger log = LoggerFactory.getLogger(SparkConnectApp.class);


    public static void main(String[] args) throws IOException {
        log.info("Starting SparkConnectApp...");
        try (SparkSession spark = SparkSession.builder().remote("sc://localhost").appName("SparkConnectApp").getOrCreate()) {
            Dataset<Long> df = spark.range(500);
            log.info("Created data frame with count {}", df.count());
        }
    }
}