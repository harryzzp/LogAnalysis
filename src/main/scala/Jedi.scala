import java.io.FileReader
import java.util.Properties

import scala.beans.BeanProperty

class Person {
  @BeanProperty var name: String = _

  //var age = 0

  private[this] var value = 0

  private var privateAge = 0

  def age = privateAge

  def age_=(newValue: Int): Unit = {
    if (newValue > privateAge) privateAge = newValue
  }

  def increment() {
    value += 1
  }

  //def isLess(other: Person) = value < other.value

  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name)
    this.age = age
  }

  override def toString = s"Person($name, $value, $privateAge, $age)"
}

class Student (var name: String, var age: Int) {

  println("Just constructed another person")
  def description = name + " is " + age + " years old"
}

object Jedi {

  def main(args: Array[String]) {
    val person = new Person
    person.age = 3
    person.age = 1
    println(person.age)
    val pp = new Person("Harry", 23)
    println(pp)
    val student = new Student("Harry", 3)
    val des = student.description
    println(des)
    val props = new Properties
    props.load(new FileReader("D:\\Documents\\Downloads\\css.css"))
  }

}