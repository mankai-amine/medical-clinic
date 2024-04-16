import java.time.LocalDate;
import java.time.Period;


abstract class Person {
    
    public enum Gender{
        MALE,
        FEMALE,
        UNSPECIFIED;
    }

    // data members 
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private int phone;

    //default constructor
    public Person(){
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = LocalDate.now();
        this.gender = Gender.MALE;
        this.email = "";
        this.phone=0;
    }

    //constructor with parameters 
    public Person(String firstName, String lastName, int yearOfBirth,int monthOfBirth, int dayOfBirth, Gender gender, String email, int phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(yearOfBirth, monthOfBirth, dayOfBirth); 
        setGender(gender); 
        setEmail(email);
        setPhone(phone);
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(int yearOfBirth,int monthOfBirth, int dayOfBirth) {
        this.dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

    //getters 
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public Gender getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    
    public int getPhone() {
        return phone;
    }

    //calculate the age 
    public int getAge(){
        LocalDate today = LocalDate.now();
        Period duration = Period.between(dateOfBirth, today);
        return duration.getYears();
    }

    //override the method toString
    @Override
    public String toString() {
        return 
                "firstName= " + firstName + '\'' +
                ", lastName= " + lastName + '\'' +
                ", dateOfBirth= " + dateOfBirth +
                ", gender= " + gender + '\''+
                ", email= " + email + '\'' +
                ", phone= " + phone + '\'' ;
    }

}