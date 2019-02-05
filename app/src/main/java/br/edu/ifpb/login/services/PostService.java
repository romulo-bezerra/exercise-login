package br.edu.ifpb.login.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

public class PostService extends IntentService {

    public PostService() {
        super("Post Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String planEmail = intent.getStringExtra("email");
        String planPassword = intent.getStringExtra("password");

        HttpClient httpClient = new HttpClient();
        String json = httpClient.bowlingJson(planEmail, planPassword);

        Boolean response = false;

        try {
            response = httpClient.post("http://ag-ifpb-sgd-server.herokuapp.com/login", json);
            intent.putExtra("responseRequestPost", response);
        } catch (IOException e) {
            Log.d("Error: ", e.getMessage());
        }

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(intent);
    }
}