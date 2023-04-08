package playground

object Playground {

  case class TreeNode[T](val value: T, val left: Option[TreeNode[T]], val right: Option[TreeNode[T]])

  def bfs[T](node: TreeNode[T]): Iterator[TreeNode[T]] = {
    Iterator.unfold(List(node))(q => {
      if (q.isEmpty) {
        None
      } else {
        Some(q.head, q.tail ++ q.head.left ++ q.head.right)
      }
    })
  }

  def dfs[T](node: TreeNode[T]): Iterator[TreeNode[T]] = {
    Iterator.unfold(List(node))(s => {
      if (s.isEmpty) {
        None
      } else {
        Some(s.last, s.init ++ s.last.right ++ s.last.left)
      }
    })
  }


  def main(args: Array[String]): Unit = {
    val cNode = TreeNode("c", None, None)
    val dNode = TreeNode("d", None, None)
    val bNode = TreeNode("b", None, Some(cNode))
    val aNode = TreeNode("a", Some(bNode), Some(dNode))

    println(
      bfs(aNode)
        .toList
        .map((node) => node.value)
    )

    println(
      dfs(aNode)
        .toList
        .map((node) => node.value)
    )
  }
}