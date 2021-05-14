
import java.util.*

data class Node(
    val name: String? = null,
    val left: Node? = null,
    val right: Node? = null,
    val binaryOperator: BinaryOperator? = null,
) {

    override fun toString(): String {
        return toString(0)
    }

    private fun toString(level: Int): String {
        if (left == null && right == null) {
            return name ?: "UNK"
        } else {
            val leftStr = left?.toString(level + 1)
            val rightStr = right?.toString(level + 1)
            val operatorStr = when (binaryOperator) {
                BinaryOperator.DIVIDE -> {
                    "/"
                }
                BinaryOperator.SUBTRACT -> {
                    "-"
                }
                BinaryOperator.ADD -> {
                    "+"
                }
                BinaryOperator.MULTIPLY -> {
                    "*"
                }
                else -> error("...")
            }
            return if (level != 0 && (binaryOperator == BinaryOperator.ADD || binaryOperator == BinaryOperator.SUBTRACT)) {
                "($leftStr $operatorStr $rightStr)"
            } else {
                "$leftStr $operatorStr $rightStr"
            }

        }
    }

    fun dfs(name: String): DfsResult {

        val stack = Stack<Node>()
        stack.push(this)

        /*
        Maps child -> parent
         */
        val parentMap = hashMapOf<Node, Node>()

        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node.name == name) {
                // we found the target node by name
                // next we must find the path it took to get here
                val paths = mutableListOf<Node>()
                var parent = parentMap[node]
                while (parent != null) {
                    paths.add(parent)
                    parent = parentMap[parent]
                }
                return DfsResult(node, paths.reversed()) // important to reverse path
            } else {
                if (node.right != null) {
                    stack.push(node.right)
                    parentMap[node.right] = node
                }
                if (node.left != null) {
                    stack.push(node.left)
                    parentMap[node.left] = node
                }
            }
        }

        error("...")
    }

}