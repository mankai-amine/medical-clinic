import java.sql.Time;
import java.time.LocalDate;
 
public class Appointment {
     
     private String appointmentID; 
     private LocalDate date;
     private Time time;
     private String doctorID;
     private String patientID; 
     
     // constructor with parameters
     public Appointment(String appointment, LocalDate date, Time time, String doctorID, String patientID){
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

     public void setTime (Time time){
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

     public Time getTime(){
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
        return "appointment ID= " + appointmentID + '\'' +
        "date= " + date + '\'' +
        "time= " + time + '\'' +
        "doctorID= " + doctorID + '\'' +
        "patientID= " + patientID + '\'' ;
    }
     
 }