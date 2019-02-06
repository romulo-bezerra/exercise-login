package br.edu.ifpb.login.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

public class PostService extends IntentService {

    private String response;

    public PostService() {
        super("Post Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String planEmail = intent.getStringExtra("email");
        String planPassword = intent.getStringExtra("password");

        HttpClient httpClient = new HttpClient();
        response = httpClient.post(planEmail, planPassword);
        intent.putExtra("responseRequestPost", response);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(intent);
    }
}