package com.example.hams;

import static com.example.hams.UpcomingAppointments.upcomingAppointmentList;
import static com.example.hams.UpcomingShifts.shiftList;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LoginDoc extends AppCompatActivity {


    private Button button;
    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    FirebaseDatabase database = MainActivity.database;
    DatabaseReference usersRef = MainActivity.usersRef;
    DatabaseReference appointmentsRef = MainActivity.appointmentsRef;

    DatabaseReference shiftRef = MainActivity.shiftRef;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_doc);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        button = (Button) findViewById(R.id.button);
        TextInputEditText emailBox = findViewById(R.id.email);
        TextInputEditText passwordBox = findViewById(R.id.pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailBox.getText());
                String password = String.valueOf(passwordBox.getText());

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success so user exists, but now we check if theyre approved or not
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if(user == null){

                                        Log.d("Info","user null");
                                    }
                                    checkStatus(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Info", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginDoc.this, "Login failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

    }
    public void openLogin(String docID){

        //TODO: when patient functionality is here, make them create the appointments, not in the log in here
        //TODO: when a patient signs in, get their health card number
        //this was only for testing purposes so we'd have a set of
        /*for(int i = 1; i<6; i++){
            Shift s = new Shift("2023-12-01","12:00","14:00");
            s.setDoctorID(docID);
            String shiftID = shiftRef.push().getKey();
            s.setShiftID(shiftID);
            shiftRef.child(shiftID).setValue(s);

            shiftList.add(s);
            Log.d("Info", "shift added: " + i);
        }*/
        Intent intent = new Intent(this, DocView.class);
        startActivity(intent);

    }


    public void openSplash(){
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    /*
    Method to check the status of the user based on the firebase attribute "status"
    If approved, login is successful, if not, a toast is shown and the user is sent to the splash page
     */
    private void checkStatus(FirebaseUser user){
        //reference the database elements connected to the user
        DatabaseReference userRef = usersRef.child(user.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    //now check what we wanna do
                    String userStatus = snapshot.child("status").getValue(String.class);
                    Log.d("Info", userStatus); //show status in logs, just for debugging
                    if(userStatus.equals("Approved")){
                        //proceed to login as normal
                        Toast.makeText(LoginDoc.this, "Login successful.",
                                Toast.LENGTH_SHORT).show();
                        //TODO: remove the parameter from login once patients can create appointments
                        String docID = snapshot.child("employeeNumber").getValue(String.class);

                        openLogin(docID);
                    } else if(userStatus.equals("Pending")){
                        //give pending message, send back to splash
                        Toast.makeText(LoginDoc.this, "Account approval pending.",
                                Toast.LENGTH_SHORT).show();
                        openSplash();
                    } else if(userStatus.equals("Rejected")){
                        //give rejected mesage, send to splash
                        Toast.makeText(LoginDoc.this, "Account approval rejected.",
                                Toast.LENGTH_SHORT).show();
                        openSplash();
                    }
                }else{
                    //there is no data found for that user?
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}