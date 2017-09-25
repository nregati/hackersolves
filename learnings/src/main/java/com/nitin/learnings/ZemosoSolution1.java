/*
 * Copyright (c) 2017. [Author nitin_regati] [File ZemosoSolution1.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.nitin.learnings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nitin_regati on 09/09/17
 */
public class ZemosoSolution1 {

	public static void main(String[] args) {
		int[] A = new int[]{0, 3, 3, 7, 5, 3, 11, 1};
		//		int[] A = new int[]{1,4,7,3,3,5};
		ZemosoSolution1 zemosoSolution1 = new ZemosoSolution1();
		System.out.println(zemosoSolution1.solution(A));
	}

	public int solution(int[] A) {

		if (A.length == 1) {
			return -1;
		}

		List<Integer> valueList = Arrays.stream(A).boxed().collect(Collectors.toList());
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (!inBetween(A[i], A[j], valueList)) {
					max = Math.max(max, Math.abs(j - i));
				}

			}
		}
		return max;

		//		Map<Integer, List<Integer>> valueToIndeXMap = new HashMap<>();
		//		for (int i = 0; i < A.length; i++) {
		//			List<Integer> indexList = valueToIndeXMap.get(A[i]);
		//			if (null == indexList) {
		//				indexList = new ArrayList<>();
		//			}
		//			indexList.add(i);
		//			Collections.sort(indexList);
		//			valueToIndeXMap.put(A[i], indexList);
		//		}
		//
		//		Arrays.sort(A);
		//
		//		int max = -1;
		//		for (int i = 0; i < A.length; i++) {
		//			int j = i + 1;
		//			if (j == A.length)
		//				break;
		//			if (A[j] == A[i])
		//				continue;
		//			List<Integer> iInd = valueToIndeXMap.get(A[i]);
		//			int indMin = 0;
		//			int indMax = 0;
		//			int jndMin = 0;
		//			int jndMax = 0;
		//			if (iInd.size() > 1) {
		//				indMin = iInd.get(0);
		//				indMax = iInd.get(iInd.size()-1);
		//			} else
		//				indMax = iInd.get(0);
		//			List<Integer> jInd = valueToIndeXMap.get(A[j]);
		//			if (jInd.size() > 1) {
		//				jndMin = jInd.get(0);
		//				jndMax = jInd.get(jInd.size()-1);
		//			}
		//			max = Math.max(max, (Math.abs(Math.max(jndMax, indMax) - Math.min(jndMin, indMin))));
		//		}
		//		return max;
	}

	public boolean inBetween(int a, int b, List<Integer> list) {
		int low = 0;
		int high = 0;
		if (a < b) {
			low = a;
			high = b;
		} else {
			low = b;
			high = a;
		}
		for (int i = low + 1; i < high; i++) {
			if (list.contains(i)) {
				return true;
			}
		}
		return false;
	}

}
