package io.concurrency.chapter02.exam02;

import java.util.List;

public class ThreadStackExample {
    public static void main(String[] args) throws InterruptedException {

        // 3개 스레드 실행
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }

        // 힙 메모리가 같은 객체를 바라볼 때
        //        Thread-0 objectReference: Hello World, HashCode: @676285473
        //        Thread-1 objectReference: Hello World, HashCode: @676285473
        //        Thread-2 objectReference: Hello World, HashCode: @676285473
        // 힙 메모리가 다른 객체를 바라볼 때
        //        Thread-0 objectReference: 102 : Hello World, HashCode: @2032109270
        //        Thread-1 objectReference: 100 : Hello World, HashCode: @1415949687
        //        Thread-2 objectReference: 101 : Hello World, HashCode: @692600636
    }

    static class MyRunnable implements Runnable {
        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
//            String objectReference = localValue + " : Hello World";
            String objectReference = "Hello World";
            System.out.println(Thread.currentThread().getName() +
                    " : 스레드 ID: " + threadId +
                    ", Value: " + localValue +
                    ", objectReference: " + objectReference +
                    ", HashCode: @" + System.identityHashCode(objectReference));
        }
    }
}