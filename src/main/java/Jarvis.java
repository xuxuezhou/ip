import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        String logo = "      ____.                  .__        \n"
                + "     |    |____ __________  _|__| ______\n"
                + "     |    \\__  \\_  __ \\  \\/ /  |/  ___/\n"
                + " /\\__|    |/ __ \\|  | \\/\\   /|  |\\___ \\ \n"
                + " \\________(____  /__|    \\_/ |__/____  >\n"
                + "               \\/                    \\/ \n";

        System.out.println(logo);
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Jarvis");
        System.out.println("What can I do for you?");
//        System.out.println("Type 'bye' to exit.");
        System.out.println("_________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print("> ");
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("_________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_________________________________________");
                break;
            } else {
                System.out.println("_________________________________________");
                System.out.println(userInput);
                System.out.println("_________________________________________");
            }
        }

        scanner.close();
    }
}
