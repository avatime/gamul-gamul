package com.gamul.common.util;

import com.gamul.api.response.YoutubeInfoRes;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import io.micrometer.core.instrument.search.Search;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Component
public class YoutubeChannelSearch {

    private static String PROPERTIES_FILENAME = "youtube.properties";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
    private static YouTube youtube;


    public static List<ResourceId> Search(String query){
        Properties properties = new Properties();
        YoutubeInfoRes youtubeInfoRes = new YoutubeInfoRes();
        List<ResourceId> videoIdList = new ArrayList<>();

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = query;

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            String apiKey = "AIzaSyCEMVrmcsR9UBBYWrWT5jc25HXF2uLFBZA";

            search.setKey(apiKey);
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                return prettyPrint(searchResultList.iterator(), queryTerm);
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return videoIdList;
    }

    private static List<ResourceId> prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }
        List<ResourceId> videoIdList = new ArrayList<>();

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            videoIdList.add(rId);

        }
        return videoIdList;
    }


    private static YoutubeInfoRes prettyPrint(Iterator<Video> iteratorSearchResults, YoutubeInfoRes youtubeInfoRes, String query) throws Exception{

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            Video singleVideo = iteratorSearchResults.next();

            // Double checks the kind is video.
            if (singleVideo.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String videoLink = "https://www.youtube.com/watch?v=" + singleVideo.getId();

                youtubeInfoRes.setThumbnailPath(thumbnail.getUrl());
                youtubeInfoRes.setName(singleVideo.getSnippet().getTitle());
                youtubeInfoRes.setChannelName(singleVideo.getSnippet().getChannelTitle());
                Long viewCount = getViewCountFromVideo(singleVideo.getId());
                youtubeInfoRes.setView(viewCount);
                String date = singleVideo.getSnippet().getPublishedAt().toString();
                youtubeInfoRes.setDate(date);
                youtubeInfoRes.setUrl(videoLink);
                youtubeInfoRes.setDescription(singleVideo.getSnippet().getDescription());

                return youtubeInfoRes;

            }
        }
        return youtubeInfoRes;
    }


    public List<YoutubeInfoRes> get(String query){
        List<ResourceId> videoIdList = Search(query);
        List<YoutubeInfoRes> youtubeInfoResList = new ArrayList<>();
        try {
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-video-duration-get").build();
            for (ResourceId videoId : videoIdList){
                YoutubeInfoRes youtubeInfoRes = new YoutubeInfoRes();

                //내가 원하는 정보 지정할 수 있어요. 공식 API문서를 참고해주세요.
                YouTube.Videos.List videos = youtube.videos().list("id,snippet,contentDetails");
                videos.setKey("AIzaSyCEMVrmcsR9UBBYWrWT5jc25HXF2uLFBZA");
                videos.setId(videoId.getVideoId());
                videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED); //조회 최대 갯수.
                List<Video> videoList = videos.execute().getItems();

                if (videoList != null) {
                    YoutubeInfoRes youtubeInfoRes1 = prettyPrint(videoList.iterator(), youtubeInfoRes, query);
                    youtubeInfoResList.add(youtubeInfoRes1);
                }

            }

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return youtubeInfoResList;
    }

    private static long getViewCountFromVideo(String videoId) throws IOException, JSONException {
        String apikey = "AIzaSyCEMVrmcsR9UBBYWrWT5jc25HXF2uLFBZA";

        String url = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id="+videoId+"&key="+apikey;
        JSONObject json = readJsonFromUrl(url);
        JSONArray items = json.getJSONArray("items");
        String parentKey = items.getJSONObject(0).getJSONObject("statistics").getString("viewCount");
        Long viewCount = Long.parseLong(parentKey);

        return viewCount;
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseBody.append(line);
            }
            String jsonText = responseBody.toString();
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            inputStream.close();
        }
    }


}
