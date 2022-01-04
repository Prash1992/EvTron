package com.ev.evtron.activities.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ev.evtron.R;
import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;
import com.ev.evtron.interfaces.viewresponseinterface.LoginViewResponseInterface;
import com.ev.evtron.interfaces.viewresponseinterface.OtpVerificationViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GenerateOtpResponseModel;
import com.ev.evtron.pojo.jsonresponse.OtpVerificationResponseModel;
import com.ev.evtron.utils.PreferenceManager;
import com.ev.evtron.viewmodel.LoginActivityViewModel;
import com.ev.evtron.viewmodel.OtpVerificationActivityViewModel;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import static com.facebook.FacebookSdk.getApplicationContext;

public class BottomSheetDialog extends BottomSheetDialogFragment implements LoginViewResponseInterface, OtpVerificationViewResponseInterface {

    private TextView tvtermsOfuse;
    private TextView tvtermsOfuseOtp;
    private ImageView ivMail;
    private ImageView ivPhone;
    private CountryCodePicker ccp;
    private View v;
    private String countrycode = "+91";
    private EditText etMobileNumber;
    private EditText etMailId;
    private String mobilenumber = "";
    private String emailid = "";
    private Button btnContinue;
    private LoginActivityViewModel loginActivityViewModel;
    private OtpVerificationActivityViewModel otpVerificationActivityViewModel;
    int flag = 1;
    private ConstraintLayout cllogin;
    private ConstraintLayout clOtp;
    private TextView tvTypeOtp;
    private TextView tvBack;
    private EditText etOne;
    private EditText etTwo;
    private EditText etThree;
    private EditText etFour;
    private String OtpOne;
    private String OtpTwo;
    private String OtpThree;
    private String OtpFour;
    private String OtpPassword;
    private Button btnLogin;
    private Button buttonVerifyFade;
    private PreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL,R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);
        inititialisation();
        loginActivityViewModel = new LoginActivityViewModel(getApplicationContext(), this);
        otpVerificationActivityViewModel = new OtpVerificationActivityViewModel(getApplicationContext(), this);
        setdefaults();
        setlisteners();
        return v;
    }

    private void setlisteners() {
        tvtermsOfuseOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });
        tvtermsOfuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });

        buttonVerifyFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtpPassword = OtpOne+OtpTwo+OtpThree+OtpFour;
                otpVerificationActivityViewModel.setFlag(flag);
                otpVerificationActivityViewModel.setCode(countrycode);
                otpVerificationActivityViewModel.setMobile(mobilenumber);
                otpVerificationActivityViewModel.setEmail(emailid);
                otpVerificationActivityViewModel.setOtp(OtpPassword);
                otpVerificationActivityViewModel.validateOtp();

            }
        });


        etOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etOne.setSelection(etOne.getText().length());
                if (s.length() >= 2) {
                    Log.i("substringvalue", s.toString().substring(s.length() - 1, s.length()));
                    etOne.setText(s.toString().substring(s.length() - 1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    etTwo.requestFocus();
                }
                OtpOne = etOne.getText().toString().trim();
                Log.i("one", OtpOne);
                //otpValidation();
            }
        });

        etTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etTwo.setSelection(etTwo.getText().length());
                if (s.length() >= 2) {
                    Log.i("substringvalue", s.toString().substring(s.length() - 1, s.length()));
                    etTwo.setText(s.toString().substring(s.length() - 1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    etThree.requestFocus();
                } else if (s.length() == 0) {
                    etTwo.requestFocus();
                }
                OtpTwo = etTwo.getText().toString().trim();
                Log.i("two", OtpTwo);
               // otpValidation();
            }
        });

        etThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("etthreebeforetext", "etthreebeforetextchanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("etthreeontextchanged", "etthreeontextchanged");
                etThree.setSelection(etThree.getText().length());
                if (s.length() >= 2) {
                    Log.i("substringvalue", s.toString().substring(s.length() - 1, s.length()));
                    etThree.setText(s.toString().substring(s.length() - 1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    etFour.requestFocus();
                } else if (s.length() == 0) {
                    etThree.requestFocus();
                }
                OtpThree = etThree.getText().toString().trim();
                Log.i("three", OtpThree);
               // otpValidation();
            }
        });

        etFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etFour.setSelection(etFour.getText().length());
                if (s.length() >= 2) {
                    Log.i("substringvalue", s.toString().substring(s.length() - 1, s.length()));
                    etFour.setText(s.toString().substring(s.length() - 1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    closeKeyboard();
                } else if (s.length() == 0) {
                    etFour.requestFocus();
                }
                OtpFour = etFour.getText().toString().trim();
                Log.i("four", OtpFour);
               // otpValidation();
            }
        });

        etOne.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    etOne.getText().clear();
                    etOne.requestFocus();
                    return true;
                }
                return false;
            }
        });

        etTwo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etTwo.getText().toString().length() == 1) {
                        etTwo.requestFocus();
                        etTwo.getText().clear();
                    } else if (etTwo.getText().toString().length() == 0) {
                        etOne.getText().clear();
                        etOne.requestFocus();
                    }
                    return true;
                }
                return false;
            }
        });

        etThree.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etThree.getText().toString().length() == 1) {
                        etThree.requestFocus();
                        etThree.getText().clear();
                    } else if (etThree.getText().toString().length() == 0) {
                        etTwo.getText().clear();
                        etTwo.requestFocus();
                    }
                    return true;
                }
                return false;
            }
        });

        etFour.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (etFour.getText().toString().length() == 1) {
                        etFour.getText().clear();
                        etFour.requestFocus();
                    } else if (etFour.getText().toString().length() == 0) {
                        etThree.getText().clear();
                        etThree.requestFocus();

                    }
                    return true;
                }
                return false;
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityViewModel.setFlag(flag);
                loginActivityViewModel.setCode(countrycode);
                loginActivityViewModel.setMobile(mobilenumber);
                loginActivityViewModel.setEmail(emailid);
                loginActivityViewModel.generateotprequest();
            }
        });
        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivMail.setVisibility(View.GONE);
                ivPhone.setVisibility(View.VISIBLE);
                ccp.setVisibility(View.GONE);
                etMobileNumber.setVisibility(View.GONE);
                etMailId.setVisibility(View.VISIBLE);
                etMobileNumber.getText().clear();
                flag = 0;
            }
        });
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPhone.setVisibility(View.GONE);
                ivMail.setVisibility(View.VISIBLE);
                etMailId.setVisibility(View.GONE);
                ccp.setVisibility(View.VISIBLE);
                etMobileNumber.setVisibility(View.VISIBLE);
                etMailId.getText().clear();
                flag = 1;
            }
        });

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countrycode = ccp.getSelectedCountryCodeWithPlus();
                Toast.makeText(getApplicationContext(), countrycode, Toast.LENGTH_SHORT).show();
            }
        });

        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mobilenumber = etMobileNumber.getText().toString();
               // checkmobileenablebutton();
            }
        });
        etMailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailid = etMailId.getText().toString();
               // checkemailenablebutton();
            }
        });

