package com.licet.tech.activity;

import static com.licet.tech.api.Service.createServiceHeader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.licet.tech.MainActivity;
import com.licet.tech.R;
import com.licet.tech.api.API;
import com.licet.tech.dialog.MethodUtils;
import com.licet.tech.model.StudentDetail;
import com.licet.tech.utils.MyPreference;

import org.json.JSONObject;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView im;
    TextView name,standard,gender,dob,blood,fathername,mother,fathermobile,mothermobile,address,district,pincode,area,admisiion_no;
    private MyPreference myPreference;
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        myPreference=new MyPreference(getApplicationContext());
        im=(ImageView)findViewById(R.id.profile_image);


        name=(TextView)findViewById(R.id.stu_name);
        standard=(TextView)findViewById(R.id.stu_class);
        gender=(TextView)findViewById(R.id.stu_gender);
        dob=(TextView)findViewById(R.id.stu_dateofbirth);
        blood=(TextView)findViewById(R.id.stu_blodd);

        fathername=(TextView)findViewById(R.id.stu_father);

        mother=(TextView)findViewById(R.id.stu_mother);
        address=(TextView)findViewById(R.id.stu_address);
        district=(TextView)findViewById(R.id.stu_district);
        fathermobile=(TextView)findViewById(R.id.stu_father_no);
        mothermobile=(TextView)findViewById(R.id.stu_mother_no);

        pincode=(TextView)findViewById(R.id.stu_pincode);
        area=(TextView)findViewById(R.id.stu_area);
        admisiion_no=(TextView)findViewById(R.id.stu_adm_no);
        if (myPreference.isInternetOn()) {
            getStudentDetails();
        }
    }
    public void getStudentDetails() {
        progressBar = new ProgressDialog(ProfileActivity.this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please Wait ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(false);
        progressBar.show();
        Call<StudentDetail> call = null;
        API apiService = createServiceHeader(API.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", myPreference.getDefaultRunTimeValue()[12]);

        Log.i("LoginMethod",jsonObject.toString());
        call = apiService.studentProfile(jsonObject);
        call.enqueue(new Callback<StudentDetail>() {
            @Override
            public void onResponse(Call<StudentDetail> call, Response<StudentDetail> response) {
                try {
                    int  data = response.body().getCode();
                    progressBar.dismiss();
                    if(data==1) {
                        String studentname=response.body().getData().getStudent_name();
                        String section_name=response.body().getData().getSection();
                        String genderstr=response.body().getData().getGender();
                        String DOB=response.body().getData().getDob();
                        String Blood=response.body().getData().getBlood_group();
                        String Fname=response.body().getData().getFather_name();
                        String Mname=response.body().getData().getMother_name();
                        String MNumber=response.body().getData().getMother_number();
                        String FNumber=response.body().getData().getFather_number();
                        String District=response.body().getData().getDistrict();
                        String Addres=response.body().getData().getSection();
                        String Area=response.body().getData().getArea();
                        String PinCode=response.body().getData().getPincode();
                        String AdmissionNO=response.body().getData().getAdmission_no();
                        String Image=response.body().getData().getPhoto();

                        Glide.with(getApplicationContext())
                                .load(Image)
                                .into(im);
                        name.setText(studentname);
                        String Std=response.body().getData().getStd();
                        standard.setText(Std+": "+studentname+" - "+section_name);

                        gender.setText(genderstr);

                        dob.setText(DOB);

                        blood.setText(Blood);

                        fathername.setText(Fname);


                        mother.setText(Mname);
                        fathermobile.setText(FNumber);
                        mothermobile.setText(MNumber);


                        district.setText(District);

                        address.setText("");
                        area.setText(Area);
                        pincode.setText(PinCode);
                        admisiion_no.setText(AdmissionNO);
                    }else {
                    }

                } catch (Exception e) {
                    MethodUtils.errorMsg(ProfileActivity.this, "Please Enter Valid Mobile Number and Password");
                    //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<StudentDetail> call, Throwable t) {
                progressBar.dismiss();

            }
        });
    }
}