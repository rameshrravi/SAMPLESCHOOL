package com.licet.tech.utils;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyPreference {

    private Context context;
    private AlertDialog dialog;

    private String maritalStatus = "maritalstatus";
    private String userid = "userid";
    private String loginStatus = "loginStatus";
    private String Bookstatus = "Bookstatus";
    private String country = "country";
    private String placeofbirth = "placeofbirth";
    private String timeofbirth = "timeofbirth";
    private String loginAccessToken = "loginAccessToken";
    private String BookingId = "";
    private String Name = "Name";
    private String MobileNo = "MobileNo";
    private String PanditCount = "PanditCount";
    private String bookPaidFree = "bookPaidFree";
    private String PaymentStatus = "PaymentStatus";
    private String yearStatus = "yearStatus";
    private String gson = "gson";
    private String itemSelect = "itemSelect";
    private String AppInstallFirstTime = "AppInstallFirstTime";
    private String MY_PREFS_NAME = "MyPrefsFile", AccessToken = null, DeviceId = null, MY_LOCATION = "LocationAccessToken";
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    public static final String knewsBaseUrl = "https://api.rss2json.com/v1/api.json?rss_url=";
    public static final String knewsHinduUrl = "https://www.thehindu.com/news/feeder/default.rss&api_key=uixdjuzbucgvdnxhpghm8bum6w65dtfaynm35s3r";
    public static final String knewsOneIndiaUrl = "http://www.oneindia.com/rss/news-india-fb.xml&api_key=uixdjuzbucgvdnxhpghm8bum6w65dtfaynm35s3r";
    public static final String knewsBBCUrl = "http://feeds.bbci.co.uk/news/world/rss.xml&api_key=uixdjuzbucgvdnxhpghm8bum6w65dtfaynm35s3r";
    public static final String knewsDinamalarUrl = "http://cinema.dinamalar.com/rss.php&api_key=uixdjuzbucgvdnxhpghm8bum6w65dtfaynm35s3r";
    public static final String knewsZeeNewsUrl = "http://zeenews.india.com/tamil/world.xml&api_key=uixdjuzbucgvdnxhpghm8bum6w65dtfaynm35s3r";

    public MyPreference(Context context) {
        this.context = context;
        mPrefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        prefsEditor = mPrefs.edit();
    }


    public String[] getDefaultRunTimeValue() {
        String[] Value = new String[17];
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("AccessToken", null);
        if (restoredText != null) {
            Value[0] = prefs.getString("AccessToken", "no data");//"No AccessToken Defined" is the default value.
            Value[1] = prefs.getString("DeviceId", "no data");//"No DeviceId Defined" is the default value.
            Value[2] = prefs.getString("AccessType", "Blank Value");
            Value[3] = prefs.getString("ProfilePic", "");
            Value[4] = prefs.getString("MoblieNumber", "");
            Value[5] = prefs.getString("Password", "");
            Value[6] = prefs.getString("CheckBoxTrue", "0");
            Value[7] = prefs.getString("FacebookLogout", "fblogout");
            Value[8] = prefs.getString("login", "");


        }
        return Value;
    }

    public String getLocationAccessToken() {
        String Value = null;
        SharedPreferences prefs = context.getSharedPreferences(MY_LOCATION, MODE_PRIVATE);
        String restoredText = prefs.getString("AccessToken", null);
        if (restoredText != null) {
            Value = prefs.getString("AccessToken", "No AccessToken Defined");//"No AccessToken Defined" is the default value.
        }
        return Value;
    }

    public final boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                // Toast.makeText(getApplicationContext(), activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(getApplicationContext(), activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public void setAllBookingDetails(Context mContext,
                                     String saveAlldetails) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("save", saveAlldetails);
        prefsEditor.commit();
    }

    public String getAllBookingDetails(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String inactiveAccounts = mPrefs.getString("save", "");
        return inactiveAccounts;
    }

    public void setImage(Context mContext,
                         String image) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("image", image);
        prefsEditor.commit();
    }

    public String getImage(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString("image", "");
        return image;
    }

    public void setBookingId(Context mContext,
                             String strBookingId) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(BookingId, strBookingId);
        prefsEditor.commit();
    }

    public String getBookingId(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strBookingId = mPrefs.getString(BookingId, "");
        return strBookingId;
    }

    public void setUserName(Context mContext,
                            String strBookingId) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(Name, strBookingId);
        prefsEditor.commit();
    }

    public String getUserName(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strBookingId = mPrefs.getString(Name, "");
        return strBookingId;
    }

    public void setUserMobileNo(Context mContext,
                                String strBookingId) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(MobileNo, strBookingId);
        prefsEditor.commit();
    }

    public String getMobileNo(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String MobileNo1 = mPrefs.getString(MobileNo, "");
        return MobileNo1;
    }


    public void setPaymentStastus(Context mContext,
                                  String strPaymentStatus) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PaymentStatus, strPaymentStatus);
        prefsEditor.commit();
    }

    public String getpaymentStatus(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strPaymentStatus = mPrefs.getString(PaymentStatus, "");
        return strPaymentStatus;
    }

    public void setYearStatus(Context mContext,
                              String strPaymentStatus) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(yearStatus, strPaymentStatus);
        prefsEditor.commit();
    }

    public String getYearStatus(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strPaymentStatus = mPrefs.getString(yearStatus, "");
        return strPaymentStatus;
    }

    public void setGson(Context mContext,
                        String strPaymentStatus) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();


        prefsEditor.putString(gson, strPaymentStatus);
        prefsEditor.commit();
    }

    public String getGson(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strPaymentStatus = mPrefs.getString(gson, "");
        return strPaymentStatus;
    }


    public void setPanditCount(Context mContext,
                               String strBookingId) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PanditCount, strBookingId);
        prefsEditor.commit();
    }

    public String getPanditCount(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String strBookingId = mPrefs.getString(PanditCount, "");
        return strBookingId;
    }

    public void setmaritalstatus(Context mContext,
                                 String maritalstatus) {
        prefsEditor.putString(maritalStatus, maritalstatus);
        prefsEditor.commit();
    }

    public String getmaritalstatus(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(maritalStatus, "");
        return image;
    }

    public void setUserID(Context mContext,
                          String dob) {
        prefsEditor.putString(userid, dob);
        prefsEditor.commit();
    }

    public String getUserId(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(userid, "");
        return image;
    }

    //timeofbirth
    public void setTimeOfBirth(Context mContext,
                               String tob) {
        prefsEditor.putString(timeofbirth, tob);
        prefsEditor.commit();
    }

    public String getTimeOfBirth(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(timeofbirth, "");
        return image;
    }
