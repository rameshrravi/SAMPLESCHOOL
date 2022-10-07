package com.licet.tech.activity;

import static com.licet.tech.api.Service.createServiceHeader;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.licet.tech.MainActivity;
import com.licet.tech.R;
import com.licet.tech.adapter.CalendarAdapter;
import com.licet.tech.api.API;
import com.licet.tech.dialog.MethodUtils;
import com.licet.tech.utils.MyPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendenceActivity extends AppCompatActivity {
    ArrayList<String> holi_array = new ArrayList<>();
    ArrayList<String> holi_array_reason = new ArrayList<>();
    ArrayList<String> absent_array = new ArrayList<>();
    ArrayList<String> absent_array_reason = new ArrayList<>();


    public static List<String> cur_absent_list = new ArrayList<>();
    public static List<String> cur_holiday_list = new ArrayList<>();
    String s_id;

    int pos;


    public Calendar month, itemmonth;// calendar instances.

    public CalendarAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList<String> items;

    @BindView(R.id.sun)
    TextView t1;
    @BindView(R.id.mon)
    TextView t2;
    @BindView(R.id.tue)
    TextView t3;
    @BindView(R.id.wed)
    TextView t4;
    @BindView(R.id.thu)
    TextView t5;
    @BindView(R.id.fri)
    TextView t6;
    @BindView(R.id.sat)
    TextView t7;

    ProgressDialog dialog;
    MyPreference myPreference;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        myPreference = new MyPreference(getApplicationContext());
        dialog = new ProgressDialog(AttendenceActivity.this);
        dialog.setMessage("Loading");
        dialog.show();


        month = Calendar.getInstance();
        itemmonth = (Calendar) month.clone();

        items = new ArrayList<String>();

        handler = new Handler();
        getAttendenceDetails();
       /* adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater);*/
        RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);
        RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }


                ((CalendarAdapter) parent.getAdapter()).setSelected(v);

                // DAte Passing


                Reason(selectedGridDate);


            }
        });

    }

    protected void setNextMonth() {
        if (month.get(Calendar.MONTH) == month.getActualMaximum(Calendar.MONTH)) {
            month.set((month.get(Calendar.YEAR) + 1),
                    month.getActualMinimum(Calendar.MONTH), 1);
        } else {
            month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH)) {
            month.set((month.get(Calendar.YEAR) - 1),
                    month.getActualMaximum(Calendar.MONTH), 1);
        } else {
            month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
        }

    }

    protected void showToast(String string) {
        //Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }

    public void Reason(String dte) {


        if (holi_array.contains(dte)) {
            for (int i = 0; i < holi_array.size(); i++) {
                String ss = holi_array.get(i);
                if (ss.equals(dte)) {
                    pos = holi_array.indexOf(ss);

                    getreason(pos, dte);
                }
            }
        } else {
            for (int i = 0; i < absent_array.size(); i++) {
                String j = absent_array.get(i);
                if (j.equals(dte)) {
                    pos = absent_array.indexOf(j);
                    //Toast.makeText(Attendence.this,""+pos,Toast.LENGTH_SHORT).show();
                    getabsentreason(pos);
                }
            }
        }


    }

    private void getabsentreason(int pos) {
        String item = absent_array_reason.get(pos);

        Toast.makeText(AttendenceActivity.this, "" + item, Toast.LENGTH_SHORT).show();
    }

    private void getreason(int pos, String dte) {
        // Toast.makeText(Attendence.this,""+pos,Toast.LENGTH_SHORT).show();
        String item = holi_array_reason.get(pos);
        DialogTextView(item, dte);
        //  Toast.makeText(Attendence.this,""+item,Toast.LENGTH_SHORT).show();
        // showToast(item);
    }


    private void DialogTextView(String item, String date) {

        final Dialog dialog = new Dialog(AttendenceActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog);
        // Set dialog title
        dialog.setTitle("Holiday");

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.dialog_date);
        TextView event = (TextView) dialog.findViewById(R.id.dialog_event);

        text.setText(date);
        event.setText(item);


        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.dialog_declineButton);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
    }

    public void refreshCalendar() {

        TextView title = (TextView) findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }


    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(Calendar.DATE, 1);
                items.add("2022-09-12");
                items.add("2022-10-07");
                items.add("2022-10-15");
                items.add("2022-10-20");
                items.add("2022-11-30");
                items.add("2022-11-28");
            }

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };

    public void getAttendenceDetails() {
        Call<ResponseBody> call = null;
        API apiService = createServiceHeader(API.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("student_id", 1);
        //jsonObject.addProperty("student_id", myPreference.getDefaultRunTimeValue()[3]);
        jsonObject.addProperty("month", 10);
        call = apiService.attendenceAPI(jsonObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    dialog.dismiss();
                    String data = response.body().string();
                    Log.i("RameshData", data);
                    JSONObject prent = new JSONObject(data);
                    String code = prent.getString("code");
                    if (code.equals("1")) {

                        JSONObject jsonObject = new JSONObject(data);

                        JSONArray json_holiday_Array = jsonObject.getJSONArray("holiday");
                        JSONArray json_leave_array = jsonObject.getJSONArray("attendance");

                        for (int i = 0; i < json_holiday_Array.length(); i++) {
                            JSONObject holiday_0bj = json_holiday_Array.getJSONObject(i);

                            String ss = holiday_0bj.getString("date");
                            String rea = holiday_0bj.getString("reason");
                            holi_array.add(ss);
                            holi_array_reason.add(rea);

                        }

                        for (int i = 0; i < json_leave_array.length(); i++) {
                            JSONObject leave_obj = json_leave_array.getJSONObject(i);

                            String ss = leave_obj.getString("reason");
                            if (ss.equals("absent")) {
                                String abs = leave_obj.getString("date");
                                String res = leave_obj.getString("reason");
                                absent_array.add(abs);
                                absent_array_reason.add(res);
                            }


                        }

                       // JSONArray cur_json_leave_array = resultobj.getJSONArray("current_attendance");
                      //  JSONArray cur_json_holiday_array = resultobj.getJSONArray("current_holiday");

/*
                        for (int i = 0; i < cur_json_holiday_array.length(); i++) {
                            JSONObject holiday_0bj = cur_json_holiday_array.getJSONObject(i);

                            String ss = holiday_0bj.getString("date");
                            cur_holiday_list.add(ss);

                        }

                        for (int i = 0; i < cur_json_leave_array.length(); i++) {
                            JSONObject leave_obj = cur_json_leave_array.getJSONObject(i);

                            String ss = leave_obj.getString("status");
                            if (ss.equals("absent")) {
                                String abs = leave_obj.getString("date");
                                cur_absent_list.add(abs);

                            }


                        }*/

                        go();
                        adapter = new CalendarAdapter(AttendenceActivity.this, (GregorianCalendar) month, holi_array, absent_array, cur_holiday_list, cur_absent_list);
                        GridView gridview = (GridView) findViewById(R.id.gridview);
                        gridview.setAdapter(adapter);
                    } else {
                        MethodUtils.errorMsg(AttendenceActivity.this, "Data Empty");
                    }

                } catch (Exception e) {
                    MethodUtils.errorMsg(AttendenceActivity.this, e.toString());
                    //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();

            }
        });
    }
    public void go()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Red line Indicates Personal leave\n\nBlue line indicates School holidays");
        alertDialogBuilder.setPositiveButton("Tap to see",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        refreshCalendar();
                        dialog.dismiss();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}