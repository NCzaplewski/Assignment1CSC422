package petdatabase;
//Commenting out unnecessary features
/**
 *
 * @author natas
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PetDatabase {
    static Pet[] pets = new Pet[5];
    static int petCount = 0;
    static int rowCount = 0;
    static Scanner s = new Scanner(System.in);
    static final String FILE = "C:\\Users\\natas\\Documents\\NetBeansProjects\\Assignment1CSC422\\PetDatabase\\src\\petdatabase\\pets.txt";
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
            System.out.println("Pet Database Program.");
            int program;
            readPets();
            do {
                program = getUserChoice();
                switch (program) {
                    case 1: showAllPets();
                            break;
                    case 2: addPets();
                            break;
                    /*case 3: updatePets();
                            break;*/
                    case 3: removePet();
                            break;
                    /*case 5: searchPetsByName();
                            break;
                    case 6: searchPetsByAge();
                            break;*/
                    case 4: savePets();
                            System.out.println("Goodbye!");
                            break;
                    default:
                            System.out.println("Invalid choice. Try again.");
                }
            }
            while (program != 4);
    }
    //List options, take choice input
    private static int getUserChoice() {
        System.out.println("\nWhat would you like to do? \n" +
        "\n 1. View all pets" +
        "\n 2. Add more pets: Type 'done' when done." +
        //"\n 3. Update an existing pet" +
        "\n 3. Remove an existing pet" +
        //"\n 5. Search pets by name" +
        //"\n 6. Search pets by age" +
        "\n 4. Exit program");
        System.out.println("Your choice: ");
        int program = s.nextInt();
        return program;
    }
            //Add pets
    public static void addPets() {
        rowCount = 0;
        //error if database if full, exit method
        if (petCount>= pets.length) {
            System.out.println("Error: Database is full.");
            return;
        }
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
        int age;
        try {
            age = s.nextInt();
            //validate age
            if (age < 1 || age > 20) {
                throw new InvalidAgeException();
            }
            pets[id] = new Pet(name, 0);
            pets[id].setAge(age);
            petCount++;
            rowCount++;
        } catch (InvalidAgeException e) {
            System.out.println("Error: Age must be between 1 and 20.");
            id--;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for age.");
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
    
    //Search names of objects regardless of case
    /*private static void searchPetsByName(){
        rowCount = 0;
        System.out.println("Enter a name to search: ");
        String searchName = s.next();
        printTableHeader();
        for (int id = 0; id < petCount; id++) {
            if (pets[id].getName().equalsIgnoreCase(searchName)) {
                    rowCount++;
                    printTableRow(id, pets[id].getName(), pets[id].getAge());
            }
        }
        printTableFooter(rowCount);
    }
    //Search ages of objects
    private static void searchPetsByAge() {
        rowCount = 0;
        System.out.println("Enter an age to search: ");
        int searchAge = s.nextInt();
        printTableHeader();
        for (int id = 0; id < petCount; id++) {
            if (pets[id].getAge() == searchAge) {
                    rowCount++;
                    printTableRow(id, pets[id].getName(), pets[id].getAge());
            }
        }
        printTableFooter(rowCount);
    }
    */
    //Remove object
    private static void removePet() {
        showAllPets();
        System.out.println("Enter the pet ID to remove: ");
        int id = s.nextInt();

        try {
            if (id < 0 || id >= petCount) {
                //Exception if ID doesn't exist
                throw new InvalidIDException();
            }

            System.out.printf("%s %d is removed.\n", pets[id].getName(), pets[id].getAge());
            petCount--;

            // Shift elements to fill the gap left by the removed pet
            for (int i = id; i < petCount; i++) {
                pets[i] = pets[i + 1];
            }
        } catch (InvalidIDException e) {
            System.out.println("Error: ID " + id + " does not exist. Choose an ID from the available list.");
            removePet();  
        }
    }
    //Update pets
    /*public static void updatePets() {
            showAllPets();
            String name = " ";
            System.out.println("Enter the pet ID you'd like to update: ");
            int id = s.nextInt();
            System.out.println("Enter new name and new age: ");
            name = s.next();
            int age = s.nextInt();
            pets[id] = new Pet(name, age);
    }
    */
    //Save pets
    private static void savePets() {
        try (FileWriter writer = new FileWriter(FILE)) {
            for (int i = 0; i < petCount; i++) {
                writer.write(pets[i].getName() + 
                        " " + pets[i].getAge() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving pets to file." + e.getMessage());
        }
    }
    
    //Load pets
    private static void readPets() {
        File file = new File(FILE);
        try (Scanner input = new Scanner(file)){
            petCount = 0; 
            //read through file
            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] split = line.split(" ");
                if (split.length == 2){
                    String name = split[0];
                    int age = Integer.parseInt(split[1]);
                    pets[petCount++] = new Pet(name, age);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + e.getMessage());
        }
    }
}
