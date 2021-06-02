package com.example.emailpasswordauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.emailpasswordauthentication.Models.Users;
import com.example.emailpasswordauthentication.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
ActivitySignUpBinding binding;
    FirebaseAuth auth;
FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we're Creating your account");
        firebaseDatabase=FirebaseDatabase.getInstance();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (binding.editTextTextEmailAddress.getText().toString(),
                                binding.editTextTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Users users=new Users(binding.editTextTextPersonName.getText().toString() , binding.editTextTextEmailAddress.getText().toString(),binding.editTextTextPassword.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("Users").child(id).setValue(users);
                            Intent intent=new Intent(SignUp.this,SignIn.class);
                            startActivity(intent);


                        }else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignUp.this,SignIn.class);
                startActivity(intent);

            }
        });


    }
}