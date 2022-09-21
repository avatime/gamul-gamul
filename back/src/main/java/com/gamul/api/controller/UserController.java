package com.gamul.api.controller;

import com.gamul.api.request.UserRegisterPostReq;
import com.gamul.api.service.UserService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "중복된 id"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> register(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq userRegisterPostReq) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        try {
            User user = userService.createUser(userRegisterPostReq);
        }
        catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(404).body(e);
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e);
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/check/{userName}")
    @ApiOperation(value = "아이디 중복 체크", notes = "<strong>아이디</strong>의 사용 가능 여부를 판단한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 404, message = "중복된 id"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> checkId(@PathVariable String userName) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        try {
            if(userService.findUsername(userName)) return ResponseEntity.status(404).body("Duplicate username");
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("server error");
        }

        return ResponseEntity.status(200).body("success");
    }
}
