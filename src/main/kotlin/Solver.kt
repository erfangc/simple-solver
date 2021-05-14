class Solver {

    fun solve(original: Node): Node {
        /*
        Step 1 - find the node to target
         */
        val (target, paths) = original.dfs(name = "x")

        /*
        currentSolution
         */
        var currentSolution = Node(name = "y")

        /*
        For each node on the paths to the target node - traversing in order - invert
        the operation defined on that node
         */
        paths.forEachIndexed { idx, node ->
            /*
            determine whether the group is on the right or on the left
            by peaking into the next node in path. If the next node is on the right
            then the right node contains our target and vice-versa
             */
            val nextNode = if (idx != paths.size - 1) {
                paths[idx + 1]
            } else {
                /*
                presumably we've reached the end
                 */
                target
            }

            val leftNode = node.left
            val rightNode = node.right

            val b = when {
                leftNode == nextNode -> {
                    rightNode
                }
                rightNode == nextNode -> {
                    leftNode
                }
                else -> {
                    when {
                        leftNode == target -> {
                            rightNode
                        }
                        rightNode == target -> {
                            leftNode
                        }
                        else -> {
                            error("this should never happen")
                        }
                    }
                }
            }

            /*
            Invert the operation in the currentPath
             */
            if (b == leftNode) {
                when (node.binaryOperator) {
                    BinaryOperator.ADD -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.SUBTRACT,
                        )
                    }
                    BinaryOperator.SUBTRACT -> {
                        currentSolution = Node(
                            left = b,
                            right = currentSolution,
                            binaryOperator = BinaryOperator.SUBTRACT,
                        )
                    }
                    BinaryOperator.DIVIDE -> {
                        currentSolution = Node(
                            left = b,
                            right = currentSolution,
                            binaryOperator = BinaryOperator.DIVIDE,
                        )
                    }
                    BinaryOperator.MULTIPLY -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.DIVIDE,
                        )
                    }
                }
            } else {
                when (node.binaryOperator) {
                    BinaryOperator.ADD -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.SUBTRACT,
                        )
                    }
                    BinaryOperator.SUBTRACT -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.ADD,
                        )
                    }
                    BinaryOperator.DIVIDE -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.MULTIPLY,
                        )
                    }
                    BinaryOperator.MULTIPLY -> {
                        currentSolution = Node(
                            left = currentSolution,
                            right = b,
                            binaryOperator = BinaryOperator.DIVIDE,
                        )
                    }
                }
            }
        }
        return currentSolution
    }

}