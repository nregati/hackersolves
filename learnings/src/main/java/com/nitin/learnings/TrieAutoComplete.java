package com.nitin.learnings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TrieAutoComplete {

	public static void main(String args[]) throws Exception {

		String[] input = {"Cat", "Dog", "Catch", "Cake", "Apple", "Door"};
		List<String> words = Arrays.asList(input);

		//This can be done using trie

		Trie trie = new Trie();
		words.forEach(trie::insert);

		Scanner inputString = new Scanner(System.in);
		String prefix = inputString.next();
		List<String> output = trie.autoComplete(prefix);
		if (output.isEmpty())
			System.out.println("No Auto complete match found, The dictionary is case sensitive try changing the case");
		System.out.println(output);

		//		System.out.println(trie.autoComplete("C"));
		//		System.out.println(trie.autoComplete("Ca"));
		//		System.out.println(trie.autoComplete("Cat"));
		//		System.out.println(trie.autoComplete("Cake"));
		//		System.out.println(trie.autoComplete("c"));
		//		System.out.println(trie.autoComplete("A"));
		//		System.out.println(trie.autoComplete("D"));
		//		System.out.println(trie.autoComplete("Do"));
		//		System.out.println(trie.autoComplete("Dog"));
		//		System.out.println(trie.autoComplete("Doo"));
	}
}

class TrieNode {

	//Trie DS

	private final Map<Character, TrieNode> children = new HashMap<>();
	private boolean endOfWord;

	Map<Character, TrieNode> getChildren() {
		return children;
	}

	boolean isEndOfWord() {
		return endOfWord;
	}

	void setEndOfWord() {
		this.endOfWord = true;
	}
}

class Trie {

	private TrieNode root;

	Trie() {
		root = new TrieNode();
	}

	//Trie insertion, insert a char if it's absent

	void insert(String word) {
		if (null != word) {
			TrieNode current = root;

			for (int i = 0; i < word.length(); i++) {
				current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			current.setEndOfWord();
		}
	}

	//Auto completion, first check if given prefix exists else return empty list

	List<String> autoComplete(String prefix) {

		if (null != prefix) {
			TrieNode current = root;
			for (int i = 0; i < prefix.length(); i++) {
				char c = prefix.charAt(i);
				TrieNode node = current.getChildren().get(c);
				if (null == node)
					return new ArrayList<>();
				current = node;
			}
			return getAllWords(prefix, current);
		}
		return new ArrayList<>();
	}

	//Start with end of prefix, if given prefix is end of word add it to the list

	private List<String> getAllWords(String prefix, TrieNode current) {
		List<String> allWords = new ArrayList<>();
		if (current.isEndOfWord())
			allWords.add(prefix);
		getWords(prefix, current, allWords);
		return allWords;
	}

	//Start adding characters to the prefix recursively

	private void getWords(String prefix, TrieNode current, List<String> allWords) {
		//Break here if no children found
		if (current.getChildren().isEmpty())
			return;
		for (Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet()) {

			//Loop through all the children

			StringBuilder prefixBuilder = new StringBuilder(prefix);
			char c = entry.getKey();
			prefixBuilder.append(c);
			TrieNode node = entry.getValue();
			//If end of word found add it to the list
			if (node.isEndOfWord())
				allWords.add(prefixBuilder.toString());
			getWords(prefixBuilder.toString(), node, allWords);
		}
	}
}