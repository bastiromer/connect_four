package connectFout.model

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(width: Int, height: Int, filling: T) = this(Vector.tabulate(height, width) { (row, col) => filling })
  val width: Int = rows.size + 1
  val height: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(height, width) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] =
    copy(rows.updated(row, rows(row).updated(col, cell)))