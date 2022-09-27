package com.gamul.api.controller;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
import com.gamul.api.service.RecipeService;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@Api(value = "레시피 API", tags = {"Recipe."})
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/{orderType}/{page}")
    @ApiOperation(value = "요리법 목록 반환", notes = "<strong>order type과 page</strong>에 따른 요리법 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeList(@RequestBody @PathVariable int orderType, int page, RecipeListReq recipeListReq) {

        List<RecipeInfoRes> recipeInfoResList = recipeService.getRecipeList(orderType, page, recipeListReq);
        return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoResList, HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "요리법 바구니 정보 반환", notes = "<strong>username</strong>에 따른 요리법 바구니 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeBasket(@RequestBody @PathVariable String userName, RecipeBasketReq recipeBasketReq){
        List<RecipeInfoRes> recipeInfoResList = recipeService.getRecipeBasket(recipeBasketReq);

        return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoResList, HttpStatus.OK);
    }

    @GetMapping("/bookmark/{userName}")
    @ApiOperation(value = "요리법 찜 목록 조회", notes = "<strong>username</strong>에 따른 요리법 찜 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeSelected(@PathVariable String userName){
        List<RecipeInfoRes> recipeInfoRes = recipeService.getRecipeSelected(userName);
        return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoRes, HttpStatus.OK);
    }
//    @GetMapping("/{recipeId}")
//    @ApiOperation(value = "인기 요리법 유튜브", notes = "<strong>recipe id</strong>에 따른 인기 요리법 반환")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
//            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//    })
//    public ResponseEntity<?> getRecipeYoutube(@PathVariable Long recipeId){
//
//    }

//    @GetMapping("/{recipeId}")
//    @ApiOperation(value = "요리법 상세 조회", notes = "<strong>recipe id</strong>에 따른 요리법 상세 정보 반환")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
//            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//    })
//    public ResponseEntity<?> getRecipeDetail(@RequestBody @PathVariable Long recipeId, RecipeDetailReq recipeDetailReq){
//
//    }

    @PutMapping("/bookmark/{userName}/{recipeId}")
    @ApiOperation(value = "요리법 찜 등록 해제", notes = "<strong>username과 recipe id</strong>에 따른 요리법 찜 등록 해제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public void recipeSelected(@PathVariable String userName, Long recipeId){
        recipeService.recipeSelected(userName, recipeId);
    }

    @GetMapping("/{recipeId}/order")
    @ApiOperation(value = "요리법 순서 조회", notes = "<strong>recipe id</strong>에 따른 요리법 순서 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeOrder(@PathVariable Long recipeId){
        List<RecipeProcedureRes> recipeProcedureResList = recipeService.getRecipeOrder(recipeId);
        return new ResponseEntity<List<RecipeProcedureRes>>(recipeProcedureResList, HttpStatus.OK);
    }

    @PutMapping("/{userName}/{recipeId}")
    @ApiOperation(value = "요리법 바구니 수정", notes = "<strong>username과 recipe id</strong>에 따른 요리법 바구니에 재료 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public void addRecipeIngredientBasket(@PathVariable String userName, Long recipeId){
        recipeService.addRecipeIngredientBasket(userName, recipeId);
    }

    @PostMapping("/{recipeId}")
    @ApiOperation(value = "요리법 조회수 추가", notes = "<strong>recipe id</strong>에 따른 요리법 조회수 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public void addRecipeViews(@PathVariable Long recipeId){

    }
}