package com.ibm.bigdata.simulator;
import java.io.File;

public class LataGenratorTest {

	public static void main(String[] args) {
		String ipAddrPath = "D:/ipAddr/";
		File addrDir = new File(ipAddrPath);
		if (!addrDir.exists()) {
			addrDir.mkdirs();
		}
		//
		LataGenerator.gernBigFile(ipAddrPath, 1000000000);
	}
}
