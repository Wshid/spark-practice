package com.wshid.spark_study.util

import org.scalatest.FunSuite

/**
  * Project:  spark_study
  * Author :  wshid
  * Date :  2019-01-01 13:15
  */
class scalaTest extends FunSuite {
  /**
    * scala 문법 요약: https://jdm.kr/blog/85
    */

  type R = Double

  /**
    * multiple block
    * 리턴값으로 함수를 내보낼 수 있다.
    */
  test("multiple block") {
    def compose(g: R => R, h: R => R): R => R = {
      (x: R) => g(h(x))
    }

    val f: R => R = compose(x => x + 2, y => y * 3)

    println(f(5))
  }

  test("pipe") {
    /**
      * pipe
      */
    println(
      (1 to 5) filter {
        _ % 2 == 0
      } map {
        _ * 2
      },
      (1 to 5).filter(_ % 2 == 0).map(_ * 2)
    )
  }

  test("currying") {
    /**
      * zscore는 인자를 두개 받는다.
      * 인자를 두개 받아서, 인자를 한개 받아 연산하는 함수를 만들어 낸다.
      */
    val zscore = (mean: R, sd: R) => {
      (x: R) => (x - mean) / sd // 함수를 리턴한다.
    }

    val curryZscore = zscore(2, 4)(3)
    println(curryZscore)


    def zscore2(mean: R, sd: R)(x: R): R = (x - mean) / sd

    //val a = zscore2(2, 3) // _를 뒤에 붙여, x가 들어갈 공간이 있는 함수임을 명시하여야 한다.
    val b = zscore2(2, 3) _
    println(b(4))

    def curryfoo(x: R)(y: R)(z: R): R = (x + y * z)

    val a = curryfoo(4)(5) _
    println(a(2))

  }

  test("generic") {

    def mapmake[T](g: T => T)(seq: List[T]) = seq.map(g)

    println(mapmake(List(5, 10, 15, 20))(List(0, 1)))
    println(mapmake(List(5, 10, 15, 20))(List(2)))
  }


  test("package Selective") {
    //import scala.collection.{Vector, Sequence}
  }

  test("concat") {
    val list: List[Int] = 1 :: List(2, 3)
    println(list)
  }

  test("range Test") {
    val valueTo = 1 to 5
    val valueUntil = 1 until 6
    val valueStep = 1 to 10 by 2
    println(valueTo, valueUntil, valueStep)
  }

  test("breakable") {
    import scala.util.control.Breaks._
    val xs = Array(1, 3, 5, 6, 8)
    breakable {
      for (x <- xs) {
        if (Math.random < 0.2) break
        println(x)
      }
    }
  }

  /**
    * for.. yield : for/sequence comprehension
    */
  test("yield, map_filter") {
    val xs = 1 to 10
    val result1 = for (x <- xs if x % 2 == 0) yield x * 10
    // yield를 안하면 값이 출력되지 않음
    val result2 = xs.filter(_ % 2 == 0).map(_ * 10)

    println(result1, result2)
  }

  test("destructuring bind, zip") {
    val xs = 1 to 10
    val ys = 11 to 20
    val result1 = for ((x, y) <- xs.zip(ys)) yield x * y
    val result2 = (xs.zip(ys)).map { case (x, y) => x * y }

    println(result1, result2)
  }


  test("cross product") {
    val xs = 1 to 10
    val ys = 11 to 20
    for (x <- xs; y <- ys) yield x * y
    val result1 = xs.flatMap(x => ys map (y => x * y))
    println(result1)
  }

  test("imperative-ish") {
    val xs = 1 to 10
    val ys = 11 to 20
    for (x <- xs; y <- ys) {
      println("%d * %d = %d".format(x, y, x * y))
    }
  }

  test("map case pattern") {
    val xs = 1 to 10
    val ys = 11 to 20
    val result1 = xs.zip(ys).map { case (x, y) => x * y }
    //val result2=(xs zip ys) map ((x,y) => x*y)
    println(result1)
  }


  /**
    * 패턴매칭을 할때, 소문자로 시작하는 변수에 담긴 값과 동일 매칭시,
    *   case구문 내부에서 ``로 묵어 주어야 한다.
    * 단, 비교하려는 변수명이 "대문자"로 시작한다면
    *   ``로 감싸지 않아도 된다.
    */
  test("Pattern") {
    val v42 = 42
    Some(3) match {
      //case Some(v42) => println("42") // 3이라는 값을 전달했으나, 일치하지 않아도 42라는 값을 출력한다.
      case Some(`v42`) => println("42")
      case _ => println("Not 42")
    }
    println("Match End")
  }

  test("singleton"){
    /**
      * singleton 객체 생성시 : object
      * 일반 class 생성시 : class
      */
  }

  test("type check & cast"){
    val x  = 4
//    println(x.isInstanceOf[String])
//    println(x.asInstanceOf[String])
  }

}
