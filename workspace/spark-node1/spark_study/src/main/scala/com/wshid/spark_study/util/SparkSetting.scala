package com.wshid.spark_study.util

import java.util.Calendar

import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.StructType
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.{Logger, LoggerFactory}

import scala.xml.{Elem, XML}
import SparkConstants._


object SparkSetting {

  private val logger: Logger = LoggerFactory.getLogger(SparkSetting.getClass)

  def createSparkContext(appName: String): SparkContext = {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName(appName)

    val sc = new SparkContext(conf)

    sc
  }

  def makeSparkSession(appName: String): SparkSession = {
    val sparkBeforeMaster = SparkSession
      .builder()
      .appName(appName)
      .config("spark.master", "local[2]").getOrCreate()
    sparkBeforeMaster
  }

  def makeDataSet(spark: SparkSession, loadPath: String): Dataset[Row] = {

    logger.info(s"load Path : $loadPath $LOGGER_TAIL ");
    /*spark.read
      .format("csv")
      .option("delimiter", ",")
      //.schema(ScalaReflection.schemaFor[gakRawSchema].dataType.asInstanceOf[StructType])
      .load(loadPath)
      //.withColumnRenamed("raw_type", COLUMN_RAW_TYPE)
      .as(DEFAULT_APP_NAME)
    //.repartition(8) // partition을 임의로 나눠본다 2배 // 더 느려짐
    // .repartition(partitions)*/
    spark.read
      .csv(loadPath)
      .as(DEFAULT_APP_NAME)
  }

  /**
    * Spark Session을 생성
    * local일때, isReal=false인자를 주어 test할 수 있다.
    *
    * @param appName
    * @param isReal
    * @return
    */
  /*  def makeSparkSession(appName: String, isReal: Boolean = true): SparkSession = {
      val sparkBeforeMaster = SparkSession
        .builder()
        .appName(if (appName != null) appName else DEFAULT_APP_NAME)
        .config("hive.metastore.uris", HIVE_METASTORE_URI) // replace with your hivemetastore service's thrift url
        .config("spark.sql.warehouse.dir", WARE_HOUSE_LOCATION) // hive-site.xml의 설정과 동일
        .enableHiveSupport() // don't forget to enable hive support


      if (isReal) sparkBeforeMaster.getOrCreate() else sparkBeforeMaster.config("spark.master", "local[4]").getOrCreate()
    }

    def makeDataSet(spark: SparkSession, partitions: Int, loadPath: String) = {
      spark.read
        .format("csv")
        .option("delimiter", "\\t")
        .schema(ScalaReflection.schemaFor[gakRawSchema].dataType.asInstanceOf[StructType])
        .load(loadPath)
        .withColumnRenamed("raw_type", COLUMN_RAW_TYPE)
        .as(DEFAULT_APP_NAME)
      //.repartition(8) // partition을 임의로 나눠본다 2배 // 더 느려짐
      // .repartition(partitions)
    }*/

  /**
    * @param spark
    * @param tablePath
    * @param datePath
    * @return
    */
  def makeDataSetParquet(spark: SparkSession, tablePath: String, datePath: String) = {
    //    spark.read
    //      .format(FORMAT_PARQUET)
    //      .option("basePath", tablePath)
    //      .load(tablePath + datePath)

    spark.read
      .option("basePath", tablePath)
      .parquet(tablePath + datePath)
  }


}