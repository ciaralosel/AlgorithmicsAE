import java.io.*;
import java.util.*;

/** program to find compression ratio using LZW compression
 */

public class Main {

	public static void main(String[] args) throws IOException {
		
		String inputFileName = args[0];
		long start = System.currentTimeMillis();
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		Trie trie = new Trie();
		int trieSize = 0;
		
		// insert all of the first 128 ascii characters into the trie
		for (int i=0; i<128; i++) {
			trie.insert((char)i, null);
			trieSize = trieSize + 1;
		}
		
		int characterCount = 0;
		int newFileSize = 0;
		// initial codeword length is 8 
		int codewordLength = 8;
		// maximum size of trie currently is 2 ** current codeword length 
		double maxSize = Math.pow(2, codewordLength);
		Node nodeToSearchFrom = null;
		Node currentNode =null;
		
		while(in.hasNextLine()) {
			String text = in.nextLine() + "\n";
			for (char currentChar : text.toCharArray()) {
				characterCount = characterCount + 1;
				if (characterCount == 1) {
					currentNode = trie.search(currentChar, null);
				} else {
					nodeToSearchFrom = trie.search(currentChar,  currentNode);
					// if node hasn't changed we know this word doesn't already exist so we add this character to the current trie branch 
					if (nodeToSearchFrom == currentNode) {
						trie.insert(currentChar, currentNode);
						trieSize = trieSize + 1;
						// if trie has reached it's current maximum capacity then codeword length needs to increment 
						// a trie now has a new maximum capacity 
						if (trieSize >= maxSize) {
							codewordLength = codewordLength + 1;
							maxSize = Math.pow(2,  codewordLength);
						}
						newFileSize = newFileSize + codewordLength;
						// set node for next iteration to be a child node of the root that represents the current character to start off the next word 
						currentNode = trie.search(currentChar, null);
					} else {
						// set node for next iteration to be node of this character 
						currentNode = nodeToSearchFrom;
					}
				}
			}
		}
		
		int originalFileSize = characterCount * 8;

		reader.close();
		in.close();

		float compressionRatio = (float)newFileSize / originalFileSize;
		System.out.println(trieSize);
		// output the results here
		System.out.println("Original file length in bits = " + originalFileSize);
		System.out.println("Compressed file length in bits = " + newFileSize);
		System.out.println("Compression ratio = " + compressionRatio);

		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}

