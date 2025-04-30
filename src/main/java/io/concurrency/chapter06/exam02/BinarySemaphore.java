package io.concurrency.chapter06.exam02;

public class BinarySemaphore implements CommonSemaphore {
    private int signal = 1;

    public synchronized void acquired() {
        while (this.signal == 0) {
            try {
                System.out.println("wait : Thread.currentThread().getName() = " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
            }
        }
        System.out.println("access : Thread.currentThread().getName() = " + Thread.currentThread().getName());
        this.signal = 0;
    }

    public synchronized void release() {
        System.out.println("release : Thread.currentThread().getName() = " + Thread.currentThread().getName());
        this.signal = 1;
        this.notify();
    }
}
