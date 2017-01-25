

package litcene


case class Tokenizer(regex: String = "[a-z]+") {

  def apply(text: String): List[Token] = {

    val matches = regex.r.findAllMatchIn(text.toLowerCase).toList

    val length = matches.length

    for ((m, i) <- matches.zipWithIndex) yield {
      new Token(m.matched, m.start, m.end, i.toDouble / length)
    }

  }

}
