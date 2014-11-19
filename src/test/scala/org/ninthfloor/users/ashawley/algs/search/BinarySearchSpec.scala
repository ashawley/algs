/**
 * BinarySearchSpec.scala --- Properties-based testing of binary search
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

package org.ninthfloor.users.ashawley.algs.search.test

import org.ninthfloor.users.ashawley.algs.search.BinarySearch
import org.ninthfloor.users.ashawley.algs.util.test.SortedProp

import org.scalacheck.Properties
import org.scalacheck.Prop

object BinarySearchSpec extends Properties("BinarySearch") with SortedProp {

  property("search") = {
    Prop.forAll { (x: Int, a: List[Int]) =>
      lazy val s = BinarySearch.search(x, a.sorted)
      (s >= 0 && a.contains(x)) ||
        (s == -1 && !a.contains(x))
    }
  }
}
