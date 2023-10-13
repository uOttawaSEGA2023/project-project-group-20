public class Doctor extends User{
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    String address;
    String employeeNumber;
    String[] specialties;


    public Doctor(String user, String pass) {
        super(user, pass);
        //automaticall set to active, WILL CHANGE LATER
        
    }

    public void register(String first, String last, String email,
                        String pass, String phone, String address,
                        String number, String[] specialties)
        {
            // fully initialize the doctor's attributes
            this.firstName = first;
            this.lastName = last;
            this.email = email;
            this.password = pass;
            this.phoneNumber = phone;
            this.address = address;
            this.employeeNumber = number;
            this.specialties = specialties;

            //later send a message to the admin to approve the registration and activate the account
            active = true;
        }
                    
                        
    
}
