package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ev.evtron.R;
import com.ev.evtron.adapters.CurrentGridItemView;
import com.ev.evtron.adapters.CurrentGridViewAdapter;
import com.ev.evtron.adapters.NextGridItemView;
import com.ev.evtron.adapters.NextGridViewAdapter;
import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;
import com.ev.evtron.interfaces.viewresponseinterface.GetSlotViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetSlotResponseModel;
import com.ev.evtron.viewmodel.GetSlotViewModel;

import java.sql.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SelectSlotActivity extends AppCompatActivity implements GetSlotViewResponseInterface {

    private Button btnestimateprice;
    private TextView tvCurrentDate;
    private TextView tvNextDate;

    private TextView tvTime;
    private GridView currentgridView;
    private GridView gvNextGrid;
    private ArrayList<String> selectedStrings;
    private ArrayList<String> selectedNextStrings;
    private static String[] numbers = new String[]{};
    private static String[] id = new String[]{};
    private static String[] slotStatusCode = new String[]{};
    private static String[] slotStatusCodeNextDay = new String[]{};
    private static String[] id1 = new String[]{};
    private static String[] nextnumbers = new String[]{};

    CurrentGridViewAdapter adapter;
    NextGridViewAdapter nextGridViewAdapter;
    DatePickerDialog.OnDateSetListener date;
    private Calendar calendar;
    int hour12hrs;
    int minutes;
    private TextView tvDate;
    private Bundle bundle;
    private GetSlotViewModel getSlotViewModel;
    int chargebayid =0;
    private String name="";
    private String address="";
    private String district="";
    private String chargerType="";
    private int stationId = 0;
    private String date1 = "";
    private String date2 = "";
    private String slotString1 = "";
    private String slotString2 = "";

    private TextView tvChargeName;
    private TextView tvChargeAddress;
    private TextView tvChargeDistrict;
    private TextView tvFourHour;
    private TextView tvThreeHour;
    private RelativeLayout rlDefault;
    private RelativeLayout rlOneHour;
    private RelativeLayout rlTwoHour;
    private RelativeLayout rlThreeHour;
    private RelativeLayout rlFourHour;
    private TextView tvTwohour;
    private TextView tvOneHour;
    private TextView tvDefault;
    private ImageView ivback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_select_slot);
        initialisation();
        getSlotViewModel = new GetSlotViewModel(this, this);
        setdefaults();
        setlisteners();
    }


    private void initialisation() {
        btnestimateprice = findViewById(R.id.btnestimateprice);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvChargeName = findViewById(R.id.tvChargeName);
        tvChargeAddress = findViewById(R.id.tvChargeAddress);
        tvChargeDistrict = findViewById(R.id.tvChargeDistrict);
        rlDefault = findViewById(R.id.rlDefault);
        rlOneHour = findViewById(R.id.rlOneHour);
        rlTwoHour = findViewById(R.id.rlTwoHour);
        rlThreeHour = findViewById(R.id.rlThreeHour);
        rlFourHour = findViewById(R.id.rlFourHour);
        tvFourHour = findViewById(R.id.tvFourHour);
        tvThreeHour = findViewById(R.id.tvThreeHour);
        tvTwohour = findViewById(R.id.tvTwohour);
        tvOneHour = findViewById(R.id.tvOneHour);
        tvDefault = findViewById(R.id.tvDefault);
        ivback = findViewById(R.id.ivback);
        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        tvNextDate = findViewById(R.id.tvNextDate);
    }

    private void setdefaults() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            chargebayid = bundle.getInt("chargebayid");
            name = bundle.getString("name");
            address = bundle.getString("address");
            district = bundle.getString("district");
            chargerType = bundle.getString("chargerType");
            stationId = bundle.getInt("stationId");
            Log.i("chargebayid", chargebayid + "");

            tvChargeName.setText(name);
            tvChargeAddress.setText(address);
            tvChargeDistrict.setText(district);

        }

        calendar = Calendar.getInstance();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String formattedDate = sdf.format(d);

        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        String formattedTime = time.format(d);
