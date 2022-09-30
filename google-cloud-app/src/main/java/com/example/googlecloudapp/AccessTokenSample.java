package com.example.googlecloudapp;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
@Configuration
public class AccessTokenSample {

    public void getAccessToken() throws IOException {
        // デフォルトのGoogle Cloud認証情報を使ってアクセストークンを取得
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
            .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
        System.out.println(String.format("credentials info: %s", credentials.toString()));
        credentials.refreshIfExpired();
        AccessToken token = credentials.getAccessToken();
        System.out.println(String.format("token : %s", token.getTokenValue()));
    }
}
