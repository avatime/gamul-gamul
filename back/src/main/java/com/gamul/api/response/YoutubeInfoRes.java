package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("YoutubeInfoRes")
public class YoutubeInfoRes {
    @JsonProperty("thumbnail_path")
    String thumbnailPath;

    // 동영상 이름
    String name;

    @JsonProperty("channel_name")
    String channelName;

    BigInteger view;

    // ex) 1년 전, 3일 전
    String date;

    String url;

    public YoutubeInfoRes(String thumbnailPath, String name, String channelName, BigInteger view, String date, String url){
        this.thumbnailPath = thumbnailPath;
        this.name = name;
        this.channelName = channelName;
        this.view = view;
        this.date = date;
        this.url = url;
    }
}
