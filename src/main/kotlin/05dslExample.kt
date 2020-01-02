import javax.swing.text.html.HTML

fun main() {
    val person = person {
        firstname = "Peter"
        lastname = "Parker"
        address {
            street = "20 Ingram Street"
            city = "New York"
            country = "USA"
        }
    }

    println(person.toString())
}

data class Person(var firstname: String? = null, var lastname : String? = null, var address: Address? = null )

data class Address(var street: String? = null, var city: String? = null, var country: String? = null)


fun person(init: Person.() -> Unit) : Person = Person().apply(init)

fun Person.address(init: Address.() -> Unit) {
    address = Address().apply(init)
}

