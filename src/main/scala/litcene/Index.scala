

package litcene

import java.io.File
import scala.collection.mutable.{HashMap,ArrayBuffer}
import scala.io.Source
import org.apache.commons.io.FilenameUtils


// TODO
// parallel tokenization
// store tokens / indexes on disk

class Index {

  val tokenizer = new Tokenizer

  val documents: HashMap[Int, String] = HashMap.empty

  val tokens: ArrayBuffer[IndexToken] = ArrayBuffer.empty

  def nextDocId: Int = {
    documents.size + 1
  }

  def index(id: String, text: String) {

    val docId = nextDocId

    documents(docId) = text

    val docTokens = for (t <- tokenizer(text)) yield {
      IndexToken(docId, t.token, t.start, t.end, t.offset)
    }

    tokens ++= docTokens

    println(id, docTokens.length)

  }

  def indexFile(id: String, path: String) {
    val text = Source.fromFile(path).getLines.mkString
    index(id, text)
  }

  def indexDirectory(path: String) {

    for (f <- Index.getListOfFiles(path)) {
      val fname = FilenameUtils.getBaseName(f.getName)
      indexFile(fname, f.getPath)
    }

  }

  def kwic(query: String, padding: Int = 50) {
    for (t <- tokens if t.token == query) {

      val docText = documents(t.docId)

      val prefix  = docText.slice(t.start-padding, t.start)
      val token   = docText.slice(t.start, t.end)
      val suffix  = docText.slice(t.end, t.end+padding)

      println(
        prefix +
        Console.RED +
        token +
        Console.RESET +
        suffix
      )

    }
  }

}

object Index {

  def getListOfFiles(path: String): List[File] = {

    val d = new File(path)

    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }

  }

  def main(args: Array[String]) {
    val idx = new Index
    idx.indexDirectory("/Users/dclure/Downloads/Science_Fiction/Corpus")
    idx.kwic("woman")
  }

}
