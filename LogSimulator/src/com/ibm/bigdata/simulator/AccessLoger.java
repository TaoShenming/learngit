package com.ibm.bigdata.simulator;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccessLoger {

	public LogModel getLogModel() {
		//
		LogModel logModel = new LogModel();

		String remoteAddr = getRemoteAddr();
		logModel.setRemoteAddr(remoteAddr);

		logModel.setRemoteUser(getRemoteUser());

		logModel.setLocalTime(getRandomDate());

		logModel.setRequestHeader(getRequestHeader());

		logModel.setResponseCode(getResponseCode());

		logModel.setReference(getReference());

		logModel.setBodyBytes(getBodyBytes());

		logModel.setAgent(getRandomAgent());

		return logModel;
	}

	private String getRemoteAddr() {
		int ipp = 0;
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < 4; ++index) {
			Random random = new Random();
			ipp = random.nextInt(256);
			builder.append(ipp);
			if (index < 3) {
				builder.append(".");
			}
		}
		return builder.toString();
	}

	private String getRemoteUser() {
		return null;
	}

	private Date getRandomDate() {
		long time = System.currentTimeMillis();
		Random random = new Random();
		long dec = random.nextInt();

		return new Date(time + dec);
	}

	private String getRequestHeader() {

		String method, url, httpflag = null;
		String[] methodArr = new String[] { "POST", "GET", "PUT", "DELETE" };

		Set<String> pages = new HashSet<String>();
		pages.add("/about");
		pages.add("/black-ip-list/");
		pages.add("/cassandra-clustor/");
		pages.add("/finance-rhive-repurchase/");
		pages.add("/hadoop-family-roadmap/");
		pages.add("/hadoop-hive-intro/");
		pages.add("/hadoop-zookeeper-intro/");
		pages.add("/hadoop-mahout-roadmap/");
		//
		String[] urlArr = new String[pages.size()];
		pages.toArray(urlArr);

		Random random = new Random();
		method = methodArr[random.nextInt(4)];
		url = urlArr[random.nextInt(10000) % urlArr.length];

		httpflag = "HTTP/1.1";

		return method + " " + url + " " + httpflag;
	}

	private int getResponseCode() {
		//
		return 200;
	}

	private String getReference() {
		return "www.baidu.com";
	}

	private int getBodyBytes() {
		int nbytes = new Random().nextInt();
		return nbytes > 0 ? nbytes : -nbytes;
	}

	private String getRandomAgent() {
		//
		Set<String> pages = new HashSet<String>();
		pages.add("Mozilla/5.0 (Windows NT 6.1)");
		pages.add("IE/10");
		pages.add("Google");
		pages.add("Baidu Spider");

		//
		String[] urlArr = new String[pages.size()];
		pages.toArray(urlArr);

		Random random = new Random();
		String agent = urlArr[random.nextInt(10000) % urlArr.length];

		return agent;
	}

	public static void main(String[] args) {
		AccessLoger accessLoger = new AccessLoger();
		System.out.println(accessLoger.getLogModel());
	}
}
