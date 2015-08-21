package com.ibm.bigdata.simulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LataGenerator {

	public static void gernBigFile(String ipAddrDir, long numberOfLine) {
		
		int nBaseLines = 100000000;
		int numOfFiles = (int) (numberOfLine / nBaseLines);

		String fileName = ipAddrDir + "0.log";
		if (numOfFiles == 0) {
			generateBatchFile(new File(fileName), (int) numberOfLine);
		} else {
			for (int index = 0; index <= numOfFiles; ++index) {
				fileName = ipAddrDir + index + ".log";
				int numOfLines = nBaseLines;
				if (index == numOfFiles) {
					numOfLines = (int) (numberOfLine % nBaseLines);
				}
				Thread thread = new Thread(new LogService(fileName, numOfLines));
				thread.start();

			}
		}
	}

	static class LogService implements Runnable {
		String fileName;
		int numOfLines;

		public LogService(String fileName, int numOfLines) {
			this.fileName = fileName;
			this.numOfLines = numOfLines;
		}

		@Override
		public void run() {
			LataGenerator.generateBatchFile(new File(fileName), numOfLines);
		}

	}

	/**
	 * 生成海量数据的大文件
	 * 
	 * @param ipFile
	 * @param numberOfLine
	 */
	public static void generateBatchFile(File ipFile, int numberOfLine) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		long startTime = System.currentTimeMillis();

		try {
			fw = new FileWriter(ipFile, true);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < numberOfLine; i++) {
				// 总数据为1亿个IP数据
				AccessLoger accessLoger = new AccessLoger();
				bw.write(accessLoger.getLogModel().toString());
				if ((i + 1) % 1000 == 0) {
					bw.flush();
				}
			}
			bw.flush();

			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime) / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
