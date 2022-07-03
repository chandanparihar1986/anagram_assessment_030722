package com.myorg.programs

import scala.collection.mutable
import scala.collection.mutable.HashMap
import java.io._

class anagram {
  private var anagram_list = new HashMap[String,  (String,mutable.HashMap[Char, Int])]();
  private var outputStr = new StringBuilder

  /*
    Write the content of the mutable stringbuiler object to a file.
    This is just a demo sink function which writes the output string content to the local file system
   */
  def writeOutput(): Unit ={
          val file = new File("output_anagram.txt" )
          val bw = new BufferedWriter(new FileWriter(file))
          bw.write(outputStr.toString())
          bw.close()
  }

  private def convertToString(map_obj:mutable.HashMap[Char, Int]): String={
    if (map_obj.isEmpty==true){
      return ""
    }
    map_obj.map(p=> p._1.toString * p._2).mkString
  }

  /*
    Compare two Hashmap objects to check if both object contains same set of keys and values
    Here ordering of elements in the list doesn't matter.
    The elements are stored in the Hashmap object therefore the max time to perform a lookup in the list would be o(1)
   */
  def checkAnagram(lst1:mutable.HashMap[Char,Int], lst2: mutable.HashMap[Char, Int]): Boolean ={
    for (k <- lst1.keys){
      if(lst2.get(k).isEmpty){
        return false
      }
      if(lst2(k) != lst1(k))
      {
        return false
      }
    }
    true
  }

  /*
   it takes a string and convert it to a hashmap of (char -> int)
   checks if the char already presents in the hashmap, increments the counter by 1
  */
  def convertToHashMap(line:String) : HashMap[Char, Int] ={
    var hashtab = new HashMap[Char, Int]();
    for (c <- line) {
      var value: Int = hashtab.getOrElse(c, 0)
      value += 1
      hashtab(c) = value
    }
    return hashtab
  }

  // remove junk/special chars from the input string
  def removeJunkChars(line:String):String={
    return if(line==null || line.isEmpty) "" else line.replaceAll("\\W", "");
  }

  // making sure each key is in lower case
  def stringToLowerCase(line:String):String={
    return if(line==null || line.isEmpty) "" else line.toLowerCase()
  }

  /*
   anagram_list => this holds a collection of (string, (string, hashmap)) where the first string is a sorted string of keys of the hashmap object
   line => an input line from the file
   Checks if the sorted string key is already present in the collection object if so, check if the new hashmap object i.e. hashtab contains
   an anagram hashmap in its value object. If so, append the second string of the anagram_list along with the anagram string value.
   If the sorted string is not present in the collection object, add it to the collection.
  */
  def listAnagram(line:String) = {
    //
    val cLine = removeJunkChars(line)
    val clLine = stringToLowerCase(cLine)
    val hashtab = convertToHashMap(clLine)
    val key = hashtab.keys.toSeq.sorted.mkString
    val existing_obj = anagram_list.get(key) match {
      case Some(value) => value
      case None =>("",mutable.HashMap.empty)
    }
    val existing_hashobj: mutable.HashMap[Char, Int] =existing_obj._2.asInstanceOf[mutable.HashMap[Char, Int]]
    val existing_word:String = existing_obj._1
    if(!existing_hashobj.isEmpty) {
      val result = checkAnagram(existing_hashobj, hashtab)
      if (result) {
        outputStr.append(existing_word + " " + cLine + "\n")
      }
    }
    else{
      anagram_list(key) = (cLine, hashtab)
    }
  }
  outputStr // this is required for the test case only
}

object anagram
{
  def main(args: Array[String]): Unit = {
    val obj = new anagram();
    val source = scala.io.Source.fromFile("words.txt")
    val lines = try source.mkString finally source.close()
    for (line <- lines.split("\n"))
    {
      obj.listAnagram(line)
    }
    obj.writeOutput()
  }
}