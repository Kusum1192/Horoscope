package com.horoscopes.android.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.Model.DataItem;
import com.horoscopes.android.Model.SignUp;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements InstallReferrerStateListener {
    TextView SignInBtn, termCondition,privacyPolicy;
    String utm_source,utm_medium,utm_campaign,referrerUrl,myGid;
    private ProgressBar bar;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    //Uri photo;
    String socialImgurl;
    String SocialId;
    String email;
    private InstallReferrerClient mReferrerClient;
    private final Executor backgroundExecutor= Executors.newSingleThreadExecutor();
    private ReferrerDetails responseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getAdvertisingId();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        bar=findViewById(R.id.progressBar);
        termCondition=findViewById(R.id.term_n_conditions);
        privacyPolicy=findViewById(R.id.privacyPolicy);
        termCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder custIntent=new CustomTabsIntent.Builder();
                custIntent.setToolbarColor(ContextCompat.getColor(LoginActivity.this, R.color.purple_500));
                openCustomTab(LoginActivity.this,custIntent.build(),Uri.parse("https://mobnews.app/astroapp/terms_conditions\n"));
            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
                customIntent.setToolbarColor(ContextCompat.getColor(LoginActivity.this, R.color.purple_500));
                openCustomTab(LoginActivity.this, customIntent.build(), Uri.parse("https://mobnews.app/astroapp/privacy_policy"));
            }
        });
        mAuth = FirebaseAuth.getInstance();
        SignInBtn=findViewById(R.id.signIn_btn);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });
        createRequest();
        setUTM();
    }
    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlerSignInResult(task);
        }
    }
    private void handlerSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account =task.getResult(ApiException.class);
            if (account!=null)
                firebaseAuthWithGoogle(account);
        }catch (ApiException e){
            //e.getStatus();
            Toast.makeText(this, "Sign failed :"+e.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }
    private void firebaseAuthWithGoogle( GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            //  FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            progressDialog.dismiss();
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        registerUser(account);
    }
    private void registerUser(GoogleSignInAccount account) {
        bar.setVisibility(View.VISIBLE);
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Call<SignUp> call = ApiClient.getMyInterface().getSignUp(android_id, Build.MODEL,
                String.valueOf(android.os.Build.VERSION.SDK_INT), "google",account.getId(),account.getDisplayName(), account.getPhotoUrl().toString(),
                BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE, account.getEmail(), utm_source, utm_medium,myGid,
                utm_campaign,"","",referrerUrl);
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(@NonNull Call<SignUp> call, @NonNull Response<SignUp> response) {
                bar.setVisibility(View.INVISIBLE);
                SignUp signUp=response.body();
                if (signUp != null) {
                    if (signUp.getMessage().matches("Success")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        //save key and value from postman response
                        myEdit.putLong("user", signUp.getUserId());
                        myEdit.putString("securityToken", signUp.getSecurityToken());
                        myEdit.putString("emailId", email);
                        myEdit.putString("imageProfile", socialImgurl);
                        myEdit.apply();
                        myEdit.commit();
                        //Log.e(TAG, "onResponse: securityToken");
                        if (myEdit.commit()) {
                            getAppopen();
                        } else {
                            Toast.makeText(LoginActivity.this, "Unable to process", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<SignUp> call, @NonNull Throwable t) {
            }
        });
    }
    private void openCustomTab(Context activity, CustomTabsIntent customTabsIntent, Uri uri) {
        customTabsIntent.launchUrl(activity, uri);
    }
       public void getLogin(String socialImgurl) {
      
    }
//setUtm
   public void getAdvertisingId() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(LoginActivity.this);
                    myGid = adInfo != null ? adInfo.getId() : null;
                    Log.i("UIDMY", myGid);
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }
        });
    }
    private void setUTM() {
        mReferrerClient = InstallReferrerClient.newBuilder(this).build();
        mReferrerClient.startConnection(LoginActivity.this);
        checkInstallReferrer();
    }

    public void onInstallReferrerSetupFinished(int responseCode) {
        switch (responseCode) {
            case InstallReferrerClient.InstallReferrerResponse.OK:
                // Connection established

                try {
                    getReferralUser();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                // API not available on the current Play Store app
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                // Connection could not be established
                break;
            case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                break;
        }
    }

    public void onInstallReferrerServiceDisconnected() {

    }
    private void getReferralUser() throws RemoteException {
        ReferrerDetails response = mReferrerClient.getInstallReferrer();
        String referrerData = response.getInstallReferrer();
        Log.e("TAG", "Install referrer:" + response.getInstallReferrer());
        // for utm terms
        HashMap<String, String> values = new HashMap<>();
        //if (values.containsKey("utm_medium") && values.containsKey("utm_term")) {
        try {
            if (referrerData != null) {
                String referrers[] = referrerData.split("&");
                Log.d(TAG, "getReferralUser: " + referrerData);
                for (String referrerValue : referrers) {
                    String keyValue[] = referrerValue.split("=");
                    values.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
                }
                Log.e("TAG", "UTM medium:" + values.get("utm_medium"));
                Log.e("TAG", "UTM term:" + values.get("utm_term"));
                utm_medium = values.get("utm_medium");
                // utm_term = values.get("utm_term");
                utm_source = values.get("utm_source");
                utm_campaign = values.get("utm_campaign");

            }
        } catch (Exception e) {

        }
    }
    private void checkInstallReferrer() {
        InstallReferrerClient referrerClient = InstallReferrerClient.newBuilder(this).build();
        backgroundExecutor.execute(() -> getInstallReferrerFromClient(referrerClient));

    }
    private void getInstallReferrerFromClient(InstallReferrerClient referrerClient) {
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:

                        try {
                            responseRef = referrerClient.getInstallReferrer();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                        referrerUrl = responseRef.getInstallReferrer();
                        // Toast.makeText(LoginActivity.this, ""+responseRef.getInstallReferrer(), Toast.LENGTH_SHORT).show();
                        // TODO: If you're using GTM, call trackInstallReferrerforGTM instead.
                        // trackInstallReferrer(referrerUrl);
                        // Only check this once.
                        // getPreferences(MODE_PRIVATE).edit().putBoolean(prefInstallReferrer, true).commit();
                        // End the connection
                        referrerClient.endConnection();

                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {

            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void getAppopen() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<AppOpen> call= ApiClient.getMyInterface().getAppOpen(userId,securityToken,
                BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE);
        call.enqueue(new Callback<AppOpen>() {
            @Override
            public void onResponse(Call<AppOpen> call, Response<AppOpen> response) {
                if (response.isSuccessful()) {
                    AppOpen appOpen = response.body();
                    if (appOpen != null) {
                        AppOpen.CustomAd caData = appOpen.getCustomAd();
                        // caData.getActionUrl();
                        // caData.getImageUrl();
                        if (appOpen.getMessage().matches("Success")) {
                            SharedPreferences sharedPreferences1 = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putString("invite",appOpen.getAppUrl());
                            editor.putString("bannerImage", caData.getImageUrl());
                            editor.putString("bannerLink", caData.getActionUrl());
                            editor.apply();
                            editor.commit();
                        }
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
            @Override
            public void onFailure(Call<AppOpen> call, Throwable t) {
            }
        });
    }
}