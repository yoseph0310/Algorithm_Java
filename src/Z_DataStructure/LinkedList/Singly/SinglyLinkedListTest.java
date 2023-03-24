package Z_DataStructure.LinkedList.Singly;

public class SinglyLinkedListTest {
    public static void main(String[] args) {
        SinglyLinkedList<Student> studentList = new SinglyLinkedList<>();

        studentList.add(new Student("홍", 92));
        studentList.add(new Student("김", 70));
        studentList.add(new Student("서", 55));
        studentList.add(new Student("박", 88));

        studentList.sort();

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i));
        }
    }

    static class Student implements Comparable<Student>{
        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Student o) {
            return o.score - this.score;
        }
    }
}
