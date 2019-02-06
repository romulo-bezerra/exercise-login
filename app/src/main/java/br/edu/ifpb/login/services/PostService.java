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
        int codResponse;
        String planEmail = intent.getStringExtra("email");
        String planPassword = intent.getStringExtra("password");

        try {
            HttpClient httpClient = new HttpClient();
            codResponse = httpClient.post(planEmail, planPassword);
            intent.putExtra("responseRequestPost", codResponse);
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
            manager.sendBroadcast(intent);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}