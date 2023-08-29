package pages;

import java.util.Scanner;

public class MainMenu {

    Scanner scanner;
    public MainMenu(){
        scanner = new Scanner(System.in);
        String scanned;

        while (true){
            System.out.println("Main Menu");
            System.out.println();
            System.out.println("1. Play New Restaurant");
            System.out.println("2. High Score");
            System.out.println("3. Exit");
            System.out.println("Input [1..3]: ");

            scanned = scanner.nextLine();

            if(scanned.equals("1")) {
                new PlayMenu();
            }
            else if(scanned.equals("2")){
                new HighscoreMenu();
            } else if(scanned.equals("3")){
                break;
            }
        }
        scanner.close();
    }
}
