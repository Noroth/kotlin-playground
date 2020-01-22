fun main() {
    var result: String = ""
    compute("Hallo", {
        result = "$it Welt"
    })
    println(result)

    val a: String? = null

    if (a.isNullOrEmpty()){
        println("Null")
    }
}

// I do not need any interface to define the signature for anonymous functions
fun compute(param: Any, body: (p: Any) -> Unit) {
    body(param)
}

fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}


