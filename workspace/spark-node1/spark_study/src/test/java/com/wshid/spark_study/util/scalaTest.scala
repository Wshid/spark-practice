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
    val b = zscore2(2, 3)_
    println(b(4))

    def curryfoo(x:R)(y:R)(z:R) : R = (x+y*z)

    val a = curryfoo(4)(5)_
    println(a(2))

  }

  test("generic"){

    def mapmake[T](g: T => T)(seq: List[T]) = seq.map(g)
    println(mapmake(List(5, 10, 15, 20))(List(0,1)))
    println(mapmake(List(5, 10, 15, 20))(List(2)))
  }

}
