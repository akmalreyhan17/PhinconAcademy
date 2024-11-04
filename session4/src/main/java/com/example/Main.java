package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Thread t = new Thread();
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        Counter counter = new Counter();

        MyThread tr = new MyThread(counter, "A");
        MyThread tr2 = new MyThread(counter, "B");
        tr.start();
        tr2.start();

        Shared shared = new Shared();
        MyThread2 trd = new MyThread2(shared, "wait");
        MyThread2 trd2 = new MyThread2(shared, "send");
        trd.start();
        //some task...
        trd2.start();

        Thread thr = new Thread(new MyRunnable());
        thr.start();

    }

    public void testMethod() throws MyException {
        throw new MyException(404, "Not found");
        // Will throw "404: Not found"
    }

    synchronized void print() {
        // critical section
    }
    

}