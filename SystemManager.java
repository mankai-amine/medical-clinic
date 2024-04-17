import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class SystemManagement {

    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;
    private List<Treatment> treatments;

    // constructor with parameters
    public SystemManagement(List<Doctor> doctors, List<Patient> patients, List<Appointment> appointments, List<Treatment> treatments ){
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
    public void scheduleAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    // rescheduleAppointment
    public void rescheduleAppointment(String appointmentID, LocalDate newDate, Time newTime){
        for (int i=0; i<appointments.size(); i++){
            // find the right appointment by using appointmentID
            if (appointments.get(i).getAppointmentID().equals(appointmentID)){
                // replace the date and the time of the appointment
                appointments.get(i).setDate(newDate);
                appointments.get(i).setTime(newTime);
                break;
            }
        }
    }

    // cancelAppointment
    public void cancelAppointment(String appointmentID){
        for (int i=0; i<appointments.size(); i++){
            // find the right appointment by using appointmentID
            if (appointments.get(i).getAppointmentID().equals(appointmentID)){
                // remove the appointment
                appointments.remove(i);
                break;
            }
        }
    }

    // recordTreatment 
    public void recordTreatment(Treatment treatment){
        treatments.add(treatment);
    }

    // addDoctor
    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }  
    
    // removeDoctor
    public void removeDoctor(String doctorID){
        for (int i=0; i<doctors.size(); i++){
            // find the right doctor by using doctorID
            if (doctors.get(i).getDoctorID().equals(doctorID)){
                // remove the doctor
                doctors.remove(i);
                break;
            }
        }
    }

    // registerPatient
    public void registerPatient(Patient patient){
        patients.add(patient);
    }

    // modify patient and doctor

    
}
