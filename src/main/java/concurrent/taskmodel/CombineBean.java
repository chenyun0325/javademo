package concurrent.taskmodel;

public class CombineBean {
	private boolean isroot;//是否是根节点
	private boolean async;//方法执行是否需要委托--异步
	private String method;//任务的key,如：A.a,表示A类的a方法
	/**
	 * <service-component servicePackage="pz.earth.biz.pzstockpool"
		class="com.hsnet.pz.earth.biz.ao.impl.PZStockPoolAOImpl">
		<service-method serviceId="queryPZStockPoolList"
			method="queryPZStockPoolList" name="queryPZStockPoolList" alias="830510"
			version="1.0.0" description="分页查询股票池">
			<service-parameters>
				<service-parameter name="f830510Req"
					type="com.hsnet.pz.earth.biz.req.F830510Req" required="true" />
			</service-parameters>
			<service-result name="result" required="true" />
		</service-method>
       </service-component>
	 */
	private Object params;//方法运行参数
	private String desc;//方法描述
	private CombineBean parent;//父节点
	
	public boolean isAsync() {
		return async;
	}
	public void setAsync(boolean async) {
		this.async = async;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public CombineBean getParent() {
		return parent;
	}
	public void setParent(CombineBean parent) {
		this.parent = parent;
	}
	public void setIsroot(boolean isroot) {
		this.isroot = isroot;
	}
	public boolean isIsroot() {
		return isroot;
	}
	
	

}
