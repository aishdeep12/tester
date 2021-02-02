package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;



public class LoginActivity extends AppCompatActivity {

    Switch buttonSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonSwitch = findViewById(R.id.switchSelection);


//        editTextLogin = findViewById(R.id.editTextLogin);
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextUserName = findViewById(R.id.editTextUserName);
//        editTextPassword = findViewById(R.id.editTextPassword);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment,loginFragment);
        fragmentTransaction.commit();

//        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(i);
//            }
//        });

        buttonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(!b){
                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.fragment,loginFragment);
                    fragmentTransaction.commit();
                    buttonSwitch.setTextOn("Login");

                }else{


                    SignupFragment signupFragment = new SignupFragment();
                    fragmentTransaction.replace(R.id.fragment,signupFragment);
                    fragmentTransaction.commit();


                    buttonSwitch.setTextOff("SignUp");
                }
            }
        });
    }
}
