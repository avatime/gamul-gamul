package com.gamul.api.controller;

import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/v1/user")
public class AlarmController {
    @PostMapping("/allergy")
    @ApiOperation(value = "알러지 등록/해제", notes = "식재료별 <strong>알러지</strong>를 등록 혹은 해제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> allergyAlarm(){
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/notice")
    @ApiOperation(value = "상하한가 알림 등록/해제", notes = "식재료별 <strong>상하한가</strong>를 등록 혹은 해제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> noticeAlarm(){
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }
}
