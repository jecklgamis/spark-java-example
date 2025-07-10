## Spark Java Example

[![Build](https://github.com/jecklgamis/spark-java-example/actions/workflows/build.yaml/badge.svg)](https://github.com/jecklgamis/spark-java-example/actions/workflows/build.yaml)

This is an example Spark job in Java.

## Requirements

Java 21

## Installing Spark

```bash
curl -LO https://dlcdn.apache.org/spark/spark-4.0.0/spark-4.0.0-bin-hadoop3-connect.tgz && tar xvf spark-4.0.0-bin-hadoop3-connect.tgz
```

Ensure SPARK_HOME env variable is pointing to the extracted directory. Run this in your
shell or set this in your `~/.bashrc` or `~/.zshrc`:

```
export SPARK_HOME=<path-to>/spark-4.0.0-bin-hadoop3-connect
export PATH="$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH"
```

## Building

```bash
./mvnw clean package 
```

## Submitting Job

Ensure you have set SPARK_HOME variable that points to your local Spark installation.

##### Submitting Job Locally

```bash
$SPARK_HOME/bin/spark-submit  \
  --master "local[*]"  \
  --class spark.examples.SimpleApp \
  target/spark-java-example.jar 
```

#####  Submitting Job To A Cluster
Ensure a Spark cluster is running. See *Starting Standalone Cluster*  on how to start a 
standalone cluster from your local machine.

Submit a job in client deployment mode. In client mode, the driver is launched directly within the spark-submit process which acts as a client to the cluster.
```bash
$SPARK_HOME/bin/spark-submit \
  --deploy-mode client \
  --master spark://$SPARK_CLUSTER_HOST:7077 \
  --class spark.examples.SimpleApp \
  target/spark-java-example.jar
````

Submit a job in cluster deployment mode. In cluster mode, the driver is launched in one of the workers in the cluster.
```bash
$SPARK_HOME/bin/spark-submit \
  --deploy-mode cluster \
  --master spark://$SPARK_CLUSTER_HOST:7077 \
  --conf spark.standalone.submit.waitAppCompletion=true \
  --class spark.examples.SimpleApp \
  target/spark-java-example.jar
```` 

## Starting Standalone Cluster
This starts a master and a single worker in your local machine. Ensure the $SPARK_HOME points
to your Spark installation.

Start the master:
```bash
$SPARK_HOME/sbin/start-master.sh
```
Get the master url either from the UI (http://localhost:8080) or from the logs. You will
use this to start a worker.

In a separate terminal, start a worker:
```bash
$SPARK_HOME/sbin/start-worker.sh <master-url>  # e.g. spark://localhost:7077
```

From the UI, ensure the worker has joined the cluster by looking at the Workers section.

To stop the master and worker:
```bash
$SPARK_HOME/sbin/stop-worker.sh
$SPARK_HOME/sbin/stop-master.sh
```

## Issues
Please raise in GitHub or send pull request? :) 