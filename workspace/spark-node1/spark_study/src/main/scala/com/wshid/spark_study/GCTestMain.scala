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


    val rawFile : String = getClass.getResource(RAW_FILE_NAME).toString
    val rawFileHeadless : String = getClass.getResource(RAW_FILE_NAME_HEADLESS).toString
//
//    sc.textFile(raw_file);

//    val ss : SparkSession = SparkSetting.makeSparkSession(DEFAULT_APP_NAME)
//
//    ss.

    val sparkSession = SparkSetting.makeSparkSession(DEFAULT_APP_NAME)



    //print(sparkSession.read.csv("target/classes/BlackFriday.csv").show());

    /**
      * schema로 헤더 지정에 따른 데이터 차이 비교
      * schema 설정시 null이 되는 이유 : https://stackoverflow.com/questions/46066704/spark-dataframe-returning-null-when-specifying-a-schema
      *   특정 내용을 해당 데이터 타입으로 바꿀 수 없는 상황이 되면, 전체 데이터를 null로 간주한다.
      */
    val ds = SparkSetting.makeDataSet(sparkSession, rawFile, true)
    print(ds.show());
    print(ds.printSchema());
    val dsHeadless = SparkSetting.makeDataSet(sparkSession, rawFileHeadless, isHeader = false, schemaBlackFriday)
    print(dsHeadless.show());
    print(dsHeadless.printSchema());

  }

}
