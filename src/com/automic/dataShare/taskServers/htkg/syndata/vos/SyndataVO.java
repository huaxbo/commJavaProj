package com.automic.dataShare.taskServers.htkg.syndata.vos;

public class SyndataVO {

	private String id;//��¼id
	private String projectId;//����豸id
	private Double accumuflux;//�ۼ�����
	private String tm;//�ϱ�ʱ��
	
	
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
