
import java.time.LocalDate;

public class Treatment {
    
    private String treatmentID;
    private String prescription; 
    private LocalDate date;
    private String doctorID;
    private String patientID; 
     
    // constructor with parameters
    public Treatment(String treatmentID, String doctorID, String patientID, LocalDate date, String prescription){
        setTreatmentID (treatmentID);
        setPrescription(prescription);
        setDate(date);
        setDoctorID(doctorID);
        setPatientID(patientID);
    }

    // setters
    public void setTreatmentID (String treatmentID){
        this.treatmentID = treatmentID;
    }
    
    public void setPrescription (String prescription){
        this.prescription = prescription;
    }

    public void setDate (LocalDate date){
        this.date = date;
    }

    public void setDoctorID (String doctorID){
        this.doctorID = doctorID;
    }

    public void setPatientID (String patientID){
        this.patientID = patientID;
    }

    // getters
    public String getTreatmentID(){
        return this.treatmentID;
    }

    public String getPrescription(){
        return this.prescription;
    }

    public LocalDate getDate(){
        return this.date;
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
        return String.format("%-15s %-12s %-12s %-15s %-50s %n", 
        treatmentID, doctorID, patientID, date, prescription);
    }
}