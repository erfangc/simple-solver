# Simple Solver

Analytically solve easy math problems

```kotlin
// Driver.kt

fun main() {
    val original = Node(
        left = Node(
            left = Node(name = "m"),
            right = Node(name = "x"),
            operator = Operator.DIVIDE,
        ),
        right = Node(name = "b"),
        operator = Operator.SUBTRACT,
    )
    // should print m / x - b
    println(original)

    val solver = Solver()
    val solution = solver.solve(original)

    // should print m / (y + b)
    println(solution)
}
```

## Performance Insights in `Solver.kt`

In any given node of the solution we must know

We can avoid the issue of repeatedly doing DFS to see if either operand contains
the target node by just comparing `paths`# simple-solver
