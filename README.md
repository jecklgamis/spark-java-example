## Spark Java Example

This is an example Spark job using Java.

## Requirements

JDK 21

## Building

```bash
./mvnw clean package 
```

## Submitting

Ensure you have set SPARK_HOME variable that points to your local Spark installation.

```bash
$SPARK_HOME/bin/spark-submit  \
  --master local[*]  \
  --class spark.examples.SimpleApp target/spark-java-example.jar 
```


