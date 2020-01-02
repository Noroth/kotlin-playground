// There are different types of casting in kotlin and checking if types are matching

fun main(args: Array<String>) {
    val ta: TypeA = TypeB("H")
    val tb = TypeB("B")
    val tc = TypeC()

    // Type checks
    // Kotlin got the "is" keyword to determine if two types are castable

    println(ta is TypeB) // True
    println(tb is TypeA) // True
    println(ta is TypeC) // False

    // Safe cast -> The value of newTypeA will be null as we cannot cast TypeB to TypeC
    val newTypeA : TypeA? = ta as? TypeC
    println(newTypeA)

    // Unsafe cast
    // This will throw a class cast exception as
    // val newTypeA1 : TypeA = ta as TypeC // hl

}

abstract class TypeA

class TypeB(val m: String) : TypeA()

class TypeC : TypeA()