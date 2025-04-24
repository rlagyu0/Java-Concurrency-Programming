package io.concurrency.chapter03.exam02;

import java.util.Map;

public class MultiThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("스레드 1이 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드 1 작동 완료.");
                soutMainThreadStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("스레드 2가 2초 동안 작동합니다.");
                Thread.sleep(2000);
                System.out.println("스레드 2 작동 완료.");
                soutMainThreadStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다.");

        thread1.join();
        thread2.join();

        soutMainThreadStatus();

        System.out.println("메인 스레드가 계속 진행합니다");

    }

    private static void soutMainThreadStatus() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread threadInner : allStackTraces.keySet()) {
            if ("main".equals(threadInner.getName())) {
                System.out.println("thread.getState() = " + threadInner.getState());
                System.out.println("thread.getName() = " + threadInner.getName());
                System.out.println("thread.getId() = " + threadInner.getId());
            }
            // join() 을 호출한 메인스레드는 WATING 상태임
            //                    thread.getState() = WAITING
            //                    thread.getName() = main
        }
    }
}
