// Trying to access private model directly (will cause compilation error)
    // System.out.println("Model: " + model); // Error: model has private access in Vehicle

    // Accessing model using getter
    System.out.println("Model: " + getModel());
}

public static void main(String[] args) {
    // Creating Car object
    Car car = new Car();

    // Setting values using setters where required or direct access where allowed
    car.setModel("Camry");
    car.brand = "Toyota"; // Protected field accessible in same package
    car.year = 2023;      // Public field
    car.setPrice(25000.0); // Using setter for price

    // Displaying all accessible fields
    car.displayDetails();
}