package com.gamul.api.controller;

import com.gamul.api.request.IngredientQuantityPostReq;
import com.gamul.api.request.MyRecipeEditReq;
import com.gamul.api.request.MyRecipeRegisterPostReq;
import com.gamul.api.response.*;
import com.gamul.api.service.DailyPriceService;
import com.gamul.api.service.MyRecipeService;
import com.gamul.api.service.UserService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.Day;
import com.gamul.db.entity.MyRecipe;
import com.gamul.db.entity.MyRecipeIngredient;
import com.gamul.db.entity.User;
import com.gamul.db.repository.*;
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

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientSelectedRepository ingredientSelectedRepository;
    @Autowired
    AllergyRepository allergyRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    HighClassRepository highClassRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    DailyPriceService dailyPriceService;

    @PostMapping("")
    @ApiOperation(value = "나만의 레시피 저장", notes = "<strong>나만의 레시피</strong>를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> RegistMyRecipe(@RequestBody @ApiParam(value="마이레시피 정보", required = true) MyRecipeRegisterPostReq myRecipeRegisterPostReq){
        try{
            User user = userService.getUserByUsername(myRecipeRegisterPostReq.getUserName());
            if(user == null)  return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            MyRecipe myRecipe = MyRecipe.builder().name(myRecipeRegisterPostReq.getMyRecipeName())
                    .user(user).build();
            if(!myRecipeRegisterPostReq.getImageDataUrl().equals("")) myRecipe = myRecipeService.saveMyRecipe(myRecipe, myRecipeRegisterPostReq.getImageDataUrl());
            else myRecipe = myRecipeService.saveMyRecipe(myRecipe);

            List<MyRecipeIngredient> list = new ArrayList<>();
            for(IngredientQuantityPostReq ingredient : myRecipeRegisterPostReq.getIngredientList()){
                list.add(MyRecipeIngredient.builder().myRecipe(myRecipe).quantity(ingredient.getQuantity()).ingredient(ingredientRepository.getById(ingredient.getIngredientId())).build());
            }
            myRecipeService.saveMyRecipeIngredient(list);
        } catch (Exception e) {
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }

        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @PutMapping("")
    @ApiOperation(value = "나만의 레시피 수정", notes = "<strong>나만의 레시피</strong>를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 405, message = "레시피 정보 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> editMyRecipe(@RequestBody @ApiParam(value="마이레시피 정보", required = true) MyRecipeEditReq myRecipeEditReq){
        try{
            User user = userService.getUserByUsername(myRecipeEditReq.getUserName());
            if(user == null)  return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 정보 없음"));
            MyRecipe myRecipe = myRecipeService.getMyRecipe(myRecipeEditReq.getMyRecipeId());
            if(myRecipe == null) return ResponseEntity.ok(BaseResponseBody.of(405, "레시피 정보 없음"));
            myRecipe.setName(myRecipeEditReq.getMyRecipeName());
            if(!myRecipeEditReq.getImageDataUrl().equals("")) myRecipe = myRecipeService.saveMyRecipe(myRecipe, myRecipeEditReq.getImageDataUrl());
            else myRecipe = myRecipeService.saveMyRecipe(myRecipe);

            myRecipeService.deleteMyRecipeIngredients(myRecipeEditReq.getMyRecipeId());
            List<MyRecipeIngredient> list = new ArrayList<>();
            for(IngredientQuantityPostReq ingredient : myRecipeEditReq.getIngredientList()){
                list.add(MyRecipeIngredient.builder().myRecipe(myRecipe).quantity(ingredient.getQuantity()).ingredient(ingredientRepository.getById(ingredient.getIngredientId())).build());
            }
            myRecipeService.saveMyRecipeIngredient(list);
        } catch (Exception e) {
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }

        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
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

    @GetMapping("/{userName}/{myRecipeId}")
    @ApiOperation(value = "나만의 요리법 상세 조회", notes = "<strong>나만의 레시피</strong>를 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> showMyrecipeInfo(@PathVariable String userName, Long myRecipeId){
        MyRecipeDetailRes myRecipeDetailRes = new MyRecipeDetailRes();
        MyRecipe myRecipe = myRecipeService.getMyRecipe(myRecipeId);
        List<MyRecipeIngredient> myRecipeIngredientList = myRecipeService.getMyRecipeIngredientList(myRecipeId);
        List<MyRecipeIngredientInfoRes> ingreidentlist = new ArrayList<>();
        List<PriceTransitionInfoRes> pricelist = getPriceTransition(myRecipeId);
        PriceTransitionInfoRes priceTransitionInfoRes = new PriceTransitionInfoRes();
        ArrayList<PriceInfoRes> dayWholePrice =new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> yearWholePrice =new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> monthWholePrice =new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> dayRetailPrice =new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> yearRetailPrice =new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> monthRetailPrice =new ArrayList<PriceInfoRes>(10);


        int idx = 0;

        try{
            for(MyRecipeIngredient myRecipeIngredient : myRecipeIngredientList){
                List<Day> dailyPrice = dailyPriceService.findDailyPrices(myRecipeIngredient.getIngredient().getId(), 1);
                priceTransitionInfoRes.getRetailsales().getDaily().set(idx, new PriceInfoRes(dailyPrice.get(0).getDatetime(), dailyPrice.get(0).getPrice()));
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(dailyPrice.get(0).getPrice())
                        .unit(dailyPrice.get(0).getUnit())
                        .quantity(dailyPrice.get(0).getQuantity())
                        .volatility(((dailyPrice.get(0).getPrice() - dailyPrice.get(1).getPrice())/dailyPrice.get(0).getPrice()) * 100)
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();

                ingreidentlist.add(ingredientInfoRes);

            }
        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        priceTransitionInfoRes.getWholesales().setDaily(new ArrayList<PriceInfoRes>(10));
        priceTransitionInfoRes.getWholesales().setYearly(new ArrayList<PriceInfoRes>(10));
        priceTransitionInfoRes.getWholesales().setMonthly(new ArrayList<PriceInfoRes>(10));
        priceTransitionInfoRes.getRetailsales().setDaily(new ArrayList<PriceInfoRes>(10));
        priceTransitionInfoRes.getRetailsales().setYearly(new ArrayList<PriceInfoRes>(10));
        priceTransitionInfoRes.getRetailsales().setMonthly(new ArrayList<PriceInfoRes>(10));

        myRecipeDetailRes.setIngredientList(ingreidentlist);
        myRecipeDetailRes.setTotalPrice(priceTransitionInfoRes.getWholesales().getDaily().get(0).getPrice());
        myRecipeDetailRes.setPriceTransitionInfo(priceTransitionInfoRes);
        myRecipeDetailRes.setImagePath(myRecipe.getImageURL());
        myRecipeDetailRes.setName(myRecipe.getName());


        return ResponseEntity.status(200).body(myRecipeDetailRes);
    }

    public List<PriceTransitionInfoRes> getPriceTransition(Long myRecipeId){
        List<PriceTransitionInfoRes> list = new ArrayList<>();

        return list;
    }

    @GetMapping("/ingredient/{userName}/{myRecipeId}")
    @ApiOperation(value = "나만의 요리법 식재료 조회", notes = "<strong>나만의 레시피</strong>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> showMyrecipeIngredientList(@PathVariable String userName, Long myRecipeId){
        List<MyRecipeIngredient> MyRecipeIngredientList = myRecipeService.getMyRecipeIngredientList(myRecipeId);
        List<MyRecipeIngredientRes> list = new ArrayList<>();
        for(MyRecipeIngredient myRecipeIngredient : MyRecipeIngredientList){
            list.add(MyRecipeIngredientRes.builder().ingredientId(myRecipeIngredient.getIngredient().getId()).quantity(myRecipeIngredient.getQuantity()).build());
        }
        return ResponseEntity.status(200).body(list);
    }

    @DeleteMapping("/{userName}/{myRecipeId}")
    @ApiOperation(value = "나만의 레시피 삭제", notes = "<strong>myRecipeId</strong>를 삭제 시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 완료"),
            @ApiResponse(code = 404, message = "존재하지 않는 id"),
            @ApiResponse(code = 405, message = "해당 유저의 레시피가 아닙니다"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> deleteMyRecipe(@PathVariable String userName, Long myRecipeId){
        try{
            if(myRecipeService.getRecipeOwner(myRecipeId).equals(userName))
                myRecipeService.deleteMyRecipe(myRecipeId);
            else return ResponseEntity.status(405).body("해당 유저의 레시피가 아닙니다");
        } catch (Exception e){
            return ResponseEntity.status(404).body("존재하지 않는 id");
        }
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestPart MultipartFile file){
        return ResponseEntity.status(200).body(file.getName());
    }

}
