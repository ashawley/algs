/**
 * SortedProp.scala --- Sorted list property
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
 *
 * Based on Kim Stebel, Daniel C. Sobral and Rahul Goma Phulore.
 * Stackoverflow.com post #7852471. October 21, 2011.
 *
 */

package org.ninthfloor.users.ashawley.algs.util.test

trait SortedProp {
  def isSorted(xs: List[Int]) = xs match {
    case Nil => true
    case x :: Nil => true
    case xs => xs.sliding(2).forall {
      case List(a, b) => a <= b
    }
  }
}
