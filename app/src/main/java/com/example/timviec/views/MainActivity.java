package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.LoadingDialog;
import com.example.timviec.model.API;
import com.example.timviec.model.User;
import com.example.timviec.router.BottomTab;
import com.example.timviec.services.ApiService;
import com.example.timviec.services.StateManagerService;
import com.example.timviec.services.StorageService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Utils.BaseActivity {

    private StateManagerService stateManager = App.getContext().getStateManager();
    private StorageService storageService = App.getContext().getStorageService();
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingDialog = new LoadingDialog(MainActivity.this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.show();
                    }
                });

                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    Log.i("DebugTag", "Fetching FCM registration token failed", task.getException());
                                    goToLogin();
                                }

                                // Get new FCM registration token
                                String token = task.getResult();
                                Log.i("DebugTag", "FCM Token: " + token);

                                String authToken = storageService.getString("authToken");
                                Log.i("DebugTag", "Authentication token: " + authToken);
                                if (authToken != null) {
                                    stateManager.setAuthToken(authToken);
                                    ApiService.apiService.getUserDetail().enqueue(new Callback<API.UserDetailResponse>() {
                                        @Override
                                        public void onResponse(Call<API.UserDetailResponse> call, Response<API.UserDetailResponse> response) {
                                            if (response.isSuccessful()) {
                                                API.UserDetailResponse res = response.body();
                                                API.UserDetailResponse.UserDetailResponseData data = res.getData();

                                                User user = stateManager.getUser();
                                                user.setDetail(data.getDetail());
                                                user.setRoleId(data.getRole());

                                                goToHome();

                                            } else {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                                    Log.e("DebugTag", jsonObject.getString("message"));
                                                    goToLogin();
                                                } catch (Exception e) {
                                                    Log.e("DebugTag", e.getMessage());
                                                    goToLogin();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<API.UserDetailResponse> call, Throwable t) {
                                            Log.e("DebugTag", t.toString());
                                            goToLogin();
                                        }
                                    });

                                } else {
                                    goToLogin();
                                }
                            }
                        });
            }
        }, 1000);
    }

    private void goToLogin() {
        loadingDialog.hide();
        Intent i = new Intent(this, SelectRoleScreen.class);
        startActivity(i);
        finish();
    }

    private void goToHome() {
        loadingDialog.hide();
        Intent i = new Intent(this, BottomTab.class);
        startActivity(i);
        finish();
    }
}