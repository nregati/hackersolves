import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

/**
 * Created by nitin_regati on 01/05/18
 */
public class DTree1 {

	//Index , Class
	private Map<Integer, Integer> classDetails;
	private double[][] matrix;

	class Tree {

		Tree left_child;
		Tree right_child;
		int best_attribute;
		double best_threshold;
		//Class,Distribution
		Map<Integer, Double> distribution;
		double gain;
		int nodeId;
	}

	private DTree1(Map<Integer, Integer> classDetails, double[][] matrix) {
		this.classDetails = classDetails;
		this.matrix = matrix;
	}

	//Class,Distribution
	private Tree dLT(List<Integer> exampleList, List<Integer> attributes,
			Map<Integer, Double> distribution, boolean random) {

		if (exampleList.size() < 50) {

			return getBasicTree(distribution);
		} else {

			boolean flag = false;
			int length = matrix[0].length;
			int oldClass = (int) matrix[exampleList.get(0)][length - 1];

			for (int s = 1; s < exampleList.size(); s++) {
				if (matrix[exampleList.get(s)][length - 1] == oldClass) {
					flag = true;
					oldClass = (int) matrix[exampleList.get(s)][length - 1];
				} else {

					flag = false;
					break;
				}
			}

			if (flag) {
				Tree tree = new Tree();
				tree.best_attribute = -1;
				tree.best_threshold = -1;
				tree.gain = -1;
				for (int i = 0; i < classDetails.size(); i++) {
					if (oldClass == classDetails.get(i)) {
						distribution.put(classDetails.get(i), 1.0);
					} else {
						distribution.put(classDetails.get(i), 0.0);
					}
				}
				tree.distribution = distribution;
				return tree;
			} else {
				List<Double> list = chooseAttributes(exampleList, attributes, random);
				int bestAttribute = (list.get(0)).intValue();
				double bestThreshold = list.get(1);
				double currGain = list.get(2);

				Tree tree = new Tree();

				tree.best_attribute = bestAttribute - 1;
				tree.best_threshold = bestThreshold;
				tree.gain = currGain;

				List<Integer> examples_left = new ArrayList<>();
				List<Integer> examples_right = new ArrayList<>();

//				exampleMaker(exampleList, bestAttribute, bestThreshold, examples_left, examples_right);

				for (Integer anExampleList : exampleList) {
					if (matrix[anExampleList][bestAttribute - 1] < bestThreshold) {
						examples_left.add(anExampleList);
					} else if (matrix[anExampleList][bestAttribute - 1] >= bestThreshold) {
						examples_right.add(anExampleList);
					}
				}

				Map<Integer, Double> newDistribution = distribution(exampleList);

				tree.left_child = dLT(examples_left, attributes, newDistribution, random);
				tree.right_child = dLT(examples_right, attributes, newDistribution, random);

				return tree;
			}
		}
	}

	private void exampleMaker(List<Integer> exampleList, int bestAttribute, double bestThreshold,
			List<Integer> examples_left, List<Integer> examples_right) {

	}

	private List<Double> chooseAttributes(List<Integer> examples,
			List<Integer> attributes, boolean random) {

		//Attribute to values mapping of examples, to find min and max element
		Map<Integer, List<Double>> attr_val_map = new HashMap<>();

		for (Integer example : examples) {
			for (int j = 0; j < attributes.size(); j++) {
				double attr_val = matrix[example][j];
				List<Double> valList = attr_val_map.get(j + 1);
				if (null == valList) {
					valList = new ArrayList<>();
				}
				valList.add(attr_val);
				attr_val_map.put(j + 1, valList);
			}
		}

		List<Double> list = new ArrayList<>();
		if (random) {
			Random rand = new Random();
			int A = attributes.get(rand.nextInt(attributes.size()));
			chooseAttributeHelper(examples, A, attr_val_map, list);
		} else {
			for (int A = 1; A <= attributes.size(); A++) {
				chooseAttributeHelper(examples, A, attr_val_map, list);
			}
		}
		return list;
	}

