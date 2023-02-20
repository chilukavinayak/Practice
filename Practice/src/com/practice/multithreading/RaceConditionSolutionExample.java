package com.practice.multithreading;

import java.util.Random;

public class RaceConditionSolutionExample  {
    public static void main(String[] args) throws Exception{
        RaceCondition1 rc = new RaceCondition1();
        Thread t1 = new Thread(()-> rc.modified());
        Thread t2 = new Thread(()-> rc.printer());
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}
class RaceCondition1 {
    Random random = new Random(System.currentTimeMillis());
    int sharedVar = 0;

    public void printer(){
        int i = 10000;
        while(i > 0) {
            synchronized (this) {
                if (sharedVar % 5 == 0)
                    if (sharedVar % 5 != 0)
                        System.out.println(sharedVar);
                i--;
            }
        }
    }
    public void modified(){
        int i = 10000;
        while(i > 0 ){
            synchronized (this){
                sharedVar = random.nextInt(1000);
                i--;
            }
        }
    }

}
