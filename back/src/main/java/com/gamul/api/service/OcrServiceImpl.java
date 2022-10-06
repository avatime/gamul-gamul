package com.gamul.api.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service("OcrService")
@RequiredArgsConstructor
public class OcrServiceImpl implements OcrService {

    @Override
    public String getItemsFromRecipe(String imageURL) throws Exception {
        String apiURL = "https://mla3zi5nl9.apigw.ntruss.com/custom/v1/18368/dbf09181e8ae362113c5dff9cb64c36d970a25de4fecdfbdf4ef2d37ef536f3c/general";
        String secretKey = "b0l4bEZKYXJ2YWZmT2hrd3FveEh2eWVHdU1ORW1TRUE=";

        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        con.setRequestProperty("X-OCR-SECRET", secretKey);

        JSONObject json = new JSONObject();
        json.put("version", "V2");
        json.put("requestId", UUID.randomUUID().toString());
        json.put("timestamp", System.currentTimeMillis());
        JSONObject image = new JSONObject();
        image.put("format", "jpg");
        image.put("data", imageURL.split(",")[1]); // image should be public, otherwise, should use data

        image.put("name", "demo");
        JSONArray images = new JSONArray();
        images.put(image);
        json.put("images", images);
        String postParams = json.toString();

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        return response.toString();
    }

}
