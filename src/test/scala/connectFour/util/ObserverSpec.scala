import org.scalatest.*
import connectFour.util.{Event, Observable, Observer}

class ObservableSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {

  class SimpleObserver extends Observer {
    var notified = false
    override def update(e: Event): Unit = notified = true
  }

  "An Observable" should "notify its observers when changes occur" in {
    val observable = new Observable {}
    val observer = new SimpleObserver
    observable.add(observer)
    //observable.notifyObservers
    observer.notified shouldEqual true
  }

  it should "correctly remove observers" in {
    val observable = new Observable {}
    val observer1 = new SimpleObserver
    val observer2 = new SimpleObserver
    observable.add(observer1)
    observable.add(observer2)
    observable.remove(observer1)
    //observable.notifyObservers
    observer1.notified shouldEqual false
    observer2.notified shouldEqual true
  }
}
