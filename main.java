import java.io.FileNotFoundException;
import java.text.ParseException;

public class main {
    
    /*
     * When we run the program, this is what happens:
     * 1. We load the data from the four .txt files
     * 2. in the main menu, we give the user the choice between 3 roles: patient, doctor, receptionist
     * 3. if the user is a patient, he can:
     *      - view his medical history (list of all his treatments)
     *      - view all his appointments
     *      - retun to the main menu 
     * 4. if the user is a doctor, he can:
     *      - view all his appointments
     *      - provide a treatment
     *      - retun to the main menu
     * 5. if the user is a receptionist, he can:
     *      - schedule an appointment
     *      - reschedule an appointment
     *      - cancel an appointment
     *      - register a doctor
     *      - remove a doctor
     *      - register a patient
     *      - retun to the main menu
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        SystemManagement.loadData();
    }
}
