import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

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

public class Program {
    
    public static void main(String[] args)  {
        // load the data of the four Lists
        try {
            SystemManagement.loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int userChoice=0;
        Scanner scanner = new Scanner (System.in);

            // prompt the user to choose between different options
            while (userChoice !=4) {
                System.out.println("Choose your role\n 1. Patient\n 2. Doctor\n 3. Receptionist\n 4. Quit");
                userChoice = scanner.nextInt(); 
                
                switch (userChoice) {
                    case 1:
                        handlePatient(scanner);
                        break;
                    case 2:
                        handleDoctor(scanner);
                        break;
                    case 3:
                        handleReceptionist(scanner);
                        break;
                    case 4:
                        System.out.println("Your are exiting the program, thank you for your visit!");
                        return;
                    default:
                        System.out.println("Please enter a valid choice: 1, 2, 3 or 4");
                }                
            }
    } 

    public static void handlePatient(Scanner scanner){
        System.out.println("******* Patient menu *******");

        // check if the patient ID is valid
        String patientID="";
        boolean isPatientIDValid = false;

        do {
            System.out.println("Please enter your patient ID");
            patientID = scanner.next().toUpperCase();
            isPatientIDValid = SystemManagement.verifyPatientID(patientID);
        } while (!isPatientIDValid);
        
        boolean isPatientMenu = true;
        while (isPatientMenu && isPatientIDValid){
            System.out.println("Please choose an option \n" +
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
                    return;
                default:
                    System.out.println("Please enter a valid choice: 1,2 or 3");
            }
        }
    }


    public static void handleDoctor(Scanner scanner){
        System.out.println("******* Doctor menu *******");
        
        // check if the doctor ID is valid
        String doctorID="";
        boolean isDoctorIDValid = false;
        
        do {
            System.out.println("Please enter your doctor ID");
            doctorID = scanner.next().toUpperCase();
            isDoctorIDValid = SystemManagement.verifyDoctorID(doctorID);
        } while (!isDoctorIDValid);
        
        boolean isDoctorMenu = true;
        while (isDoctorMenu && isDoctorIDValid){
            System.out.println("Please choose an option \n" +
                                " 1. view your appointments \n" +
                                " 2. provide a treatemnt \n" +
                                " 3. retun to the main menu ");
            int userChoice;
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("This is the list of your appointments:");
                    System.out.println(String.format("%-15s %-12s %-12s %-15s %-30s ", 
                    "Appointment ID", "Doctor ID", "Patient ID", "Date", "Time")); 

                    String appointmentsList = SystemManagement.viewDoctorAppointments(doctorID);
                    System.out.println(appointmentsList);
                    break;
                case 2:
                    System.out.println("Please enter the patient ID");
                    String patientID = scanner.next();
                    scanner.nextLine();
                    System.out.println("Please enter the prescription");
                    String prescription = scanner.nextLine();
                    try {
                        SystemManagement.provideTreatment(doctorID, patientID,prescription);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please enter a valid choice: 1, 2 or 3");
            }
        }
    }

    public static void handleReceptionist(Scanner scanner){
        System.out.println("******* Receptionist menu *******");
        
        boolean isReceptionistMenu = true;
        while (isReceptionistMenu){
            System.out.println("Please choose an option \n" +
                                " 1. schedule an appointment \n" +
                                " 2. reschedule an appointment \n" +
                                " 3. cancel an appointment \n" +
                                " 4. register a doctor \n" +
                                " 5. remove a doctor \n" +
                                " 6. register a patient \n" +
                                " 7. retun to the main menu ");

            int userChoice;
            userChoice = scanner.nextInt();

            switch (userChoice) {
                // schedule appointment
                case 1:
                    System.out.println("Please enter the doctor ID");
                    String doctorID = scanner.next();

                    System.out.println("Please enter the patient ID");
                    String patientID = scanner.next();

                    System.out.println("Please enter the date of the appointment");
                    String dateString = scanner.next();
                    LocalDate date = LocalDate.parse(dateString);

                    System.out.println("Please enter the time of the appointment (HH:mm)");
                    String timeString = scanner.next();
                    LocalTime time = LocalTime.parse(timeString);
            
                    try {
                        SystemManagement.scheduleAppointment(doctorID, patientID, date, time);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                    
                // reschedule appointment
                case 2:
                    System.out.println("Please enter the appointment ID");
                    String appointmentID = scanner.next();

                    System.out.println("Please enter the new date of the appointment (YYYY-MM-DD)");
                    dateString = scanner.next();
                    date = LocalDate.parse(dateString);

                    System.out.println("Please enter the new time of the appointment (HH:mm)");
                    timeString = scanner.next();
                    time = LocalTime.parse(timeString);
            
                    try {
                        SystemManagement.rescheduleAppointment(appointmentID, date, time);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                
                // cancel appointment
                case 3:
                    System.out.println("Please enter the appointment ID");
                    appointmentID = scanner.next();
            
                    try {
                        SystemManagement.cancelAppointment(appointmentID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                // register a doctor
                case 4:
                    System.out.println("Please enter the doctor's first name:");
                    String firstname = scanner.next();

                    System.out.println("Please enter the doctor's last name:");
                    String lastname = scanner.next();

                    System.out.println("Please enter the doctor's date of birth (YYYY-MM-DD):");
                    dateString = scanner.next();
                    LocalDate dateOfBirth = LocalDate.parse(dateString);

                    System.out.println("Please enter the doctor's gender (MALE, FEMALE, UNSPECIFIED):");
                    String genderString = scanner.next();
                    Person.Gender gender = Person.Gender.valueOf(genderString.toUpperCase()) ;

                    System.out.println("Please enter the doctor's email:");
                    String email = scanner.next();

                    System.out.println("Please enter the doctor's phone:");
                    int phone = Integer.parseInt(scanner.next());
                    
                    scanner.nextLine();
                    
                    System.out.println("Please enter the doctor's specialty:");
                    String specialty = scanner.nextLine();
            
                    try {
                        SystemManagement.registerDoctor(firstname, lastname, dateOfBirth, gender, email, phone, specialty);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;    

                // remove doctor
                case 5:
                    System.out.println("Please enter the doctor ID");
                    doctorID = scanner.next();
            
                    try {
                        SystemManagement.removeDoctor(doctorID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                // register a patient
                case 6:
                    System.out.println("Please enter the patient's first name:");
                    firstname = scanner.next();

                    System.out.println("Please enter the patient's last name:");
                    lastname = scanner.next();

                    System.out.println("Please enter the patient's date of birth (YYYY-MM-DD):");
                    dateString = scanner.next();
                    dateOfBirth = LocalDate.parse(dateString);

                    System.out.println("Please enter the patient's gender (MALE, FEMALE, UNSPECIFIED):");
                    genderString = scanner.next();
                    gender = Person.Gender.valueOf(genderString.toUpperCase()) ;

                    System.out.println("Please enter the patient's email:");
                    email = scanner.next();

                    System.out.println("Please enter the patient's phone:");
                    phone = Integer.parseInt(scanner.next());

                    scanner.nextLine();

                    System.out.println("Please enter the patient's insurance company:");
                    String insuranceCompany = scanner.nextLine();
            
                    try {
                        SystemManagement.registerPatient(firstname, lastname, dateOfBirth, gender, email, phone, insuranceCompany);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    return;
                default:
                    System.out.println("Please enter a valid choice between 1 and 7");
            }
        }
    }

}
