import kotlin.IllegalArgumentException

/*
In Java, all exceptions that are not of type RuntimeException have to be declared as checked exceptions
You have to make sure the catch any exception that might be thrown and have to annotate the function with the "throws" keyword


Kotlin gets rid of checked exceptions.
They give a simple example on why they removed it:

https://kotlinlang.org/docs/reference/exceptions.html
The following is an example interface of the JDK implemented by StringBuilder class:
Appendable append(CharSequence csq) throws IOException;

every time we need to append something in the string builder, we need to wrap it with a try catch.
If we use a String builder for building log strings, the catch block will be empty.

For good Java practices you should not ignore exceptions and leave the catch block empty

try {
    log.append(message)
}
catch (IOException e) {
    // Must be safe
}

 */

fun main(args: Array<String>) {

    // Kotlin does have the standard try catch finally syntax, which is known from java.

    // In Kotlin try can be put in an expression to retrieve a return value
    val n = try {  failWith { IllegalArgumentException("This failed") } } catch (e: IllegalArgumentException) {e.message}

    println(n)


    // throw is also an expression that can be contained in the elvis operator.
    // Elvis operator will be explained in the null safety part

    val a = A(null)
    val result =
        try { a.message ?: throw IllegalArgumentException("Message was null") }
        catch(e: IllegalArgumentException) {
            println("The last line in the expression automatically uses the return keyword")
            "This string will be marked as unused by the IDE"
            "This string will be returned"
        }

    println(result)


}

// Class only uses a constructor and therefore
class A(val message: String?)

/*
Nothing is a special type in kotlin with no values that indicates that a function will never return
The difference between Nothing and Unit is for indication purposes.

Unit -> is meant to be the Java void keyword and show that a function has no value of interest that will be returned
Nothing -> Indicates that a function will not return normally such as a function that will always throw an exception
 */
fun failWith(context: () -> Throwable): Nothing {
    throw context()
}