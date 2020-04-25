package annotations;

import java.io.IOException;

// Demonstration
public class Demo {
	public static void main(String[] args) {
		Car car = new Car();
		try {
			new Initialization().setFields(car, Car.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(car);
	}
}
