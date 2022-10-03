package com.fmsfrontend.ltctours.activityClass;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.prefrence.SharedPrefManager;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
private LinearLayout buttonGetOTP;
private EditText etMobile;
private ProgressBar progressBar;
String inputNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonGetOTP=findViewById(R.id.btnLogin);
        etMobile=findViewById(R.id.etMobile);
        progressBar=findViewById(R.id.progressBar);
        etMobile.addTextChangedListener(loginTextWatcher);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNumber = etMobile.getText().toString().trim();
                SendOTPMobile();

            }
        });
      if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }

    private TextWatcher loginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (etMobile.getText().toString().length() == 10) {
                buttonGetOTP.setEnabled(true);
            }else {
                buttonGetOTP.setEnabled(false);

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private void SendOTPMobile() {
        progressBar.setVisibility(View.VISIBLE);
        buttonGetOTP.setVisibility(View.INVISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + etMobile.getText().toString(),
                60,
                TimeUnit.SECONDS,
                LoginActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(LoginActivity.this, VerifyOTPActivity.class);
                        intent.putExtra("mobile", inputNumber);
                        intent.putExtra("verificationId", verificationId);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
        );

    }
}