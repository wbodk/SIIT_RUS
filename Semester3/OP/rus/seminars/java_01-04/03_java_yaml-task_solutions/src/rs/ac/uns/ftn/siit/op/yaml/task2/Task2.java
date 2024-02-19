package rs.ac.uns.ftn.siit.op.yaml.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;
import rs.ac.uns.ftn.siit.op.yaml.example03.Product;

public class Task2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Yaml yaml = new Yaml();

		try (InputStream inputExchange = new FileInputStream(new File("resources/exchange.yaml"));) {
			Map<String, Double> exchangeValues = (Map<String, Double>) yaml.load(inputExchange);
			String selectedCurrency = getSelectedCurrencyValue(exchangeValues);

			Invoice updatedInvoice = updateInvoice(exchangeValues.get(selectedCurrency).floatValue());

			try (Writer writer = new FileWriter("resources/invoice_" + selectedCurrency + ".yaml")) {
				yaml.dump(updatedInvoice, writer);
			}

		} catch (IllegalArgumentException ie) {
			System.out.println(ie.getMessage());
		}

	}

	static Invoice updateInvoice(float currencyValue) throws FileNotFoundException, IOException {
		try (InputStream inputInvoice = new FileInputStream(new File("resources/invoice.yaml"));) {
			Yaml yaml = new Yaml(new Constructor(Invoice.class));
			Invoice invoice = (Invoice) yaml.load(inputInvoice);

			for (Product product : invoice.product) {
				product.price = product.price * currencyValue;
			}

			invoice.tax = invoice.tax * currencyValue;
			invoice.total = invoice.total * currencyValue;

			return invoice;
		}

	}

	static String getSelectedCurrencyValue(Map<String, Double> exchangeValues) {
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Choose a currency: " + exchangeValues.keySet());
			String selectedCurrency = sc.nextLine().trim();

			if (!exchangeValues.containsKey(selectedCurrency)) {
				throw new IllegalArgumentException("Selected currency doesn't exist!");
			}

			return selectedCurrency;
		}

	}
}
