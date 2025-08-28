package br.com.mariojp.solid.srp;

public class ReceiptService {
	public String generate(Order order) {
		double subtotal = order.getItems().stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
		Double taxValueSystem;
		try {
			taxValueSystem = Double.parseDouble(System.getProperty("tax.rate"));
		} catch (Exception e) {
			taxValueSystem = 0.10;
		}

		double tax = TaxCalculator.calculateTax(subtotal, taxValueSystem); //Taxa 10 fixa :(
		double total = subtotal + tax;

		return ReceiptFormatter.formatterRec(order, tax, subtotal, total);
	}
}
