package spark.examples;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SimpleApp {
    private static final Logger log = LoggerFactory.getLogger(SimpleApp.class);

    public static void main(String[] args) throws IOException {
        try (SparkSession spark = SparkSession.builder().appName("SimpleApp").getOrCreate()) {
            Dataset<Long> df = spark.range(500);
            log.info("Created data frame with count {}", df.count());
            df.show();
        }
    }
}