package com.example.tester;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {

    EditText editTextEmail;
    EditText editTextPassword ;
    Button buttonLogin;
    private FirebaseAuth mAuth;
    TextView textViewSignUp,forgotPassword;
    ProgressDialog progressBar;
    public String userName;
    FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        textViewSignUp = rootView.findViewById(R.id.textViewSignUp);
        forgotPassword = rootView.findViewById(R.id.textViewForgotPassword);
        progressBar = new ProgressDialog(getContext());


//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = editTextEmail.getText().toString().trim();
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText((getContext()), "Enter your registered email", Toast.LENGTH_LONG).show();
//                    return;
//
//                }
//                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getContext(), "we have sent you an email to reset your password!", Toast.LENGTH_LONG).show();
//
//                        } else {
//                            Toast.makeText(getContext(), "Failed to send email to reset", Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//
//                });
//            }
//        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), SignupFragment.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

loginUserAccount();

            }
        });


        return  rootView;
    }


    private void loginUserAccount() {
        //progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_LONG).show();

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String uid = firebaseUser.getUid();

                            //progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.putExtra("email",editTextEmail.getText().toString());

                            intent.putExtra("uid",uid);

                            startActivity(intent);


                        }
                        else {
                            Toast.makeText(getContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }


}
