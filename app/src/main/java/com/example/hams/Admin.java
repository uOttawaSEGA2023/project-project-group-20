package com.example.hams;
public class Admin extends User{

    //one specific set of Admin credentials
    private static String userName = "Admin20";
    private static String passWord = "AdminPass123";

    public Admin() {
        super(userName, passWord);
        status = "Approved";
    }

    public String getUser(){
        return userName;
    }

    public String getPass(){
        return passWord;
    }

    public void approve(User user){
        user.setStatus("Approved");
    }
    
    public void reject(User user){
        user.setStatus("Rejected");
    }
}
