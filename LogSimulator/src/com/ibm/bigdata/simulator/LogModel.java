package com.ibm.bigdata.simulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogModel {

	private String remoteAddr;
	private String remoteUser;
	private Date localTime;
	private String requestHeader;
	
	private int responseCode;
	private int bodyBytes;
	
	private String reference;
	private String agent;

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public Date getLocalTime() {
		return localTime;
	}

	public void setLocalTime(Date localTime) {
		this.localTime = localTime;
	}

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getBodyBytes() {
		return bodyBytes;
	}

	public void setBodyBytes(int bodyBytes) {
		this.bodyBytes = bodyBytes;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(remoteAddr);
		//
		remoteUser = (remoteUser == null) ? " " : remoteUser;
		builder.append(" " + "-" + remoteUser + "-");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss",
				Locale.US);
		builder.append(" " + "[" +sdf.format(localTime) + "]");
		builder.append(" " + "\"" + requestHeader + "\"");
		builder.append(" " + responseCode);
		builder.append(" " + bodyBytes);
		builder.append(" " + reference);
		builder.append(" " + "\"" + agent + "\"" + "\n");
		
		return builder.toString();
	}
}
