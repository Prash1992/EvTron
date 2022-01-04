package com.ev.evtron.activities.ui;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.ev.evtron.fragments.AccountFragment;
import com.ev.evtron.fragments.GreenEnergyFragment;
import com.ev.evtron.fragments.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ev.evtron.BottomSheetFull;
import com.ev.evtron.R;
import com.ev.evtron.activities.PersonalInformationActivity;
import com.ev.evtron.activities.ui.ui.BottomSheetEVownerDialog;
import com.ev.evtron.utils.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class BottomNavigationActivity extends AppCompatActivity {
    private BottomNavigationView navView;
    private PreferenceManager preferenceManager;
    private BottomSheetDialog bottomSheet;
    private BottomSheetEVownerDialog bottomSheetEVownerDialog;
    private RelativeLayout rlwholelayout;
    public static boolean ontochedscreen = false;
    MeowBottomNavigation meowBottomNavigation;
    private FragmentManager fragmentManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            assert action != null;
            switch (action) {
                case "user_loggedin_successfully":
                    bottomSheet.dismiss();
                    if (getPreferenceManager().getPrefUserFlag().equals("01")){
                        bottomSheetEVownerDialog = new BottomSheetEVownerDialog();
                        bottomSheetEVownerDialog.setCancelable(false);
                        bottomSheetEVownerDialog.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                    break;

                case "ev_owner":
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                    Intent personalinformationactivityintent = new Intent(getApplicationContext(), PersonalInformationActivity.class);
                    startActivity(personalinformationactivityintent, options.toBundle());
                    bottomSheetEVownerDialog.dismiss();
                    break;

                case "dismiss_bottom_sheet":
                    bottomSheetEVownerDialog.dismiss();
                    break;

//                case "openLocationBottomSheet":
//                    Log.i("openLocationBottomSheet", "openLocationBottomSheet");
//                    BottomSheetFull bottomSheetFull = new BottomSheetFull();
//                    bottomSheetFull.show(getSupportFragmentManager(), bottomSheetFull.getTag());
//                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_navigation);
//        navView = findViewById(R.id.nav_view);
//        navView.setItemIconTintList(null);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navView, navController);

        initialisation();
        fragmentManager = getSupportFragmentManager();
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.greenenergy));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_home_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.iconmaterialaccountcircle));
        setdefaults();
        setlisteners();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, new IntentFilter("user_loggedin_successfully"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, new IntentFilter("ev_owner"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, new IntentFilter("dismiss_bottom_sheet"));
//        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, new IntentFilter("openLocationBottomSheet"));

    }

    private void setlisteners() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!getPreferenceManager().getPrefIsLoggedIn()) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                if (!ontochedscreen) {
                    bottomSheet = new BottomSheetDialog();
                    bottomSheet.show(getSupportFragmentManager(),
                            "ModalBottomSheet");
                }
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    private void setdefaults() {
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
//                        Toast.makeText(getApplicationContext(), "1 clicked", Toast.LENGTH_SHORT).show();
                        loadFragment(new GreenEnergyFragment());
                        break;
                    case 2:
                        loadFragment(new MapsFragment());
//                        Toast.makeText(getApplicationContext(), "2 clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        loadFragment(new AccountFragment());
//                        Toast.makeText(getApplicationContext(), "3 clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        meowBottomNavigation.show(2,true);
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
               // Toast.makeText(getApplicationContext(), item.getId()+"clicked", Toast.LENGTH_SHORT).show();

            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), item.getId()+"Reselected", Toast.LENGTH_SHORT).show();

            }
        });



        if (!getPreferenceManager().getPrefIsLoggedIn()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bottomSheet = new BottomSheetDialog();
                    bottomSheet.show(getSupportFragmentManager(),
                            "ModalBottomSheet");
                }
            }, 1500);

        }
    }

    private void initialisation() {
        meowBottomNavigation = findViewById(R.id.bottomNav);

        // rlwholelayout = findViewById(R.id.rlwholelayout);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);

    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("permission", "granted");
                Intent intent = new Intent("load_map");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
        }
    }

    /**
     * @brief Show logout dialog to logout from the application
     */
    public void show_type_identifier_dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.type_identifier_dialog);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (getResources().getDisplayMetrics().heightPixels);

        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button btnEventhusiastic = (Button) dialog.findViewById(R.id.btnEventhusiastic);
        TextView btnEvowner = (TextView) dialog.findViewById(R.id.btnEvowner);
//            TextView tvSkip = (TextView) dialog.findViewById(R.id.tvSkip);


//            tvSkip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
        btnEventhusiastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                Intent intent = new Intent(getApplicationContext(), PersonalInformationActivity.class);
                startActivity(intent, options.toBundle());
            }

        });

        btnEvowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onresume", "called");
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragment.setArguments(bundlesupport);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}