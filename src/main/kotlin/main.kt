fun main(args: Array<String>) {
    pln("Hello World")
}


fun <T> T.extensionFunc(method: (T) -> Unit) {
    method(this)
}

fun pln(message: Any?) = println(message)


fun tryExample(){
    throw IllegalArgumentException("This is an exception thrown in a function")
}