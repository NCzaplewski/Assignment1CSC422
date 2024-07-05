
package petdatabase;

/**
 *
 * @author natas
 */
public class Pet {
    //Data fields
    private String name;
    private int age;
    
    //constructor
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    //getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    
    //setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) throws petdatabase.InvalidAgeException {
        if (age < 1 || age > 50) {
            throw new petdatabase.InvalidAgeException();
        }
        else {
            this.age = age;
        }
    }
}

