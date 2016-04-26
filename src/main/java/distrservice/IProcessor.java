package distrservice;

import java.util.Map;

public interface IProcessor {
	/**
	 * 统一处理接口
	 * @param transCode
	 * @param parmas
	 * @return
	 */
	String doProcessor(String transCode, Map<String, Object> parmas);

	/**
	 * 参数合法性校验
	 * @param params
	 * @return
	 */
	String init(String params);
	
	
}