//end timeofbirth


    //timeofbirth
    public void setLoginAccessToken(
            String AccessToken) {
        prefsEditor.putString(loginAccessToken, AccessToken);
        prefsEditor.commit();
    }

    public String getLoginAccessToken(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String AccessToken = mPrefs.getString(loginAccessToken, "");
        return AccessToken;
    }

    //POB
    public void setPlaceOfBirth(Context mContext,
                                String pob) {
        prefsEditor.putString(placeofbirth, pob);
        prefsEditor.commit();
    }

    public String getPlaceOfBirth(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(placeofbirth, "");
        return image;
    }
//end POB

    //familymember
    public void setLoginStatus(
            String family) {
        prefsEditor.putString(loginStatus, family);
        prefsEditor.commit();
    }

    public String getLoginStatus(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(loginStatus, "");
        return image;
    }

    //end familymember
    public void setBookstatus(Context mContext,
                              String lang) {
        prefsEditor.putString(Bookstatus, lang);
        prefsEditor.commit();
    }

    public String getBookstatus(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String lang = mPrefs.getString(Bookstatus, "");
        return lang;
    }

    public void setAppLocale(String localeCode) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }

    public void setCountry(Context mContext,
                           String lang) {
        prefsEditor.putString(country, lang);
        prefsEditor.commit();
    }

    public String getCountry(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String lang = mPrefs.getString(country, "");
        return lang;
    }

    public void setItemSelect(Context mContext,
                              int ItemSelect) {
        prefsEditor.putInt(itemSelect, ItemSelect);
        prefsEditor.commit();
    }

    public int getItemSelect(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int ItemSelect = mPrefs.getInt(itemSelect, 0);
        return ItemSelect;
    }

    public void setAppInstallFirstTime(Context mContext,
                                       String ItemSelect) {
        prefsEditor.putString(AppInstallFirstTime, ItemSelect);
        prefsEditor.commit();
    }

    public String getInstallAppFirstTime(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String ItemSelect = mPrefs.getString(AppInstallFirstTime, "");
        return ItemSelect;
    }


    //vedhas
    public void setBookPaidFree(Context mContext,
                                String Vedhas) {
        prefsEditor.putString(bookPaidFree, Vedhas);
        prefsEditor.commit();
    }

    public String getBookPaidFree(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String image = mPrefs.getString(bookPaidFree, "");
        return image;
    }
    //End vedhas


    public boolean checkEditTextEmpty(EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }

    public boolean isValidEmail(EditText editText) {
        return Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString()).matches();
    }


    public boolean checktimings(String time, String endtime) {
        String pattern = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.AM_PM) == Calendar.AM) {
            // AM
            //which is from server;
            String splitTime[] = time.split(":");
            String hours = splitTime[0];
            String minutes = splitTime[1];
            int afterTwele = Integer.parseInt(hours);
            if (afterTwele < 12) {
                try {
                    Date date1 = sdf.parse(time);
                    Date date2 = sdf.parse(endtime);
                    if (afterTwele == 1 || afterTwele == 2 || afterTwele == 3 || afterTwele == 4 || afterTwele == 5 || afterTwele == 6 || afterTwele == 7 || afterTwele == 8) {
                        if (date1.before(date2)) {
                            return true;
                        } else {

                            return false;
                        }
                    } else {
                        if (date1.after(date2)) {
                            return true;
                        } else {

                            return false;
                        }
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Date date1 = sdf.parse(time);
                    Date date2 = sdf.parse(endtime);

                    if (date1.before(date2)) {
                        return true;
                    } else {

                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // PM

            try {
                Date date1 = sdf.parse(time);
                Date date2 = sdf.parse(endtime);

                if (date1.before(date2)) {
                    return true;
                } else {

                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean checkTodaytime(String time, String endtime) {

        String pattern = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if (date2.before(date1)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public String convertDecimalFromString(String strAmount) {
        Log.i("ZeroValue", strAmount);
        String decimalAmount = strAmount;
        if (strAmount.equals("0")) {
            decimalAmount = "0.00";

        } else {
            double amount = Double.parseDouble(decimalAmount);
            String Pooja_amount = (String.format(" %.2f", amount));
            DecimalFormat f = new DecimalFormat("##.00");
            decimalAmount = f.format(Double.parseDouble(Pooja_amount));
        }
        Log.i("ZeroValue", decimalAmount);
        return decimalAmount;
    }

    public String convertDecimalFromStringFormat(String strAmount) {
        Log.i("ZeroValue", strAmount);
        String decimalAmount = strAmount;
        if (strAmount.equals("0")) {
            decimalAmount = "0.00";

        } else {
            double amount = Double.parseDouble(decimalAmount);
            String Pooja_amount = (String.format(" %.2f", amount));
            DecimalFormat f = new DecimalFormat("0.00");
            decimalAmount = f.format(Double.parseDouble(Pooja_amount));
        }
        Log.i("ZeroValue", decimalAmount);
        return decimalAmount;
    }

    public String convertDate(String date) throws ParseException {

        String deliveryDate = date;
        SimpleDateFormat dateFormatprev = new SimpleDateFormat("dd-MM-yyyy");
        Date d = dateFormatprev.parse(deliveryDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String changedDate = dateFormat.format(d);
        return changedDate;
    }

    public void callClass(Activity activity, Class  aClass, int type) {
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
        if (type == 0) {
            activity.finish();
        }

    }



}
