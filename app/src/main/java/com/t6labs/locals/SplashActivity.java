package com.t6labs.locals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();
    private static final String TERMS_AND_CONDITIONS_KEY = "terms_and_conditions";

    // TODO: 2019-09-13 default firebase cache is 12* hours, use no cache for debug build
    //private static final int CACHE = 3600;

    Button continueButton;
    FirebaseRemoteConfig firebaseRemoteConfig;

    private TextView tandc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tandc = findViewById(R.id.tv_splash_terms_conditions);

        initDefaultValues();

        continueButton = findViewById(R.id.btn_splash_continue);

        continueButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

    }

    void initDefaultValues() {
        setupRemoteConfig();
    }

    //get app config values
    private void setupRemoteConfig() {

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                //.setMinimumFetchIntervalInSeconds(CACHE)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        fetchTermsAndConditions();

    }

    private void fetchTermsAndConditions() {
        firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d(TAG, "onComplete: success remote config");
                    } else {
                        Log.d(TAG, "onComplete: fail");
                    }
                    displayTermsAndConditions();
                });
    }

    private void displayTermsAndConditions() {
        tandc.setText(firebaseRemoteConfig.getString(TERMS_AND_CONDITIONS_KEY));
    }
}
