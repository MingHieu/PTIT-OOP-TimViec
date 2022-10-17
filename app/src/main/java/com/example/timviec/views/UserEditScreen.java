package com.example.timviec.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomDialog;
import com.example.timviec.components.CustomInput;
import com.example.timviec.components.LoadingDialog;
import com.example.timviec.model.API;
import com.example.timviec.model.User;
import com.example.timviec.services.ApiService;
import com.example.timviec.services.StateManagerService;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEditScreen extends Utils.BaseActivity {
    private ImageView mAvatar;
    private CustomInput mName;
    private CustomInput mDob;
    private RadioGroup mGender;
    private CustomInput mAddress;
    private CustomInput mPhoneNumber;
    private CustomInput mDescription;
    private StateManagerService stateManager = App.getContext().getStateManager();
    private User user = stateManager.getUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_screen);

        mAvatar = findViewById(R.id.edit_user_avatar);
        String avatar = (String) user.getDetail().get("avatar");
        if (avatar != null) {
            Utils.setBase64UrlImageView(mAvatar, avatar);
        } else {
            mAvatar.setImageResource(R.drawable.img_default_user);
        }

        ((ImageView) findViewById(R.id.edit_user_avatar_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UserEditScreen.this)
                        .compress(1024)
                        .cropSquare()
                        .start();
            }
        });

        mName = findViewById(R.id.edit_user_name);
        mName.setValue((String) user.getDetail().get("name"));

        mDob = findViewById(R.id.edit_user_dob);
        mDob.setValue((String) user.getDetail().get("dob"));

        mGender = findViewById(R.id.edit_user_gender);
        if (user.getDetail().get("gender") != null) {
            int gender = ((Number) user.getDetail().get("gender")).intValue();
            switch (gender) {
                case 1:
                    ((RadioButton) findViewById(R.id.edit_user_gender_male)).setChecked(true);
                    break;
                case 2:
                    ((RadioButton) findViewById(R.id.edit_user_gender_female)).setChecked(true);
                    break;
                default:
            }
        }

        mAddress = findViewById(R.id.edit_user_address);
        mAddress.setValue((String) user.getDetail().get("address"));

        ((CustomInput) findViewById(R.id.edit_user_email)).setValue((String) user.getDetail().get("email"));

        mPhoneNumber = findViewById(R.id.edit_user_phone_number);
        mPhoneNumber.setValue((String) user.getDetail().get("phone_number"));

        mDescription = findViewById(R.id.edit_user_description);
        mDescription.setValue((String) user.getDetail().get("introduction"));

        ((CustomButton) findViewById(R.id.edit_user_cancel_btn)).setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
                finish();
            }
        });

        ((CustomButton) findViewById(R.id.edit_user_approve_btn)).setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                LoadingDialog loadingDialog = new LoadingDialog(UserEditScreen.this);
                loadingDialog.show();

                API.UpdateFreelancerBody body = new API.UpdateFreelancerBody(
                        Utils.getBase64UrlImageView(mAvatar),
                        mName.getValue(),
                        mDob.getValue(),
                        mAddress.getValue(),
                        mPhoneNumber.getValue(),
                        mDescription.getValue(),
                        mGender.getCheckedRadioButtonId() == R.id.edit_user_gender_male ? 1 : 2);
                ApiService.apiService.updateFreelancer(body).enqueue(new Callback<API.UpdateFreelancerResponse>() {
                    @Override
                    public void onResponse(Call<API.UpdateFreelancerResponse> call, Response<API.UpdateFreelancerResponse> response) {
                        loadingDialog.hide();
                        if (response.isSuccessful()) {
                            API.UpdateFreelancerResponse res = response.body();

                            user.getDetail().put("avatar", Utils.getBase64UrlImageView(mAvatar));
                            user.getDetail().put("name", mName.getValue());
                            user.getDetail().put("dob", mDob.getValue());
                            user.getDetail().put("gender", mGender.getCheckedRadioButtonId() == R.id.edit_user_gender_male ? 1 : 2);
                            user.getDetail().put("address", mAddress.getValue());
                            user.getDetail().put("phone_number", mPhoneNumber.getValue());
                            user.getDetail().put("introduction", mDescription.getValue());

                            CustomDialog dialog = new CustomDialog(UserEditScreen.this, res.getMessage(), null, CustomDialog.DialogType.SUCCESS);
                            dialog.onConfirm(new Runnable() {
                                @Override
                                public void run() {
                                    onBackPressed();
                                }
                            });
                            dialog.show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                CustomDialog dialog = new CustomDialog(UserEditScreen.this, jsonObject.getString("message"), null, CustomDialog.DialogType.ERROR);
                                dialog.show();
                            } catch (Exception e) {
                                CustomDialog dialog = new CustomDialog(UserEditScreen.this, e.getMessage(), null, CustomDialog.DialogType.ERROR);
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<API.UpdateFreelancerResponse> call, Throwable t) {
                        loadingDialog.hide();
                        Log.e("DebugTag", t.toString());
                        CustomDialog dialog = new CustomDialog(UserEditScreen.this, t.getMessage(), null, CustomDialog.DialogType.ERROR);
                        dialog.show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            mAvatar.setImageURI(uri);
        }
    }
}