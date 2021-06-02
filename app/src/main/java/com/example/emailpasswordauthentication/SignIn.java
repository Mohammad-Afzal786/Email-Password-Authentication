package com.example.emailpasswordauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.emailpasswordauthentication.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    ActivitySignInBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(SignIn.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login your account");
        firebaseDatabase=FirebaseDatabase.getInstance();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword
                        (binding.editTextTextEmailAddress.getText().toString(),
                                binding.editTextTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(SignIn.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignIn.this,SignUp.class);
                startActivity(intent);

            }
        });

    }
}