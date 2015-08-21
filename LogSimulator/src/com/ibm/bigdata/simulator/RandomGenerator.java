package com.ibm.bigdata.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

	// 最大类型数
	private final static int MAX_TOKEN_STATE = 100;
	
	/**
	 * 1.输入最大100个元素的数组，每个数组表示每类占有的百分比，内部自带百分比调整。    
	 * 2.即如果外部输入的数字之和不是整数100，内部会根据百分比，自动调整其比例，使总和=100.0    
	 * 3.然后内部建立10000个单元的类型数组，根据传入的每种类型的比例，在类型数组中批量填充对应的类型值    
	 * 4.总之，类型数组中每种类型的数量，占据的比例正好是输入的百分比    
	 * 5.最后，在0~10000中取随机，然后在对应的类型数组单元中取类型值，即为返回的类型    
	 */
	
	public class State {
		//
		private int oid;
		private double ratio;
		
		public State() {
		}
		
		public State(int oid, double ratio) {
			this.oid = oid;
			this.ratio = ratio;
		}

		public int getOid() {
			return oid;
		}
		public void setOid(int oid) {
			this.oid = oid;
		}
		public double getRatio() {
			return ratio;
		}
		public void setRatio(double ratio) {
			this.ratio = ratio;
		}
	}
	
	private List<State> discreteStateArray = new ArrayList<State>();
	
	public RandomGenerator() {
		// default
	}
	
	public RandomGenerator(double[] tokenRatios, int nVaid) {
		
		if (tokenRatios.length > MAX_TOKEN_STATE || nVaid > 3) {
			throw new RuntimeException("invalid params");
		}
		
		int enlarge = (int) Math.pow(10, nVaid);
		
		double sumratio = 0.0;
		for (int index = 0; index < tokenRatios.length; ++index) {
			sumratio += tokenRatios[index] * enlarge;
		}
		
		for (int index = 0; index < tokenRatios.length; ++index) {
			//
			tokenRatios[index] *= enlarge;
			//
			if (Math.abs(sumratio - enlarge) > 1e-5) {
				tokenRatios[index] /= sumratio;
			}
		}
		
		//
		
		
		int start = 0, end = start;
		for (int index = 0; index < tokenRatios.length; ++index) {
			start = (int)(tokenRatios[index]) * index;
			end = (int)(tokenRatios[index]) * (index + 1);
			for (int it = start; it < end; ++it) {
				discreteStateArray.add(new State(index, tokenRatios[index]));
			}
		}
		// 
	}
	
	public int getRandomState() {
		//
		Random random = new Random();
		int index = random.nextInt(discreteStateArray.size());
		//
		return discreteStateArray.get(index).getOid();
	}
	
	public static void main(String[] args) {
		
		double[] cases = {0.12, 0.40, 0.30, 0.07, 0.11};
		RandomGenerator generator = new RandomGenerator(cases, 2);
		
		int[] statics = new int[cases.length];
		for (int i = 0; i < statics.length; i++) {
			statics[i] = 0;
		}
		
		int nCases = 1000;
		for (int index = 0; index < nCases; ++index) {
			int state = generator.getRandomState();
			statics[state]++;
		}
		//
		for (int i = 0; i < statics.length; i++) {
			System.out.println((statics[i] + 0.0) / nCases);
		}
	}
}
