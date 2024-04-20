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

    private static List<Doctor> doctors;
    private static List<Patient> patients;
    private static List<Appointment> appointments;
    private static List<Treatment> treatments;

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
        int i=0;

        while (scanner.hasNextLine()){
            String firstName,lastName, email , doctorID, specialty;
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
            doctorID = lineParts[6];
            specialty = lineParts[7];

            doctors.set(i, new Doctor(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, doctorID, specialty));
            i++;
        }

        scanner.close();
    }

    // Load patients
    public static void loadPatients() throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader(patientsFileName) );
        int i=0;

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

            patients.set(i, new Patient(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone, patientID, insuranceCompany));
            i++;
        }

        scanner.close();
    }

    // Load appointments
    public static void loadAppointments() throws FileNotFoundException, ParseException{
        Scanner scanner = new Scanner(new FileReader(appointmentsFileName) );
        int i=0;

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

            appointments.set(i, new Appointment(appointmentID, doctorID, patientID, date, time));
            i++;
        }

        scanner.close();
    }

    // Load treatments
    public static void loadTreatments() throws FileNotFoundException, ParseException{
        Scanner scanner = new Scanner(new FileReader(treatmentsFileName) );
        int i=0;

        while (scanner.hasNextLine()){
            String treatmentID, doctorID, patientID, prescription;
            LocalDate date;             

            String line = scanner.nextLine();
            String[] lineParts= line.split(",");
            treatmentID = lineParts[0];
            doctorID = lineParts[1];
            patientID = lineParts[2];
            date = LocalDate.parse(lineParts[3]);
            prescription = lineParts[4];

            treatments.set(i, new Treatment(treatmentID, doctorID, patientID, date, prescription));
            i++;
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

    // get the list of treatments for one particular patient 
    public List<Treatment> getMedicalHistory(String patientID){
        List<Treatment> medicalHistory = new ArrayList<>();
        
        for (Treatment treatment : treatments ){
            // find the treatments that correspond to that particular patient
            if (treatment.getPatientID().equals(patientID)){
                // add the treatment to the patient's medical history
                medicalHistory.add(treatment);
            }
        }
        
        return medicalHistory;
    }

    // get the list of appointments for one particular patient
    public List<Appointment> getAppointmentsHistory(String patientID){
        List<Appointment> appointmentsHistory = new ArrayList<>();
        
        for (Appointment appointment : appointments ){
            // find the appointments that correspond to that particular patient
            if (appointment.getPatientID().equals(patientID)){
                // add the appointment to the patient's appointments history
                appointmentsHistory.add(appointment);
            }
        }
        
        return appointmentsHistory;
    }

    // get the list of appointments for one particular doctor
    public List<Appointment> getDoctorAppointments(String doctorID){
        List<Appointment> doctorAppointments = new ArrayList<>();
        
        for (Appointment appointment : appointments ){
            // find the appointments that correspond to that particular doctor
            if (appointment.getDoctorID().equals(doctorID)){
                // add the appointment to the doctor's appointments history
                doctorAppointments.add(appointment);
            }
        }
        
        return doctorAppointments;
    }

    // scheduleAppointment
    public static void scheduleAppointment(Appointment appointment) throws IOException{
        // update appointments list
        appointments.add(appointment);
        
        // append appointments.txt with new appointment
        try (FileWriter writer = new FileWriter(appointmentsFileName, true)) {
            String str = String.format("%s,%s,%s,%tF,%t%n", 
                        appointment.getAppointmentID(),
                        appointment.getDoctorID(),
                        appointment.getPatientID(),
                        appointment.getDate(),
                        appointment.getTime());
            writer.write(str);

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
                String str = String.format("%s,%s,%s,%tF,%t%n", 
                appointments.get(i).getAppointmentID(),
                appointments.get(i).getDoctorID(),
                appointments.get(i).getPatientID(),
                appointments.get(i).getDate(),
                appointments.get(i).getTime());
                
                writer.write(str);
            }
            System.out.println("The appointment has been updated!");
            System.out.println();
        }
        
    }

    // cancelAppointment
    public static void cancelAppointment(String appointmentID) throws IOException{
        
        // update appintments list
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
                    String str = String.format("%s,%s,%s,%tF,%t%n", 
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

    // provideTreatment 
    public static void provideTreatment(Treatment treatment) throws IOException{
        // update treatments list
        treatments.add(treatment);
        
        // append the treatments.txt with new treatment
        try (FileWriter writer = new FileWriter(treatmentsFileName,true)) {
            String str = String.format("%s,%s,%s,%tF,%s",
                        treatment.getTreatmentID(),
                        treatment.getDoctorID(),
                        treatment.getPatientID(),
                        treatment.getDate(),
                        treatment.getPrescription());
            writer.write(str);

            System.out.println("New treatment added successfully!");
            System.out.println(treatment);
            System.out.println();
        }
    }

    // registerDoctor
    public static void registerDoctor(Doctor doctor) throws IOException{
        // update doctors list
        doctors.add(doctor);
        
        // append the doctors.txt with new doctor
        try (FileWriter writer = new FileWriter(doctorsFileName,true)) {
            String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s%n", 
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getDateOfBirth(),
                        doctor.getGender(),
                        doctor.getEmail(),
                        doctor.getPhone(),
                        doctor.getDoctorID(),
                        doctor.getSpecialty());
            writer.write(str);

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
                    String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s%n", 
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

    // registerPatient
    public static void registerPatient(Patient patient) throws IOException{
        //update patients list
        patients.add(patient);
        
        // append the patient.txt with new patient
        try (FileWriter writer = new FileWriter(patientsFileName,true)) {
            String str = String.format("%s,%s,%tF,%s,%s,%d,%s,%s%n", 
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
