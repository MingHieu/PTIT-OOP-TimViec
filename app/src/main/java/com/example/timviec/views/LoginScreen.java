package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomDialog;
import com.example.timviec.components.CustomInput;
import com.example.timviec.model.API;
import com.example.timviec.router.BottomTab;
import com.example.timviec.services.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends Utils.BaseActivity {
    private CustomInput mUsernameInput;
    private CustomInput mPasswordInput;
    private CustomButton mLoginButton;
    private TextView mSignupButton;
    private static final String[] COUNTRIES = new String[]{
            " Afghanistan ", " Albania ", " Algeria ", " Andorra ", " Angola "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setUpScreen();

        mUsernameInput = findViewById(R.id.login_screen_username_input);
        mUsernameInput.setSuggestList(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, COUNTRIES));
        mPasswordInput = findViewById(R.id.login_screen_password_input);
        mLoginButton = findViewById(R.id.login_screen_login_btn);
        mSignupButton = findViewById(R.id.login_screen_signup_btn);

        mLoginButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                login();
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignup();
            }
        });
    }

    private void login() {
        Bundle extras = getIntent().getExtras();
        int roleId = extras.getInt("roleId");
        App.getContext().getStateManager().getUser().setRoleId(roleId);

        mLoginButton.setLoading(true);

        ApiService.apiService.login(new API.LoginBody(
                        mUsernameInput.getValue(),
                        mPasswordInput.getValue()))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("DebugTag", "onResponse: " + response.body());
                        Intent i = new Intent(LoginScreen.this, BottomTab.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("DebugTag", "onFailure: " + t.getMessage());
                        mLoginButton.setLoading(false);
                        CustomDialog dialog = new CustomDialog(LoginScreen.this, t.getMessage(), null, null);
                        dialog.show();
                    }
                });
    }

    private void goToSignup() {
        Bundle extras = getIntent().getExtras();
        int roleId = extras.getInt("roleId");
        if (roleId == 1) {
            Intent i = new Intent(this, SignupUserScreen.class);
            startActivity(i);
        }
        if (roleId == 2) {
            Intent i = new Intent(this, SignupEnterpriseScreen.class);
            startActivity(i);
        }
    }
}