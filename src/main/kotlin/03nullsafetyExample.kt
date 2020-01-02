import kotlin.system.exitProcess

/*
One major part of Kotlin is, that you mostly can get ride of null pointer exceptions as kotlin supports
non nullable reference types. By default when defining variables or constants they cannot be null
*/

fun main(args: Array<String>) {

    // the following line will create a compiler error
    // The hl is just a custom expression to highlight the line in my intellij
    // Settings -> Editor -> TODO -> new pattern -> \b.*\/\/.*hl\b.*
    // val a : Int = null // hl

    // If you need to assign null to a variable (I'm leaving constants out because that makes no sense)
    // you need to add a question mark to indicate that a variable can be nullable

    var b : Int? = null

    println("The value of b is $b")


    val c1 = Car(null)
    val c2 : Car? = null


    val brand1 = c1.brand

    // val brand2 = c2.brand  // hl this will create a compiler expcetion because I do not check if the variable is possibly null

    // to correctly retrieve the value for the brand, you need to add the "?" sign to perform a null check
    val brand2 = c2?.brand

    /*
    if you want to risk a null pointer exception to be thrown you can use the "!!" operator
    I got no example for this being a good practice
    */

    //val brand3 = c2!!.brand // hl uncomment this line to throw a null pointer exception
}

class Car(var brand: String?)