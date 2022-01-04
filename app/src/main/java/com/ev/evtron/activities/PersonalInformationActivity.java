package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ev.evtron.R;
import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;
import com.ev.evtron.interfaces.viewresponseinterface.PersonalInformationViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetEvDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateCustomerInfoResponseModel;
import com.ev.evtron.utils.PreferenceManager;
import com.ev.evtron.viewmodel.PersonalInformationActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class PersonalInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PersonalInformationViewResponseInterface {

    private Button btnSave;
//    private TextView tvSkip;
//    String[] carbrand = {"Select Brand", "TATA", "HYUNDAI", "BENZ", "BMW","Tata"};
    String[] carbrand = {};
//    String[] carmodel = {"Select Model", "eon", "santro", "venue", "creta","Tigor EV"};
    String[] carmodel = {};
    private Spinner spCarBrand;
    private Spinner spCarModel;
    private PersonalInformationActivityViewModel personalInformationActivityViewModel;
    private PreferenceManager preferenceManager;

    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String carmodelresponse;
    private String carManufacturerresponse;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etMobile;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_personal_information);
        initialisation();
        personalInformationActivityViewModel = new PersonalInformationActivityViewModel(getApplicationContext(), this);
        setdefaults();
        setlisteners();
    }

    private void setlisteners() {
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                firstname = etFirstName.getText().toString();
            }
        });

        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lastname = etLastName.getText().toString();
            }
        });

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mobile = etMobile.getText().toString();
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                email = etEmail.getText().toString();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalInformationActivityViewModel.setFirstName(firstname);
                personalInformationActivityViewModel.setLastName(lastname);
                personalInformationActivityViewModel.setCode("+91");
                personalInformationActivityViewModel.setRole("0");
//                personalInformationActivityViewModel.setId(String.valueOf(getPreferenceManager().getPrefcustomerId()));
                personalInformationActivityViewModel.setCarModel(carmodelresponse);
                personalInformationActivityViewModel.setCarManufacturer(carManufacturerresponse);
                personalInformationActivityViewModel.setEmail(email);
                personalInformationActivityViewModel.setMobile(mobile);
                personalInformationActivityViewModel.updateCustomerInfo();
//                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
//                Intent intent = new Intent(getApplicationContext(), SlotDetailsActivity.class);
//                startActivity(intent, options.toBundle());
//                finish();
            }
        });
//        tvSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }

    private void setdefaults() {
        spCarBrand.setOnItemSelectedListener(this);
        spCarModel.setOnItemSelectedListener(this);
        personalInformationActivityViewModel.getCustomerDetail();
    }

    private void initialisation() {
        btnSave = findViewById(R.id.btnSave);
//        tvSkip = findViewById(R.id.tvSkip);
        spCarBrand = findViewById(R.id.spCarBrand);
        spCarModel = findViewById(R.id.spCarModel);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(),carbrand[i] , Toast.LENGTH_LONG).show();
        carmodelresponse = carmodel[i];
        carManufacturerresponse = carbrand[i];
        Log.i("logcarmodel",carmodelresponse);
        Log.i("logcarmanufacture",carManufacturerresponse);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void getCustomerdetailProcessed(GetCustomerInfoResponseModel getCustomerInfoResponseModel) {
        firstname = getCustomerInfoResponseModel.getFirstName();
        lastname = getCustomerInfoResponseModel.getLastName();
        mobile = getCustomerInfoResponseModel.getMobile();
        email = getCustomerInfoResponseModel.getEmail();
        if (!getCustomerInfoResponseModel.carDetails.isEmpty()){
            carmodelresponse = getCustomerInfoResponseModel.carDetails.get(0).carModel;
            carManufacturerresponse = getCustomerInfoResponseModel.carDetails.get(0).carManufacturer;

        }

        if (firstname != null) {
            getPreferenceManager().setPrefUserName(firstname);
            etFirstName.setText(firstname);
        }
        if (lastname != null) {
            etLastName.setText(lastname);
        }
        if (mobile != null) {
            getPreferenceManager().setPrefUserMobileNumber(mobile);
            etMobile.setText(mobile);
        }
        if (email != null) {
            getPreferenceManager().setPrefUserMailId(email);
            etEmail.setText(email);
        }

        personalInformationActivityViewModel.evDetails();
    }

    @Override
    public void ongetCustomerdetailFailure(ErrorBody errorBody, int statuscode) {
        Toast.makeText(getApplicationContext(), errorBody.ErrorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void customerdetailupdated(UpdateCustomerInfoResponseModel updateCustomerInfoResponseModel) {
        Toast.makeText(getApplicationContext(),updateCustomerInfoResponseModel.getMsg(),Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void updateCustomerdetailFailure(ErrorBody errorBody, int statuscode) {

    }

    @Override
    public void getEvDetailsReceived(List<GetEvDetailsResponseModel> evDetailsResponseModel) {
        carbrand = new String[evDetailsResponseModel.size()];
        carmodel = new String[evDetailsResponseModel.size()];

        for (int i=0;i<evDetailsResponseModel.size();i++){
            carbrand[i] = evDetailsResponseModel.get(i).manufacturerName;
        }

        for (int j = 0;j<evDetailsResponseModel.size();j++){
            carmodel[j] = evDetailsResponseModel.get(j).modelDetails.get(0).modName;
        }
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter arraycarbrand = new ArrayAdapter(this, R.layout.spinner_textview, carbrand);
        ArrayAdapter arraycarmodel = new ArrayAdapter(this, R.layout.spinner_textview, carmodel);
        arraycarbrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arraycarmodel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spCarBrand.setAdapter(arraycarbrand);
        spCarModel.setAdapter(arraycarmodel);

        if (carmodelresponse!=null){
            for (int i=0;i<carmodel.length;i++){
                Log.i("list",carmodel[i]);
                if (carmodelresponse.equals(carmodel[i])){
                    spCarModel.setSelection(i);
                }
            }
        }

        if (carManufacturerresponse!=null){
            for (int i=0;i<carbrand.length;i++){
                Log.i("list",carbrand[i]);
                if (carManufacturerresponse.equals(carbrand[i])){
                    spCarBrand.setSelection(i);
                }
            }

        }
    }

    @Override
    public void getEvDetailsErrorReceived(ErrorBody errorBody, int statusCode) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();

    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}