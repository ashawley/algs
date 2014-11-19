/**
 * KnuthMorrisPratt.scala --- Properties-based testing of Knuth-Morris-Pratt
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

package org.ninthfloor.users.ashawley.algs.search.test

import org.ninthfloor.users.ashawley.algs.search.KnuthMorrisPratt

import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Prop
import org.scalacheck.Prop.BooleanOperators
import org.scalacheck.Properties

object KnuthMorrisPrattSpec extends Properties("KnuthMorrisPratt") {

  implicit val arbKmp = Arbitrary(Gen.resultOf { (s: String, w: String) =>
    new KnuthMorrisPratt(s, w)
  })

  property("table.length") = {
    import Prop.AnyOperators
    Prop.forAll {
      (s: String, w: String) =>
        new KnuthMorrisPratt(s, w).table.length ?= w.length
    }
  }

  property("table >= -1") = {
    Prop.forAll {
      (s: KnuthMorrisPratt) =>
        s.table.forall(_ >= -1)
    }
  }

  property("table(0)") = {
    import Prop.AnyOperators
    Prop.forAll {
      (s: String, w: String) =>
        (w.length > 0) ==> {
          new KnuthMorrisPratt(s, w).table(0) ?= -1
        }
    }
  }

  property("table(1)") = {
    import Prop.AnyOperators
    Prop.forAll {
      (s: String, w: String) =>
        (w.length > 1) ==> {
          new KnuthMorrisPratt(s, w).table(1) ?= 0
        }
    }
  }

  lazy val stringLongerThan2 = Arbitrary.arbitrary[String].suchThat(_.length > 2)

  property("table(2)") = {
    import Prop.AnyOperators
    Prop.forAll('s |: Arbitrary.arbitrary[String], 'w |: stringLongerThan2) {
      (s: String, w: String) =>
        (w(0) == w(2)) ==> {
          (new KnuthMorrisPratt(s, w).table(2) ?= 1) :| "with prefix match"
        }
        (w(0) != w(2)) ==> {
          (new KnuthMorrisPratt(s, w).table(2) ?= 0) :| "with prefix match"
        }
        (new KnuthMorrisPratt(s, w.take(2) + w).table(2) ?= 1) :| "with forged prefix"
    }
  }

  property("table(3)") = {
    Prop.forAll {
      (s: String, w: String) =>
        (w.length > 3) ==> {
          List(0, 1, 2).contains(new KnuthMorrisPratt(s, w).table(3))
        }
    }
  }

  property("matchPos >= -1") = {
    Prop.forAll {
      (s: KnuthMorrisPratt) =>
        s.matchPos >= -1
    }
  }

  property("matchPos == s.indexOf(w)") = {
    import Prop.AnyOperators
    Prop.forAll {
      (s: String, w: String) =>
        new KnuthMorrisPratt(s, w).matchPos ?= s.indexOf(w)
    }
  }

  property("search") = {
    import Prop.AnyOperators
    Prop.forAll {
      (s: String, w: String) =>
        KnuthMorrisPratt.search(s, w) ?= s.indexOf(w)
    }
  }
}
