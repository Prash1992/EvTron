package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ev.evtron.R;
import com.ev.evtron.activities.ui.BottomNavigationActivity;
import com.ev.evtron.utils.PreferenceManager;

public class SplashActivity extends AppCompatActivity {
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_splash);
        gotoWelcomeTutorial();

    }

    private void gotoWelcomeTutorial() {

        final boolean isInitialDeploy = getPreferenceManager().getPrefIsInitialDeploy();
        final boolean isLoggedIn = getPreferenceManager().getPrefIsLoggedIn();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (isInitialDeploy) {
                    intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                }
//                else if (isLoggedIn) {
//                    intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
//                    intent.putExtra("id",getPreferenceManager().getPrefcustomerId());
//                    intent.putExtra("jwtToken",getPreferenceManager().getPrefJwtToken());
//                } else
                else {
                    intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                }
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 3000);
    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

}