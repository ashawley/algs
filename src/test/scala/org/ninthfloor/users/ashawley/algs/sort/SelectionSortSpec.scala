/**
 * SelectionSortSpec.scala --- Properties-based testing of selection sort
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

package org.ninthfloor.users.ashawley.algs.sort.test

import org.ninthfloor.users.ashawley.algs.sort.SelectionSort
import org.ninthfloor.users.ashawley.algs.util.test.SortedProp

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean

object SelectionSortSpec extends Properties("SelectionSort") with SortedProp {

  property("sort") = {
    forAll { a: List[Int] =>
      val r = SelectionSort.sort(a)
      isSorted(r) :| "isSorted" &&
        a.forall(r.contains(_)) :| "containsAll" &&
        (a.size == r.size) :| "sameSize"
    }
  }
}
