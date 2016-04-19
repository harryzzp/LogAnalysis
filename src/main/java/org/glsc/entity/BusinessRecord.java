package org.glsc.entity;

import java.io.Serializable;

public class BusinessRecord implements Serializable {
	
	private static final long serialVersionUID = -4664230567124694620L;

	private String clientId;
	
	private String clientName;
	
	private String salesDept;
	
	private String businessName;
	
	private String summary;
	
	private String assignee;
	
	private String assigneeDept;
	
	private String assigneeUserId;
	
	private String assigneeTime;
	
	private String witness;
	
	private String witnessDept;
	
	private String witnessUserId;
	
	private String witnessTime;
	
	private String witnessWay;
	
	private String transactor;
	
	private String transactorDept;
	
	private String transactorUserId;
	
	private String transactorTime;
	
	private String reviewer;
	
	private String reviewerDept;
	
	private String reviewerUserId;
	
	private String reviewerTime;
	
	private String processStatus;
	
	private String processResult;
	
	private String isOffsite;
	
	private String businessSource;
	
//	private String imageArchive;

	public BusinessRecord(String clientId, String clientName, String salesDept, String businessName, String summary,
			String assignee, String assigneeDept, String assigneeUserId, String assigneeTime, String witness,
			String witnessDept, String witnessUserId, String witnessTime, String witnessWay, String transactor,
			String transactorDept, String transactorUserId, String transactorTime, String reviewer, String reviewerDept,
			String reviewerUserId, String reviewerTime, String processStatus, String processResult, String isOffsite,
			String businessSource) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.salesDept = salesDept;
		this.businessName = businessName;
		this.summary = summary;
		this.assignee = assignee;
		this.assigneeDept = assigneeDept;
		this.assigneeUserId = assigneeUserId;
		this.assigneeTime = assigneeTime;
		this.witness = witness;
		this.witnessDept = witnessDept;
		this.witnessUserId = witnessUserId;
		this.witnessTime = witnessTime;
		this.witnessWay = witnessWay;
		this.transactor = transactor;
		this.transactorDept = transactorDept;
		this.transactorUserId = transactorUserId;
		this.transactorTime = transactorTime;
		this.reviewer = reviewer;
		this.reviewerDept = reviewerDept;
		this.reviewerUserId = reviewerUserId;
		this.reviewerTime = reviewerTime;
		this.processStatus = processStatus;
		this.processResult = processResult;
		this.isOffsite = isOffsite;
		this.businessSource = businessSource;
//		this.imageArchive = imageArchive;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSalesDept() {
		return salesDept;
	}

	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeDept() {
		return assigneeDept;
	}

	public void setAssigneeDept(String assigneeDept) {
		this.assigneeDept = assigneeDept;
	}

	public String getAssigneeUserId() {
		return assigneeUserId;
	}

	public void setAssigneeUserId(String assigneeUserId) {
		this.assigneeUserId = assigneeUserId;
	}

	public String getAssigneeTime() {
		return assigneeTime;
	}

	public void setAssigneeTime(String assigneeTime) {
		this.assigneeTime = assigneeTime;
	}

	public String getWitness() {
		return witness;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}

	public String getWitnessDept() {
		return witnessDept;
	}

	public void setWitnessDept(String witnessDept) {
		this.witnessDept = witnessDept;
	}

	public String getWitnessUserId() {
		return witnessUserId;
	}

	public void setWitnessUserId(String witnessUserId) {
		this.witnessUserId = witnessUserId;
	}

	public String getWitnessTime() {
		return witnessTime;
	}

	public void setWitnessTime(String witnessTime) {
		this.witnessTime = witnessTime;
	}

	public String getWitnessWay() {
		return witnessWay;
	}

	public void setWitnessWay(String witnessWay) {
		this.witnessWay = witnessWay;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getTransactorDept() {
		return transactorDept;
	}

	public void setTransactorDept(String transactorDept) {
		this.transactorDept = transactorDept;
	}

	public String getTransactorUserId() {
		return transactorUserId;
	}

	public void setTransactorUserId(String transactorUserId) {
		this.transactorUserId = transactorUserId;
	}

	public String getTransactorTime() {
		return transactorTime;
	}

	public void setTransactorTime(String transactorTime) {
		this.transactorTime = transactorTime;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewerDept() {
		return reviewerDept;
	}

	public void setReviewerDept(String reviewerDept) {
		this.reviewerDept = reviewerDept;
	}

	public String getReviewerUserId() {
		return reviewerUserId;
	}

	public void setReviewerUserId(String reviewerUserId) {
		this.reviewerUserId = reviewerUserId;
	}

	public String getReviewerTime() {
		return reviewerTime;
	}

	public void setReviewerTime(String reviewerTime) {
		this.reviewerTime = reviewerTime;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getIsOffsite() {
		return isOffsite;
	}

	public void setIsOffsite(String isOffsite) {
		this.isOffsite = isOffsite;
	}

	public String getBusinessSource() {
		return businessSource;
	}

	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}

//	public String getImageArchive() {
//		return imageArchive;
//	}
//
//	public void setImageArchive(String imageArchive) {
//		this.imageArchive = imageArchive;
//	}

	@Override
	public String toString() {
		return "BusinessRecord [clientId=" + clientId + ", clientName=" + clientName + ", salesDept=" + salesDept
				+ ", businessName=" + businessName + ", summary=" + summary + ", assignee=" + assignee
				+ ", assigneeDept=" + assigneeDept + ", assigneeUserId=" + assigneeUserId + ", assigneeTime="
				+ assigneeTime + ", witness=" + witness + ", witnessDept=" + witnessDept + ", witnessUserId="
				+ witnessUserId + ", witnessTime=" + witnessTime + ", witnessWay=" + witnessWay + ", transactor="
				+ transactor + ", transactorDept=" + transactorDept + ", transactorUserId=" + transactorUserId
				+ ", transactorTime=" + transactorTime + ", reviewer=" + reviewer + ", reviewerDept=" + reviewerDept
				+ ", reviewerUserId=" + reviewerUserId + ", reviewerTime=" + reviewerTime + ", processStatus="
				+ processStatus + ", processResult=" + processResult + ", isOffsite=" + isOffsite + ", businessSource="
				+ businessSource + "]";
	}

}
