/*
 * Copyright (c) 2017. [Author nitin_regati] [File DecisionTrees.java]
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

import java.io.File;
import java.net.URL;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Created by nitin_regati on 19/08/17
 */
public class DecisionTrees {

	public static void main(String[] args) {

		DecisionTrees trees = new DecisionTrees();
		trees.importData();
	}

	public void importData() {

		String fileName = "Social_Network_Ads.csv";
		URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
		if (null != url) {
			File dataFile = new File(url.getFile());
			try {
				DataSource dataSource = new DataSource(dataFile.getAbsolutePath());
				Instances data = dataSource.getDataSet();
				System.out.println(data);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
