package jvm;

import java.util.HashMap;
import java.util.Map;

public class Mock {
    private static Map<String, Address> map = new HashMap<String, Address>();

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (i < 1000000) {
            Address address = new Address();
            address.setZip(i);
            address.setNumber("xfdsafdasdfdfsaxxxxxxxxxx" + i);
            map.put("key" + i, address);
            i++;
            Thread.sleep(1);
        }

    }

}
