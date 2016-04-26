package distrservice;

import java.util.Map;


public class Processor830001 implements IProcessor {

	@Override
	public String doProcessor(String transCode, Map<String, Object> parmas) {
		// TODO Auto-generated method stub
		System.err.println(transCode);
		System.err.println(parmas);
		return transCode;
	}

	@Override
	public String init(String params) {
		// TODO Auto-generated method stub
		return "xx";
	}

}
