package ck.mianshi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;


public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);
        //原子引用
        AtomicReference<User> atomicReference = new AtomicReference<>(z3);
        System.out.print(atomicReference.get());

        atomicReference.compareAndSet(z3, li4);
        System.out.print(atomicReference.get());
    }
}



@Data
@ToString
@AllArgsConstructor
class User{
    String username;
    int age;
}