import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class SystemManagement {

    static List<Doctor> doctors = new ArrayList<>();
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<Treatment> treatments = new ArrayList<>();

    private static final String doctorsFileName = "doctors.txt";
    private static final String patientsFileName = "patients.txt";
    private static final String appointmentsFileName = "appointments.txt";
    private static final String treatmentsFileName = "treatments.txt";

    // loadData
    public static void loadData () throws FileNotFoundException, ParseException {
        loadDoctors();
        loadPatients();
        loadAppointments();
        loadTreatments();
    }

    // Load doctors
    public static void loadDoctors() throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader(doctorsFileName) );

        while (scanner.hasNextLine()){
            String firstName,lastName, email , doctorID, specialty;
            int yearOfBirth, monthOfBirth, dayOfBirth, phone;
            Person.Gender gender;

            String line = scanner.nextLine();

            if (!line.isEmpty()){
                String[] lineParts= line.split(",");
                
                firstName = lineParts[0];
                lastName = lineParts[1];
                yearOfBirth = LocalDate.parse(lineParts[2]).getYear();
                monthOfBirth = LocalDate.parse(lineParts[2]).getMonthValue();
                dayOfBirth = LocalDate.parse(lineParts[2]).getDayOfMonth();
                gender = Person.Gender.valueOf(lineParts[3]) ;
                email= lineParts[4];
                phone = Integer.parseInt(lineParts[5]) ;
                doctorID = lineParts[6];
                specialty = lineParts[7];
    
                Doctor doctor = new Doctor(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, doctorID, specialty);
                doctors.add(doctor);
            } else {
                break;
            }
            
        }

        scanner.close();
    }

    // Load patients
    public static void loadPatients() throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader(patientsFileName) );

        while (scanner.hasNextLine()){
            String firstName,lastName, email , patientID, insuranceCompany;
            int yearOfBirth, monthOfBirth, dayOfBirth, phone;
            Person.Gender gender;

            String line = scanner.nextLine();
            String[] lineParts= line.split(",");
            firstName = lineParts[0];
            lastName = lineParts[1];
            yearOfBirth = LocalDate.parse(lineParts[2]).getYear();
            monthOfBirth = LocalDate.parse(lineParts[2]).getMonthValue();
            dayOfBirth = LocalDate.parse(lineParts[2]).getDayOfMonth();
            gender = Person.Gender.valueOf(lineParts[3]) ;
            email= lineParts[4];
            phone = Integer.parseInt(lineParts[5]) ;
            patientID = lineParts[6];
            insuranceCompany = lineParts[7];

            Patient patient = new Patient (firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, patientID, insuranceCompany);
            patients.add(patient);
        }

        scanner.close();
    }

    // Load appointments
    public static void loadAppointments() throws FileNotFoundException, ParseException{
        Scanner scanner = new Scanner(new FileReader(appointmentsFileName) );

        while (scanner.hasNextLine()){
            String appointmentID, doctorID, patientID;
            LocalDate date;
            LocalTime time;

            String line = scanner.nextLine();
            String[] lineParts= line.split(",");
            appointmentID = lineParts[0];
            doctorID = lineParts[1];
            patientID = lineParts[2];
            date = LocalDate.parse(lineParts[3]);
            time = LocalTime.parse(lineParts[4])  ;

            Appointment appointment = new Appointment (appointmentID, doctorID, patientID, date, time);
            appointments.add(appointment);
        }

        scanner.close();
    }

    // Load treatments
    public static void loadTreatments() throws FileNotFoundException, ParseException{
        Scanner scanner = new Scanner(new FileReader(treatmentsFileName) );

        while (scanner.hasNextLine()){
            String treatmentID, doctorID, patientID, prescription;
            LocalDate date;             

            String line = scanner.nextLine();
            String[] lineParts= line.split(",");
            //System.out.println(Arrays.toString(lineParts));

            treatmentID = lineParts[0];
            doctorID = lineParts[1];
            patientID = lineParts[2];
            date = LocalDate.parse(lineParts[3]);
            prescription = lineParts[4];

            Treatment treatment = new Treatment (treatmentID, doctorID, patientID, date, prescription);
            treatments.add(treatment);
        }

        scanner.close();
    }

    // getters
    public static List<Doctor> getDoctors (){
        return doctors;
    }

    public static List<Patient> getPatients (){
        return patients;
    }

    public static List<Appointment> getAllAppointments (){
        return appointments;
    }

    public static List<Treatment> getAllTreatments (){
        return treatments;
    }

    // verify a patient ID
    public static boolean verifyPatientID (String patientID){
        for (Patient patient : patients ){
            if (patientID.equals(patient.getPatientID())){
                return true;
            }
        }
        System.out.println("This Patient ID doesn't exist");
        return false; 
    }

    // verify a doctor ID
    public static boolean verifyDoctorID (String doctorID){
        for (Doctor doctor : doctors ){
            if (doctorID.equals(doctor.getDoctorID())){
                return true;
            }
        }
        System.out.println("This doctor ID doesn't exist");
        return false; 
    }

    // verify an appointment ID
    public static boolean verifyAppointmentID (String appointmentID){
        for (Appointment appointment : appointments ){
            if (appointmentID.equals(appointment.getAppointmentID())){
                return true;
            }
        }
        System.out.println("This appointment ID doesn't exist");
        return false; 
    }

    // get the list of treatments for one particular patient 
    public static String viewMedicalHistory(String patientID){
        //List<Treatment> medicalHistory = new ArrayList<>();
        String str = "";
        
        for (Treatment treatment : treatments ){
            // find the treatments that correspond to that particular patient
            if (treatment.getPatientID().equals(patientID)){
                // add the treatment to the patient's medical history
                //medicalHistory.add(treatment);
                str += treatment.toString();
            }
        }
        
        return str;
    }

    // get the list of appointments for one particular patient
    public static String viewAppointmentsList(String patientID){
        //List<Appointment> appointmentsHistory = new ArrayList<>();
        String str = "";
        
        for (Appointment appointment : appointments ){
            // find the appointments that correspond to that particular patient
            if (appointment.getPatientID().equals(patientID)){
                // add the appointment to the patient's appointments history
                //appointmentsHistory.add(appointment);
                str += appointment.toString() ;
            }
        }
        
        return str;
    }

    // get the list of appointments for one particular doctor
    public static String viewDoctorAppointments(String doctorID){
        // List<Appointment> doctorAppointments = new ArrayList<>();
        String str = "";

        for (Appointment appointment : appointments ){
            // find the appointments that correspond to that particular doctor
            if (appointment.getDoctorID().equals(doctorID)){
                // add the appointment to the doctor's appointments history
                str += appointment.toString() ;
            }
        }
        
        return str;
    }

    // generate a new appointment ID
    public static String generateAppointmentID (){
        int length = appointments.size();
        String lastAppointmentID = appointments.get(length-1).getAppointmentID();
        int lastID = Integer.parseInt(lastAppointmentID.substring(2)) ;
        String appointmentID = "AP" + (lastID+1);
        return appointmentID;
    }

    // scheduleAppointment
    public static void scheduleAppointment(String doctorID, String patientID, LocalDate date, LocalTime time) throws IOException{
        // update appointments list
        String appointmentID = generateAppointmentID();
        Appointment appointment = new Appointment(appointmentID, doctorID, patientID, date, time);
        appointments.add(appointment);
        
        // append appointments.txt with new appointment
        try (FileWriter writer = new FileWriter(appointmentsFileName, true)) {
            String str = String.format("%s,%s,%s,%tF,%tR", 
                        appointmentID,
                        doctorID,
                        patientID,
                        date,
                        time);
            writer.write("\n" + str);

            System.out.println("New appointment added successfully!");
            System.out.println(appointment);
            System.out.println();
        }
        
    }

    // rescheduleAppointment
    public static void rescheduleAppointment(String appointmentID, LocalDate newDate, LocalTime newTime) throws IOException{
        // update appoinments list
        for (int i=0; i<appointments.size(); i++){
            // find the right appointment by using appointmentID
            if (appointments.get(i).getAppointmentID().equals(appointmentID)){
                // replace the date and the time of the appointment
                appointments.get(i).setDate(newDate);
                appointments.get(i).setTime(newTime);
                break;
            }
        }

        // update appointments.txt by modifying the appointment
        try (FileWriter writer = new FileWriter(appointmentsFileName)) {
            for (int i=0; i<appointments.size();i++){
                String str = String.format("%s,%s,%s,%tF,%t", 
                appointmentID,
                appointments.get(i).getDoctorID(),
                appointments.get(i).getPatientID(),
                newDate,
                newTime);
                
                writer.write(str);
            }
            System.out.println("The appointment has been updated!");
            System.out.println();
        }
        
    }

    // cancelAppointment
    public static void cancelAppointment(String appointmentID) throws IOException{
        
        // update appointments list
        int initialLength = appointments.size();
        for (int i=0; i<appointments.size(); i++){
            // find the right appointment by using appointmentID
            if (appointments.get(i).getAppointmentID().equals(appointmentID)){
                // remove the appointment
                appointments.remove(i);
                break;
            }
        }

        // update appointments.txt by removing the appointment
        if (initialLength != appointments.size()){
            try (FileWriter writer = new FileWriter(appointmentsFileName)) {
                for (int i=0; i<appointments.size(); i++){
                    String str = String.format("%s,%s,%s,%tF,%t", 
                    appointments.get(i).getAppointmentID(),
                    appointments.get(i).getDoctorID(),
                    appointments.get(i).getPatientID(),
                    appointments.get(i).getDate(),
                    appointments.get(i).getTime());

                    writer.write(str);
                }

                System.out.println("The appointment has been cancelled!");
                System.out.println();
            } 
            
        } else {
            System.out.println("This appointment ID doesn't exist");
        }

    }

    // generate a new treatment ID
    public static String generateTreatmentID (){
        int length = treatments.size();
        String lastTreatmentID = treatments.get(length-1).getTreatmentID();
        int lastID = Integer.parseInt(lastTreatmentID.substring(2)) ;
        String treatmentID = "TR" + (lastID+1);
        return treatmentID;
    }

    // provideTreatment 
    public static void provideTreatment(String doctorID, String patientID, String prescription) throws IOException {
        // update treatments list
        String treatmentID = generateTreatmentID();
        Treatment treatment = new Treatment(treatmentID, doctorID, patientID, LocalDate.now(), prescription);
        treatments.add(treatment);
        
        // append the treatments.txt with new treatment
        try (FileWriter writer = new FileWriter(treatmentsFileName,true)) {
            String str = String.format("%s,%s,%s,%s,%s",
                        treatmentID,
                        doctorID,
                        patientID,
                        LocalDate.now(),
                        prescription);
            writer.write("\n" + str );

            System.out.println("New treatment added successfully!");
            System.out.println(treatment);
            System.out.println();
        }
    }

    // generate a new doctor ID
    public static String generateDoctorID (){
        int length = doctors.size();
        String lastDoctorID = doctors.get(length-1).getDoctorID();
        int lastID = Integer.parseInt(lastDoctorID.substring(2)) ;
        String doctorID = "DC" + (lastID+1);
        return doctorID;
    }

    // registerDoctor
    public static void registerDoctor(String firstName, String lastName, LocalDate dateOfBirth, Person.Gender gender, String email, int phone, String specialty) throws IOException{
        // update doctors list
        String doctorID = generateDoctorID();
        int yearOfBirth = dateOfBirth.getYear();
        int monthOfBirth = dateOfBirth.getMonthValue();
        int dayOfBirth = dateOfBirth.getDayOfMonth();
        Doctor doctor = new Doctor (firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, doctorID, specialty);
        doctors.add(doctor);
        
        // append the doctors.txt with new doctor
        try (FileWriter writer = new FileWriter(doctorsFileName,true)) {
            String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s", 
                        firstName,
                        lastName,
                        dateOfBirth,
                        gender,
                        email,
                        phone,
                        doctorID,
                        specialty);
            writer.write("\n" + str);

            System.out.println("New doctor added successfully!");
            System.out.println(doctor);
            System.out.println();
        }
    }  
    
    // removeDoctor
    public static void removeDoctor(String doctorID) throws IOException {
        // update the doctors list
        int initialLength = doctors.size();
        for (int i=0; i<doctors.size(); i++){
            // find the right doctor by using doctorID
            if (doctors.get(i).getDoctorID().equals(doctorID)){
                // remove the doctor
                doctors.remove(i);
                break;
            }
        }

        // update the doctors.txt by removing the doctor
        if (initialLength != doctors.size()){
            try (FileWriter writer = new FileWriter(doctorsFileName)) {
                for (int i=0; i<appointments.size(); i++){
                    String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s", 
                    doctors.get(i).getFirstName(),
                    doctors.get(i).getLastName(),
                    doctors.get(i).getDateOfBirth(),
                    doctors.get(i).getGender(),
                    doctors.get(i).getEmail(),
                    doctors.get(i).getPhone(),
                    doctors.get(i).getDoctorID(),
                    doctors.get(i).getSpecialty());

                    writer.write(str);
                }
                System.out.println("This doctor is no longer part of our clinic.");
                System.out.println();
            } 
            
        } else {
            System.out.println("This doctor ID doesn't exist");
        }
    }

    // generate a new patient ID
    public static String generatePatientID (){
        int length = patients.size();
        String lastPatientID = patients.get(length-1).getPatientID();
        int lastID = Integer.parseInt(lastPatientID.substring(2)) ;
        String patientID = "PT" + (lastID+1);
        return patientID;
    }

    // registerPatient
    public static void registerPatient(String firstName, String lastName, LocalDate dateOfBirth, Person.Gender gender, String email, int phone, String insuranceCompany) throws IOException{
        //update patients list
        String patientID = generatePatientID();
        int yearOfBirth = dateOfBirth.getYear();
        int monthOfBirth = dateOfBirth.getMonthValue();
        int dayOfBirth = dateOfBirth.getDayOfMonth();
        Patient patient = new Patient(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, patientID, insuranceCompany);
        patients.add(patient);
        
        // append the patient.txt with new patient
        try (FileWriter writer = new FileWriter(patientsFileName,true)) {
            String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s", 
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getDateOfBirth(),
                        patient.getGender(),
                        patient.getEmail(),
                        patient.getPhone(),
                        patient.getPatientID(),
                        patient.getInsuranceCompany());
            writer.write(str);

            System.out.println("New patient added successfully!");
            System.out.println(patient);
            System.out.println();
        }
    }
    
}
