package com.gamul.api.controller;

import com.gamul.api.request.IngredientListReq;
import com.gamul.api.request.OfflineMartDetailInfoReq;
import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.*;
import com.gamul.api.service.IngredientService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.common.util.NaverShopSearch;
import com.gamul.db.entity.HighClass;
import com.gamul.db.entity.User;
import com.gamul.db.repository.IngredientRepository;
import com.gamul.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "식재료 관련 API", tags = {"Ingredient."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingredients")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/{orderType}/{highClassId}")
    @ApiOperation(value = "식재료 목록 반환", notes = "<strong>order type과 high clas id</strong>에 따른 식재료 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getIngredientList(@PathVariable int orderType, @PathVariable Long highClassId, @RequestBody IngredientListReq ingredientListReq) {
        System.out.println("컨트롤러: " + highClassId);
        List<IngredientInfoRes> ingredientList = ingredientService.getIngredientList(ingredientListReq);
        return new ResponseEntity<List<IngredientInfoRes>>(ingredientList, HttpStatus.OK);
    }

    @GetMapping("/bookmark/{userName}")
    @ApiOperation(value = "식재료 찜 목록 반환", notes = "<strong>user name</strong>에 따른 식재료 찜 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getSelectedIngredientList(@PathVariable String userName) {
        List<IngredientInfoRes> ingredientSelectedList = ingredientService.getIngredientSelectedList(userName);
        return new ResponseEntity<List<IngredientInfoRes>>(ingredientSelectedList, HttpStatus.OK);
    }

    @GetMapping("/{ingredientId}")
    @ApiOperation(value = "식재료 상세 정보", notes = "<strong>ingredient id</strong>에 따른 식재료 상세 정보 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getIngredientDetailInfo(@PathVariable Long ingredientId) {
        IngredientDetailRes ingredientDetailRes = ingredientService.getIngredientDetailInfo(ingredientId);

        // 온라인 마트 정보 추가
        String query = ingredientDetailRes.getIngredientInfo().getName();
        String resultString = naverShopSearch.search(query);
//        List<OnlineMartInfoRes> onlineMartInfoResList = naverShopSearch.OnlineMartInfo(resultString);
//        ingredientDetailRes.setOnlineMartInfo(onlineMartInfoResList);


        return new ResponseEntity<IngredientDetailRes>(ingredientDetailRes, HttpStatus.OK);
    }

    @GetMapping("/high-class")
    @ApiOperation(value = "식재료 대분류 목록 조회", notes = "<strong></strong>식재료 대분류 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getHighClassList() {
        try{
            List<HighClass> highClassNameResList = ingredientService.getHighClassList();
            return new ResponseEntity<List<HighClass>>(highClassNameResList, HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
    }

    @PutMapping("/bookmark/{userName}/{ingredientId}")
    @ApiOperation(value = "식재료 찜 등록 해제", notes = "<strong>username과 ingredient id</strong>에 따른 식재료 찜 등록/해제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "존재하지 않는 id"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> ingredientSelected(@PathVariable String userName, @PathVariable Long ingredientId) {
        try{
            if (userRepository.existsByUsername(userName)){
                ingredientService.ingredientSelected(userName, ingredientId);
            }else{
                return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            }
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @PutMapping("/basket/{userName}/{ingredientId}")
    @ApiOperation(value = "식재료 바구니 등록 해제", notes = "<strong>username과 ingredient id</strong>에 따른 식재료 바구니 등록/해제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "존재하지 않는 id"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> ingredientBasket(@PathVariable String userName, @PathVariable Long ingredientId) {
        try{
            if (userRepository.existsByUsername(userName)){
                ingredientService.ingredientBasket(userName, ingredientId);
            }else{
                return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            }
        }catch (Exception e){

            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/basket/{userName}")
    @ApiOperation(value = "식재료 바구니 목록 조회", notes = "<strong>username</strong>에 따른 식재료 바구니 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getIngredientBasket(@PathVariable String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);

        List<IngredientInfoRes> basketList = ingredientService.getBasketList(userName);
        return new ResponseEntity<List<IngredientInfoRes>>(basketList, HttpStatus.OK);
    }

    @GetMapping("/{ingredientId}/stores")
    @ApiOperation(value = "오프라인 마트 정보", notes = "<strong>ingredient id</strong>에 따른 마트 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getStoreList(@PathVariable Long ingredientId, @RequestBody OfflineMartInfoReq offlineMartInfoReq){
        List<OfflineMartInfoRes> storeList = ingredientService.getStoreList(offlineMartInfoReq);
        return new ResponseEntity<List<OfflineMartInfoRes>>(storeList, HttpStatus.OK);
    }

    @GetMapping("/stores/{storeId}")
    @ApiOperation(value = "오프라인 마트 상세 정보", notes = "<strong>store id</strong>에 따른 마트 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getStoreIngredientList(@PathVariable Long storeId, @RequestBody OfflineMartDetailInfoReq offlineMartDetailInfoReq) {
        List<IngredientInfoRes> storeIngredientList = ingredientService.getStoreIngredientList(offlineMartDetailInfoReq);
        return new ResponseEntity<List<IngredientInfoRes>>(storeIngredientList, HttpStatus.OK);
    }

    @GetMapping("/online/{ingredientId}")
    @ApiOperation(value = "온라인 마트 상세 정보", notes = "<strong>ingredient id</strong>에 따른 마트 상세 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getOnlineIngredient(@PathVariable Long ingredientId){
        try{
            if(!ingredientRepository.existsById(ingredientId)){
                return ResponseEntity.ok(BaseResponseBody.of(405, "존재하지 않는 식재료"));
            }
            String query = ingredientService.getOnlineIngredientInfo(ingredientId);
            String resultString = naverShopSearch.search(query);
            List<OnlineMartInfoRes> onlineMartInfoResList = naverShopSearch.fromJSONtoItems(resultString);
            return ResponseEntity.status(200).body(onlineMartInfoResList);
        } catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
    }

}
