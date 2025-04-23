package io.concurrency.chapter02.exam03;

public class RunnableStateThreadExample {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("스레드 상태: " + Thread.currentThread().getState()); // RUNNABLE
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
