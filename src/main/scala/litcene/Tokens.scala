

package litcene


case class Token(
  token: String,
  start: Int,
  end: Int,
  offset: Double
)

case class IndexToken(
  docId: Int,
  token: String,
  start: Int,
  end: Int,
  offset: Double
)
