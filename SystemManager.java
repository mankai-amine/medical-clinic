import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class SystemManager {

    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;
    private List<Treatment> treatments;

    private final String doctorsFileName = "doctors.txt";
    private final String patientsFileName = "patients.txt";
    private final String appointmentsFileName = "appointments.txt";
    private final String treatmentsFileName = "treatments.txt";


    // constructor with parameters
    public SystemManager(List<Doctor> doctors, List<Patient> patients, List<Appointment> appointments, List<Treatment> treatments ){
        setDoctors(doctors);
        setPatients(patients);
        setAppointments(appointments);
        setTreatments(treatments);
    }

    // setters
    public void setDoctors(List<Doctor> doctors){
        this.doctors = doctors;
    }

    public void setPatients(List<Patient> patients){
        this.patients = patients;
    }

    public void setAppointments(List<Appointment> appointments){
        this.appointments = appointments;
    }

    public void setTreatments(List<Treatment> treatments){
        this.treatments = treatments;
    }

    // getters
    public List<Doctor> getDoctors (){
        return this.doctors;
    }

    public List<Patient> getPatients (){
        return this.patients;
    }

    public List<Appointment> getAllAppointments (){
        return this.appointments;
    }

    public List<Treatment> getAllTreatments (){
        return this.treatments;
    }

    // get the list of treatments for one particular patient 
    public List<Treatment> getMedicalHistory(String patientID){
        List<Treatment> medicalHistory = new ArrayList<>();
        
        for (Treatment treatment : this.treatments ){
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
        
        for (Appointment appointment : this.appointments ){
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
        
        for (Appointment appointment : this.appointments ){
            // find the appointments that correspond to that particular doctor
            if (appointment.getDoctorID().equals(doctorID)){
                // add the appointment to the doctor's appointments history
                doctorAppointments.add(appointment);
            }
        }
        
        return doctorAppointments;
    }

    // scheduleAppointment
    public void scheduleAppointment(Appointment appointment) throws IOException{
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
    public void rescheduleAppointment(String appointmentID, LocalDate newDate, Time newTime) throws IOException{
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
    public void cancelAppointment(String appointmentID) throws IOException{
        
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

    // recordTreatment 
    public void recordTreatment(Treatment treatment) throws IOException{
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

    // addDoctor
    public void addDoctor(Doctor doctor) throws IOException{
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
    public void removeDoctor(String doctorID) throws IOException {
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
    public void registerPatient(Patient patient) throws IOException{
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
