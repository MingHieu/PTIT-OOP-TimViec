package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomDialog;
import com.example.timviec.components.CustomInput;
import com.example.timviec.model.API;
import com.example.timviec.model.User;
import com.example.timviec.router.BottomTab;
import com.example.timviec.services.ApiService;
import com.example.timviec.services.StateManagerService;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends Utils.BaseActivity {
    private CustomInput mUsernameInput;
    private CustomInput mPasswordInput;
    private CustomButton mLoginButton;
    private TextView mSignupButton;
    private StateManagerService stateManager = App.getContext().getStateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setUpScreen();

        mUsernameInput = findViewById(R.id.login_screen_username_input);
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
        mLoginButton.setLoading(true);

        ApiService.apiService.login(new API.LoginBody(
                        mUsernameInput.getValue(),
                        mPasswordInput.getValue()))
                .enqueue(new Callback<API.LoginResponse>() {
                    @Override
                    public void onResponse(Call<API.LoginResponse> call, Response<API.LoginResponse> response) {
                        if (response.isSuccessful()) {
                            API.LoginResponse res = response.body();
                            Log.i("DebugTag", res.toString());

                            String accessToken = res.getAccessToken();
                            stateManager.setAuthToken(accessToken);

                            User user = new User();
                            user.setRoleId(res.getRole());
                            user.setDetail(res.getDetail());
                            stateManager.setUser(user);

                            Intent i = new Intent(LoginScreen.this, BottomTab.class);
                            startActivity(i);
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                CustomDialog dialog = new CustomDialog(LoginScreen.this, jsonObject.getString("message"), null, null);
                                dialog.show();
                                mLoginButton.setLoading(false);
                            } catch (Exception e) {
                                CustomDialog dialog = new CustomDialog(LoginScreen.this, e.getMessage(), null, null);
                                dialog.show();
                                mLoginButton.setLoading(false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<API.LoginResponse> call, Throwable t) {
                        Log.e("DebugTag", t.toString());
                        CustomDialog dialog = new CustomDialog(LoginScreen.this, t.getMessage(), null, null);
                        dialog.show();
                        mLoginButton.setLoading(false);
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