//        Log.i("hour12hrs",hour12hrs+"");
        Log.i("formatted", formattedDate + "");
        Log.i("formattedTime", formattedTime + "");
        tvTime.setText(formattedDate);

        updateLabel();

        selectedStrings = new ArrayList<>();
        selectedNextStrings = new ArrayList<>();
        currentgridView = findViewById(R.id.gvCurrentGrid);
        gvNextGrid = findViewById(R.id.gvNextGrid);

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
        String currentdate = simpleDateFormat.format(calendar.getTime());
        Log.i("current",currentdate);

        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("gmt"));
        String gmtTime = df.format(new Date());
        Log.i("gmt",gmtTime);

        //getSlotViewModel.setChargerbayId(String.valueOf(chargebayid));
        getSlotViewModel.setChargerType(chargerType);
        getSlotViewModel.setStationId(String.valueOf(stationId));
       // getSlotViewModel.setTime("2021-11-10 02:15:22 UTC+0000");
        getSlotViewModel.setTime(currentdate +" " + gmtTime.substring(0,gmtTime.length()-3)+" "+"UTC+0000");
        getSlotViewModel.getslots();

    }

    private void setlisteners() {

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rlDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDefault.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_green_selection));
                tvDefault.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

                rlOneHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvOneHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlTwoHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvTwohour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlThreeHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvThreeHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlFourHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvFourHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));


            }
        });

        rlOneHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDefault.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvDefault.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlOneHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_green_selection));
                tvOneHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

                rlTwoHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvTwohour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlThreeHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvThreeHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlFourHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvFourHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));


            }
        });

        rlTwoHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDefault.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvDefault.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlOneHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvOneHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlTwoHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_green_selection));
                tvTwohour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

                rlThreeHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvThreeHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlFourHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvFourHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));


            }
        });

        rlThreeHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDefault.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvDefault.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlOneHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvOneHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlTwoHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvTwohour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlThreeHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_green_selection));
                tvThreeHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

                rlFourHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvFourHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));


            }
        });

        rlFourHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlDefault.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvDefault.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlOneHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvOneHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlTwoHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvTwohour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlThreeHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_selection));
                tvThreeHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.elephant_grey));

                rlFourHour.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_slot_green_selection));
                tvFourHour.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));


            }
        });

        currentgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int selectedIndex = adapter.selectedPositions.indexOf(i);
                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((CurrentGridItemView) view).display(false);
                    selectedStrings.remove((String) adapterView.getItemAtPosition(i));
                } else {
                    if (adapter.getSlotStatusitem(i).equals("1")) {
                        ((CurrentGridItemView) view).isclickable(false);
                    } else {
                        adapter.selectedPositions.add(i);
                        view.setTag(i);
                        ((CurrentGridItemView) view).display(true);
                        Log.i("gettag", view.getTag() + "");
                        selectedStrings.add((String) adapterView.getItemAtPosition(i));
                        Log.i("selectedcurrent",selectedStrings.toString());
//                        Log.i("converted", Arrays.toString(selectedStrings.toArray()));


                    }
                }
                StringBuilder sb = new StringBuilder();
                for (String s : selectedStrings)
                {
                    sb.append(s);
                    sb.append(",");
                }
                String removecomma = sb.toString();
                try {
                    slotString1= removecomma.substring(0,removecomma.length()-1);
                    Log.i("currentconverted",slotString1);
                }catch (Exception e){
                    slotString1 = "";
                }

            }
        });
        gvNextGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int selectedIndex = nextGridViewAdapter.selectedPositions.indexOf(i);
                if (selectedIndex > -1) {
                    nextGridViewAdapter.selectedPositions.remove(selectedIndex);
                    ((NextGridItemView) view).display(false);
                    selectedNextStrings.remove((String) adapterView.getItemAtPosition(i));
                } else {
                    if (nextGridViewAdapter.getSlotStatusCodeNextDayItem(i).equals("1")) {
                        ((NextGridItemView) view).isclickable(false);
                    } else {
                        nextGridViewAdapter.selectedPositions.add(i);
                        view.setTag(i);
                        ((NextGridItemView) view).display(true);
                        Log.i("gettag", view.getTag() + "");
                        selectedNextStrings.add((String) adapterView.getItemAtPosition(i));

                    }
                }

                StringBuilder sb = new StringBuilder();
                for (String s : selectedNextStrings)
                {
                    sb.append(s);
                    sb.append(",");
                }
                String removecomma = sb.toString();

                try {
                    slotString2= removecomma.substring(0,removecomma.length()-1);
                    Log.i("nextconverted",slotString2);
                }catch (Exception e){
                    slotString2 = "";
                    e.printStackTrace();
                }

                //Log.i("removecomma",removecomma);
            }
        });

        btnestimateprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EstimationActivity.class);
                if (slotString1.isEmpty()){
                    intent.putExtra("currentDate","");
                }else {
                    intent.putExtra("currentDate",date1);

                }

                if (slotString2.isEmpty()){
                    intent.putExtra("nextDate","");

                }else {
                    intent.putExtra("nextDate",date2);
                }
                intent.putExtra("slotString1",slotString1);
                intent.putExtra("slotString2",slotString2);
                intent.putExtra("chargerBayId",chargebayid);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            }
        });


