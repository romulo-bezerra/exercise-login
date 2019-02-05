package br.edu.ifpb.login.services;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    //Retorna true em caso de sucesso ou false do contrário
    Boolean post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }

    //Monta o body da requisição
    String bowlingJson(String email, String senha) {
        return "{"
                + "'email':'" + email + "',"
                + "'password':'" + senha + "'"
                + "}";
    }

}
