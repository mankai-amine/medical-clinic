class Doctor extends Person{
    // data members
    private String doctorID;
    private String specialty;
  
    // Constructor with parameters
    public Doctor(String firstName, String lastName, int yearOfBirth, int monthOfBirth, int dayOfBirth, Gender gender, String email, int phone, String doctorID, String specialty) {
        super(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone);
        setDoctorID(doctorID);
        setSpecialty(specialty);
    }

    // setters
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }

    // getters
    public String getDoctorID() {
        return doctorID;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    //toString
    @Override
    public String toString(){
        return super.toString() +
            String.format("%-10s %-20s %n", 
            doctorID,
            specialty); 
    }

}


