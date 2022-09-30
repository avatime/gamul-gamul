package com.gamul.api.controller;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
import com.gamul.api.service.RecipeService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.RecipeSelected;
import com.gamul.db.repository.RecipeRepository;
import com.gamul.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/{orderType}/{page}")
    @ApiOperation(value = "요리법 목록 반환", notes = "<strong>order type과 page</strong>에 따른 요리법 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeList(@RequestBody RecipeListReq recipeListReq) {

        List<RecipeInfoRes> recipeInfoResList = recipeService.getRecipeList(recipeListReq);
        return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoResList, HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "요리법 바구니 정보 반환", notes = "<strong>username</strong>에 따른 요리법 바구니 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeBasket(@RequestBody RecipeBasketReq recipeBasketReq){
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
            @ApiResponse(code = 404, message = "존재하지 않는 user"),
            @ApiResponse(code = 405, message = "존재하지 않는 recipe"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> recipeSelected(@PathVariable String userName, @PathVariable Long recipeId){
        try{
            if (userRepository.existsByUsername(userName)){
                if (!recipeRepository.existsById(recipeId)) {
                    return ResponseEntity.ok(BaseResponseBody.of(405, "레시피 없음"));
                }
                recipeService.recipeSelected(userName, recipeId);
            }
            else{
                return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            }
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
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
            @ApiResponse(code = 404, message = "존재하지 않는 user"),
            @ApiResponse(code = 405, message = "존재하지 않는 recipe"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> addRecipeIngredientBasket(@PathVariable String userName, @PathVariable Long recipeId){
        try{
            if (!userRepository.existsByUsername(userName)){
                return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            }else if (!recipeRepository.existsById(recipeId)){
                ResponseEntity.ok(BaseResponseBody.of(405, "레시피 없음"));
            }else{
                recipeService.addRecipeIngredientBasket(userName, recipeId);
            }
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/{recipeId}")
    @ApiOperation(value = "요리법 조회수 추가", notes = "<strong>recipe id</strong>에 따른 요리법 조회수 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 405, message = "존재하지 않는 요리법"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> addRecipeViews(@PathVariable Long recipeId){
        try{
            if (!recipeRepository.existsById(recipeId)){
                return ResponseEntity.ok(BaseResponseBody.of(405, "레시피 없음"));
            }else{
                recipeService.addRecipeViews(recipeId);
            }
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }
}
