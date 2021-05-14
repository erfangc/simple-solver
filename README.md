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

## Description of General Algorithm

First use a DFS to find the target `Node`, as well as the sequence of
nodes that must be traversed to get from the root `Node` to the target `Node`

We traverse this path **in order**, and invert the operation and operands on each node. 
At each iteration in this traversal, the inversion operation requires that we know which operand `contains` our target `Node` and which operand does not. Instead of
doing another two graph traversals, we can infer this from peeking into the next node along our intended path

If the next `Node` in path is `==` the current `Node`'s left operand then the target is on the left and vice versa