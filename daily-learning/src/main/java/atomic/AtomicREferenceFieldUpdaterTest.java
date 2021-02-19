package atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicREferenceFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater updater= AtomicReferenceFieldUpdater.newUpdater(Person .class, String.class, "name");
        Person person = new Person();
        updater.compareAndSet(person ,person .name,"老王") ;
        System.out.println(person.name);
    }
}


class  Person {
    volatile  String name="老刘";
}