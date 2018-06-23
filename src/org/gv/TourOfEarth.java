package org.gv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings({ "nls", "javadoc", "boxing" })
public class TourOfEarth {
	private int baseIdentifier;
	private int countOfTokens;
	private int countOfChoices;
	private Integer[] tokenIdentifiers;
	private Map<Integer, String> choiceMap = new LinkedHashMap<>();
	private boolean flag = false;

	/**
	 * @return the baseIdentifier
	 */
	public int getBaseIdentifier() {
		return baseIdentifier;
	}

	/**
	 * @param baseIdentifier
	 *            the baseIdentifier to set
	 */
	public void setBaseIdentifier(int baseIdentifier) {
		this.baseIdentifier = baseIdentifier;
	}

	/**
	 * @return the countOfTokens
	 */
	public int getCountOfTokens() {
		return countOfTokens;
	}

	/**
	 * @param countOfTokens
	 *            the countOfTokens to set
	 */
	public void setCountOfTokens(int countOfTokens) {
		this.countOfTokens = countOfTokens;
	}

	/**
	 * @return the countOfChoices
	 */
	public int getCountOfChoices() {
		return countOfChoices;
	}

	/**
	 * @param countOfChoices
	 *            the countOfChoices to set
	 */
	public void setCountOfChoices(int countOfChoices) {
		this.countOfChoices = countOfChoices;
	}

	public void initChoice(String choice) {
		int value = Integer.valueOf(choice.trim());
		boolean possible = checkPossibilityWithModulo(value);
		if (!possible) {
			System.err.println("Checking recursive...");
			flag = false;
			possible = checkPossibilityWithSum(value);
		}
		if (!possible) {
			System.err.println("Checking multiply and recursive...");
			flag = false;
			possible = checkPossibilityWithMultiplyAndSum(value);
		}
		choiceMap.put(value, possible ? "YES" : "NO");
	}

	public void printChoices() {
		System.err.println("Choices ::: " + choiceMap);
		System.err.println("Choices Values ::: " + choiceMap.values());
	}

	public void processInput(String input) {
		String[] splitInput = input.split("\\s+");
		if (splitInput.length != 2) {
			throw new IllegalArgumentException(
					"Number of inputs allowed is 2. Count of tokens and count of Choices");
		}
		countOfTokens = Integer.valueOf(splitInput[0].trim());
		countOfChoices = Integer.valueOf(splitInput[1].trim());
		System.err.println("Count of tokens ::: " + countOfTokens);
		System.err.println("Count of Choices ::: " + countOfChoices);
		tokenIdentifiers = new Integer[countOfTokens];
	}

	public void processTokens(String input) {
		String[] splitInput = input.split("\\s+");
		if (splitInput.length != countOfTokens) {
			throw new IllegalArgumentException(
					"Number of tokens allowed is " + countOfTokens + ".");
		}
		for (int i = 0; i < splitInput.length; i++) {
			tokenIdentifiers[i] = Integer.valueOf(splitInput[i].trim());
		}
		System.err.println("Token Identifiers ::: " + Arrays.toString(tokenIdentifiers));
	}

	public boolean checkPossibilityWithModulo(int choice) {
		for (int token : tokenIdentifiers) {
			if (choice % token == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPossibilityWithSum(int choice) {
		return sumUpRecursive(Arrays.asList(tokenIdentifiers), choice, new ArrayList<>());
	}

	public boolean checkPossibilityWithMultiplyAndSum(int choice) {
		boolean possible = false;
		for(int i = 0; i < countOfTokens; i++) {
			List<Integer> sublist = new ArrayList<>(Arrays.asList(tokenIdentifiers).subList(i+1, countOfTokens));
			if(i > 1) {
				sublist.addAll(Arrays.asList(tokenIdentifiers).subList(0, i));
			}
			System.err.println("Sublist ::: " + sublist);
			for(int j = 2; j < 20; j++) {
				int multiplied = j * tokenIdentifiers[i];
				System.err.println("Multiplied ::: " + multiplied);
				possible= sumUpRecursive(sublist, choice, Arrays.asList(new Integer[] {multiplied}));
				if(possible) {
					break;
				}
			}
			if(possible) {
				break;
			}
		}
		return possible;
	}

	public boolean sumUpRecursive(List<Integer> numbers, int target, List<Integer> partial) {
		int s = 0;
		for (int x : partial) {
			s += x;
		}
		if (s == target) {
			flag = true;
		}
		for (int i = 0; i < numbers.size(); i++) {
			ArrayList<Integer> remaining = new ArrayList<>(numbers.subList(i + 1, numbers.size()));
			int n = numbers.get(i);
			remaining.addAll(numbers.subList(0, i));
			ArrayList<Integer> partialList = new ArrayList<>(partial);
            partialList.add(n);
            if(flag) {
				break;
			}
            sumUpRecursive(remaining, target, partialList);
		}
		return flag;
	}

	public static void main(String args[]) throws Exception {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
		System.err.println("Enter the input");
		// Console console = System.console();
		// String input = console.readLine();
		TourOfEarth solution = new TourOfEarth();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String firstLineInput = reader.readLine();
		solution.processInput(firstLineInput);
		String secondLineInput = reader.readLine();
		System.err.println("Second Line ::: " + secondLineInput);
		solution.processTokens(secondLineInput);

		int choiceCount = solution.getCountOfChoices();
		int counter = 0;
		while (counter++ < choiceCount) {
			solution.initChoice(reader.readLine());
		}

		solution.printChoices();
	}
}