/**
 * 文件名：ConnectParam.java
 *
 * 版本信息：
 * 日期：2014-6-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.io.Serializable;

/**
 * 
 * 项目名称：portal 02
 * 类名称：ConnectParam
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-11 下午10:27:47
 * 修改人：chenyun
 * 修改时间：2014-6-11 下午10:27:47
 * 修改备注：
 * @version 
 * 
 */
public class ConnectParam implements Serializable {
	private String driver;//驱动
	private String url;//数据库连接地址
	private String username;
    private String password;
    private int minconn;//最小连接数
    private int maxconn;//最大连接数
    private String poolname;//连接池名称
    private long timeout;//失效时间
    private long waitime;//最长等待时间
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMinconn() {
		return minconn;
	}
	public void setMinconn(int minconn) {
		this.minconn = minconn;
	}
	public int getMaxconn() {
		return maxconn;
	}
	public void setMaxconn(int maxconn) {
		this.maxconn = maxconn;
	}
	public String getPoolname() {
		return poolname;
	}
	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public long getWaitime() {
		return waitime;
	}
	public void setWaitime(long waitime) {
		this.waitime = waitime;
	}
    
}
