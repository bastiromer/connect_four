package connectFour.util

import org.scalatest.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


// TestObserver is a test implementation of the Observer trait
class TestObserver extends Observer {
  var updatedEvent: Option[Event] = None
  def update(e: Event): Unit = updatedEvent = Some(e)
}

// TestObservable is a test implementation of the Observable trait
class TestObservable extends Observable {
  def triggerEvent(e: Event): Unit = notifyObservers(e)
}

class ObservableSpec extends AnyWordSpec with Matchers {
  "Observable" should {
    "notify subscribed observers when an event occurs" in {
      val observable = new TestObservable()
      val observer1 = new TestObserver()
      val observer2 = new TestObserver()

      observable.add(observer1)
      observable.add(observer2)

      val event = Event.End
      observable.triggerEvent(event)

      observer1.updatedEvent shouldEqual Some(event)
      observer2.updatedEvent shouldEqual Some(event)
    }

    "not notify unsubscribed observers" in {
      val observable = new TestObservable()
      val observer1 = new TestObserver()
      val observer2 = new TestObserver()

      observable.add(observer1)
      observable.add(observer2)
      observable.remove(observer2)

      val event = Event.End
      observable.triggerEvent(event)

      observer1.updatedEvent shouldEqual Some(event)
      observer2.updatedEvent shouldEqual None
    }
  }
}