	private void chooseAttributeHelper(List<Integer> examples, int A,
			Map<Integer, List<Double>> attr_val_map, List<Double> list) {

		double max_gain = -1;
		double best_attribute;
		double best_threshold;
		//Get the values of attribute and sort it
		List<Double> valList = attr_val_map.get(A);
		Collections.sort(valList);
		double L = valList.get(0);
		double M = valList.get(valList.size() - 1);
		double gain;
		for (int K = 1; K <= 50; K++) {

			double threshold = L + ((K * (M - L)) / 51);

			gain = information_gain(examples, A, threshold);

			if (gain > max_gain) {
				list.clear();
				best_attribute = A;
				best_threshold = threshold;
				max_gain = gain;
				list.add(best_attribute);
				list.add(best_threshold);
				list.add(max_gain);
			}

		}
	}


	private double information_gain(List<Integer> examples, int A, double threshold) {
		List<Integer> examples_left = new ArrayList<>();
		List<Integer> examples_right = new ArrayList<>();

		exampleMaker(examples, A, threshold, examples_left, examples_right);

		//Split the examples class wise

		//Class,Corresponding example array
		Map<Integer, List<Integer>> classExm = new HashMap<>();

		//Length with class
		int length = matrix[examples.get(0)].length;

		gainHelper(examples, classExm, length);

		//Split the examples_left class wise

		//Class,Corresponding example array
		Map<Integer, List<Integer>> classExm_left = new HashMap<>();

		gainHelper(examples_left, classExm_left, length);

		//Split the examples_right class wise

		//Class,Corresponding example array
		Map<Integer, List<Integer>> classExm_right = new HashMap<>();

		gainHelper(examples_right, classExm_right, length);

		//Calculate size
		double exampleSize = examples.size();
		double exampleSizeLeft = examples_left.size();
		double exampleSizeRight = examples_right.size();

		//Initialize Entropy
		double entropy_node = 0.0;
		double entropy_left = 0.0;
		double entropy_right = 0.0;

		//Calculate entropy for node N

		for (int i = 0; i < classDetails.size(); i++) {

			if (classExm.get(classDetails.get(i)) != null) {
				double classSize = classExm.get(classDetails.get(i)).size();
				entropy_node = entropy_node - (((classSize / exampleSize)) * (
						Math.log((classSize / exampleSize)) / Math.log(2.0d)));

			}
		}

		//Calculate the entropy for class_left
		for (int i = 0; i < classDetails.size(); i++) {
			if (classExm_left.get(classDetails.get(i)) != null) {
				double classSize = classExm_left.get(classDetails.get(i)).size();
				entropy_left = entropy_left - (((classSize / exampleSizeLeft) * (
						Math.log(classSize / exampleSizeLeft) / Math.log(2.0d))));
			}

		}

		//Calculate the entropy for class_right
		for (int i = 0; i < classDetails.size(); i++) {
			if (classExm_right.get(classDetails.get(i)) != null) {
				double classSize = classExm_right.get(classDetails.get(i)).size();
				entropy_right = entropy_right - (((classSize / exampleSizeRight) * (
						Math.log(classSize / exampleSizeRight) / Math.log(2.0d))));
			}

		}

		//Calculating information gain
		return entropy_node - (((exampleSizeLeft / exampleSize) * entropy_left)
				+ ((exampleSizeRight / exampleSize) * entropy_right));
	}

	private void gainHelper(List<Integer> examples, Map<Integer, List<Integer>> classExm,
			int length) {
		for (Integer example : examples) {
			int currClass = (int) matrix[example][length - 1];
			if (classExm.get(currClass) == null) {
				List<Integer> exm = new ArrayList<>();
				exm.add(example);
				classExm.put(currClass, exm);
			} else {
				List<Integer> exm = classExm.get(currClass);
				exm.add(example);
				classExm.put(currClass, exm);
			}
		}
	}

