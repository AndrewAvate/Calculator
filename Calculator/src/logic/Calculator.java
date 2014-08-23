package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

	private String result = "";
	private String reversePolishNotation = "";
	private Map<String, Integer> priority = new HashMap<String, Integer>();
	List<String> output = new ArrayList<String>();

	public Calculator() {
		priority.put("+", 1);
		priority.put("-", 1);
		priority.put("*", 2);
		priority.put("/", 2);
		priority.put("^", 3);
	}

	public void convertToReversePolishNotation(String inputStr) {
		setResult("");
		setReversePolishNotation("");
		String inputString = inputStr;
		String[] inputArray = inputString.trim().split("(?<=\\D)|(?=\\D)");
		System.out.println(Arrays.toString(inputArray));

		Stack<String> stack = new Stack<String>();
		output = new ArrayList<String>();
		String currentSymbol = "";

		for (String s : inputArray) {
			currentSymbol = s;

			if (currentSymbol.equals("(")) {
				stack.add(currentSymbol);
			}

			else if (currentSymbol.equals(")")) {
				while (!stack.isEmpty()) {
					if (!stack.lastElement().equals("(")) {
						output.add(stack.pop());
					} else if (stack.lastElement().equals("(")) {
						stack.pop();
						break;
					}
				}
			}

			// если операнд имеет высший приоритет, то кладем его в стек:
			else if (priority.get(currentSymbol) != null
					&& priority.get(currentSymbol) == 3)
				stack.add(currentSymbol);
			/*
			 * если операнд имеет не высший приоритет, то достаем из стека
			 * операнды с более высоким приоритетом и ложим их в выходной лист.
			 * После этого кладем текущий операнд в стек:
			 */
			else if (priority.get(currentSymbol) != null
					&& priority.get(currentSymbol) != 3) {

				if (!stack.isEmpty()) {
					if (priority.get(stack.lastElement()) != null
							&& priority.get(currentSymbol) != null
							&& priority.get(stack.lastElement()) >= priority
									.get(currentSymbol))
						output.add(stack.pop());
				}
				stack.add(currentSymbol);
			} else
				output.add(currentSymbol);
		}

		while (!stack.isEmpty()) {
			output.add(" ");
			output.add(stack.pop());
		}

		for (String s : output) {
			reversePolishNotation += s;
		}

	}

	public void calculateReversePolishNotation() {
		Stack<Double> calcStack = new Stack<Double>();
		String regExp = "[^0-9]"; // не цифра
		Pattern pattern = Pattern.compile(regExp);

		for (String s : output) {
			Matcher matcher = pattern.matcher(s);
			double result = 0;
			double var1 = 0;
			double var2 = 0;
			if (matcher.find()) {
				switch (s) {
				case "+":
					var2 = calcStack.pop();
					var1 = calcStack.pop();
					result = var1 + var2;
					calcStack.push(result);
					break;
				case "-":
					var2 = calcStack.pop();
					var1 = calcStack.pop();
					result = var1 - var2;
					calcStack.push(result);
					break;

				case "*":
					var2 = calcStack.pop();
					var1 = calcStack.pop();
					result = var1 * var2;
					calcStack.push(result);
					break;

				case "/":
					var2 = calcStack.pop();
					var1 = calcStack.pop();
					result = var1 / var2;
					calcStack.push(result);
					break;

				case "^":
					var2 = calcStack.pop();
					var1 = calcStack.pop();
					result = Math.pow(var1, var2);
					calcStack.push(result);
					break;
				}
			} else {
				calcStack.push(Double.valueOf(s));
			}

		}
		
		result = calcStack.pop().toString();
	}

	// getter & setters

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReversePolishNotation() {
		return reversePolishNotation;
	}

	public void setReversePolishNotation(String reversePolishNotation) {
		this.reversePolishNotation = reversePolishNotation;
	}

}
