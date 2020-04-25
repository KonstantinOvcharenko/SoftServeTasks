package annotations;

// Create class with new annotations for setting
@InitClass
public class Car {

	@InitField
	String mark;

	@InitField
	String model;

	@InitField
	int price;

	@InitField
	int year;

	@Override
	public String toString() {
		return "Car, mark=" + mark + ", model=" + model + ", price=" + price + ", year=" + year;
	}
}
