package io.concurrency.chapter06.exam02;

public class CountingSemaphore implements CommonSemaphore {
    private int signal;
    private final int permits;

    public CountingSemaphore(int permits) {
        this.permits = permits;
        this.signal = permits;
    }

    public void acquired() {
        synchronized (this) {
            while (this.signal == 0) {
                try {
                    wait();
                    System.out.println("wait : Thread.currentThread().getName() = " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("access : Thread.currentThread().getName() = " + Thread.currentThread().getName());
            this.signal--;
        }
    }

    public synchronized void release() {
        if (this.signal < permits) { // signal 값이 permits 보다 작을 때만 증가
            this.signal++;
            System.out.println("release : Thread.currentThread().getName() = " + Thread.currentThread().getName());
            notifyAll();
        }
    }
}
