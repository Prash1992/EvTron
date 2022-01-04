package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.ev.evtron.R;
import com.ev.evtron.utils.PreferenceManager;

public class FilterActivity extends AppCompatActivity {
    private PreferenceManager preferenceManager;
    private RadioGroup rgchargertype;
    private RadioButton rball;
    private RadioButton rbac;
    private RadioButton rbdc;
    private ImageView ivpin1;
    private ImageView ivpin2;
    private ImageView ivpin3;
    private ImageView ivpin4;
    private ImageView ivpin5;
    private ImageView ivpin6;
    private RelativeLayout rlpin1;
    private RelativeLayout rlpin2;
    private RelativeLayout rlpin3;
    private RelativeLayout rlpin4;
    private RelativeLayout rlpin5;
    private RelativeLayout rlpin6;

    private RelativeLayout rlivbar;
    private RelativeLayout rlplayarea;
    private RelativeLayout rlpool;
    private RelativeLayout rlrestaurant;
    private RelativeLayout rlrooms;
    private RelativeLayout rlwifi;

    private ImageView ivbar;
    private ImageView ivplayarea;
    private ImageView ivpool;
    private ImageView ivrestaurant;
    private ImageView ivrooms;
    private ImageView ivwifi;

    private int ivbarcount = 0;
    private int ivplayareacount = 0;
    private int ivpoolcount = 0;
    private int ivrestaurantcount = 0;
    private int ivroomscount = 0;
    private int ivwificount = 0;

    private int pin1count = 0;
    private int pin2count = 0;
    private int pin3count = 0;
    private int pin4count = 0;
    private int pin5count = 0;
    private int pin6count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_filter);
        initialisation();
        setListeners();
        setdefaults();
    }

    private void setdefaults() {
    }

    private void setListeners() {
        rgchargertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rball) {
                    rbac.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));
                    rball.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg_white));
                    rbdc.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));
                } else if (i == R.id.rbac) {
                    rbac.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg_white));
                    rball.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));
                    rbdc.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));


                } else if (i == R.id.rbdc) {
                    rbac.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));
                    rball.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg));
                    rbdc.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.layout_bg_white));
                }
            }
        });

        ivpin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin1count++;
                if (pin1count==1){
                    rlpin1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin1count=0;
                    rlpin1.setBackgroundResource(0);
                }
            }
        });
        ivpin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin2count++;
                if (pin2count==1){
                    rlpin2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin2count=0;
                    rlpin2.setBackgroundResource(0);
                }
            }
        });
        ivpin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin3count++;
                if (pin3count==1){
                    rlpin3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin3count=0;
                    rlpin3.setBackgroundResource(0);
                }
            }
        });
        ivpin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin4count++;
                if (pin4count==1){
                    rlpin4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin4count=0;
                    rlpin4.setBackgroundResource(0);
                }
            }
        });
        ivpin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin5count++;
                if (pin5count==1){
                    rlpin5.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin5count=0;
                    rlpin5.setBackgroundResource(0);
                }
            }
        });
        ivpin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin6count++;
                if (pin6count==1){
                    rlpin6.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    pin6count=0;
                    rlpin6.setBackgroundResource(0);
                }
            }
        });
        ivbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivbarcount++;
                if (ivbarcount==1){
                    rlivbar.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivbarcount=0;
                    rlivbar.setBackgroundResource(0);
                }
            }
        });
        ivpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivpoolcount++;
                if (ivpoolcount==1){
                    rlpool.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivpoolcount=0;
                    rlpool.setBackgroundResource(0);
                }
            }
        });
        ivplayarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivplayareacount++;
                if (ivplayareacount==1){
                    rlplayarea.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivplayareacount=0;
                    rlplayarea.setBackgroundResource(0);
                }
            }
        });
        ivrestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivrestaurantcount++;
                if (ivrestaurantcount==1){
                    rlrestaurant.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivrestaurantcount=0;
                    rlrestaurant.setBackgroundResource(0);
                }
            }
        });
        ivrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivroomscount++;
                if (ivroomscount==1){
                    rlrooms.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivroomscount=0;
                    rlrooms.setBackgroundResource(0);
                }
            }
        });
        ivwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivwificount++;
                if (ivwificount==1){
                    rlwifi.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.circle));
                }else {
                    ivwificount=0;
                    rlwifi.setBackgroundResource(0);
                }
            }
        });
    }


    private void initialisation() {
        rgchargertype = findViewById(R.id.rgchargertype);
        rball = findViewById(R.id.rball);
        rbac = findViewById(R.id.rbac);
        rbdc = findViewById(R.id.rbdc);
        ivpin1 = findViewById(R.id.ivpin1);
        ivpin2 = findViewById(R.id.ivpin2);
        ivpin3 = findViewById(R.id.ivpin3);
        ivpin4 = findViewById(R.id.ivpin4);
        ivpin5 = findViewById(R.id.ivpin5);
        ivpin6 = findViewById(R.id.ivpin6);
        ivbar = findViewById(R.id.ivbar);
        ivplayarea = findViewById(R.id.ivplayarea);
        ivpool = findViewById(R.id.ivpool);
        ivrestaurant = findViewById(R.id.ivrestaurant);
        ivrooms = findViewById(R.id.ivrooms);
        ivwifi = findViewById(R.id.ivwifi);
        rlpin1 = findViewById(R.id.rlpin1);
        rlpin2 = findViewById(R.id.rlpin2);
        rlpin3 = findViewById(R.id.rlpin3);
        rlpin4 = findViewById(R.id.rlpin4);
        rlpin5 = findViewById(R.id.rlpin5);
        rlpin6 = findViewById(R.id.rlpin6);
        rlivbar = findViewById(R.id.rlivbar);
        rlplayarea = findViewById(R.id.rlplayarea);
        rlpool = findViewById(R.id.rlpool);
        rlrestaurant = findViewById(R.id.rlrestaurant);
        rlrooms = findViewById(R.id.rlrooms);
        rlwifi = findViewById(R.id.rlwifi);
//        rangeslider = findViewById(R.id.rangeslider);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

}