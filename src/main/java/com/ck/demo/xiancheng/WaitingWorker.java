package com.ck.demo.xiancheng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitingWorker implements  Runnable {

    private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;

    public WaitingWorker(List<String> outputScraper, CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter) {
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }

    @Test
    public void test() throws InterruptedException{
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter))).limit(5).collect(Collectors.toList());
        workers.forEach(Thread::start);
        readyThreadCounter.await();
        outputScraper.add("Worker ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        outputScraper.add("Workder complete");
        /**
        assertThat(outputScraper)
                .containsExactly(
                        "Workers ready",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Workers complete"
                );*/
    }




















}
