//        llDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(SelectSlotActivity.this, date, calendar
//                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH)).show();
//
//            }
//        });


    }

    private void updateLabel() {
        String myFormat = "MMM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("datee", sdf.format(calendar.getTime()));
        String currentdate = sdf.format(calendar.getTime());
        Date currentTime = Calendar.getInstance().getTime();
        Log.i("currenttime", currentTime + "");

        tvDate.setText(currentdate.substring(4, 6) + " " + currentdate.substring(0, 3) + " " + currentdate.substring(7));
    }

    @Override
    public void getslotreceived(GetSlotResponseModel getSlotResponseModels) {
        date1 = getSlotResponseModels.getCurrentDate();
        date2 = getSlotResponseModels.getNextDate();
        tvCurrentDate.setText(date1);
        tvNextDate.setText(date2);
        numbers = new String[getSlotResponseModels.getCurrentDaySlot().size()];
        id = new String[getSlotResponseModels.getCurrentDaySlot().size()];
        slotStatusCode = new String[getSlotResponseModels.getCurrentDaySlot().size()];
        id1 = new String[getSlotResponseModels.getNextDaySlot().size()];
        nextnumbers = new String[getSlotResponseModels.getNextDaySlot().size()];
        slotStatusCodeNextDay = new String[getSlotResponseModels.getNextDaySlot().size()];
        int size = getSlotResponseModels.getCurrentDaySlot().size();
        int nextsize = getSlotResponseModels.getNextDaySlot().size();
        int dpToENterForCurrentGrid = 0 ;
        int dpToEnterForNextGrid = 0 ;
        int defaultsize = 430;
        Log.i("size",size+"");
        if (size<=6){
            dpToENterForCurrentGrid = defaultsize;
        }else if (size <=12){
            dpToENterForCurrentGrid = defaultsize * 2;
        }else if (size <=18){
            dpToENterForCurrentGrid = defaultsize * 3;
        }else if (size <=24){
            dpToENterForCurrentGrid = defaultsize * 4;
        }else if (size <=30){
            dpToENterForCurrentGrid = defaultsize * 5;
        }else if (size <=36){
            dpToENterForCurrentGrid = defaultsize * 6;
        }else if (size <=42){
            dpToENterForCurrentGrid = defaultsize * 7;
        }else if (size <=48){
            dpToENterForCurrentGrid = defaultsize * 8;
        }else if (size <=54){
            dpToENterForCurrentGrid = defaultsize * 9;
        }else if (size <=60){
            dpToENterForCurrentGrid = defaultsize * 10;
        }else if (size <=66){
            dpToENterForCurrentGrid = defaultsize * 11;
        }else if (size <=72){
            dpToENterForCurrentGrid = defaultsize * 12;
        }else if (size <=78){
            dpToENterForCurrentGrid = defaultsize * 13;
        }else if (size <=84){
            dpToENterForCurrentGrid = defaultsize * 14;
        }else if (size <=90){
            dpToENterForCurrentGrid = defaultsize * 15;
        }else if (size <=96){
            dpToENterForCurrentGrid = defaultsize * 16;
        }

        if (nextsize<=6){
            dpToEnterForNextGrid = defaultsize;
        }else if (nextsize <=12){
            dpToEnterForNextGrid = defaultsize * 2;
        }else if (nextsize <=18){
            dpToEnterForNextGrid = defaultsize * 3;
        }else if (nextsize <=24){
            dpToEnterForNextGrid = defaultsize * 4;
        }else if (nextsize <=30){
            dpToEnterForNextGrid = defaultsize * 5;
        }else if (nextsize <=36){
            dpToEnterForNextGrid = defaultsize * 6;
        }else if (nextsize <=42){
            dpToEnterForNextGrid = defaultsize * 7;
        }else if (nextsize <=48){
            dpToEnterForNextGrid = defaultsize * 8;
        }else if (nextsize <=54){
            dpToEnterForNextGrid = defaultsize * 9;
        }else if (nextsize <=60){
            dpToEnterForNextGrid = defaultsize * 10;
        }else if (nextsize <=66){
            dpToEnterForNextGrid = defaultsize * 11;
        }else if (nextsize <=72){
            dpToEnterForNextGrid = defaultsize * 12;
        }else if (nextsize <=78){
            dpToEnterForNextGrid = defaultsize * 13;
        }else if (nextsize <=84){
            dpToEnterForNextGrid = defaultsize * 14;
        }else if (nextsize <=90){
            dpToEnterForNextGrid = defaultsize * 15;
        }else if (nextsize <=96){
            dpToEnterForNextGrid = defaultsize * 16;
        }
        for (int i = 0; i < getSlotResponseModels.getCurrentDaySlot().size(); i++) {
            Log.i("slotdetail", getSlotResponseModels.getCurrentDaySlot().get(i).getTimeString());
            numbers[i] = getSlotResponseModels.getCurrentDaySlot().get(i).getTimeString();
            id[i] = getSlotResponseModels.getCurrentDaySlot().get(i).getId();
            slotStatusCode[i] = getSlotResponseModels.getCurrentDaySlot().get(i).getSlotStatusCode();
        }

        for (int i = 0; i < getSlotResponseModels.getNextDaySlot().size(); i++) {
            Log.i("nextslotdetail", getSlotResponseModels.getNextDaySlot().get(i).getTimeString());
            nextnumbers[i] = getSlotResponseModels.getNextDaySlot().get(i).getTimeString();
            id1[i] = getSlotResponseModels.getNextDaySlot().get(i).getId();
            slotStatusCodeNextDay[i] = getSlotResponseModels.getNextDaySlot().get(i).getSlotStatusCode();
        }

        ViewGroup.LayoutParams layoutParams = currentgridView.getLayoutParams();
        int layoutHHeight = pxToDp(dpToENterForCurrentGrid);
        layoutParams.height = layoutHHeight; //this is in pixels
        currentgridView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParamsnext = gvNextGrid.getLayoutParams();
        int layoutHeight = pxToDp(dpToEnterForNextGrid);
        layoutParamsnext.height = layoutHeight; //this is in pixels
        gvNextGrid.setLayoutParams(layoutParamsnext);


        adapter = new CurrentGridViewAdapter(numbers,id,slotStatusCode, this);
        nextGridViewAdapter = new NextGridViewAdapter(nextnumbers,id1,slotStatusCodeNextDay, this);
        currentgridView.setAdapter(adapter);
        gvNextGrid.setAdapter(nextGridViewAdapter);

    }

    @Override
    public void onFailure(ErrorBody errorBody, int statuscode) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {

    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}