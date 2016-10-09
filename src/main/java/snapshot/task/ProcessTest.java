package snapshot.task;

import java.io.*;

/**
 * Created by cy111966 on 2016/7/21.
 */
public class ProcessTest {

    public static void main(String[] args) {

	try {
	    for (int i = 0; i < 1000; i++) {

		new Thread(new task("c:\\IECapt.exe", "https://item.taobao.com/item.htm?id=534354362822",
			"c:/snapshot/" + i + ".png")).start();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    static class task implements Runnable {
	private String cmd;
	private String url;
	private String imgPath;

	public task(String cmd, String url, String imgPath) {
	    this.cmd = cmd;
	    this.url = url;
	    this.imgPath = imgPath;
	}

	@Override
	public void run() {
	    // String cmd =
	    // "c:\\IECapt.exe https://www.baidu.com/ c:/snapshot/wy.png";
	    String imgCmd = cmd + " " + url + " " + imgPath;
	    try {
		Process exec = Runtime.getRuntime().exec(imgCmd);
		int waitFor = exec.waitFor();
		printMessage(exec.getInputStream());
		printMessage(exec.getErrorStream());
		System.out.println("wait:" + waitFor);
		int exitValue = exec.exitValue();
		System.out.println("exit:" + exitValue);
		// exec.destroy();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	private static void printMessage(final InputStream input) {
	    new Thread(new Runnable() {
		public void run() {
		    Reader reader = new InputStreamReader(input);
		    BufferedReader bf = new BufferedReader(reader);
		    String line = null;
		    try {
			while ((line = bf.readLine()) != null) {
			    System.out.println(line);
			}
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    }).start();
	}
    }
}