	private Map<Integer, Double> distribution(
			List<Integer> exampleList) {   //Class,Distribution
		Map<Integer, Double> distribution = new HashMap<>();
		//Class,Corresponding example array
		Map<Integer, List<Integer>> distributionSum = new HashMap<>();

		//Length with class
		int length = matrix[exampleList.get(0)].length;

		for (int i = 0; i < classDetails.size(); i++) {
			List<Integer> exm = new ArrayList<>();
			distributionSum.put(classDetails.get(i), exm);
		}

		for (Integer anExampleList : exampleList) {
			int currClass = (int) matrix[anExampleList][length - 1];

			{
				List<Integer> exm = distributionSum.get(currClass);
				exm.add(anExampleList);
				distributionSum.put(currClass, exm);
			}
		}

		double exampleSize = exampleList.size();

		for (int i = 0; i < distributionSum.size(); i++) {
			if (distributionSum.get(classDetails.get(i)) != null) {
				int size = distributionSum.get(classDetails.get(i)).size();
				double distrb = size / exampleSize;
				distribution.put(classDetails.get(i), distrb);
			} else {
				distribution.put(classDetails.get(i), 0.0);
			}
		}

		return distribution;

	}

	//To display the result
	private void displayTrainingResult(Tree tree, int treeId) {

		Queue<Tree> queue = new LinkedList<>();
		Tree current = tree;
		current.nodeId = 1;
		queue.add(current);
		while (!queue.isEmpty()) {

			current = queue.poll();
			if (current != null) {
				System.out
						.printf("tree=%2d, node=%3d, feature=%2d, thr=%6.2f, gain=%f\n", treeId, current.nodeId,
								current.best_attribute, current.best_threshold, current.gain);
				if (current.left_child != null) {
					current.left_child.nodeId = current.nodeId * 2;
					queue.add(current.left_child);

				}

				if (current.right_child != null) {
					current.right_child.nodeId = (current.nodeId * 2) + 1;
					queue.add(current.right_child);
				}
			}
		}
	}

	//Classification
	//Optimized

	private void optimizedRanClassification(Tree tree, List<Integer> examples_test,
			double[][] matrix_test, List<Integer> attributes) {

		double totalAcc = 0;
		double[][] classificationArr = new double[examples_test.size()][4];

		for (int i = 0; i < examples_test.size(); i++) {
			Tree curr = tree;

			while (curr.best_attribute != -1) {
				double thr = curr.best_threshold;
				int attr = curr.best_attribute;

				if (matrix_test[i][attr] < thr) {
					curr = curr.left_child;
				} else {
					curr = curr.right_child;
				}

			}

			Map<Integer, Double> dist = curr.distribution;
			double max = -1.0;
			double maxclass = -1;
			int count = 0;

			for (int d = 0; d < dist.size(); d++) {
				if (dist.get(classDetails.get(d)) >= max) {
					max = dist.get(classDetails.get(d));
					maxclass = classDetails.get(d);
				}
			}

			for (int d = 0; d < dist.size(); d++) {
				if (dist.get(classDetails.get(d)) == max) {
					count = count + 1;
				}
			}
			int acc = 0;

			if (maxclass == matrix_test[i][attributes.size()]) {
				acc = 1 / count;
			}

			totalAcc = totalAcc + acc;

			classificationArr[i][0] = i;
			classificationArr[i][1] = maxclass;
			classificationArr[i][2] = matrix_test[i][attributes.size()];
			classificationArr[i][3] = acc;
		}

		for (double[] aClassificationArr : classificationArr) {
			System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4.2f\n",
					(int) aClassificationArr[0],
					(int) aClassificationArr[1], (int) aClassificationArr[2], aClassificationArr[3]);

		}

