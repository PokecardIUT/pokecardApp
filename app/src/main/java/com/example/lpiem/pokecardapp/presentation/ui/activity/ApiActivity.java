package com.example.lpiem.pokecardapp.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lpiem.pokecardapp.R;
import com.example.lpiem.pokecardapp.data.manager.api.GithubServiceImpl;

public class ApiActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int SIGN_IN_FB = 9002;
    private static final int SIGN_IN_GOOGLE = 9003;
    private static final int SIGN_OUT = 8000;
    TextView tvCard;
    TextView tvName;
    TextView tvEmail;
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_get_card);

        tvCard = findViewById(R.id.tvCard);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        findViewById(R.id.btSignOut).setOnClickListener(this);
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        if(!name.isEmpty() && !email.isEmpty()) {
            tvName.setText(name+",");
            tvEmail.setText(email);
        }
        if(isOnline()) {
            Log.d("commMgr", "Network connected");
            new GithubServiceImpl().getGithubUsername("ahmedrizwan")
                    .subscribe(userInfo -> tvCard.setText(userInfo));
        }
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSignOut:
                startActivityForResult(new Intent(this, LoginActivity.class), SIGN_OUT);;
        }
    }
}
