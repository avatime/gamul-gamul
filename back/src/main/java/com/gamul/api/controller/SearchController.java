package com.gamul.api.controller;

import com.gamul.api.response.SearchRes;
import com.gamul.api.service.SearchService;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "검색 API", tags = {"Search."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/{keyword}")
    @ApiOperation(value = "검색 정보 반환", notes = "<strong>keyword</strong>에 따른 식재료 및 요리법 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> search(@PathVariable String keyword){
        SearchRes searchRes = searchService.search(keyword);
        return new ResponseEntity<SearchRes>(searchRes, HttpStatus.OK);
    }
}
