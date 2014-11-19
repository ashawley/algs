/**
 * KnuthMorrisPratt.scala --- Tail-recursive Knuth-Morris-Pratt search
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
 * Edition. Addison-Wesley. 1998.  Page 277-292.
 */

package org.ninthfloor.users.ashawley.algs.search

import scala.util.Try

/**
 * Singleton object providing a binary search method.
 *
 * == Integers ==
 * Example interactive session:
 * {{{
 * scala> KnuthMorrisPratt.search("ababc", "abc")
 * res1: Int = 2
 *
 * scala> KnuthMorrisPratt.search("ababz", "abc")
 * res2: Int = -1
 * }}}
 *
 * == Command-line  ==
 * Compilation:
 * {{{
 * $ scalac ./org/ninthfloor/users/ashawley/algs/search/KnuthMorrisPratt.scala
 * }}}
 *
 * Usage:
 * {{{
 * $ scala org.ninthfloor.users.ashawley.algs.search.KnuthMorrisPratt file.txt < search.txt
 * }}}
 */

object KnuthMorrisPratt {
  /**
   * Search string `s' for occurence of substring `w`.
   *
   * @return Index in `s` where `w' is found or else -1
   */
  def search(s: String, w: String) = new KnuthMorrisPratt(s, w).matchPos
}

class KnuthMorrisPratt(val s: String, val w: String) {

  val table = buildTable(0, 0)

  def buildTable(i: Int, j: Int): List[Int] = (i, j) match {
    case (i, j) if w.length <= i => Nil
    case (0, j) => -1 :: buildTable(1, j)
    case (1, j) => 0 :: buildTable(2, j)
    case (i, j) if w(i) == w(j) => (j + 1) :: buildTable(i + 1, j + 1)
    case (i, j) => 0 :: buildTable(i + 1, 0)
  }

  val matchPos = search(0, 0)

  /**
   * Search numbers on standard input in a file, display misses.
   */
  @annotation.tailrec
  final def search(i: Int, j: Int): Int = (i, j) match {
    case (i, j) if w.length == 0 => 0
    case (i, j) if s.length <= i + j => -1
    case (i, j) if w(j) == s(i + j) && w.length - 1 == j => -1
    case (i, j) if w(j) == s(i + j) => search(i, j + 1)
    case (i, j) if table(j) > -1 => search(i + j - table(j), table(j))
    case (i, _) => search(i + 1, 0)
  }
}
