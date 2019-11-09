package a1;

import java.util.Scanner;
import java.lang.Math;

public class A1Jedi {

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
		double totalScore = 0;
		double totalValue = 0;
		// exam total
		double midtermTotal = 0;
		double final_ETotal = 0;

		double[][] grades = new double[num_students][totalAssign + 3];
		// grade variables
		int a = 0;
		int aM = 0;
		int bP = 0;
		int b = 0;
		int bM = 0;
		int cP = 0;
		int c = 0;
		int cM = 0;
		int dP = 0;
		int d = 0;
		int f = 0;

		double[] assign = new double[num_students];

		// part 1
		for (int i = 0; i < num_students; i++) {
			int r = 0;
			while (r < totalAssign + 2) {
				// suppressed

				String f_name = scanner.next();

				String l_name = scanner.next();

				int participation = scanner.nextInt();
				// Calculate participation points
				double part_percent = (participation / (total_part * 0.8)) * 100;
				if (part_percent > 100) {
					part_percent = 100;
				}

				grades[i][0] = part_percent;
				// store assignment score

				r = 1;
				while (r <= totalAssign) {
					grades[i][r] = scanner.nextDouble();
					r++;
				}
				// calculate assignments percentage
				// add up the total score
				totalScore = 0;
				for (int x = 0; x < totalAssign; x++) {
					totalScore += grades[i][x + 1];
				}
				// add up the total value
				totalValue = 0;
				for (int x = 0; x < totalAssign; x++) {
					totalValue += assign_value[x];
				}

				// calculate assignment

				assign[i] = (totalScore / totalValue) * 100;

				// store the test scores
				double midterm = scanner.nextDouble();
				grades[i][totalAssign + 1] = midterm;

				/////

				double final_E = scanner.nextDouble();
				grades[i][totalAssign + 2] = final_E;

				r = totalAssign + 3;
			}
		}

		for (int v = 0; v < num_students; v++) {
			midtermTotal += grades[v][totalAssign + 1];
		}
		for (int v = 0; v < num_students; v++) {
			final_ETotal += grades[v][totalAssign + 2];
		}

		//////////////// part 2
		double midtermAverage = 0;
		double midtermSquared = 0;
		double[] midtermList2 = new double[num_students];
		for (int z = 0; z < num_students; z++) {
			// mid aver
			midtermAverage = (midtermTotal / num_students);
		}
		// (xi -u)squared
		for (int x = 0; x < num_students; x++) {
			midtermList2[x] = (grades[x][totalAssign + 1] - midtermAverage)
					* (grades[x][totalAssign + 1] - midtermAverage);
		}
		// add up the squared numbers
		for (int x = 0; x < num_students; x++) {
			midtermSquared += midtermList2[x];
		}

		// Divide by the total students
		double midtermSD = Math.sqrt(midtermSquared / num_students);

