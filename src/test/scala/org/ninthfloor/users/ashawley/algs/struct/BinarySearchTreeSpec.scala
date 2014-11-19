/**
 * BinarySearchTreeSpec.scala --- Properties-based testing of binary search tree
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
 */

package org.ninthfloor.users.ashawley.algs.struct.test

import org.ninthfloor.users.ashawley.algs.struct.BinarySearchTree
import org.ninthfloor.users.ashawley.algs.struct.Leaf

import org.scalacheck.Properties
import org.scalacheck.Prop

object BinarySearchTreeSpec extends Properties("BinarySearchTree") {

  property("search") = {
    Prop.forAll { (x: Int, a: List[Int]) =>
      Leaf(x).insert(x) == Leaf(x)
    }
  }
}
