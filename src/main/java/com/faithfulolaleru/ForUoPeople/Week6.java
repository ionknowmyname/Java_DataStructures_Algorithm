package com.faithfulolaleru.ForUoPeople;

import java.util.Scanner;

public class Week6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the type of vehicle (Car/Motorcycle/Truck):");
        String vehicleType = scanner.nextLine();

        Vehicle vehicle = null;

        switch (vehicleType.toLowerCase()) {
            case "car":
                vehicle = new Car("Toyota", "Camry", 2021);
                ((CarVehicle) vehicle).setNumberOfDoors(4);
                ((CarVehicle) vehicle).setFuelType("Petrol");
                break;

            case "motorcycle":
                vehicle = new Motorcycle("Harley", "Street 750", 2020);
                ((MotorVehicle) vehicle).setNumberOfWheels(2);
                ((MotorVehicle) vehicle).setMotorcycleType("Cruiser");
                break;

            case "truck":
                vehicle = new Truck("Volvo", "FH16", 2019);
                ((TruckVehicle) vehicle).setCargoCapacity(18.0);
                ((TruckVehicle) vehicle).setTransmissionType("Manual");
                break;

            default:
                System.out.println("Invalid vehicle type.");
                break;
        }

        if (vehicle != null) {
            System.out.println("Vehicle Details:");
            System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Year: " + vehicle.getYear());

            if (vehicle instanceof CarVehicle) {
                System.out.println("Number of Doors: " + ((CarVehicle) vehicle).getNumberOfDoors());
                System.out.println("Fuel Type: " + ((CarVehicle) vehicle).getFuelType());
            } else if (vehicle instanceof MotorVehicle) {
                System.out.println("Number of Wheels: " + ((MotorVehicle) vehicle).getNumberOfWheels());
                System.out.println("Motorcycle Type: " + ((MotorVehicle) vehicle).getMotorcycleType());
            } else if (vehicle instanceof TruckVehicle) {
                System.out.println("Cargo Capacity: " + ((TruckVehicle) vehicle).getCargoCapacity() + " tons");
                System.out.println("Transmission Type: " + ((TruckVehicle) vehicle).getTransmissionType());
            }
        }

        scanner.close();
    }

    public interface Vehicle {
        String getMake();
        String getModel();
        int getYear();
    }

    public interface CarVehicle {
        void setNumberOfDoors(int doors);
        int getNumberOfDoors();
        void setFuelType(String fuelType);
        String getFuelType();
    }

    public interface MotorVehicle {
        void setNumberOfWheels(int wheels);
        int getNumberOfWheels();
        void setMotorcycleType(String type);
        String getMotorcycleType();
    }

    public interface TruckVehicle {
        void setCargoCapacity(double capacity);
        double getCargoCapacity();
        void setTransmissionType(String type);
        String getTransmissionType();
    }

    public static class Car implements Vehicle, CarVehicle {
        private String make;
        private String model;
        private int year;
        private int numberOfDoors;
        private String fuelType;

        public Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        @Override
        public String getMake() { return make; }
        @Override
        public String getModel() { return model; }
        @Override
        public int getYear() { return year; }

        @Override
        public void setNumberOfDoors(int doors) { this.numberOfDoors = doors; }
        @Override
        public int getNumberOfDoors() { return numberOfDoors; }
        @Override
        public void setFuelType(String fuelType) { this.fuelType = fuelType; }
        @Override
        public String getFuelType() { return fuelType; }
    }

    public static class Motorcycle implements Vehicle, MotorVehicle {
        private String make;
        private String model;
        private int year;
        private int numberOfWheels;
        private String motorcycleType;

        public Motorcycle(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        @Override
        public String getMake() { return make; }
        @Override
        public String getModel() { return model; }
        @Override
        public int getYear() { return year; }

        @Override
        public void setNumberOfWheels(int wheels) { this.numberOfWheels = wheels; }
        @Override
        public int getNumberOfWheels() { return numberOfWheels; }
        @Override
        public void setMotorcycleType(String type) { this.motorcycleType = type; }
        @Override
        public String getMotorcycleType() { return motorcycleType; }
    }

    public static class Truck implements Vehicle, TruckVehicle {
        private String make;
        private String model;
        private int year;
        private double cargoCapacity;
        private String transmissionType;

        public Truck(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        @Override
        public String getMake() { return make; }
        @Override
        public String getModel() { return model; }
        @Override
        public int getYear() { return year; }

        @Override
        public void setCargoCapacity(double capacity) { this.cargoCapacity = capacity; }
        @Override
        public double getCargoCapacity() { return cargoCapacity; }
        @Override
        public void setTransmissionType(String type) { this.transmissionType = type; }
        @Override
        public String getTransmissionType() { return transmissionType; }
    }
}
