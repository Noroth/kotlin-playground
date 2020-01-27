fun main() {
    var result: String = ""
    compute("Hallo") {
        result = "$it Welt"
    }
    println(result)

    val a: String? = null

    if (a.isNullOrEmpty()){
        println(" Null")
    }

    val myVar = "Hallo"

    val newVar = myVar.doWith { "$this Welt"  }
    println(newVar)

    val list = specialListOf("A", "B", "C") {
        index, value ->
        if (index % 2 == 0) {
           value
        } else {
         ""
        }
    }

    list.forEach(::print)

}

// I do not need any interface to define the signature for anonymous functions
fun compute(param: Any, body: (p: Any) -> Unit) {
    body(param)
}

fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun <T, R> T.doWith(body: T.() -> R): R{
    return this.body();
}

fun <T> specialListOf(vararg elements: T, adjuster: (Int, T) -> T) : List<T>{
    val result = mutableListOf<T>()

    for ((idx, element) in elements.withIndex()){
        val newElement = adjuster(idx, element)
        result.add(newElement)
    }
    return result
}




