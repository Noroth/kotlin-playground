// Classes in Kotlin

fun main() {
    val c: Animal = Dog("Dog", 5, 4.5)
    // Kotlin supports string interpolation, no formatting required.
    println("I can directly access the properties. Name:  ${c.name}, Age: ${c.age}, Size ${c.size}")

    val animals = listOf(
        Dog("Lara", 5, 4.5).apply { ownerName = "Alex"  },
        Dog("Lotte", 5, 4.5).apply { ownerName = "Kevin" },
        Cat("Kevin", 1, 2.0)
    )

    // public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit
    // an anonymous function with one parameter will wrap it to the keyword "it"
    animals.forEach {
        it.sayHello()
        it.introduce()
    }

    // The data class automatically creates the following functions:
    // equals, getHashCode, toString and copy
    val addr = AddressData("A", "B", "C", "D", "E")
    println(addr.hashCode())

    // Companion objects
    val factoryDog = Animal.createAnimalOfType<Dog>()
    factoryDog?.sayHello()

    // Object declarations
    ClassRegistrations.registerClassOfType<String>()
    ClassRegistrations.classRegistrations.forEach {
        println(it)
    }
}

// Kotlin is immutable by default.
// If we want to inherit from a class we need to declare it abstract or open
// The difference between var and val is that val is immutable and var is mutable
// For good practice you should use val as much as possible because kotlin lives the principle of immutability
// which is also a big part of most functional languages
abstract class Animal(val name: String, var age: Int, val size: Double) {
    abstract fun sayHello()

    fun introduce() {
        println("My name is $name, my age is $age, my size is $size ${if (hasOwner) "my owner is $ownerName" else ""}")
    }

    // Properties with backing fields
    private var _ownerName: String? = null
    var ownerName: String
        get() {
            if (this._ownerName == null) {
                return ""
            }
            return _ownerName!!
        }
        set(value) {
            if (this._ownerName.isNullOrEmpty()) {
                this._ownerName = value
            }
        }
    val hasOwner: Boolean
        get() = !this.ownerName.isNullOrEmpty()

    // Kotlin does not have static classes like Java does.
    // Companion objects are declared within a class to provide methods from a class context.
    companion object Factory {
        inline fun <reified T : Animal> createAnimalOfType(): Animal? {
            return when (T::class) {
                Dog::class -> Dog("dog", 1, 1.0)
                Cat::class -> Cat("cat", 1, 1.0)
                else -> null
            }
        }
    }
}

// The class defines a default constructor and therefore we have to call him
// The colon replaces the word extends and implements for classes and interfaces
// Kotlin supports default values for parameters
class Dog(name: String = "dog", age: Int, size: Double) : Animal(name, age, size) {
    override fun sayHello() {
        println("Bark")
    }

}

class Cat(name: String, age: Int, size: Double) : Animal(name, age, size) {
    override fun sayHello() {
        println("Dogs are better")
    }
}

// Kotlin has a special keyword for classes that only purposes are to hold data.
// Those are called data classes
data class AddressData(
    val street: String,
    val houseNumber: String,
    val zipCode: String,
    val city: String,
    val country: String
)

// Object declarations are singleton by default.
// Singletons are counted as anti pattern in general but there are cases where they make sense
object ClassRegistrations {

    // Kotlin supports late initialization by default
    private lateinit var registrations: ArrayList<Any>

    // Kotlin got properties with getters and setters
    val classRegistrations: ArrayList<Any>
        get() {
            // Since Kotlin 1.2 this is how you can check whether a lateinit variable is initialized already.
            // As it is declared as non nullable, a null check is always true
            if (!ClassRegistrations::registrations.isInitialized) {
                registrations = arrayListOf()
            }
            return registrations
        }


    inline fun <reified T> registerClassOfType() {
        val cls = T::class

        if (!this.classRegistrations.contains(cls)) {
            this.classRegistrations.add(cls)
        }
    }
}