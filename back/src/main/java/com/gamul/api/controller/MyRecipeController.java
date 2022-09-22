package com.gamul.api.controller;

import com.gamul.api.request.MyRecipeRegisterPostReq;
import com.gamul.api.service.MyRecipeService;
import com.gamul.api.service.UserService;
import com.gamul.db.entity.MyRecipe;
import com.gamul.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "마이레시피 API", tags = {"MyRecipe."})
@RestController
@RequestMapping("/api/v1/recipes/my")
public class MyRecipeController {
    @Autowired
    MyRecipeService myRecipeService;

    @Autowired
    UserService userService;

    @PostMapping("")
    @ApiOperation(value = "나만의 레시피 저장", notes = "<strong>나만의 레시피</strong>를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> myrecipe(@RequestBody @ApiParam(value="마이레시피 정보", required = true) MyRecipeRegisterPostReq myRecipeRegisterPostReq){
        try{
            User user = userService.getUserByUsername(myRecipeRegisterPostReq.getUserName());
            if(user == null)  return ResponseEntity.status(404).body("사용자 없음");
            MyRecipe myRecipe = MyRecipe.builder().name(myRecipeRegisterPostReq.getMyRecipeName())
                    .user(user).build();
            myRecipe = myRecipeService.saveMyRecipe(myRecipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 에러");
        }

        return ResponseEntity.status(200).body("가물가물");
    }
}
