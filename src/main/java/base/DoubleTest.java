package base;

/**
 * Created by cy111966 on 2016/11/21.
 */
public class DoubleTest {

  public static void main(String[] args) {
    double total =2.0;
    double price =0.1;
    double remaining = total;
    int count =0;
    while (remaining>=price){
      count++;
      remaining-=price;
    }
    System.out.println(count);
    System.out.println(remaining);
  }
}
