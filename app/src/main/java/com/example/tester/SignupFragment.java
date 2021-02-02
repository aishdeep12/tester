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

import java.util.concurrent.Executor;


public class SignupFragment extends Fragment {

    EditText editTextEmail;
    EditText editTextUserName;
    EditText editTextPassword;
    TextView editTextLogin;
    Button buttonSignUp;
    ProgressDialog progressDialog;
    public FirebaseAuth firebaseAuth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        progressDialog = new ProgressDialog(getContext());
        buttonSignUp = rootView.findViewById(R.id.buttonSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        editTextLogin = rootView.findViewById(R.id.editTextLogin);
        editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
        editTextUserName = rootView.findViewById(R.id.editTextUserName);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });

        return rootView;
    }


    public void registerUser() {

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //String userName = editTextUserName.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(),"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(),"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
//        final Intent intent = new Intent(getContext(), MainActivity.class);
//        intent.putExtra("Email", editTextEmail.toString());

        // if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(SignUp.this , Registration.class);
//                            intent.putExtra("Email",editTextEmail.toString());
//                            String fuser = task.getResult().getUser().getUid();
//                            System.out.println(fuser);
                            Intent I = new Intent(getContext(), MainActivity.class);
                            I.putExtra("Email", editTextEmail.getText().toString());
                            I.putExtra("name",editTextUserName.getText().toString());
//                            I.putExtra("userId", fuser);
                            startActivity(I);
                            //display some message here
                            Toast.makeText(getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            Toast.makeText(getContext(), "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    public void alreadyUser(View view) {

        Intent I = new Intent(getContext(), LoginFragment.class);
        startActivity(I);
    }
}
