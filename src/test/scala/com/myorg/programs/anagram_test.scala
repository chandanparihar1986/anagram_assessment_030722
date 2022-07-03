package com.myorg.programs
import org.scalatest.funsuite.AnyFunSuite

class anagram_test extends AnyFunSuite {

  test("testAnagram"){
    val obj = new anagram()
    val line1= "dozen"
    val line2= "zoned"
    val lst1= obj.convertToHashMap(line1)
    val lst2= obj.convertToHashMap(line2)
    assert(obj.checkAnagram(lst1,lst2)==true)
  }

  test("testLowerCaseOnNullString"){
    val obj = new anagram()
    val value:String = null
    assert(obj.stringToLowerCase(value)=="")
  }

  test("testRemoveJunkOnNullString"){
    val obj = new anagram()
    val value:String = null
    assert(obj.removeJunkChars(value)=="")
  }

  test("testListAnagram"){
    val obj = new anagram()
    val line1= "dozen"
    obj.listAnagram(line1)
    val line2= "zoned"
    val outputstr = obj.listAnagram(line2)
    assert(outputstr.toString=="dozen zoned\n")
  }

}
