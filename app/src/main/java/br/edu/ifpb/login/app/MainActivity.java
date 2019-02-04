package br.edu.ifpb.login.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifpb.login.R;
import br.edu.ifpb.login.services.PostService;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiverPost = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            btnAcessar.setText("Acessar");
            btnAcessar.setEnabled(true);

            Boolean responseRequestPost = intent.getBooleanExtra("responseRequestPost", false);

            if (responseRequestPost){
                Toast.makeText(getBaseContext(), "SUCCESS",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "FAILED",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Button btnAcessar;
    private EditText etEmail, etPassword;
    private String planEmail, planPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(broadcastReceiverPost, new IntentFilter("INTENT_REQUEST_POST"));
    }

    public void onClickStart(View view) {
        btnAcessar = findViewById(R.id.btnAcessar);
        btnAcessar.setEnabled(false);
        btnAcessar.setText("Aguarde");

        etEmail = findViewById(R.id.etEmail);
        planEmail = etEmail.getText().toString();
        etPassword = findViewById(R.id.etPassword);
        planPassword = etPassword.getText().toString();

        Intent intent = new Intent(this, PostService.class);
        intent.putExtra("email", planEmail);
        intent.putExtra("password", planPassword);
        intent.setAction("INTENT_REQUEST_POST");

        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.unregisterReceiver(broadcastReceiverPost);
    }
}