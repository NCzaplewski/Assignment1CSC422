package petdatabase;

/**
 *
 * @author natas
 */

import java.util.Scanner;
public class PetDatabase {
    static int CAPACITY;
    static Pet[] pets = new Pet[100];
    static int petCount = 0;
    static int rowCount = 0;
    static Scanner s = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            System.out.println("Pet Database Program.");
            int program = getUserChoice();
            do {
            switch (program) {
            case 1: showAllPets();
                    break;
            case 2: addPets();
                    break;
            case 3: //updatePets();
                    break;
            case 4: //removePet();
                    break;
            case 5: //searchPetsByName();
                    break;
            case 6: //searchPetsByAge();
                    break;
            case 7: System.out.println("Goodbye!");
                    break;
            }
            program = getUserChoice();
            }
            while (program != 7);
    }
    //List options, take choice input
    private static int getUserChoice() {
        System.out.println("\nWhat would you like to do? \n" +
        "\n 1. View all pets" +
        "\n 2. Add more pets: Type 'done' when done." +
        "\n 3. Update an existing pet" +
        "\n 4. Remove an existing pet" +
        "\n 5. Search pets by name" +
        "\n 6. Search pets by age" +
        "\n 7. Exit program");
        System.out.println("Your choice: ");
        int program = s.nextInt();
        return program;
    }
            //Add pets
    public static void addPets() {
        rowCount = 0;
        String name = " ";
        for (int id = 0; id < pets.length ; id++) {
                if (petCount != 0) {
                        id = petCount;
                }
                System.out.println("Add pet (name, age): ");
                name = s.next();
        if (name.equalsIgnoreCase("done")) {
            System.out.printf("%s pet(s) added.", rowCount);
            break;
        }
        int age = s.nextInt();
        try {
            pets[id] = new Pet(name, 0);
            pets[id].setAge(age);
            petCount++;
            rowCount++;
        } catch (InvalidAgeException e) {
            System.out.println("Invalid age. Must be between 1 and 50.");
            id--;
        } 
        }
    }
    private static void printTableHeader() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
    }
    private static void printTableRow(int id, String name, int age) {
        System.out.printf("| %-3d| %-10s|%4d | \n", id, name, age);
    }
    private static void printTableFooter(int rowCount) {
        System.out.println("+----------------------+\n");
        System.out.printf("%s rows in set.\n", rowCount);
    }

    //Show all pets
    private static void showAllPets() {
        rowCount = 0;
        printTableHeader();
        for (int id = 0; id < petCount; id++) {
                printTableRow(id, pets[id].getName(), pets[id].getAge());
                rowCount++;
        }
        printTableFooter(rowCount);
    }

}
