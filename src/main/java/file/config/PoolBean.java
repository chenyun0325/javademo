package file.config;

public class PoolBean {
    private String poolname;

    private String pooltype;

    private String switchs;

    public String getSwitchs() {
        return switchs;
    }

    public void setSwitchs(String switchs) {
        this.switchs = switchs;
    }

    public String getPooltype() {
        return pooltype;
    }

    public void setPooltype(String pooltype) {
        this.pooltype = pooltype;
    }

    private int corepoolsize;

    private int maximunpoolsize;

    private int queuesize;

    public int getQueuesize() {
        return queuesize;
    }

    public void setQueuesize(int queuesize) {
        this.queuesize = queuesize;
    }

    private int keepAliveTime;

    private String unit;

    private String queueType;

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public int getCorepoolsize() {
        return corepoolsize;
    }

    public void setCorepoolsize(int corepoolsize) {
        this.corepoolsize = corepoolsize;
    }

    public int getMaximunpoolsize() {
        return maximunpoolsize;
    }

    public void setMaximunpoolsize(int maximunpoolsize) {
        this.maximunpoolsize = maximunpoolsize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

}
