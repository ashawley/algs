/**
 * BinarySearch.scala --- Tail-recursive binary search
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
 * Edition. Addison-Wesley. 1998.  Page 56.
 */

package org.ninthfloor.users.ashawley.algs.search

import scala.util.Try

/**
 * Singleton object providing a binary search method.
 *
 * == Integers ==
 * Example interactive session:
 * {{{
 * scala> val nums = List(1, 2, 3)
 * nums: List[Int] = List(1, 2, 3)
 *
 * scala> BinarySearch.search(1, nums)
 * res1: Int = 0
 *
 * scala> BinarySearch.search(3, nums)
 * res2: Int = 2
 *
 * scala> BinarySearch.search(4, nums)
 * res3: Int = -1
 * }}}
 *
 * == Command-line  ==
 * Compilation:
 * {{{
 * $ scalac ./org/ninthfloor/users/ashawley/algs/search/BinarySearch.scala
 * }}}
 *
 * Usage:
 * {{{
 * $ scala org.ninthfloor.users.ashawley.algs.search.BinarySearch file.txt < search.txt
 * }}}
 */
object BinarySearch {

  /**
   * Search for value `k` in list `a`.
   *
   * @return Index in `a` where `k' is found or else -1
   */
  def search(k: Int, a: Seq[Int]) = {
    @annotation.tailrec
    def search2(lo: Int, mid: Int, hi: Int): Int = {
      Try(a(mid)).toOption match {
        case Some(x) if lo > hi => -1
        case Some(x) if x == k => mid
        case Some(x) if x > k => search2(lo, (mid - lo) / 2, mid - 1)
        case Some(x) if x < k => search2(mid + 1, mid + 1 + (hi - mid) / 2, hi)
        case None => -1
      }
    }
    search2(0, a.length / 2, a.length - 1)
  }

  /**
   * Search numbers on standard input in a file, display misses.
   */
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val codec = scala.io.Codec.ISO8859
    val sortedNums = {
      for {
        line <- Source.fromFile(new java.io.File(args(0))).getLines
        num <- Try(line.trim.toInt).toOption
      } yield (num)
    }.toList.sorted
    for {
      line <- Source.fromInputStream(System.in).getLines
      num <- Try(line.trim.toInt).toOption if search(num, sortedNums) == -1
    } println(num)
  }
}
