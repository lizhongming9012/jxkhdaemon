package org.jxkh.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
	/**
	 * 取得0-maxN-1之间不重复的acquireN个数的数组（利用set无序的，对象不可以重复和list有序的对象可以重复的特性，使用Random类来实现的）
	 * 
	 * @param acquireN
	 *            要取得的数量；
	 * @param maxN
	 *            取数范围的最大数即0-maxN-1；
	 * @return
	 */
	@SuppressWarnings("unused")
	public static int[] getRandomXuhao(int acquireN, int maxN) {

		int[] xhSet = new int[acquireN];
		if (acquireN > maxN) {
			return null;
		}
		try {
			Set<String> iSet = new HashSet<String>();//无序的，对象不可以重复
			List<String> list = new ArrayList<String>();//有序的对象可以重复
			Random rdmRandom = new Random();
			while (list.size() < acquireN) {
				int i = rdmRandom.nextInt(maxN);
				if (iSet.add(i + "")) {
					list.add(i + "");
				}
			}
			Iterator<String> it = list.iterator();
			int j = 0;
			while (it.hasNext()) {
				xhSet[j] = Integer.parseInt(it.next().toString());
				j++;
			}
			return xhSet;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("抽取意外！");
			return null;
		}
	}

}
