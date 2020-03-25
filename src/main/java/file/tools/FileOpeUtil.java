package file.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOpeUtil {

    public static void main(String[] args) {
        String path = "cmds.txt";
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add("cmd" + i);
        }
        writeFile(path, list);
        List resList = readFile(path);
        System.err.println(resList.get(1));
    }

    private FileOpeUtil() {
    }

    public static List readFile(String path) {
        List list = new ArrayList();
        ObjectInputStream oin = null;
        try {
            File f = new File(path);
            if (f.exists()) {
                oin = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(f)));
                list = (List) oin.readObject();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (oin != null) {
                    oin.close();
                }
            } catch (IOException e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }

        return list;
    }

    public static void writeFile(String pathname, List list) {
        File file = new File(pathname);
        ObjectOutputStream oout = null;
        try {
            oout = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(file)));
            oout.writeObject(list);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                oout.close();
            } catch (IOException e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }

}
