#!/bin/bash
echo "spark study start=============="

### Common and Load file on Hadoop ###
spark_jar="spark_study-1.0-SNAPSHOT.jar"

workspace="/root/workspace/spark_study"

dir_script=$workspace/script
dir_target=$workspace/target

package="com.wshid.spark_study"
spark_main="GCTestMain"

# PARAMETER
run_type="real"
raw_file="/root/workspace/raw_files/BlackFriday.csv"

echo "WorkSpace Move ========"
cd $dir_script
echo "Prev Jar Delete ========"
echo $pwd
rm $spark_jar
echo "New Jar Deploy ========"
echo $pwd
cp $dir_target/$spark_jar .

echo "=== spark submit! $spark_jar ==="
spark-submit --class $package.$spark_main --master local[2] $spark_jar --run-type $run_type --raw-file $raw_file