//        tvBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cllogin.setVisibility(View.VISIBLE);
//                clOtp.setVisibility(View.GONE);
//            }
//        });
    }

//    private void checkmobileenablebutton() {
//        if (mobilenumber != null) {
//            if (mobilenumber.length() == 10) {
//                btnContinue.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_medium_button));
//            } else {
//                btnContinue.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_grey_button));
//            }
//        }
//    }

//    private void checkemailenablebutton() {
//        if (emailid != null) {
//            if (emailid.length() > 0) {
//                btnContinue.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_medium_button));
//            } else {
//                btnContinue.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_grey_button));
//            }
//        }
//    }

    private void setdefaults() {
BottomNavigationActivity.ontochedscreen = true;
    }

    private void inititialisation() {
        tvtermsOfuse = v.findViewById(R.id.tvtermsOfuse);
        tvtermsOfuseOtp = v.findViewById(R.id.tvtermsOfuseOtp);
        ivMail = v.findViewById(R.id.ivMail);
        ivPhone = v.findViewById(R.id.ivPhone);
        ccp = v.findViewById(R.id.ccp);
        etMobileNumber = v.findViewById(R.id.etMobileNumber);
        etMailId = v.findViewById(R.id.etMailId);
        btnContinue = v.findViewById(R.id.btnContinue);
        cllogin = v.findViewById(R.id.cllogin);
        clOtp = v.findViewById(R.id.clOtp);
        etOne = v.findViewById(R.id.etOtpOne);
        etTwo = v.findViewById(R.id.etOtpTwo);
        etThree = v.findViewById(R.id.etOtpThree);
        etFour = v.findViewById(R.id.etOtpFour);
       // btnLogin = v.findViewById(R.id.btnLogin);
        buttonVerifyFade = v.findViewById(R.id.buttonVerifyFade);
        tvTypeOtp = v.findViewById(R.id.tvTypeOtp);
        //tvBack = v.findViewById(R.id.tvBack);
    }

    @Override
    public void generateOtpProcessed(GenerateOtpResponseModel generateOtpResponseModel) {
        if (generateOtpResponseModel.getCode().equals("200") || generateOtpResponseModel.getCode().equals("300")) {
            Toast.makeText(getApplicationContext(), generateOtpResponseModel.getOtp().toString(), Toast.LENGTH_SHORT).show();
            movetoverificationscreen();
        }

    }

    @Override
    public void OtpVerificationProcessed(OtpVerificationResponseModel otpVerificationResponseModel) {
        if (otpVerificationResponseModel.getCode().equals("300")){
            Toast.makeText(getApplicationContext(),otpVerificationResponseModel.getMsg(),Toast.LENGTH_SHORT).show();
        }else {
            getPreferenceManager().setPrefIsLoggedIn(true);
            getPreferenceManager().setPrefCustomerId(otpVerificationResponseModel.getId());
            getPreferenceManager().setPrefUserFlag(otpVerificationResponseModel.getUserFlag());
            getPreferenceManager().setPrefJwtToken(otpVerificationResponseModel.getJwtToken());
            Intent intent = new Intent("user_loggedin_successfully");
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        }
    }

    @Override
    public void onFailure(ErrorBody errorBody, int statuscode) {
        Toast.makeText(getApplicationContext(), errorBody.ErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void movetoverificationscreen() {
        cllogin.setVisibility(View.GONE);
        clOtp.setVisibility(View.VISIBLE);
        if (flag == 1) {
            tvTypeOtp.setText(getString(R.string.type_otp) + "\n" + mobilenumber);
        } else if (flag == 0) {
            tvTypeOtp.setText(getString(R.string.type_otp) + "\n" + emailid);
        }

//        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
//        Intent intent = new Intent(getApplicationContext(), OtpVerficationActivity.class);
//        intent.putExtra("flag", loginActivityViewModel.getFlag());
//        intent.putExtra("code", loginActivityViewModel.getCode());
//        intent.putExtra("mobile", loginActivityViewModel.getMobile());
//        intent.putExtra("email", loginActivityViewModel.getEmail());
//        startActivity(intent, options.toBundle());
    }

    /**
     * @brief Method to check whether the user entered the otp to show the login button
     */
//    private void otpValidation() {
//        OtpPassword = OtpOne + OtpTwo + OtpThree + OtpFour;
//        if (OtpPassword.length() != 4) {
//            buttonVerifyFade.setVisibility(View.VISIBLE);
//            btnLogin.setVisibility(View.GONE);
//        } else {
//            buttonVerifyFade.setVisibility(View.GONE);
//            btnLogin.setVisibility(View.VISIBLE);
//        }
//    }

    /**
     * @brief Method to close the keyboard
     */
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

    @Override
    public void onDestroy() {
        BottomNavigationActivity.ontochedscreen = false;
        super.onDestroy();
    }
}
