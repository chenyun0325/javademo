package jvm;

public class StudentTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student st1 = new Student();
        System.err.println(st1.getBook());
        System.err.println(Student.getAddress());

        Student st2 = new Student();
        System.err.println(st2.getBook());
        System.err.println(Student.getAddress());
        // DateFormat format;
        String namespace = StudentTest.class.getName();
        System.err.println(namespace);

    }

}
