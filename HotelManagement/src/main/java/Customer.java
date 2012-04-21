

import java.util.Vector;

//From book: 'Refactoring' by Martin Fowler
//This is the original code before refactoring begins

public class Customer {

	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentals.addElement(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		for(Rental rental : rentals) {
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.calculateAmount()) + "\n";
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
		result += "You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter points";

		return result;
	}
	
	public String HtmStatement() {
		String result = "<h1>Rental Record for " + getName() + "</h1>";

		for(Rental rental : rentals) {
			// show figures for this rental
			result += "<p>" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.calculateAmount()) + "</p>";
		}

		// add footer lines
		result += "<p>Amount owed is " + String.valueOf(getTotalAmount()) + "</p>";
		result += "<p>You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter points.</p>";

		return result;
		
	}

	public double getTotalAmount() {
		double totalAmount = 0;
		for(Rental rental : rentals) {
			totalAmount += rental.calculateAmount();
		}
		return totalAmount;
	}

	public int getFrequentRenterPoints() {
		int frequentRenterPoints = 0;
		for(Rental rental : rentals) {
			frequentRenterPoints += rental.calculateRenterPoints();
		}
		return frequentRenterPoints;
	}
	
}
