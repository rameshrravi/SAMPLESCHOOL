package com.licet.tech.api;


import com.google.gson.JsonObject;
import com.licet.tech.model.StudentDetail;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    /*@POST("register.php")
    Call<SignUpResponse> SIGN_UP_RESPONSE_CALL(@Body JsonObject requestParameter);

    @GET("home_banner.php")
    Call<Banner> BANNER_CALL();

    @GET("home_logo.php")
    Call<Logo> LOGO_CALL();

    @GET("jpsrwebservice.php?getbusiness")
    Call<BusinessModel> BUSINESS_MODEL_CALL();

    @GET("jpsrwebservice.php?getad")
    Call<AdsResultModel> ADS_RESULT_MODEL_CALL();



    @GET("jpsrwebservice.php?getoffer")
    Call<OfferModel> OFFER_MODEL_CALL();

    @GET("jpsrwebservice.php?getnews")
    Call<NewsModel> NEWS_MODEL_CALL();


    @GET("location_list.php")
    Call<LocationList> LOCATION_LIST_CALL();

    @GET("business_city_list.php")
    Call<ResponseBody> cityList();

    @GET("testimonials_list.php")
    Call<TestmonalsModel> TESTMONALS_MODEL_CALL();

    @POST("ward_no.php")
    Call<ResponseBody> WardNo(@Body JsonObject requestParameter);

    @POST("area_list.php")
    Call<ResponseBody> AreaList(@Body JsonObject requestParameter);

    @POST("signup_send_verify_sms.php")
    Call<ResponseBody> sendSMS(@Body JsonObject requestParameter);

    @POST("signup_otp_verify.php")
    Call<ResponseBody> veriyOTP(@Body JsonObject requestParameter);

    @POST("forgot_password_sms_verify.php")
    Call<ResponseBody> forgotpassword(@Body JsonObject requestParameter);

    @POST("reset_password.php")
    Call<ResponseBody> resetPassword(@Body JsonObject requestParameter);

    @POST("signin.php")
    Call<LoginModel> LOGIN_MODEL_CALL(@Body JsonObject requestParameter);

    @POST("register.php")
    Call<LoginModel> REGISTERBUSINESS(@Body JsonObject requestParameter);

    @Multipart
    @POST("/en/Api/Results/UploadFile")
    Call<LoginModel> REGISTERBUSINESS(@Part("file") File file,
                                      @Body JsonObject requestParameter,
                                      Callback<Response> callback);

    Call<String> REGISTERBUSINESS(MultipartBody.Part fileToUpload, @Body JsonObject requestParameter);


    @GET
    Call<ResponseBody> getNewsList(@Url String url);*/
    /*  @GET("getBooksList")
    Call<BookList> BOOK_LIST_CALL();

    @GET("getAllFreeBooks")
    Call<BookList> GETFREEBOOKS();
    @POST("getBooksByPublishYear")
    Call<BookList> PublishYear(@Body JsonObject requestParameter);

    @GET("getBookPublishYears")
    Call<BooksYearPojo> YEAR_POJO_CALL();

    @POST("userRegister")
    Call<RegisterModel> REGISTER_MODEL_CALL(@Body JsonObject requestParameter);
    @POST("getUserSubscriptions")
    Call<SubscriptionYear> SUBSCRIPTION_YEAR_CALL(@Body JsonObject requestParameter);

    @POST("sendLoginOTP")
    Call<LoginModel> LOGIN_MODEL_CALL(@Body JsonObject requestParameter);

    @POST("reSendLoginOTP")
    Call<LoginModel> RELOGIN_MODEL_CALL(@Body JsonObject requestParameter);

    @POST("validateLoginOTP")
    Call<ValidateOtpModel> VALIDATE_OTP_MODEL_CALL(@Body JsonObject requestParameter);

    @POST("addSubscription")
    Call<SubscriptionModel> SUBSCRIPTION_MODEL_CALL(@Body JsonObject requestParameter);

    @GET("getSubscriptionCost")
    Call<SubscriptionCostModel> SUBSCRIPTION_COST_MODEL_CALL();
    @GET("getAboutPageContent")
    Call<AbouUsmodel> ABOU_USMODEL_CALL();
    @POST("viewMyProfile")
    Call<MyProfileModel> MY_PROFILE_MODEL_CALL(@Body JsonObject requestParameter);
    @POST("updateMyProfile")
    Call<UpdateProile> UPDATE_PROILE_CALL(@Body JsonObject requestParameter);
    *//*@POST("getPaidBooksByPublishYear")
    Call<ValidateOtpModel> VALIDATE_OTP_MODEL_CALL(@Body JsonObject requestParameter);*/
    @POST("signin.php")
    Call<ResponseBody> loginAPI(@Body JsonObject requestParameter);
    @POST("attendanceList.php")
    Call<ResponseBody> attendenceAPI(@Body JsonObject requestParameter);
    @POST("studentProfile.php")
    Call<StudentDetail> studentProfile(@Body JsonObject requestParameter);


    @POST("/api/account/userinfo")
    Call<ResponseBody> getLoginUserDetails(@Header("Authorization") String basic);

    /*@GET("/api/order/services")
    Call<ServiceListModel> getServiceList(@Header("Authorization") String basic);*/

    @GET("/api/order/services")
    Call<ResponseBody> getServiceList(@Header("Authorization") String basic);

 /*   @GET("/api/customer/serviceproviders")
    Call<ArrayList<ServiceprovidersModel>> getListProvide(@Header("Authorization") String basic);
*/
    @GET("/api/common/apartmentlist")
    Call<ResponseBody> getAparmnetList();

    @GET("/api/account/OtpList")
    Call<ResponseBody> getOTPList();

    @Headers("Content-Type: application/json")
    @POST("/api/customer/create")
    Call<ResponseBody> registerr(@Body JsonObject requestParameter);

    @Headers("Content-Type: application/json")
    @POST("/api/order/create")
    Call<ResponseBody> OrderCreate(@Header("Authorization") String basic,@Body JsonObject requestParameter);

    @Headers("Content-Type: application/json")
    @POST("/api/account/validateotp")
    Call<ResponseBody> validateOTP(@Header("Authorization") String basic, @Body JsonObject requestParameter);

    @Headers("Content-Type: application/json")
    @POST("/api/customer/resendotp")
    Call<ResponseBody> ResendOTp(@Header("Authorization") String basic);

   /* @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/api/customer/create")
    Call<ResponseBody> register(
            @Field(Comman.CustomerName) String CustomerName, @Field(Comman.MobileNumber) String MobileNumber,
            @Field(Comman.ApartmentID) int ApartmentID, @Field(Comman.ApartmentName) String ApartmentName,
            @Field(Comman.HouseNo) String HouseNo, @Field(Comman.AddressLine) String AddressLine,
            @Field(Comman.PinCode) String PinCode,
            @Field(Comman.MapLocation) String MapLocation, @Field(Comman.Password) String Password);*/


    @FormUrlEncoded
    @POST("/apitoken")
    Call<ResponseBody> sendWifiLog(@Header("Authorization") String token,
                                   @Field("grant_type") String grant_type, @Field("username") String username,
                                   @Field("password") String password);
}
