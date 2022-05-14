package com.example.dakbayaknowadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ImageView profilePic;
    public Uri imageUri;
    int SELECT_IMAGE_CODE = 1;

    private FirebaseAuth mAuth;

    private TextView loginNowButton;
    private Button registerButton;
    private EditText firstnameText, lastnameText, genderText, ageText, emailText, passwordText, confirmPasswordText, phoneText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        loginNowButton = (TextView) findViewById(R.id.loginNowButton);
        loginNowButton.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        firstnameText = (EditText) findViewById(R.id.firstname);
        lastnameText = (EditText) findViewById(R.id.lastname);
        genderText = (EditText) findViewById(R.id.gender);
        ageText = (EditText) findViewById(R.id.age);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPassword);
        phoneText = (EditText) findViewById(R.id.phone);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginNowButton:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.registerButton:
                registerAdmin();
                break;
        }
    }

    private void registerAdmin() {
        String firstname = firstnameText.getText().toString().trim();
        String lastname = lastnameText.getText().toString().trim();
        String gender = genderText.getText().toString().trim();
        String age = ageText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String confirmPassword = confirmPasswordText.getText().toString().trim();
        String phone = phoneText.getText().toString().trim();

        if (firstname.isEmpty()) {
            firstnameText.setError("First Name is required!");
            firstnameText.requestFocus();
            return;
        }
        if (lastname.isEmpty()) {
            lastnameText.setError("Last Name is required!");
            lastnameText.requestFocus();
            return;
        }
        if (gender.isEmpty()) {
            genderText.setError("Gender is required!");
            genderText.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            ageText.setError("Age is required!");
            ageText.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailText.setError("Email is required!");
            emailText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Please provide valid Email!");
            emailText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordText.setError("Password is required!");
            passwordText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordText.setError("Min password length should be 6 characters!");
            passwordText.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            confirmPasswordText.setError("Confirm your password!");
            confirmPasswordText.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            phoneText.setError("Phone Number is required!");
            phoneText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Admin admin = new Admin(firstname, lastname, gender, age, email, phone);

                            FirebaseDatabase.getInstance().getReference("admin")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Admin has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(Register.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}