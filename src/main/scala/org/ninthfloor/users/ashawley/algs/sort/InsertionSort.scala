/**
 * InsertionSort.scala --- Recursive insertion sort with pattern matching
 *
 * Copyright (C) 2014 Aaron S. Hawley
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Commentary:
 *
 * Based on Sedgewick, Robert. Algorithms in C, Parts 1-4:
 * Fundamentals, Data Structures, Sorting, Searching, 3rd
 * Edition. Addison-Wesley. 1998.  Page 264.
 */

package org.ninthfloor.users.ashawley.algs.sort

import scala.util.Try

import org.ninthfloor.users.ashawley.algs.util.WordReader

/**
 * Singleton object providing an insertion sort method.
 *
 * == Integers ==
 * Example interactive session:
 * {{{
 * scala> InsertionSort.sort(List(2, 3, 1))
 * res1: List[Int] = List(1, 2, 3)
 * }}}
 *
 * == Command-line  ==
 * Compilation:
 * {{{
 * $ scalac ./org/ninthfloor/users/ashawley/algs/sort/InsertionSort.scala
 * }}}
 *
 * Usage:
 * {{{
 * $ scala org.ninthfloor.users.ashawley.algs.sort.InsertionSort < sort.txt
 * }}}
 */
object InsertionSort extends WordReader {

  /**
   * Sort list of integers `a` in ascending order.
   *
   * @return Sorted list
   */
  def sort(a: List[Int]): List[Int] = {
    def insert(x: Int, a: List[Int]): List[Int] = a match {
      case Nil => x :: Nil
      case a :: aa =>
        if (x <= a) x :: a :: aa
        else a :: insert(x, aa)
    }
    a match {
      case Nil => Nil
      case a :: aa => insert(a, sort(aa))
    }
  }

  /**
   * Sort numbers on standard input in a file, display misses.
   */
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val codec = scala.io.Codec.ISO8859
    val input = Source.fromInputStream(System.in)
    val words = new WordIterator(input)
    val nums = {
      for {
        word <- words
        num <- Try(word.trim.toInt).toOption
      } yield (num)
    }
    sort(nums.toList).foreach(println)
  }
}
