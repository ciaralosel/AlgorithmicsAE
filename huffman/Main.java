import java.io.*;
import java.util.*;
/** program to find compression ratio using Huffman coding
 */

public class Main {

	public static void main(String[] args) throws IOException { 
		
		String inputFileName = args[0];
		long start = System.currentTimeMillis();
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);

		int characterCount = 0;
		// use hash map to map characters to their frequencies 

		Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		while(in.hasNextLine()) {
			String text = in.nextLine() + "\n";
			for (char currentChar : text.toCharArray()) {
				
				characterCount = characterCount + 1;

				if (frequencyMap.containsKey(currentChar)) {
					// if character is already in the map then increment the frequency 

					frequencyMap.replace(currentChar, frequencyMap.get(currentChar)+1);
				} else {
					// if character not in map, add it 

					frequencyMap.put(currentChar, 1);
				}

			}
		}

		reader.close();
		in.close();
		
		// create an integer array containing all the frequencies 

		List<Integer> frequencies = new ArrayList<Integer>();
		for (int freq : frequencyMap.values()) {
			frequencies.add(freq);
		}
		Collections.sort(frequencies);
		
		int firstMin;
		int secondMin;
		int weightedPathLength = 0;
		
		// calculate weighted path length by continuously adding the two smallest frequencies until we reach the root node 

		while (frequencies.size() > 1) {
			firstMin = frequencies.get(0);
			secondMin = frequencies.get(1);
			int sum = firstMin + secondMin;
			frequencies.remove(0);
			frequencies.remove(0);
			int i = 0;
			while ((i<frequencies.size()) && (sum > frequencies.get(i))){
				i = i + 1; 
			 }
			frequencies.add(i, sum);
			weightedPathLength = weightedPathLength + sum;
		}
		
		int originalFileSize = characterCount * 8;
		int newFileSize = weightedPathLength;
		float compressionRatio = (float)newFileSize / originalFileSize;
		
		// output the results here
		System.out.println("Original file length in bits = " + originalFileSize);
		System.out.println("Compressed file length in bits = " + newFileSize);
		System.out.println("Compression ratio = " + compressionRatio);

		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}

