package com.gamul.api.controller;

import com.gamul.api.request.MyRecipeRegisterPostReq;
import com.gamul.api.response.MyRecipeInfoRes;
import com.gamul.api.service.MyRecipeService;
import com.gamul.api.service.UserService;
import com.gamul.db.entity.MyRecipe;
import com.gamul.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
            if(!myRecipeRegisterPostReq.getImageDataUrl().equals("")) myRecipe = myRecipeService.saveMyRecipe(myRecipe, myRecipeRegisterPostReq.getImageDataUrl());
            else myRecipe = myRecipeService.saveMyRecipe(myRecipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }

        return ResponseEntity.status(200).body("Success");
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestPart MultipartFile file){
        return ResponseEntity.status(200).body(file.getName());
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "나만의 레시피 목록 조회", notes = "<strong>나만의 레시피</strong>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> showMyrecipeList(@PathVariable String userName){
        List<MyRecipeInfoRes> myRecipeList = new ArrayList<>();
        try{
            User user = userService.getUserByUsername(userName);
            if(user == null)  return ResponseEntity.status(404).body("사용자 없음");
            List<MyRecipe> list = myRecipeService.getMyRecipeList(user.getId());
            for(MyRecipe myRecipe : list) {
                myRecipeList.add(MyRecipeInfoRes.builder()
                                .myRecipeId(myRecipe.getId())
                                .imagePath(myRecipe.getImageURL())
                                .name(myRecipe.getName())
                        .build());
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 에러");
        }

        return ResponseEntity.status(200).body(myRecipeList);
    }
}
