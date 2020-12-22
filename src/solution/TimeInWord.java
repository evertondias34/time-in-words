package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeInWord {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String PATTERN_HOURS = "((0[0-9]|1[0-9]|2[0-3]))";
	private static final String PATTERN_MINUTES = "([0-5][0-9])";

	private static String numbersName[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
			"twenty six", "twenty seven", "twenty eight", "twenty nine" };

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("time.out"));

		System.out.println("*** CONVERT HOURS TO WORDS *** \n");

		Boolean isContinueRegister = true;

		while (isContinueRegister) {
			int hour = getValue("THE HOURS(hh -> 00-23)", PATTERN_HOURS);
			int minute = getValue("THE MINUTES(mm -> 00-59)", PATTERN_MINUTES);

			String result = convertTimeInWords(hour, minute);

			String hourInput = hour >= 10 ? hour + "" : "0" + hour;
			String minuteInput = minute >= 10 ? minute + "" : "0" + minute;


			String timeInWords = hourInput + ":" + minuteInput + " -> " + result;
			
			System.out.println(timeInWords);
			System.out.println("----------------------------");

			bufferedWriter.write(result);
			bufferedWriter.newLine();

			System.out.println("Type Y to continue registering ");
			isContinueRegister = isContinue(scanner.nextLine());
		}
		
		System.out.println("--- FINISHED --- \n");

		bufferedWriter.close();

		scanner.close();
	}

	static boolean isContinue(String inputExit) {
		boolean isContinue = false;

		if (inputExit.toUpperCase().equals("Y")) {
			isContinue = true;
		}

		return isContinue;
	}

	static int getValue(String type, String pattern) {
		Boolean isValidValue = false;
		String input = "";

		while (!isValidValue) {
			System.out.println("Report " + type + ":");

			input = scanner.nextLine();

			isValidValue = isValidInput(input, pattern);
		}

		int valueInput = Integer.parseInt(input);

		return valueInput;
	}

	static boolean isValidInput(final String time, String patternCurrent) {
		boolean isValidInput = true;

		Pattern pattern = Pattern.compile(patternCurrent);
		Matcher matcher = pattern.matcher(time);
		isValidInput = matcher.matches();

		if (!isValidInput) {
			System.err.print("Invalid format!\n");
		}

		return isValidInput;
	}

	static String convertTimeInWords(int hour, int minute) {

		int hourCurrent = hour > 12 ? hour - 12 : hour;

		String timeInWord = "";

		if (minute <= 30) {
			timeInWord = minute == 0 ? getOClock(hourCurrent)
					: minute == 15 ? getQuarterPast(hourCurrent)
							: minute == 30 ? getHalf(hourCurrent) : getPast(hourCurrent, minute);

		} else {
			int nextHour = hour == 23 ? 0 : hour + 1;

			timeInWord = minute == 45 ? getQuarterTo(nextHour) : getTo(nextHour, minute);
		}

		return timeInWord;
	}

	static String getOClock(int hour) {
		return numbersName[hour] + " o' clock";
	}

	static String getQuarterPast(int hour) {
		return "quarter past " + numbersName[hour];
	}

	static String getHalf(int hour) {
		return "half past " + numbersName[hour];
	}

	static String getPast(int hour, int minute) {

		String minuteCurrent = minute == 1 ? " minute" : " minutes";

		return numbersName[minute] + minuteCurrent + " half past " + numbersName[hour];
	}

	static String getQuarterTo(int hour) {
		return "quarter to " + numbersName[hour];
	}

	static String getTo(int hour, int minute) {
		String minuteCurrent = minute == 59 ? " minute" : " minutes";

		return numbersName[60 - minute] + minuteCurrent + " to " + numbersName[hour];
	}

}
