import java.time.LocalDate;
import java.time.LocalTime;
 
public class Appointment {
     
     private String appointmentID; 
     private LocalDate date;
     private LocalTime time;
     private String doctorID;
     private String patientID; 
     
     // constructor with parameters
     public Appointment(String appointment, String doctorID, String patientID, LocalDate date, LocalTime time){
         setAppointmentID(appointment);
         setDate(date);
         setTime(time);
         setDoctorID(doctorID);
         setPatientID(patientID);
     }
     
     // setters
     public void setAppointmentID (String appointmentID){
         this.appointmentID = appointmentID;
     }
 
     public void setDate (LocalDate date){
         this.date = date;
     }

     public void setTime (LocalTime time){
        this.time = time;
    }
 
     public void setDoctorID (String doctorID){
         this.doctorID = doctorID;
     }
 
     public void setPatientID (String patientID){
         this.patientID = patientID;
     }
 
     // getters
     public String getAppointmentID(){
         return this.appointmentID;
     }
 
     public LocalDate getDate(){
         return this.date;
     }

     public LocalTime getTime(){
        return this.time;
    }
 
     public String getDoctorID(){
         return this.doctorID;
     }
 
     public String getPatientID(){
         return this.patientID;
     }

    //toString
    @Override
    public String toString(){
        return String.format("%-15s %-12s %-12s %-15s %-15s %n", 
        appointmentID, doctorID, patientID, date, time);
    }
     
 }