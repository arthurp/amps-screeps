package org.singingwizard.prickle

import scalajs.js
import scalajs.js.JSConverters._
import scala.collection.mutable
import prickle._
import scala.util.{ Try, Success, Failure }

case class NativeJsConfig(val prefix: String = "#", val areSharedObjectsSupported: Boolean = true)
    extends PConfig[js.Any] with NativeJsBuilder with NativeJsReader {

  def onUnpickle(id: String, value: Any, state: mutable.Map[String, Any]) = {
    state += (id -> value)
  }

}

trait NativeJsBuilder extends PBuilder[js.Any] {
  def makeNull(): js.Any = null
  def makeBoolean(b: Boolean): js.Any = b
  def makeNumber(x: Double): js.Any = x

  def makeString(s: String): js.Any = s
  def makeArray(elems: js.Any*): js.Any = elems.toJSArray
  def makeObject(fields: Seq[(String, js.Any)]): js.Any = fields.toMap.toJSDictionary
}

trait NativeJsReader extends PReader[js.Any] {
  def isNull(x: js.Any): Boolean = (x: Any) match {
    case null => true
    case _    => false
  }
  def readBoolean(x: js.Any): Try[Boolean] = (x: Any) match {
    case true  => Success(true)
    case false => Success(false)
    case other => error("boolean", s"$other")
  }
  def readNumber(x: js.Any): Try[Double] = (x: Any) match {
    case x: Double => Success(x)
    case other     => error("number", s"$other")
  }
  def readString(x: js.Any): Try[String] = (x: Any) match {
    case s: String => Success(s)
    case other     => error("string", s"$other")
  }
  def readArrayLength(x: js.Any): Try[Int] = x match {
    case x: js.Array[_] => Success(x.size)
    case other          => error("array length", s"$other")
  }
  def readArrayElem(x: js.Any, index: Int): Try[js.Any] = x match {
    case x: js.Array[_] if index < x.length => Success(x(index).asInstanceOf[js.Any])
    case other                              => error(s"array($index)", s"$other")
  }
  def readObjectField(x: js.Any, field: String): Try[js.Any] = x match {
    case x: js.Object => {
      val d = x.asInstanceOf[js.Dictionary[js.Any]]
      Try(d(field)).orElse(fail(
        s"Cannot read field '$field' of '$x', available fields: ${d.keys.mkString(", ")}"))
    }
    case other => error(s"field \'$field\'", s"$other")
  }

  def error(exp: String, actual: String) = Failure(new RuntimeException(s"Expected: $exp  Actual: $actual"))

  def fail(msg: String) = Failure(new RuntimeException(msg))
}
