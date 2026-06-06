import java.util.Scanner;

public class converter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Choose your converter:
                    1. Km to miles
                    2. °C to °F
                    3. Kg to LB
                    4. Quit
                    """);

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Please, type your value in km: ");
                    double km = scanner.nextDouble();
                    double miles = km * 0.621371;
                    System.out.println("Result: " + miles + " miles");
                    break;

                case 2:
                    System.out.print("Please, type your value in °C: ");
                    double celsius = scanner.nextDouble();
                    double fahrenheit = (celsius * 1.8) + 32;
                    System.out.println("Result: " + fahrenheit + " °F");
                    break;

                case 3:
                    System.out.print("Please, type your value in kg: ");
                    double kg = scanner.nextDouble();
                    double lb = kg * 2.205;
                    System.out.println("Result: " + lb + " lb");
                    break;

                case 4:
                    scanner.close();
                    return; // quitte le programme

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
