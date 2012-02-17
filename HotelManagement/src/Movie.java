// From book: 'Refactoring' by Martin Fowler
// This is the original code before refactoring begins

public class Movie {

	private static final int CHILDRENS = 2;
	private static final int NEWRELEASE = 1;
	private static final int REGULAR = 0;

	private String _title;
	private int _priceCode;

	private Movie(String title, int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(int arg) {
		_priceCode = arg;
	}
	public String getTitle() {
		return _title;
	}

	double charge(int daysRented) {
		double result = 0;
		switch (_priceCode) {
		case REGULAR:
			result += 2;
			if (daysRented > 2)
				result += (daysRented - 2) * 1.5;
			break;
		case NEWRELEASE:
			result += daysRented * 3;
			break;
		case CHILDRENS:
			result += 1.5;
			if (daysRented > 3)
				result += (daysRented - 3) * 1.5;
			break;

		}
		return result;
	}
	

	public static Movie createChildrenMovie(String title) {
		return new Movie(title, CHILDRENS);
	}

	public static Movie createRegularMovie(String title) {
		return new Movie(title, REGULAR);
	}

	public static Movie createNewReleaseMovie(String title) {
		return new Movie(title, NEWRELEASE);
	}

	public int getRenterPoints(int daysRented) {
		if ((getPriceCode() == Movie.NEWRELEASE) && daysRented > 1)  return 2;
		else return 1;
	}


}