		for (int q = 0; q < num_students; q++) {

			double normalizedScoreMidterm = (grades[q][totalAssign + 1] - midtermAverage) / midtermSD;
			if (normalizedScoreMidterm >= 2.0) {
				grades[q][totalAssign + 1] = 100;
			} else if (normalizedScoreMidterm == 1.0) {
				grades[q][totalAssign + 1] = 94;
			} else if (normalizedScoreMidterm == 0) {
				grades[q][totalAssign + 1] = 85;
			} else if (normalizedScoreMidterm == -1.0) {
				grades[q][totalAssign + 1] = 75;
			} else if (normalizedScoreMidterm == -1.5) {
				grades[q][totalAssign + 1] = 65;
			} else if (normalizedScoreMidterm == -2.0) {
				grades[q][totalAssign + 1] = 55;
			} else if (normalizedScoreMidterm == -3.0) {
				grades[q][totalAssign + 1] = 30;
			} else if (normalizedScoreMidterm <= -4.0) {
				grades[q][totalAssign + 1] = 0;
			} else {
				// curved_score formula
				double low_normalMidterm = 0;
				double high_normalMidterm = 0;

				double low_curvedMidterm = 0;
				double high_curvedMidterm = 0;
				if (normalizedScoreMidterm < 2.0 && normalizedScoreMidterm > 1.0) {
					low_normalMidterm = 1.0;
					high_normalMidterm = 2.0;
					low_curvedMidterm = 94;
					high_curvedMidterm = 100;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else if (normalizedScoreMidterm < 1.0 && normalizedScoreMidterm > 0.0) {
					low_normalMidterm = 0.0;
					high_normalMidterm = 1.0;
					low_curvedMidterm = 85;
					high_curvedMidterm = 94;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else if (normalizedScoreMidterm < 0.0 && normalizedScoreMidterm > -1.0) {
					low_normalMidterm = -1.0;
					high_normalMidterm = 0.0;
					low_curvedMidterm = 75;
					high_curvedMidterm = 85;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else if (normalizedScoreMidterm < -1.0 && normalizedScoreMidterm > -1.5) {
					low_normalMidterm = -1.5;
					high_normalMidterm = -1.0;
					low_curvedMidterm = 65;
					high_curvedMidterm = 75;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else if (normalizedScoreMidterm < -1.5 && normalizedScoreMidterm > -2.0) {
					low_normalMidterm = -2.0;
					high_normalMidterm = -1.5;
					low_curvedMidterm = 55;
					high_curvedMidterm = 65;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else if (normalizedScoreMidterm < -2.0 && normalizedScoreMidterm > -3.0) {
					low_normalMidterm = -3.0;
					high_normalMidterm = -2.0;
					low_curvedMidterm = 30;
					high_curvedMidterm = 55;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				} else {
					low_normalMidterm = -4.0;
					high_normalMidterm = -3.0;
					low_curvedMidterm = 0;
					high_curvedMidterm = 30;
					grades[q][totalAssign + 1] = (((normalizedScoreMidterm - low_normalMidterm)
							/ (high_normalMidterm - low_normalMidterm)) * (high_curvedMidterm - low_curvedMidterm))
							+ low_curvedMidterm;
				}

			}
		}

		// final average

		double finalAverage = final_ETotal / num_students;
		double finalSquared = 0;
		// (xi -u) squared
		double[] finalList2 = new double[num_students];
		for (int x = 0; x < num_students; x++) {
			finalList2[x] = (grades[x][totalAssign + 2] - finalAverage) * (grades[x][totalAssign + 2] - finalAverage);
		}
		// add up the squared numbers

		for (int x = 0; x < num_students; x++) {
			finalSquared += finalList2[x];
		}

		// Divide by the total students
		double finalSD = Math.sqrt(finalSquared / num_students);

		for (int w = 0; w < num_students; w++) {
			double normalizedScoreFinal = (grades[w][totalAssign + 2] - finalAverage) / finalSD;
			if (normalizedScoreFinal >= 2.0) {
				grades[w][totalAssign + 2] = 100;
			} else if (normalizedScoreFinal == 1.0) {
				grades[w][totalAssign + 2] = 94;
			} else if (normalizedScoreFinal == 0) {
				grades[w][totalAssign + 2] = 85;
			} else if (normalizedScoreFinal == -1.0) {
				grades[w][totalAssign + 2] = 75;
			} else if (normalizedScoreFinal == -1.5) {
				grades[w][totalAssign + 2] = 65;
			} else if (normalizedScoreFinal == -2.0) {
				grades[w][totalAssign + 2] = 55;
			} else if (normalizedScoreFinal == -3.0) {
				grades[w][totalAssign + 2] = 30;
			} else if (normalizedScoreFinal <= -4.0) {
				grades[w][totalAssign + 2] = 0;
			} else {
				// curved_score formula
				double low_normalFinal = 0;
				double high_normalFinal = 0;

				double low_curvedFinal = 0;
				double high_curvedFinal = 0;
				if (normalizedScoreFinal < 2.0 && normalizedScoreFinal > 1.0) {
					low_normalFinal = 1.0;
					high_normalFinal = 2.0;
					low_curvedFinal = 94;
					high_curvedFinal = 100;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else if (normalizedScoreFinal < 1.0 && normalizedScoreFinal > 0.0) {
					low_normalFinal = 0.0;
					high_normalFinal = 1.0;
					low_curvedFinal = 85;
					high_curvedFinal = 94;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else if (normalizedScoreFinal < 0.0 && normalizedScoreFinal > -1.0) {
					low_normalFinal = -1.0;
					high_normalFinal = 0.0;
					low_curvedFinal = 75;
					high_curvedFinal = 85;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else if (normalizedScoreFinal < -1.0 && normalizedScoreFinal > -1.5) {
					low_normalFinal = -1.5;
					high_normalFinal = -1.0;
					low_curvedFinal = 65;
					high_curvedFinal = 75;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else if (normalizedScoreFinal < -1.5 && normalizedScoreFinal > -2.0) {
					low_normalFinal = -2.0;
					high_normalFinal = -1.5;
					low_curvedFinal = 55;
					high_curvedFinal = 65;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else if (normalizedScoreFinal < -2.0 && normalizedScoreFinal > -3.0) {
					low_normalFinal = -3.0;
					high_normalFinal = -2.0;
					low_curvedFinal = 30;
					high_curvedFinal = 55;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				} else {
					low_normalFinal = -4.0;
					high_normalFinal = -3.0;
					low_curvedFinal = 0;
					high_curvedFinal = 30;
					grades[w][totalAssign
							+ 2] = (((normalizedScoreFinal - low_normalFinal) / (high_normalFinal - low_normalFinal))
									* (high_curvedFinal - low_curvedFinal)) + low_curvedFinal;
				}

			}
		}

		// part 3

		for (int y = 0; y < num_students; y++) {

			double waverage = (.4 * assign[y] + .15 * grades[y][0] + .2 * grades[y][totalAssign + 1]
					+ .25 * grades[y][totalAssign + 2]) / 100;

			if (waverage >= .94) {
				a++;
			} else if (waverage >= .90 && waverage < .94) {
				aM++;
			} else if (waverage >= .86 && waverage < .90) {
				bP++;
			} else if (waverage >= .83 && waverage < .86) {
				b++;
			} else if (waverage >= .80 && waverage < .83) {
				bM++;
			} else if (waverage >= .76 && waverage < .80) {
				cP++;
			} else if (waverage >= .73 && waverage < .76) {
				c++;
			} else if (waverage >= .70 && waverage < .73) {
				cM++;
			} else if (waverage >= .65 && waverage < .70) {
				dP++;
			} else if (waverage >= .60 && waverage < .65) {
				d++;
			} else {
				f++;
			}
		}

		System.out.println("A : " + a);
		System.out.println("A-: " + aM);
		System.out.println("B+: " + bP);
		System.out.println("B : " + b);
		System.out.println("B-: " + bM);
		System.out.println("C+: " + cP);
		System.out.println("C : " + c);
		System.out.println("C-: " + cM);
		System.out.println("D+: " + dP);
		System.out.println("D : " + d);
		System.out.println("F : " + f);

	}

}
