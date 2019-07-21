package ck.mianshi;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo{
    private static int count = 6;
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for(int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " quit");
            }, CountryEnum.forEach_CountryEnum(i).getName()).start();
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println("over!");
    }
}
