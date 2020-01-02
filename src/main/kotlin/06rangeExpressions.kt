fun main() {
    val i = 3

    // Range expression can check if a number is in a specific range
    if (i in 1..4) {
        print("$i ")
    } // prints 3
    println()
    // Creates a for loop and assigns the index to a variable which is usable within the braces
    for (idx in 1..5) {
        print("$idx ")
    } // prints 1 to 5
    println()
    for (idx in 4 downTo 1) {
        print("$idx ")
    } // prints 4 to 1
    println()
    for (idx in 1 until 10) {
        print("$idx ")
    } // prints 1 to 9
    println()
    for (idx in 1..10 step 2) {
        print("$idx ")
    } // prints odd numbers
    println()

}