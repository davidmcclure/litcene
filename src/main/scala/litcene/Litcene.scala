

package litcene

import scala.io.Source
import scala.collection.mutable.{HashMap}


// tokenize text, with ratios
// index text - id, text
// basic concordance


case class Token(token: String, start: Int, end: Int, offset: Double)


case class Tokenizer(regex: String = "[a-z]+") {

  def apply(text: String): List[Token] = {

    val matches = regex.r.findAllMatchIn(text.toLowerCase).toList

    for ((m, i) <- matches.zipWithIndex) yield {
      new Token(m.matched, m.start, m.end, i.toDouble / matches.length)
    }

  }

}


class Index {

  val tokenizer = new Tokenizer

  val documents: HashMap[String, String] = HashMap.empty

  def index(id: String, text: String) {
    documents(id) = text
  }

  def indexFile(id: String, path: String) {
    val text = Source.fromFile(path).getLines.mkString
    index(id, text)
  }

}
