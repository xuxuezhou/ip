package jarvis.util;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("> "); // 添加命令提示符
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("      ____.                  .__        ");
        System.out.println("     |    |____ __________  _|__| ______");
        System.out.println("     |    \\__  \\_  __ \\  \\/ /  |/  ___/");
        System.out.println(" /\\__|    |/ __ \\|  | \\/\\   /|  |\\___ \\ ");
        System.out.println(" \\________(____  /__|    \\_/ |__/____  >");
        System.out.println("               \\/                    \\/ ");
        System.out.println("_________________________________________");
        System.out.println("Hello, sir. I am Jarvis, your task assistant.");
        System.out.println("How can I assist you today?");
        System.out.println("_________________________________________");
    }

    public void showLine() {
        System.out.println("_________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
