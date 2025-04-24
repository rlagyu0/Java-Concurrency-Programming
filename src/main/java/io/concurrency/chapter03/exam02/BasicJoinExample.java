package io.concurrency.chapter03.exam02;

import java.util.Map;
import java.util.Set;

public class BasicJoinExample {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("스레드가 5초 동안 작동합니다.");
                Thread.sleep(5000);
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
                System.out.println("스레드 작동 완료.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        try {
            // 메인스레드는 thread 의 task 가 종료될때까지 기다릴거야!
            // while (isAlive()) wait(0);
            // 내부적으로 wait 과 notify 를 이용해 상태 제어
            thread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드가 계속 진행합니다");
    }
}
