package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.viewresponseinterface.ReviewViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.ReviewResponseModel;
import com.ev.evtron.viewmodel.ReviewViewModel;

public class ReviewActivity extends AppCompatActivity implements ReviewViewResponseInterface {

    int angryEmoji = 0X1F621;
    int sadEmoji = 0x1F614;
    int neutralEmoji = 0x1F610;
    int happyEmoji = 0x1F60A;
    int loveEmoji = 0x1F970;
    private TextView tvAngry;
    private TextView tvSad;
    private TextView tvNeutral;
    private TextView tvHappy;
    private TextView tvLove;
    private Button btnSubmit;
    private ReviewViewModel reviewViewModel;
    private RelativeLayout rlAngry;
    private RelativeLayout rlSad;
    private RelativeLayout rlHappy;
    private RelativeLayout rlNeutral;
    private RelativeLayout rlLove;
    private String rating = "";
    private EditText etReview;
    private Bundle bundle;
    private String bookingInternalId = "";
    private ImageView ivBackReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initialisation();
        reviewViewModel = new ReviewViewModel(this, this);
        setDefaults();
        setListeners();
    }

    private void setListeners() {

        rlAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "1";
                rlAngry.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_review));
                rlSad.setBackgroundResource(0);
                rlNeutral.setBackgroundResource(0);
                rlHappy.setBackgroundResource(0);
                rlLove.setBackgroundResource(0);
            }
        });
        rlSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "2";
                rlSad.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_review));
                rlAngry.setBackgroundResource(0);
                rlNeutral.setBackgroundResource(0);
                rlHappy.setBackgroundResource(0);
                rlLove.setBackgroundResource(0);
            }
        });
        rlNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "3";
                rlNeutral.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_review));
                rlAngry.setBackgroundResource(0);
                rlSad.setBackgroundResource(0);
                rlHappy.setBackgroundResource(0);
                rlLove.setBackgroundResource(0);
            }
        });
        rlHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "4";
                rlHappy.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_review));
                rlAngry.setBackgroundResource(0);
                rlSad.setBackgroundResource(0);
                rlNeutral.setBackgroundResource(0);
                rlLove.setBackgroundResource(0);
            }
        });
        rlLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "5";
                rlLove.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_review));
                rlAngry.setBackgroundResource(0);
                rlSad.setBackgroundResource(0);
                rlNeutral.setBackgroundResource(0);
                rlHappy.setBackgroundResource(0);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewViewModel.setBookingInternalId(bookingInternalId);
                reviewViewModel.setRating(rating);
                reviewViewModel.setReviews(etReview.getText().toString());
                reviewViewModel.sendReview();
            }
        });

        ivBackReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setDefaults() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            bookingInternalId = bundle.getString("BookingInternaId");
        }
        tvAngry.setText(getEmojiByUnicode(angryEmoji));
        tvSad.setText(getEmojiByUnicode(sadEmoji));
        tvNeutral.setText(getEmojiByUnicode(neutralEmoji));
        tvHappy.setText(getEmojiByUnicode(happyEmoji));
        tvLove.setText(getEmojiByUnicode(loveEmoji));
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void initialisation() {
        tvAngry = findViewById(R.id.tvAngry);
        tvSad = findViewById(R.id.tvSad);
        tvNeutral = findViewById(R.id.tvNeutral);
        tvHappy = findViewById(R.id.tvHappy);
        tvLove = findViewById(R.id.tvLove);
        btnSubmit = findViewById(R.id.btnSubmit);
        rlAngry = findViewById(R.id.rlAngry);
        rlSad = findViewById(R.id.rlSad);
        rlHappy = findViewById(R.id.rlHappy);
        rlNeutral = findViewById(R.id.rlNeutral);
        rlLove = findViewById(R.id.rlLove);
        etReview = findViewById(R.id.etReview);
        ivBackReview = findViewById(R.id.ivBackReview);
    }

    @Override
    public void getReviewStatus(ReviewResponseModel reviewResponseModel) {
        Toast.makeText(getApplicationContext(),reviewResponseModel.msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(ErrorBody errorBody, int statuscode) {

    }
}