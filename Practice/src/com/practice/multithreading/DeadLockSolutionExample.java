package com.practice.multithreading;

public class DeadLockSolutionExample {
    public static void main(String[] args) throws InterruptedException{
        DeadLock1 deadLock = new DeadLock1();
        Thread t1 = new Thread(()->{
            for(int i=1;i<100;i++){
                deadLock.increment();
            }
            System.out.println("Completed increment");
        });

        Thread t2 = new Thread(()->{
            for(int i=1;i<100;i++){
                deadLock.decrement();
            }
            System.out.println("Completed decrement");
        });
        t1.start();
     //   sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }
}
class DeadLock1{
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private int count = 0;

    public void increment(){
        synchronized (lock1){
            System.out.println("acquired lock1");
            synchronized (lock2){
                count++;
            }
        }
    }

    public void decrement(){
        synchronized (lock1){
            System.out.println("acquired lock2");
            synchronized (lock2){
                count--;
            }
        }
    }

}
