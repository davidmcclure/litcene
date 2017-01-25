

package litcene


case class Token(
  token: String,
  start: Int,
  end: Int,
  offset: Double
)

case class IndexToken(
  docId: String,
  token: String,
  start: Int,
  end: Int,
  offset: Double
)
