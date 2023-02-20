package com.practice.multithreading;

import java.util.Random;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class UnsafeThreadProgram {
    private int start;
    private int end;

    static Random random = new Random(System.currentTimeMillis());
    private int result;

    public void increment() throws InterruptedException {
        result++;
    }

    public void decrement() throws InterruptedException {
        result--;
    }

    public void printResult(){
        System.out.println(result);
    }
    public UnsafeThreadProgram(int start, int end){
        this.start = start;
        this.end = end;
    }


    public static void main(String[] args) throws InterruptedException {
      simulate();
    }

    public static void simulate() throws InterruptedException {
        UnsafeThreadProgram unsafeThreadProgram = new UnsafeThreadProgram(1, 100);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        int rand = random.nextInt(100);
                        unsafeThreadProgram.increment();
                        sleep(rand);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t2 = new Thread() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        int rand = random.nextInt(100);
                        unsafeThreadProgram.decrement();
                        sleep(rand);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        unsafeThreadProgram.printResult();
    }

}
