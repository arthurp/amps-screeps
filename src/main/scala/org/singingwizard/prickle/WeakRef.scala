package org.singingwizard.prickle

import scala.collection.mutable
import prickle._
import scala.util.Success

class WeakRef[T](private val _value: T) {
  def get(): T = {
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
}

object WeakRef {
  def apply[T](v: T) = new WeakRef(v)

  implicit def pickler[T](implicit p: Pickler[T]): Pickler[WeakRef[T]] = new Pickler[WeakRef[T]] {
    def pickle[P](value: WeakRef[T], state: PickleState)(implicit config: PConfig[P]): P = {
      Pickle(value._value, state)
    }
  }
  implicit def unpickler[T >: Null](implicit up: Unpickler[T]): Unpickler[WeakRef[T]] = new Unpickler[WeakRef[T]] {
    def unpickle[P](pickle: P, state: mutable.Map[String, Any])(implicit config: PConfig[P]) = {
      Unpickle[T].from(pickle, state).map(WeakRef(_)).orElse(Success(WeakRef[T](null)))
    }
  }
}
