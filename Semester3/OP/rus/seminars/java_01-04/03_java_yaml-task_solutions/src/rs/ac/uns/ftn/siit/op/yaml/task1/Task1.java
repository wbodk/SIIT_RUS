package rs.ac.uns.ftn.siit.op.yaml.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class Task1 {

	public static void main(String args[]) throws IOException {
		Yaml yaml = new Yaml(new Constructor(Invoice.class));

		try (InputStream input = new FileInputStream(new File("resources/invoice.yaml"));
				Writer writer = new FileWriter("resources/invoice_mod.yaml")) {

			Invoice invoice = (Invoice) yaml.load(input);
			System.out.println(invoice);

			Address newAddress = recieveAddressInput();
			invoice.billTo.address = newAddress;

			yaml.dump(invoice, writer);
		}

	}

	public static Address recieveAddressInput() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("State:");
			String state = sc.nextLine();
			System.out.println("City:");
			String city = sc.nextLine();
			System.out.println("Postal Code:");
			String postalCode = sc.nextLine();
			System.out.println("Lines:");
			String lines = sc.nextLine();

			return new Address(lines, city, state, postalCode);
		}

	}

}
