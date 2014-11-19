/**
 * BinarySearchTree.scala --- Binary search tree with pattern matching
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
 * Edition. Addison-Wesley. 1998.  Page 502-.
 */

package org.ninthfloor.users.ashawley.algs.struct

import scala.util.Try

import org.ninthfloor.users.ashawley.algs.util.WordReader

class Node {
  def insert(a: Int) = this match {
    case Tree(x: Int, left: Node, right: Node) => Leaf(a)
    case Leaf(x: Int) => Leaf(a)
  }
}

case class Tree(x: Int, left: Node, right: Node) extends Node

case class Leaf(x: Int) extends Node

/**
 * Singleton object providing a binary search tree.
 *
 * == Integers ==
 * Example interactive session:
 * {{{
 * scala> BinarySearchTree.insert(List(2, 3, 1))
 * res1: List[Int] = List(1, 2, 3)
 * }}}
 *
 * == Command-line  ==
 * Compilation:
 * {{{
 * $ scalac ./org/ninthfloor/users/ashawley/algs/struct/BinarySearchTree.scala
 * }}}
 *
 * Usage:
 * {{{
 * $ scala org.ninthfloor.users.ashawley.algs.struct.BinarySearchTree < data.txt
 * }}}
 */
object BinarySearchTree extends WordReader {

  /**
   * Data list of integers `a` in ascending order.
   *
   * @return Dataed list
   */
  def sort(a: List[Int]): List[Int] = {
    def merge(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
      case (Nil, b) => b
      case (a, Nil) => a
      case (a :: aa, b :: bb) =>
        if (a < b) a :: merge(aa, b :: bb)
        else b :: merge(a :: aa, bb)
    }
    a match {
      case Nil => Nil
      case x :: Nil => a
      case a => {
        val half = a.length / 2
        val (a1, a2) = a.splitAt(half)
        merge(sort(a1), sort(a2))
      }
    }
  }

  /**
   * Sort numbers on standard input in a file, display misses.
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
