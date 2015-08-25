package com.automic.dataShare.taskServers.htkg.syndata.vos;

public class SyndataVO {

	private String id;//记录id
	private String projectId;//测控设备id
	private Double accumuflux;//累计流量
	private String tm;//上报时间
	
	
	/**
	 * @param id
	 * @param projectId
	 * @param accumuflux
	 * @param tm
	 */
	public SyndataVO(String id,String projectId,
			Double accumuflux,String tm){
		this.id = id;
		this.projectId = projectId;
		this.accumuflux = accumuflux;
		this.tm = tm;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Double getAccumuflux() {
		return accumuflux;
	}
	public void setAccumuflux(Double accumuflux) {
		this.accumuflux = accumuflux;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	
	
}
