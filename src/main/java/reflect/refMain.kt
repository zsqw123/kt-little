package reflect

fun main() {
    println("---------constructors")
    println(PClass::class.java.declaredConstructors.toList()) // self
    println(PClass::class.java.constructors.toList()) // public
    println("---------methods")
    println(PClass::class.java.declaredMethods.toList()) // self
    println(PClass::class.java.methods.toList()) // all - self + public
    println("---------fields")
    println(PPClass::class.java.declaredFields.toList()) // self
    println(PPClass::class.java.fields.toList()) // all - self + public
    println("---------class")
    println(PClass::class.java.declaredClasses.toList()) // self
    println(PClass::class.java.classes.toList()) // public
    println(PPClass::class.java.classes.toList()) // all - self + public
    println("---------VClass")
    println(VClass::class.java.declaredConstructors.toList())
    println(VClass::class.java.constructors.toList())
    println(VClass::class.java.declaredMethods.toList())
    println(VClass::class.java.methods.toList())
}