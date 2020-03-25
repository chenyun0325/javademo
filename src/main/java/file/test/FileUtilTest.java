package file.test;

import file.tools.ConfigurationHelper;

import java.io.*;

public class FileUtilTest {

    public static void main(String[] args) {
        String path = "classpath:config/config.properties";
        ConfigurationHelper configHelper = new ConfigurationHelper();
        File file = configHelper.loadFile(path, "utf-8");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            // BufferedInputStream bufferedInputStream=new
            // BufferedInputStream(inputStream);
            /*
             * FileReader fileReader = new FileReader(file); BufferedReader
             * bfreader = new BufferedReader(fileReader);
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
