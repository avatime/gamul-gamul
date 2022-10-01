package com.gamul.common.util;

import com.gamul.api.response.YoutubeInfoRes;

import java.util.List;

public interface YoutubeProvider {
    List<YoutubeInfoRes> get(String query);
}
