package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

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
import com.example.timviec.services.StorageService;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupUserScreen extends Utils.BaseActivity {

    private CustomInput mName;
    private CustomInput mEmail;
    private CustomInput mPassword;
    private CustomInput mRepeatPassword;
    private CustomButton mSignupButton;
    private StateManagerService stateManager = App.getContext().getStateManager();
    private StorageService storageService = App.getContext().getStorageService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user_screen);

        setUpScreen();

        mName = findViewById(R.id.sign_up_user_name);
        mEmail = findViewById(R.id.sign_up_user_email);
        mPassword = findViewById(R.id.sign_up_user_password);
        mRepeatPassword = findViewById(R.id.sign_up_user_repeat_password);

        mSignupButton = findViewById(R.id.sign_up_user_button);
        mSignupButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                signup();
            }
        });

    }

    private void signup() {
        ApiService.apiService.createFreelancer(new API.CreateFreelancerBody(
                        mName.getValue(),
                        mEmail.getValue(),
                        mPassword.getValue()))
                .enqueue(new Callback<API.SignupUserResponse>() {
                    @Override
                    public void onResponse(Call<API.SignupUserResponse> call, Response<API.SignupUserResponse> response) {
                        if (response.isSuccessful()) {
                            API.SignupUserResponse res= response.body();

                            String authToken = res.getToken();
                            stateManager.setAuthToken(authToken);
                            storageService.setString("authToken", authToken);

                            User user = new User();
                            user.setRoleId(res.getRole());
//                            user.setDetail(res.getEmail());
                            stateManager.setUser(user);

                            Intent i = new Intent(SignupUserScreen.this, BottomTab.class);
                            startActivity(i);
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                CustomDialog dialog = new CustomDialog(SignupUserScreen.this, jsonObject.getString("message"), null, CustomDialog.DialogType.ERROR);
                                dialog.show();
                            } catch (Exception e) {
                                CustomDialog dialog = new CustomDialog(SignupUserScreen.this, e.getMessage(), null, null);
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<API.SignupUserResponse> call, Throwable t) {
                        mSignupButton.setLoading(false);
                        Utils.handleFailure(SignupUserScreen.this, t);
                    }
                });
    }
}