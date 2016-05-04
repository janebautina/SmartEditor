package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Jane Bautina
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		if (!wordList.isEmpty()) {
			return;
		}
		String[] arrayOfWords = sourceText.split(" ");

		starter = arrayOfWords[0];
		String prevWord = starter;

		for (int i = 1; i < arrayOfWords.length; i++) {
			int index = containsWord(prevWord);
			if (index == -1) {
				ListNode ln = new ListNode(prevWord);
				ln.addNextWord(arrayOfWords[i]);
				wordList.add(ln);
			} else {
				wordList.get(index).addNextWord(arrayOfWords[i]);
			}
			prevWord = arrayOfWords[i];
		}
		// last Word
		int index = containsWord(prevWord);
		if (index == -1) {
			ListNode ln = new ListNode(prevWord);
			ln.addNextWord(starter);
			wordList.add(ln);
		} else {
			wordList.get(index).addNextWord(starter);
		}
	}

	/**
	 * Check if wordList contains the word
	 * 
	 * @param word
	 * @return -1 if wordList doesn't contain the word index of the word in the
	 *         list otherwise
	 */
	private int containsWord(String word) {
		int index = 0;
		for (ListNode n : wordList) {
			if (n.getWord().equals(word)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (wordList.isEmpty()) {
			return "";
		}
		if (numWords == 0) {
			return "";
		}
		String currentWord = starter;
		String outPut = "";
		outPut += currentWord + " ";
		int numberOfWords = 1;
		while (numberOfWords < numWords) {
			ListNode ln = wordList.get(containsWord(currentWord));
			String randomWord = ln.getRandomNextWord(rnGenerator);
			outPut += randomWord + " ";
			currentWord = randomWord;
			numberOfWords++;
		}
		return outPut;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		wordList.clear();
		train(sourceText);
	}

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;
	private int nextWordsSize;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
		nextWordsSize = 0;
	}

	public String getWord() {
		return word;
	}

	public int getNextWordsSize() {
		return nextWordsSize;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
		nextWordsSize++;
	}

	public String getRandomNextWord(Random generator) {
		return nextWords.get(generator.nextInt(nextWordsSize));
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
