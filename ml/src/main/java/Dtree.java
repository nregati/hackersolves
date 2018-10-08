import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Dtree {

	private static Random r = new Random();
	private List<Double> classList = new ArrayList<>();
	private SampleData trainingData = new SampleData();
	private String training_file;
	private String test_file;
	private String option;
	private Integer pruningThreshold;
	private Double classificationAccuracy = 0.0;
	private Integer forestSize = 1;

	private Dtree(String training_file, String test_file, String option, Integer pruningThreshold) {
		this.training_file = training_file;
		this.test_file = test_file;
		this.option = option;
		this.pruningThreshold = pruningThreshold;
	}

	public static void main(String[] args) {

		if (args.length < 4 || args.length > 4) {
			System.out.println("Please follow the input format :Dtree <training_file> <test_file> <option> <pruning_thr>");
			return;
		}

		Dtree d = new Dtree(args[0], args[1], args[2], Integer.parseInt(args[3]));

		if (args[2].contains("forest")) {
			Integer tempForestSize;
			tempForestSize = Integer.parseInt(args[2].replace("forest", ""));
			d.forestSize = tempForestSize;
			d.option = "randomized";
		}

		try {
			d.readTrainingData();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		//learn decision Tree
		ArrayList<Node> forest = new ArrayList<>();
		for (int i = 0; i < d.forestSize; i++) {
			Node root = d.DTL(d.trainingData, SampleData.dimensionCount, d.distribution(d.trainingData), d.pruningThreshold, 1);
			forest.add(root);
		}

		for (int i = 0; i < d.forestSize; i++) {
			Node root = forest.get(i);
			Node.visitBFS(root, i);
		}

		try {
			if (d.forestSize == 1) {
				d.classifyTestData(forest.get(0));
			} else { // classification based on forest
				d.classifyTestData(forest);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	private void classifyTestData(ArrayList<Node> forest) throws FileNotFoundException {
		int id = 0;
		File trainingFile = new File(this.test_file);
		Scanner sc;
		sc = new Scanner(trainingFile);
		List<Double> dimensions;
		double temp;
		while (sc.hasNextLine()) { // for new test data
			Scanner line = new Scanner(sc.nextLine());
			dimensions = new ArrayList<>();
			while (line.hasNext()) {// dimensions of a test data
				temp = Double.parseDouble(line.next());
				dimensions.add(temp);
			}
			classificationAccuracy = classificationAccuracy + classifyTestRecord(dimensions, id, forest);
			id++;
			line.close();
		}
		classificationAccuracy = classificationAccuracy / (id - 1);
		System.out.printf("classification accuracy=%6.4f\n", classificationAccuracy);
		sc.close();
	}

	private Double classifyTestRecord(List<Double> dimensions, int object_id,
			ArrayList<Node> forest) {
		double trueClass = dimensions.get(dimensions.size() - 1);
		List<Double> distributionTemp;
		List<Double> distribution = forest.get(0).parseRecord(dimensions);
		for (int n = 1; n < forest.size(); n++) {
			distributionTemp = forest.get(n).parseRecord(dimensions);
			for (int i = 0; i < distribution.size(); i++) {
				distribution.set(i, distribution.get(i) + distributionTemp.get(i));
			}
		}
		for (int i = 0; i < distribution.size(); i++) {
			distribution.set(i, distribution.get(i) / (double) forest.size());
		}
		Double tempMax = distribution.get(0);
		List<Integer> maxIndexList = new ArrayList<>();
		maxIndexList.add(0);
		int tempIndex;
		Double accuracy = 0.0;

		for (int i = 1; i < distribution.size(); i++) {
			if (distribution.get(i) > tempMax) {
				tempMax = distribution.get(i);
				maxIndexList.clear();
				maxIndexList.add(i);
			} else if (distribution.get(i).equals(tempMax)) {
				maxIndexList.add(i);

			}

		}

		Double predictedClass = -1.0;
		if (maxIndexList.size() == 1) {
			tempIndex = maxIndexList.get(0);
			predictedClass = classList.get(tempIndex);

			if (trueClass == predictedClass) {
				classificationAccuracy++;
				accuracy = 1.0;
			} else {
				accuracy = 0.0;
			}
		} else if (maxIndexList.size() > 1) {
			System.out.println("size > 0 :" + maxIndexList.size());
			List<Double> tempTieClasses = new ArrayList<>();
			for (Integer aMaxIndexList : maxIndexList) {
				tempTieClasses.add(classList.get(aMaxIndexList));
			}
			Random r = new Random();
			tempIndex = r.nextInt(maxIndexList.size());
			predictedClass = classList.get(tempIndex);
			if (tempTieClasses.contains(trueClass)) {
				accuracy = 1.0 / maxIndexList.size();
			} else {
				accuracy = 0.0;
			}
		}
		System.out.printf("ID=%5d, predicted=%3.0f, true=%3.0f, accuracy=%4.2f\n", object_id, predictedClass, trueClass, accuracy);
		return accuracy;
	}

	private void readTrainingData() throws FileNotFoundException {

		Set<Double> classLabelSet = new HashSet<>();
		File trainingFile = new File(this.training_file);
		Scanner sc;
		sc = new Scanner(trainingFile);

		List<Double> dimensions;
		double temp = 0;

		while (sc.hasNextLine()) { // for new sample
			Scanner line = new Scanner(sc.nextLine());
			dimensions = new ArrayList<>();

			while (line.hasNext()) { // dimensions of a sample
				temp = Double.parseDouble(line.next());
				dimensions.add(temp);
			}

			int tempIndex;
			if (classLabelSet.add(temp)) {
				this.classList.add(temp);
			}
			tempIndex = this.classList.indexOf(temp);
			dimensions.set(dimensions.size() - 1, (double) tempIndex); //setting class label to index of array
			if (this.trainingData != null) { //existing class
				this.trainingData.add(dimensions);
			} else { //new class
				this.trainingData = new SampleData();
				this.trainingData.add(dimensions);

			}
			line.close();
		}
		sc.close();

		SampleData.ClassCount = classList.size();

	}


	private Node DTL(SampleData data, Integer attribute, List<Double> distribution,
			Integer pruning_threshold, Integer nodeId) {
		SampleData dataLeft;
		SampleData dataRight;
		Node newNode = new Node();
		newNode.ID = nodeId;

		if (data.samples.size() < pruning_threshold || hasSameClass(data)) {

			newNode.distribution = distribution;
			newNode.threshold = -1.0;
			newNode.feature = -1;
			newNode.gain = -1.0;

			return newNode;
		}
		else {
			String temp[];
			temp = chooseAttribute(data, attribute, this.option);
			newNode.feature = Integer.parseInt(temp[0]);
			newNode.threshold = Double.parseDouble(temp[1]);
			newNode.gain = Double.parseDouble(temp[2]);
			dataLeft = newNode.getDataForLeftNode(data);
			dataRight = newNode.getDataForRightNode(data);
			newNode.left = DTL(dataLeft, attribute, distribution(dataLeft), pruning_threshold,
					nodeId * 2);
			newNode.right = DTL(dataRight, attribute, distribution(dataRight), pruning_threshold,
					(nodeId * 2 + 1));
		}
		return newNode;
	}


	private boolean hasSameClass(SampleData data) {
		List<Double> sample;
		if (data.sampleCount == 0) {
			return true;
		}
		sample = data.samples.get(0);
		double tempClassLabel = sample.get(sample.size() - 1);

		for (int i = 1; i < data.sampleCount; i++) {
			sample = data.samples.get(i);
			double temp = sample.get(sample.size() - 1);
			if (tempClassLabel != temp) {
				return false;
			}
		}
		return true;
	}

	private List<Double> distribution(SampleData data) {
		List<Double> tempDistribution = new ArrayList<>();
		for (int i = 0; i < SampleData.ClassCount; i++) {
			tempDistribution.add(0.0);
		}

		List<Double> sample;
		for (int i = 0; i < data.sampleCount; i++) {
			sample = data.samples.get(i);
			tempDistribution.set(sample.get(sample.size() - 1).intValue(),
					tempDistribution.get(sample.get(sample.size() - 1).intValue()) + 1);
		}

		for (int i = 0; i < SampleData.ClassCount; i++) {
			tempDistribution.set(i, tempDistribution.get(i) / (double) data.sampleCount);
		}

		return tempDistribution;
	}


	private String[] chooseAttribute(SampleData examples, Integer attribute,
			String attributeSelectionType) {

		String[] returnString = new String[3];
		Integer bestAttribute = -1;
		Double max_gain = -1.0, best_threshold = -1.0;

		Double L, M;
		List<Double> attributeValues = new ArrayList<>();

		if (attributeSelectionType.equalsIgnoreCase("optimized")) {
			for (int i = 0; i < attribute; i++) {
				attributeValues = new ArrayList<>();

				for (int j = 0; j < examples.sampleCount; j++) //getting values for attribute i
				{
					attributeValues.add(examples.samples.get(j).get(i));
				}

				L = Collections.min(attributeValues);
				M = Collections.max(attributeValues);

				//attributeValues.clear();

				for (int k = 1; k <= 50; k++) {
					double threshold = L + k * (M - L) / 51;
					Double gain = informationGain(examples, i, threshold);

					if (gain > max_gain) {

						max_gain = gain;
						bestAttribute = i;
						best_threshold = threshold;

					}

				}

			}
		} else //randomize
		{

			Integer a = r.nextInt(attribute);
			double gain;

			for (int j = 0; j < examples.sampleCount; j++) //getting values for attribute i
			{
				attributeValues.add(examples.samples.get(j).get(a));
			}

			L = Collections.min(attributeValues);
			M = Collections.max(attributeValues);

			attributeValues.clear();

			for (int k = 1; k <= 50; k++) {
				double threshold = L + k * (M - L) / 51;
				gain = informationGain(examples, a, threshold);
				if (gain > max_gain) {
					max_gain = gain;
					bestAttribute = a;
					best_threshold = threshold;
				}


			}


		}

		returnString[0] = bestAttribute + "";
		returnString[1] = best_threshold + "";
		returnString[2] = max_gain + "";

		return returnString;
	}


	private double informationGain(SampleData examples, int a, double threshold) {

		Double entropy = 0.0;
		Double countLeft = 0.0;
		Double countRight = 0.0;
		Double entropyLeft = 0.0, entropyRight = 0.0;

		List<Double> tempDistribution = new ArrayList<>();
		List<Double> tempDistributionLeft = new ArrayList<>();
		List<Double> tempDistributionRight = new ArrayList<>();

		for (int i = 0; i < SampleData.ClassCount; i++) {
			tempDistribution.add(0.0);
			tempDistributionLeft.add(0.0);
			tempDistributionRight.add(0.0);
		}

		List<Double> sample;
		for (int i = 0; i < examples.sampleCount; i++) {
			sample = examples.samples.get(i);
			Double classLabel = sample.get(sample.size() - 1);
			tempDistribution.set(classLabel.intValue(), tempDistribution.get(classLabel.intValue()) + 1);

			if (sample.get(a) >= threshold) {
				countRight++;
				//System.out.println(classLabel.intValue());
				tempDistributionRight.set(classLabel.intValue(), tempDistributionRight.get(classLabel.intValue()) + 1);

			} else {
				countLeft++;
				tempDistributionLeft.set(classLabel.intValue(), tempDistributionLeft.get(classLabel.intValue()) + 1);
			}
		}
		for (int i = 0; i < SampleData.ClassCount; i++) {
			double temp = tempDistribution.get(i);
			double tempL = tempDistributionLeft.get(i);
			double tempR = tempDistributionRight.get(i);
			if (temp != 0.0)
			{
				entropy = entropy - ((temp / (double) examples.sampleCount) * Math.log(temp / (double) examples.sampleCount) / Math.log(2));
			}
			if (tempDistributionLeft.get(i) != 0)
			{
				entropyLeft = entropyLeft - ((tempL / countLeft) * Math.log((tempL / countLeft)) / Math.log(2));
			}
			if (tempDistributionRight.get(i) != 0)
			{
				entropyRight = entropyRight - (tempR / countRight) * Math.log((tempR / countRight)) / Math.log(2);
			}
		}
		return entropy - ((countLeft / examples.sampleCount) * entropyLeft) - (
				(countRight / examples.sampleCount) * entropyRight);
	}

	private void classifyTestData(Node root) throws FileNotFoundException {
		int id = 0;
		File trainingFile = new File(this.test_file);
		Scanner sc;
		sc = new Scanner(trainingFile);
		List<Double> dimensions;
		double temp;
		while (sc.hasNextLine()) //for new test data
		{
			Scanner line = new Scanner(sc.nextLine());
			dimensions = new ArrayList<>();
			while (line.hasNext()) //dimensions of a test data
			{

				temp = Double.parseDouble(line.next());
				dimensions.add(temp);

			}
			classificationAccuracy = classificationAccuracy + classifyTestRecord(dimensions, id, root);
			id++;
			line.close();
		}
		classificationAccuracy = classificationAccuracy / (id - 1);
		System.out.printf("classification accuracy=%6.4f\n", classificationAccuracy);
		sc.close();
	}

	private Double classifyTestRecord(List<Double> dimensions, int object_id, Node root) {

		double trueClass = dimensions.get(dimensions.size() - 1);
		List<Double> distribution = root.parseRecord(dimensions);
		Double tempMax = distribution.get(0);
		List<Integer> maxIndexList = new ArrayList<>();
		maxIndexList.add(0);
		int tempIndex;
		Double accuracy = 0.0;

		for (int i = 1; i < distribution.size(); i++) {
			if (distribution.get(i) > tempMax) {
				tempMax = distribution.get(i);
				maxIndexList.clear();
				maxIndexList.add(i);
			} else if (distribution.get(i).equals(tempMax)) {
				maxIndexList.add(i);
			}
		}

		Double predictedClass = -1.0;
		if (maxIndexList.size() == 1) {
			tempIndex = maxIndexList.get(0);
			predictedClass = classList.get(tempIndex);
			if (trueClass == predictedClass) {
				classificationAccuracy++;
				accuracy = 1.0;
			} else {
				accuracy = 0.0;
			}
		} else if (maxIndexList.size() > 1) {
			System.out.println("size > 0 :" + maxIndexList.size());
			List<Double> tempTieClasses = new ArrayList<>();
			for (Integer aMaxIndexList : maxIndexList) {
				tempTieClasses.add(classList.get(aMaxIndexList));
			}
			Random r = new Random();
			tempIndex = r.nextInt(maxIndexList.size());
			predictedClass = classList.get(tempIndex);
			if (tempTieClasses.contains(trueClass)) {
				accuracy = 1.0 / maxIndexList.size();
			} else {
				accuracy = 0.0;
			}
		}

		System.out
				.printf("ID=%5d, predicted=%3.0f, true=%3.0f, accuracy=%4.2f\n", object_id, predictedClass,
						trueClass, accuracy);

		return accuracy;
	}
}

class Node {

	List<Double> distribution;
	Integer ID;
	Integer feature;
	Double threshold;
	Double gain;
	Node left = null;
	Node right = null;

	SampleData getDataForRightNode(SampleData data) {
		SampleData newSampleData = new SampleData();
		List<Double> sample;
		for (int i = 0; i < data.sampleCount; i++) {
			sample = data.samples.get(i);
			if (sample.get(feature) >= threshold) {
				newSampleData.add(sample);
			}
		}
		return newSampleData;
	}

	SampleData getDataForLeftNode(SampleData data) {
		SampleData newSampleData = new SampleData();
		List<Double> sample;
		for (int i = 0; i < data.sampleCount; i++) {
			sample = data.samples.get(i);
			if (sample.get(feature) < threshold) {
				newSampleData.add(sample);
			}
		}
		return newSampleData;
	}

	static void visitBFS(Node root, Integer tree_id) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current != null) {
				current.displayNode(tree_id);
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
		}
	}

	private void displayNode(int tree_id) {
		System.out.printf("tree=%2d, node=%3d, feature=%2d, thr=%6.2f, gain=%f\n", tree_id, this.ID,
				this.feature, this.threshold, this.gain);
	}

	List<Double> parseRecord(List<Double> testRecord) {
		List<Double> distribution;
		if (this.distribution == null) {
			if (testRecord.get(this.feature) < this.threshold) {
				distribution = this.left.parseRecord(testRecord);
			} else {
				distribution = this.right.parseRecord(testRecord);
			}
		} else {
			distribution = new ArrayList<>(this.distribution);
		}
		return distribution;
	}
}

class SampleData {

	static Integer ClassCount;
	static Integer dimensionCount;
	Integer sampleCount = 0;
	ArrayList<List<Double>> samples = new ArrayList<>();

	void add(List<Double> x) {
		samples.add(x);
		sampleCount++;
		dimensionCount = x.size() - 1;
	}
}