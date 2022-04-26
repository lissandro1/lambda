package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employed;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String file = sc.nextLine();

		System.out.print("Enter salary: ");
		Double baseSalary = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			List<Employed> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employed(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			List<String> email = list.stream().filter(x -> x.getSalary() > baseSalary)
					.map(x -> x.getEmail()).sorted()
					.collect(Collectors.toList());

			email.forEach(System.out::println);

			Double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);

			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}
}
