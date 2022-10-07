package com.licet.tech.activity;

import static com.licet.tech.api.Service.createServiceHeader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.licet.tech.MainActivity;
import com.licet.tech.R;
import com.licet.tech.api.API;
import com.licet.tech.dialog.MethodUtils;
import com.licet.tech.utils.MyPreference;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.parent_login_button)
     Button parent_login_button;
    @BindView(R.id.p_login_username)
     EditText p_login_username;
    @BindView(R.id.p_login_password)
     EditText p_login_password;
    private MyPreference myPreference;
    private ProgressDialog progressBar;
    String android_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        ButterKnife.bind(this);
        myPreference=new MyPreference(getApplicationContext());
        parent_login_button.setOnClickListener(this);
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public void loginAPI() {
        Call<ResponseBody> call = null;
        API apiService = createServiceHeader(API.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("token", "asasasasasasasasasasas");
        jsonObject.addProperty("admission_no", p_login_username.getText().toString());
        jsonObject.addProperty("password", p_login_password.getText().toString());
        call = apiService.loginAPI(jsonObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    progressBar.dismiss();
                    String data = response.body().string();
                    Log.i("RameshData", data);
                    JSONObject prent = new JSONObject(data);
                    String code=prent.getString("code");
                    if(code.equals("1")) {
                        String respons = prent.getString("response");
                        String user_id = prent.getString("user_id");
                        String student_name = prent.getString("student_name");
                        String std_id = prent.getString("std_id");
                        String std_name = prent.getString("std_name");
                        String section_id = prent.getString("section_id");
                        String section_name = prent.getString("section_name");
                        String father_name = prent.getString("father_name");
                        String mother_name = prent.getString("mother_name");
                        String admisson = p_login_username.getText().toString();
                        String password = p_login_password.getText().toString();

                        myPreference.setBookingId(getApplicationContext(), prent.getString("user_id"));
                        myPreference.setStuentDetails(getApplicationContext(),
                                "", android_id, student_name, std_id, std_name, section_id,
                                section_name, father_name, mother_name, admisson, password, "",user_id);
                        Intent intent = new Intent(ParentLogin.this, MainActivity.class);
                        intent.putExtra("status", "yes");
                        startActivity(intent);
                        finish();
                    }else {
                        MethodUtils.errorMsg(ParentLogin.this, "Login Failed");
                    }

                } catch (Exception e) {
                    MethodUtils.errorMsg(ParentLogin.this, "Please Enter Valid Mobile Number and Password");
                    //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.dismiss();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parent_login_button:
                parentLogin();
                break;
        }
    }

    public void parentLogin() {
        if (p_login_username.getText().toString().equals("")) {
            MethodUtils.errorMsg(ParentLogin.this, "Enter Mobile Number");
            //Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
        } else if (p_login_password.getText().toString().equals("")) {
            MethodUtils.errorMsg(ParentLogin.this, "Enter Your Password");

        } else {
            if (myPreference.isInternetOn()) {
                progressBar = new ProgressDialog(ParentLogin.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("Please Wait ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.setCancelable(false);
                progressBar.show();
                loginAPI();
            } else {
                MethodUtils.errorMsg(ParentLogin.this, "Turn On your Newtwork");
            }
        }

    }
}