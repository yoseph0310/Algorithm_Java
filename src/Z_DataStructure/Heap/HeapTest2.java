package Z_DataStructure.Heap;

import java.util.Comparator;

public class HeapTest2 {
    public static void main(String[] args) {

        Heap<Student> heap1 = new Heap<>();
        Heap<Student> heap2 = new Heap<>(comparator);

        heap1.add(new Student("마이클", 40));
        heap2.add(new Student("마이클", 40));

        heap1.add(new Student("조던", 27));
        heap2.add(new Student("조던", 27));

        heap1.add(new Student("백종원", 48));
        heap2.add(new Student("백종원", 48));

        heap1.add(new Student("천종원", 28));
        heap2.add(new Student("천종원", 28));

        heap1.add(new Student("짱구", 5));
        heap2.add(new Student("짱구", 5));

        heap1.add(new Student("둘리", 200000000));
        heap2.add(new Student("둘리", 200000000));

        System.out.println("[Heap 1] : 이름순(같을 경우 나이 오름차순)");
        while(!heap1.isEmpty()) {
            System.out.println(heap1.remove());
        }
        System.out.println();

        System.out.println("[Heap 2] : 나이 내림차순(같을 경우 이름순)");
        while(!heap2.isEmpty()) {
            System.out.println(heap2.remove());
        }
        System.out.println();
    }

    private static Comparator<Student> comparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            // 나이가 같다면 이름순
            if (o1.age == o2.age) {
                return o1.name.compareTo(o2.name);
            }
            return o2.age - o1.age;
        }
    };

    static class Student implements Comparable<Student> {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public int compareTo(Student o) {
            if (this.name.compareTo(o.name) == 0) {
                return this.age - o.age;
            }

            return this.name.compareTo(o.name);
        }

        public String toString() {
            return "이름 : " + name + "\t나이 : " + age;
        }
    }
}
