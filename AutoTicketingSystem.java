import java.util.Scanner;

public class AutoTicketingSystem {

    private static String[][] parkingLot = null;

    private static void createParkingLot(int numberOfSlots) {
        String[][] parkingLotNew = new String[numberOfSlots][2];
        parkingLot = parkingLotNew;
        System.out.println("Created a parking lot with " + numberOfSlots + " slots");

    }

    private static void park(String registrationNumber, String color) {

        for (int i = 0; i < parkingLot.length; i++) {
            if (parkingLot[i][0] == null) {
                parkingLot[i][0] = registrationNumber;
                parkingLot[i][1] = color;
                System.out.println("Allocated slot number: " + (i + 1));
                return;
            }
        }
        System.out.println("Sorry, parking lot is full");
    }

    private static void status() {

        System.out.println("Slot No.\tRegistration No\tColour");
        for (int i = 0; i < parkingLot.length; i++) {
            if (parkingLot[i][0] != null) {
                System.out.println((i + 1) + "\t\t" + parkingLot[i][0] + "\t" + parkingLot[i][1]);
            }
        }
    }

    private static void leave(int slot) {

        parkingLot[slot - 1][0] = null;
        parkingLot[slot - 1][1] = null;
        System.out.println("Slot number " + slot + " is free");

    }

    private static void registrationNumbersForCarsWithColour(String color) {

        for (int i = 0; i < parkingLot.length; i++) {
            if (parkingLot[i][1].equals(color)) {
                System.out.println(parkingLot[i][0]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] tokens = input.split(" ");

            String command = tokens[0];

            try {

                if (command.equals("create_parking_lot")) {
                    createParkingLot(Integer.valueOf(tokens[1]));

                } else if (parkingLot == null) {
                    System.out.println("Parking lot is not created");
                } else if (command.equals("park")) {
                    park(tokens[1], tokens[2]);
                } else if (command.equals("leave")) {
                    leave(Integer.valueOf(tokens[1]));
                } else if (command.equals("status")) {
                    status();
                } else if (command.equals("registration_numbers_for_cars_with_colour")) {
                    registrationNumbersForCarsWithColour(tokens[1]);
                } else {
                    System.err.println("Error! invalid command");
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid Input");
            } catch (Exception e) {
                System.err.println("Error! " + e.getMessage());
            }
        }
        scanner.close();
    }
}
