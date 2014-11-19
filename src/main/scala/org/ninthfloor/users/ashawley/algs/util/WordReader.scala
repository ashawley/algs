/**
 * WordReader.scala --- Iterate words from a character buffer
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

package org.ninthfloor.users.ashawley.algs.util

import scala.collection.AbstractIterator

/**
 * Mixin that provides a `WordIterator`
 *
 */
trait WordReader {
  /**
   * Class `WordIterator` splits `BufferedSource` into word strings.
   *
   * @example {{{new WordIterator(scala.io.Source.fromString("all bad dads"))}}}
   * @param Buffer to read
   */
  class WordIterator(val buffer: Iterator[Char]) extends /* AbstractIterator[String] with */ Iterator[String] {

    /**
     * Tests whether the buffer can provide another word.
     *
     * @return `true` if a subsequent call to `next` will yield a word, else `false`.
     */
    def hasNext = buffer.hasNext

    /**
     * Produces the next word from the buffer.
     *
     * @return The next word from the buffer, if `hasNext` is `true`,
     * undefined behavior otherwise.
     */
    def next = {
      val chars = buffer.takeWhile(_.isWhitespace == false)
      if (chars.isEmpty) Iterator.empty.next
      else chars.mkString
    }
  }
}
