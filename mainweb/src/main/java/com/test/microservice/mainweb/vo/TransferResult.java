package com.test.microservice.mainweb.vo;

public class TransferResult {
	private String status;
	private int withdrawPort;
	private int depositPort;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getWithdrawPort() {
		return withdrawPort;
	}
	public void setWithdrawPort(int withdrawPort) {
		this.withdrawPort = withdrawPort;
	}
	public int getDepositPort() {
		return depositPort;
	}
	public void setDepositPort(int depositPort) {
		this.depositPort = depositPort;
	}
	
}
