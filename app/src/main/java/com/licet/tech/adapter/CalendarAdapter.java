package com.licet.tech.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.licet.tech.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;



public class CalendarAdapter extends BaseAdapter {

    private Context mContext;

    private java.util.Calendar month;
    public GregorianCalendar pmonth; // calendar instance for previous month

    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat df;

    ArrayList<String> cal_leave=new ArrayList<>();
    private ArrayList<String> items;
    public static List<String> dayString;
    public static List<String> holiday_list;
    public static List<String> holiday_list_reason;

    public static List<String> absent_list_reason;
    public static List<String> absent_list;


    public static List<String> cur_absent_list;
    public static List<String> cur_holiday_list;
    private View previousView;

    public CalendarAdapter(Context c, GregorianCalendar monthCalendar, ArrayList<String> holiday, ArrayList<String> absent, List<String> cur_holiday, List<String> cur_absent) {
        CalendarAdapter.dayString = new ArrayList<String>();
        month = monthCalendar;
        CalendarAdapter.holiday_list=holiday;
        CalendarAdapter.absent_list=absent;
        CalendarAdapter.cur_absent_list=cur_absent;
        CalendarAdapter.cur_holiday_list=cur_holiday;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        mContext = c;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();
    }

    public void setItems(ArrayList<String> items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }

    public int getCount() {
        return dayString.size();
    }

    public Object getItem(int position) {
        return dayString.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView,reason;
        ImageView back_image;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_item, null);

        }


        dayView = (TextView) v.findViewById(R.id.date);
        reason = (TextView) v.findViewById(R.id.cal_reason);
        back_image=(ImageView)v.findViewById(R.id.textback_image);
        // separates daystring into parts.
        String[] separatedTime = dayString.get(position).split("-");
        // taking last part of date. ie; 2 from 2012-12-02
        String gridvalue = separatedTime[2].replaceFirst("^0*", "");
        // checking whether the day is in current month or not.
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            // setting offdays to white color.
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.
            dayView.setTextColor(Color.BLACK);
        }

        if (dayString.get(position).equals(curentDateString)) {

            setSelected(v);
            previousView = v;
          //  v.setBackgroundResource(R.drawable.green_white);
        } else {
            v.setBackgroundResource(R.drawable.list_item_background);
        }


        // create date string for comparison
        String date = dayString.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }

        // show icon if date is not empty and it exists in the items array
        ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
        if (date.length() > 0 && items != null && items.contains(date)) {
            iw.setVisibility(View.VISIBLE);
        } else {
            iw.setVisibility(View.INVISIBLE);
        }





        for (int i=0;i<absent_list.size();i++) {
            String s = absent_list.get(i);

            if(dayString.get(position).equals(s))
            {
                dayView.setTextColor(Color.RED);
               // dayView.setTextSize(17);
               // v.setBackgroundColor(Color.RED);
                v.setBackgroundResource(R.drawable.redd);

            }

            if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
                // setting offdays to white color.
                dayView.setTextColor(Color.GRAY);
                dayView.setClickable(false);
                dayView.setFocusable(false);
            } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
                dayView.setTextColor(Color.GRAY);
                dayView.setClickable(false);
                dayView.setFocusable(false);
            } else {
                // setting curent month's days in blue color.
                dayView.setTextColor(Color.BLACK);
            }


        }




        for (int i=0;i<holiday_list.size();i++)
        {


            String s_holi= holiday_list.get(i);


            if(dayString.get(position).equals(s_holi))
            {
                dayView.setTextColor(Color.BLACK);


                v.setBackgroundResource(R.drawable.blue);
            }
            else {
                dayView.setTextColor(Color.BLACK);
            }

            if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
                // setting offdays to white color.
                dayView.setTextColor(Color.GRAY);
                dayView.setClickable(false);
                dayView.setFocusable(false);
            } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
                dayView.setTextColor(Color.GRAY);
                dayView.setClickable(false);
                dayView.setFocusable(false);
            } else {
                // setting curent month's days in blue color.
                dayView.setTextColor(Color.BLACK);
            }


        }
        dayView.setText(gridvalue);

        return v;
    }

    public View setSelected(View view) {
        return view;
    }

    public void refreshDays() {
        // clear items
        items.clear();
        dayString.clear();
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        /**
         * Calendar instance for getting a complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        /**
         * setting the start date as previous month's required date.
         */
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        /**
         * filling calendar gridview.
         */
        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            dayString.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }



}
