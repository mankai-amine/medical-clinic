class Patient extends Person{
    private String insuranceCompany;
    private String patientID;

    // Constructor with parameters
    public Patient(String firstName, String lastName, int yearOfBirth,int monthOfBirth, int dayOfBirth, Gender gender, String email, int phone, String patientID, String insuranceCompany) {
        super(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email, phone);
        setInsuranceCompany(insuranceCompany);
        setPatientID(patientID);
    }

    // Setters
    public void setInsuranceCompany(String insuranceCompany){
        this.insuranceCompany = insuranceCompany;
    }

    public void setPatientID(String patientID){
        this.patientID = patientID;
    }

    // Getters
    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getPatientID(){
        return this.patientID;
    }

    //toString
    @Override
    public String toString(){
        return super.toString() + ", insurance company= " + insuranceCompany + '\'' +
        ", patientID= " + patientID + '\'' ;
    }

}
