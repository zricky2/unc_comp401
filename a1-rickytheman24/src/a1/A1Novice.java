package a1;
import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		calculate(scanner);

	}

	public static void calculate(Scanner scanner) {
		int num_people = scanner.nextInt();

		for (int i = 0; i < num_people; i++) {
			String f_name = scanner.next();
			String l_name = scanner.next();
			char letter = f_name.charAt(0);
			double assign = scanner.nextDouble();
			double partic = scanner.nextDouble();
			double midterm = scanner.nextDouble();
			double final_E = scanner.nextDouble();
			double waverage = (.4 * assign + .15 * partic + .2 * midterm + .25 * final_E) / 100;
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
