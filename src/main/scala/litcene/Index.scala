

package litcene

import java.io.File
import scala.collection.mutable.{HashMap,ArrayBuffer}
import scala.io.Source
import org.apache.commons.io.FilenameUtils


class Index {

  val tokenizer = new Tokenizer

  val documents: HashMap[String, String] = HashMap.empty

  val tokens: ArrayBuffer[IndexToken] = ArrayBuffer.empty

  def index(id: String, text: String) {

    documents(id) = text

    val docTokens = for (t <- tokenizer(text)) yield {
      IndexToken(id, t.token, t.start, t.end, t.offset)
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
    println(idx.documents.size)
  }

}
