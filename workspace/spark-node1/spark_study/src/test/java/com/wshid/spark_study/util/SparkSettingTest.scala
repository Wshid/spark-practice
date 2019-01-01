package com.wshid.spark_study.util

import org.scalatest.FunSuite

/**
  * Project:  spark_study
  * Author :  wshid
  * Date :  2018-12-28 19:53
  */
class SparkSettingTest extends FunSuite {

  test("testArgumentParser") {
    println(SparkSetting.argumentParser(Array("--run-type", "3", "abcde")));
  }
}
