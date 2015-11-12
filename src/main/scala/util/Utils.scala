package util

/**
 * Created by iozi on 09/11/2015.
 */
object Utils {

  def updateElement[A](elem :A, update :(A,List[A]) => List[A])(ls : List[A]) : List[A] = ls match {
    case head :: tail if head == elem => update(elem,tail)
    case head :: tail => head :: updateElement(elem,update)(tail)
    case List() => List()
  }
}
