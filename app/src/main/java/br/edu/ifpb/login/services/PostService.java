package br.edu.ifpb.login.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

public class PostService extends IntentService {

    public PostService() {
        super("PostService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String planEmail = intent.getStringExtra("email");
        String planPassword = intent.getStringExtra("password");
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);

        try {
            HttpClient httpClient = new HttpClient();
            int codeResponse = httpClient.post(planEmail, planPassword);
            intent.putExtra("responsePost", codeResponse);
            manager.sendBroadcast(intent);
        } catch (Exception e) {
            manager.sendBroadcast(intent);
        }
    }
}