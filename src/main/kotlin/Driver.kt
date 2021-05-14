fun main() {
    val original = Node(
        left = Node(
            left = Node(name = "m"),
            right = Node(name = "x"),
            binaryOperator = BinaryOperator.DIVIDE,
        ),
        right = Node(
            name = "b",
        ),
        binaryOperator = BinaryOperator.SUBTRACT,
    )
    // should print m / x - b
    println(original)

    val solver = Solver()
    val solution = solver.solve(original)

    // should print m / (y + b)
    println(solution)
}
