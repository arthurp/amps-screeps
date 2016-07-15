package org.singingwizard.prickle

import scala.collection.mutable
import prickle._
import scala.util.{Try, Success, Failure}

class WeakRef[T] private (private val _value: T, private val _hashCode: Int) {
  def get: T = {
    if (_value != null)
      _value
    else
      throw new IllegalStateException("WeakRef is empty")
  }

  def isDefined: Boolean = _value != null
  def isEmpty: Boolean = _value == null

  override def toString() = {
    if (isDefined) {
      s"WeakRef(${_value})"
    } else {
      s"WeakRef(empty)"
    }
  }

  def canEqual(other: Any) = {
    other.isInstanceOf[org.singingwizard.prickle.WeakRef[T]]
  }

  override def equals(other: Any) = {
    other match {
      case that: org.singingwizard.prickle.WeakRef[T] => 
        that.canEqual(WeakRef.this) && _value != null && _value == that._value
      case _ => false
    }
  }

  // TODO: I cannot get a stable hashcode for things. So hack it.
  override def hashCode() = 42
}

object WeakRef {
  def weakrefHash[T](_value: T) = {
    val prime = 41
    prime + (if(_value == null) 0 else _value.hashCode)
  }
  
  import scala.language.implicitConversions
  implicit def apply[T](v: T) = new WeakRef(v, weakrefHash(v))

  def unapply[T](v: WeakRef[T]) = {
    if(v.isDefined) {
      Some(v.get)
    } else {
      None
    }
  }

  implicit def pickler[T](implicit p: Pickler[T]): Pickler[WeakRef[T]] = new Pickler[WeakRef[T]] {
    def pickle[P](value: WeakRef[T], state: PickleState)(implicit config: PConfig[P]): P = {
      if (value.isDefined)
        config.makeArray(Pickle(value.hashCode, state), Pickle(value._value, state))
      else
        config.makeArray(Pickle(value.hashCode, state))
    }
  }
  implicit def unpickler[T >: Null](implicit up: Unpickler[T]): Unpickler[WeakRef[T]] = new Unpickler[WeakRef[T]] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]) = { 
      config.readArrayLength(pickle) flatMap { n =>
        if (n == 2)
          (for {
            e1 <- config.readArrayElem(pickle, 1)
            e0 <- config.readArrayElem(pickle, 0) 
            v <- Unpickle[T].from(e1, state)
            hash <- Unpickle[Int].from(e0, state)
          } yield new WeakRef(v, hash)) orElse { Success(WeakRef[T](null)) }
        else if (n == 1)
          (for {
            e0 <- config.readArrayElem(pickle, 0) 
            hash <- Unpickle[Int].from(e0, state)
          } yield new WeakRef(null, hash))
        else 
          Failure(new RuntimeException("Weak ref should be array of length 1 or 2."))
      }
    }
  }
}
