package base;

/**
 * Created by cy111966 on 2016/11/21.
 */
public class Test {

  public static void test(Boolean test) {
    test = ! test;
    System.out.println("In test(boolean) : test = " + test);
  }
  public static void main(String[] args) {
    Boolean test = true;
    System.out.println("Before test(boolean) : test = " + test);
    test(test);
    System.out.println("After test(boolean) : test = " + test);
  }
}
