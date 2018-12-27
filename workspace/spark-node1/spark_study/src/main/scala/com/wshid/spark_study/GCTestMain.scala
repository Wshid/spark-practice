package com.wshid.spark_study

import com.wshid.spark_study.util.SparkSetting
import org.apache.spark.SparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.slf4j.{Logger, LoggerFactory}
import com.wshid.spark_study.util.SparkConstants._

/**
  * Project:  spark_study
  * Author :  wshid
  * Date :  2018-12-27 15:50
  */
object GCTestMain {

  private val logger: Logger = LoggerFactory.getLogger(GCTestMain.getClass)

  def main(args : Array[String]) = {
    logger.info("Hello World");

//
//    val usersDF = spark.read.load("examples/src/main/resources/users.parquet")
//    usersDF.select("name", "favorite_color").write.save("namesAndFavColors.parquet")

    val sc : SparkContext = SparkSetting.createSparkContext("SPARK_STUDY")


    val raw_file : String = getClass.getResource(RAW_FILE_NAME).toString
//
//    sc.textFile(raw_file);

//    val ss : SparkSession = SparkSetting.makeSparkSession(DEFAULT_APP_NAME)
//
//    ss.

    val sparkSession = SparkSetting.makeSparkSession(DEFAULT_APP_NAME)



    //print(sparkSession.read.csv("target/classes/BlackFriday.csv").show());

    val ds = SparkSetting.makeDataSet(sparkSession, raw_file)

    print(ds.show());

    printf("print main");
  }

  case class Person(name : String, age : Int)
}
