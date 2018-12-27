package com.wshid.spark_study.util

import org.apache.spark.sql.types.{CharType, DataTypes, StructField, StructType}
import org.apache.spark.sql.types.DataTypes._

/**
  * Project:  spark_study
  * Author :  wshid
  * Date :  2018-12-27 15:53
  */
object SparkConstants {

  val EXIT_CODE_NOT_REGEX_MATCH: Int = 1
  val EMPTY_STRING: String = "";
  val DEFAULT_INT: Int = -1;
  val LOGGER_TAIL: String = "\t ================"

  val RAW_FILE_NAME: String = "/BlackFriday.csv";
  val RAW_FILE_NAME_HEADLESS: String = "/BlackFriday.headerless.csv";

  val DEFAULT_APP_NAME: String = "SPARK_STUDY"


  case class BlackFriday(userId: Int, productId: String, gender: Char, age: String,
                         occupation: Int, cityCategory: Char, stayInCurrentCityYears: String, maritalStatus: Boolean,
                         productCategory1: Int, productCategory2: Int, productCategory3: Int, perchase: Int)

  /**
    * BlackFriday에 대한 Schema를 지정한다.
    * 전부 StringType으로 설정 한뒤, 이후 Type을 Compact하게 맞춰가는 방식을 사용한다.
    * StringType 지정시, 빈 값을 자동으로 null로 치환된다.
    */
  val schemaBlackFriday: StructType = StructType(Array(
    StructField("userId", StringType, false),
    StructField("productId", StringType, false),
    StructField("gender", StringType, true),
    StructField("age", StringType, true),
    StructField("occupation", StringType, true),
    StructField("cityCategory", StringType, true), // 단일 글자 != ByteType
    StructField("stayInCurrentCityYears", StringType, true),
    StructField("maritalStatus", StringType, true), // 0,1의 값이라고 해서 BooleanType이 먹히지 않음
    StructField("productCategory1", IntegerType, true),
    StructField("productCategory2", IntegerType, true),
    StructField("productCategory3", IntegerType, true),
    StructField("perchase", LongType, true)
  ))

}
