import java.time.LocalDate;

class Doctor extends Person{
    // data members
    private String doctorID;
    private LocalDate dateEmployed;
    private String specialty;

    // Defautl constructor
    public Doctor(){
        this.doctorID = "";
        this.dateEmployed = LocalDate.now();
        this.specialty = "";
    };
    
    // Constructor with parameters
    public Doctor(String firstName, String lastName, int yearOfBirth,int monthOfBirth, int dayOfBirth, Gender gender, String email, int phone, String doctorID, LocalDate dateEmployed, String specialty) {
        super(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone);
        setDoctorID(doctorID);
        setDateEmployed(dateEmployed);
        setSpecialty(specialty);
    }

    // setters
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setDateEmployed(LocalDate dateEmployed){
        this.dateEmployed = dateEmployed;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }

    // getters
    public String getDoctorID() {
        return doctorID;
    }

    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    public String getSpecialty() {
        return this.specialty;
    }

        //toString
    @Override
    public String toString(){
        return super.toString() + ", doctorID= " + doctorID + '\'' +
        ", date employed= " + dateEmployed + '\'' +
        ", specialty= " + specialty  ;
    }

}