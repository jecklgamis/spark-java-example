#!/usr/bin/env bash

if [ -z "$SPARK_HOME" ]; then
  echo "SPARK_HOME is not set. Please set it to your Spark installation directory."
  exit 1
fi
java -cp target/spark-java-example.jar  spark.examples.SparkConnectApp

