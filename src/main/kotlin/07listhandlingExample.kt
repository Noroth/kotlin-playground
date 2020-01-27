fun main() {
    val immutableList = (1..20).toList()

    println(immutableList) // prints a list with 20 elements

    println(immutableList.filter { it % 2 == 0 }) // prints a lit with 10 elements and only even numbers

    println(immutableList.map { it*2 }) // prints all 20 elements multiplied by 2


    // maps
    val numberMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 4
    )

    println(numberMap.toString())

    println(10 times 4) // 40

}

private infix fun Int.times(i : Int): Int{
    return this * i;
}

private fun getValue(value: Int) : Int{
    return value
}