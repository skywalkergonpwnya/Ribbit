package com.skywalkergonpwnya.ribbit.ribbit_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends Activity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected EditText mNativeLanguage;
    protected EditText mLearningLanguage;
    protected Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);
        mEmail = (EditText) findViewById(R.id.emailField);
        mNativeLanguage = (EditText) findViewById(R.id.spokenLanguageField);
        mLearningLanguage = (EditText) findViewById(R.id.learningLanguageField);
        mSignUpButton = (Button) findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();
                String nativeLanguage = mNativeLanguage.getText().toString();
                String learningLanguage = mLearningLanguage.getText().toString();

                //TODO: better analysis if input is correct
                username = username.trim();
                password = password.trim();
                email = email.trim();
                nativeLanguage = nativeLanguage.trim().toLowerCase();
                learningLanguage = learningLanguage.trim().toLowerCase();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || nativeLanguage.isEmpty() || learningLanguage.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(getString(R.string.signup_error_message))
                        .setTitle(getString(R.string.signup_error_title))
                        .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.put(ParseConstants.KEY_LANGUAGE_NATIVE,nativeLanguage);
                    newUser.put(ParseConstants.KEY_LANGUAGE_LEARNING,learningLanguage);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);
                            if (e==null) {
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(getString(R.string.signup_error_title))
                                        .setPositiveButton(android.R.string.ok,null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
