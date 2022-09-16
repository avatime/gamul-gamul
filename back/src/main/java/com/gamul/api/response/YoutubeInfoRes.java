package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("YoutubeInfoRes")
public class YoutubeInfoRes {
    @JsonProperty("thumbnail_path")
    String thumbnailPath;

    // 동영상 이름
    String name;

    @JsonProperty("channel_name")
    String channelName;

    int view;

    // ex) 1년 전, 3일 전
    String date;

    String url;
}
