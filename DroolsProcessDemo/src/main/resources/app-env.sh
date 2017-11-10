#!/usr/bin/env bash
SPARK_HOME="/opt/spark-2.2.0-bin-hadoop2.6"
MAIN_CLASS="com.fosun.DroolsClean"

# drools doesn't work with KyroSerializer
SPARK_SUBMIT_OPTS="--principal ranmx/fonova-hadoop0.fx01@FONOVA.COM \
                    --keytab /home/ranmx/ranmx-hadoop0.fx01.keytab \
                    --master yarn \
                    --queue root.dev \
                    --deploy-mode client \
                    --driver-memory 15g \
                    --executor-cores 4 \
                    --executor-memory 15g \
                    --conf spark.serializer=org.apache.spark.serializer.JavaSerializer \
                    --num-executors 22"


