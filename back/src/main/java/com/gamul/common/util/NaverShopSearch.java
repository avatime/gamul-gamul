package com.gamul.common.util;

import com.gamul.api.response.OnlineIngredientInfoRes;
import com.gamul.api.response.OnlineMartInfoRes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Naver-Client-Id", "2BbWN2sPNh5NaRrJn_Ct");
        headers.add("X-Naver-Client-Secret", "TToFxEndXi");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public List<OnlineIngredientInfoRes> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items  = rjson.getJSONArray("items");
        List<OnlineIngredientInfoRes> ret = new ArrayList<>();
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            OnlineIngredientInfoRes onlineIngredientInfoRes = new OnlineIngredientInfoRes(itemJson);
            ret.add(onlineIngredientInfoRes);
        }
        return ret;
    }

    public List<OnlineMartInfoRes> OnlineMartInfo(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items  = rjson.getJSONArray("items");
        List<OnlineMartInfoRes> ret = new ArrayList<>();
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            OnlineMartInfoRes onlineMartInfoRes = new OnlineMartInfoRes(itemJson);
            ret.add(onlineMartInfoRes);
        }
        return ret;
    }

}
