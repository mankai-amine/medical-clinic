import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class main {
    
    /*
     * When we run the program, this is what happens:
     * 1. We load the data from the four .txt files (the four Lists are therefore initialized) 
     * 2. in the main menu, we give the user the choice between 3 roles: patient, doctor, receptionist
     * 3. if the user is a patient, he can:
     *      - view his medical history (list of all his treatments)
     *      - view all his appointments
     *      - retun to the main menu 
     * 4. if the user is a doctor, he can:
     *      - view all his appointments
     *      - provide a treatment
     *      - retun to the main menu
     * 5. if the user is a receptionist, he can:
     *      - schedule an appointment
     *      - reschedule an appointment
     *      - cancel an appointment
     *      - register a doctor
     *      - remove a doctor
     *      - register a patient
     *      - retun to the main menu
     */

    
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        // load the data of the four Lists
        SystemManagement.loadData();

        int userChoice;
        Scanner scanner = new Scanner (System.in);

        boolean isMainMenu = true;
        while (isMainMenu){
            System.out.println("Choose your role\n 1. Patient\n 2. Doctor\n 3. Receptionist\n 4. Quit");
            userChoice = scanner.nextInt(); 
            
            switch (userChoice) {
                case 1:
                    handlePatient(scanner);
                    //break;
                case 2:
                    //handleDoctor();
                case 3:
                    //handleReceptionist();
                case 4:
                    System.out.println("You decided to quit the program. Thank you for your visit!");
                    isMainMenu = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice: 1, 2, 3 or 4");
            }
        }

    } 

    public static void handlePatient(Scanner scanner){
        System.out.println("******* Patient menu *******");
        
        System.out.println("Please enter your patient ID");
        String patientID = scanner.next();
        
        // check if the patientID is valid //

        boolean isPatientMenu = true;
        while (isPatientMenu){
            System.out.println("What do you want to do? \n" +
                                " 1. view your medical history \n" +
                                " 2. view your appointments \n" +
                                " 3. retun to the main menu ");
            int userChoice;
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("This is your medical history:");
                    System.out.println(String.format("%-15s %-12s %-12s %-15s %-50s", 
                    "Treatment ID", "Doctor ID", "Patient ID", "Date", "Prescription")); 

                    String medicalHistory = SystemManagement.viewMedicalHistory(patientID);
                    System.out.println(medicalHistory);
                    break;
                case 2:
                    System.out.println("This is the list of your appointments:");
                    System.out.println(String.format("%-15s %-12s %-12s %-15s %-15s ", 
                    "Appointment ID", "Doctor ID", "Patient ID", "Date", "Time")); 

                    String appointmentsList = SystemManagement.viewAppointmentsList(patientID);
                    System.out.println(appointmentsList);
                    break;
                case 3:
                    //isPatientMenu = false;
                    return;
                default:
                    System.out.println("Please enter a valid choice: 1,2 or 3");
            }
        }

        
        
    }


}