		System.out.printf("classification accuracy=%6.4f\n", totalAcc / examples_test.size());
	}


	//Classification
	//Random 3
	private void RanClassification3(List<Tree> list, List<Integer> examples_test,
			double[][] matrix_test, List<Integer> attributes) {
		// treeID, Collection of Distribution for all objects
		Map<Integer, Map<Integer, Map<Integer, Double>>> distributionMap = new HashMap<>();

		for (int t = 0; t < list.size(); t++) {
			Tree tree = list.get(t);
			Map<Integer, Map<Integer, Double>> distributionList = new HashMap<>();

			for (int i = 0; i < examples_test.size(); i++) {
				Tree curr = tree;

				while (curr.best_attribute != -1) {
					double thr = curr.best_threshold;
					int attr = curr.best_attribute;

					if (matrix_test[i][attr] < thr) {
						curr = curr.left_child;
					} else {
						curr = curr.right_child;
					}

				}
				distributionList.put(i, curr.distribution);
			}
			distributionMap.put(t, distributionList);
		}

		Map<Integer, Map<Integer, Double>> distributionSumList = new HashMap<>();

		for (int i = 0; i < examples_test.size(); i++) {

			Map<Integer, Double> avdist = new HashMap<>();

			for (int c = 0; c < classDetails.size(); c++) {
				double sum = 0;
				for (int s = 0; s < list.size(); s++) {
					sum = sum + distributionMap.get(s).get(i).get(classDetails.get(c));
				}
				avdist.put(classDetails.get(c), sum);
			}
			distributionSumList.put(i, avdist);

		}

		for (int i = 0; i < distributionSumList.size(); i++) {
			Map<Integer, Double> dist = distributionSumList.get(i);

			for (int m = 0; m < classDetails.size(); m++) {
				double val = dist.get(classDetails.get(m));
				val = val / list.size();
				dist.put(classDetails.get(m), val);
			}

			distributionSumList.put(i, dist);
		}

		double totalAcc = 0;
		double[][] classificationArr = new double[examples_test.size()][4];

		for (int i = 0; i < distributionSumList.size(); i++) {

			Map<Integer, Double> dist = distributionSumList.get(i);
			double max = -1.0;
			double maxclass = -1;
			int count = 0;

			for (int d = 0; d < dist.size(); d++) {
				if (dist.get(classDetails.get(d)) >= max) {
					max = dist.get(classDetails.get(d));
					maxclass = classDetails.get(d);
				}
			}

			for (int d = 0; d < dist.size(); d++) {
				if (dist.get(classDetails.get(d)) == max) {
					count = count + 1;
				}
			}
			int acc = 0;

			if (maxclass == matrix_test[i][attributes.size()]) {
				acc = 1 / count;
			}

			totalAcc = totalAcc + acc;

			classificationArr[i][0] = i;
			classificationArr[i][1] = maxclass;
			classificationArr[i][2] = matrix_test[i][attributes.size()];
			classificationArr[i][3] = acc;
		}

		for (double[] aClassificationArr : classificationArr) {
			System.out.printf("ID=%5d, predicted=%3d, true=%3d, accuracy=%4.2f\n",
					(int) aClassificationArr[0],
					(int) aClassificationArr[1], (int) aClassificationArr[2], aClassificationArr[3]);

		}

		System.out.printf("classification accuracy=%6.4f\n", totalAcc / examples_test.size());


	}

	private Tree getBasicTree(Map<Integer, Double> distribution) {
		Tree tree = new Tree();
		tree.best_attribute = -1;
		tree.best_threshold = -1;
		tree.gain = -1;
		tree.distribution = distribution;
		return tree;
	}

	public static void main(String[] args) throws IOException {

		if (args.length != 3) {
			System.out.println("Please provide arguments 'training_file' 'test_file' 'option'");
			System.exit(1);
		}
		String trainingPath = args[0];
		String testPath = args[1];
		String option = args[2];

		//Load training data into a matrix
		FileReader fileReader = new FileReader(trainingPath);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		List<String> examples = new ArrayList<>();
		Map<Integer, Integer> classCount = new HashMap<>();

		while (bufferReader.ready()) {

			String firstLine = bufferReader.readLine();
			firstLine = firstLine.trim();
			examples.add(firstLine);
		}
		int length = examples.get(0).split("\\s+").length;

		double[][] matrix = new double[examples.size()][length];

		for (int i = 0; i < examples.size(); i++) {
			String[] arr = examples.get(i).split("\\s+");

			for (int j = 0; j < length; j++) {

				matrix[i][j] = Double.parseDouble(arr[j]);
			}
		}

		//Load training data into a matrix

		FileReader fileReader_test = new FileReader(testPath);
		BufferedReader bufferReader_test = new BufferedReader(fileReader_test);
		List<String> examples_test = new ArrayList<>();

		while (bufferReader_test.ready()) {

			String firstLine = bufferReader_test.readLine();
			firstLine = firstLine.trim();
			examples_test.add(firstLine);
		}

		double[][] matrix_test = new double[examples_test.size()][length];

		for (int i = 0; i < examples_test.size(); i++) {
			String[] arr = examples_test.get(i).split("\\s+");

			for (int j = 0; j < length; j++) {

				matrix_test[i][j] = Double.parseDouble(arr[j]);
			}
		}

		for (String example : examples) {
			int currClass = Integer.parseInt(example.split("\\s+")[length - 1]);
			if (classCount.containsKey(currClass)) {
				int currCount = classCount.get(currClass);
				currCount = currCount + 1;
				classCount.put(currClass, currCount);
			} else {
				classCount.put(currClass, 1);
			}

		}

		Map<Integer, Double> distribution = new HashMap<>();
		//To fill details in classDetails
		Map<Integer, Integer> classDetails = new HashMap<>();
		int classDet = 0;
		for (Map.Entry<Integer, Integer> classCountEntry : classCount.entrySet()) {
			distribution
					.put(classCountEntry.getKey(), (double) (classCountEntry.getValue() / examples.size()));
			classDetails.put(classDet++, classCountEntry.getKey());
		}

		List<Integer> exampleList = new ArrayList<>();

		for (int i = 0; i < examples.size(); i++) {
			exampleList.add(i);
		}

		List<Integer> exampleList_test = new ArrayList<>();

		for (int i = 0; i < examples_test.size(); i++) {
			exampleList_test.add(i);
		}

		//To create an attribute list
		List<Integer> attributes = new ArrayList<>();

		for (int i = 1; i <= length - 1; i++) {
			attributes.add(i);
		}

		DTree1 dt = new DTree1(classDetails, matrix);

		//Optimized
		if (option.equalsIgnoreCase("optimized")) {
			Tree tree = dt.dLT(exampleList, attributes, distribution, false);
			dt.displayTrainingResult(tree, 0);

			dt.optimizedRanClassification(tree, exampleList_test, matrix_test, attributes);
		}

		//Random
		else if (option.equalsIgnoreCase("randomized")) {
			Tree treern = dt.dLT(exampleList, attributes, distribution, true);
			dt.displayTrainingResult(treern, 0);
			dt.optimizedRanClassification(treern, exampleList_test, matrix_test, attributes);
		}
		//Random 3
		else if (option.equalsIgnoreCase("forest3")) {
			List<Tree> list3 = new ArrayList<>();

			for (int i = 0; i < 3; i++) {
				list3.add(dt.dLT(exampleList, attributes, distribution, true));
			}
			for (int i = 0; i < 3; i++) {
				dt.displayTrainingResult(list3.get(i), i);
			}

			dt.RanClassification3(list3, exampleList_test, matrix_test, attributes);
		}
		//Random15
		else if (option.equalsIgnoreCase("forest15")) {
			List<Tree> list = new ArrayList<>();

			for (int i = 0; i < 15; i++) {
				list.add(dt.dLT(exampleList, attributes, distribution, true));
			}

			for (int i = 0; i < 15; i++) {
				dt.displayTrainingResult(list.get(i), i);
			}
			dt.RanClassification3(list, exampleList_test, matrix_test, attributes);
		}

		bufferReader.close();
		bufferReader_test.close();
	}

}
