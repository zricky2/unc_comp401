package a1;

import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		calculate(scanner);

	}

	public static void calculate(Scanner scanner) {
		// total assignments
		int totalAssign = scanner.nextInt();
		// worth of assignments
		int[] assign_value = new int[totalAssign];
		for (int p = 0; p < totalAssign; p++) {

			assign_value[p] = scanner.nextInt();
		}
		// total participation points
		double total_part = scanner.nextDouble();

		// number of students
		int num_students = scanner.nextInt();

		// output
		for (int i = 0; i < num_students; i++) {
			String f_name = scanner.next();
			String l_name = scanner.next();
			char letter = f_name.charAt(0);
			int participation = scanner.nextInt();
			// store assignment score
			double[] assign_score = new double[totalAssign];
			for (int x = 0; x < totalAssign; x++) {

				assign_score[x] = scanner.nextDouble();
			}
			// store the test scores
			double midterm = scanner.nextDouble();
			double final_E = scanner.nextDouble();
			// Calculate participation points
			double total_part_adjusted = total_part * 0.8;
			double part_percent = (participation / total_part_adjusted) * 100;
			if (part_percent > 100) {
				part_percent = 100;
			}
			// calculate assignments percentage
			// add up the total score
			double totalScore = 0;
			for (int w = 0; w < totalAssign; w++) {
				totalScore += assign_score[w];
			}
			// add up the total value
			double totalValue = 0;
			for (int v = 0; v < totalAssign; v++) {
				totalValue += assign_value[v];
			}
			// calculate assignment
			double assign = (totalScore / totalValue) * 100;

			double waverage = (.4 * assign + .15 * part_percent + .2 * midterm + .25 * final_E) / 100;

			if (waverage >= .94) {
				System.out.println(letter + ". " + l_name + " A");
			} else if (waverage >= .9 && waverage < .94) {
				System.out.println(letter + ". " + l_name + " A-");
			} else if (waverage >= .86 && waverage < .9) {
				System.out.println(letter + ". " + l_name + " B+");
			} else if (waverage >= .83 && waverage < .86) {
				System.out.println(letter + ". " + l_name + " B");
			} else if (waverage >= .8 && waverage < .83) {
				System.out.println(letter + ". " + l_name + " B-");
			} else if (waverage >= .76 && waverage < .8) {
				System.out.println(letter + ". " + l_name + " C+");
			} else if (waverage >= .73 && waverage < .76) {
				System.out.println(letter + ". " + l_name + " C");
			} else if (waverage >= .7 && waverage < .73) {
				System.out.println(letter + ". " + l_name + " C-");
			} else if (waverage >= .65 && waverage < .7) {
				System.out.println(letter + ". " + l_name + " D+");
			} else if (waverage >= .6 && waverage < .65) {
				System.out.println(letter + ". " + l_name + " D");
			} else {
				System.out.println(letter + ". " + l_name + " F");
			}
		}

	}

}
