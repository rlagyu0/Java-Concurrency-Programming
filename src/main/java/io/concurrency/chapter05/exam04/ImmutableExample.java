package io.concurrency.chapter05.exam04;

public class ImmutableExample  implements Runnable{
    private ImmutablePerson person;

    public ImmutableExample(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 이름: " + person.getName() + ", 나이: " + person.getAge());
    }

    public static void main(String[] args) {

        ImmutablePerson person = new ImmutablePerson("홍길동", 25);

        for (int i = 0; i < 10; i++) {
            new Thread(new ImmutableExample(person)).start();
        }
    }
}

// 상속도(따로 노출될 위험) 못받을 뿐 아니라 변경 불가능하게 클래스와 필드를 구성하면
// 스레드에 안정하다고 할 수 있다.
//불변성 보장: 클래스의 동작이 수정되거나 오버라이드되는 것을 방지하여 코드의 안정성과 예측 가능성을 높인다.
final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
