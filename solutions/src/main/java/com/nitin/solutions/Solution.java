/*
 * Copyright (c) 2017. [Author nitin_regati] [File Solution.java]
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

package com.nitin.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nitin_regati on 27/09/17
 */
public class Solution {

	public static void main(String[] args) {

	}

	public List<Integer> getElem(List<Integer> list, int N) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> returnList = new ArrayList<>();
		for(int ele : list){
			Integer valCounter = map.get(ele);
			if(null == valCounter){
				map.put(ele, 1);
			} else {
				valCounter++;
				map.put(ele, valCounter);
			}
		}
		for(Map.Entry<Integer, Integer> entrySet : map.entrySet()){
			if(entrySet.getValue() == N)
				returnList.add(entrySet.getKey());
		}

		return returnList;
	}

	public boolean dupeFinder(Integer[] arr) {
		int size = arr.length;
		Set<Integer> set = new HashSet<>(Arrays.asList(arr));
		return set.size() != size;
	}

}
