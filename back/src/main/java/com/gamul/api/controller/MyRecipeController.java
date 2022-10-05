package com.gamul.api.controller;

import com.gamul.api.request.IngredientQuantityPostReq;
import com.gamul.api.request.MyRecipeEditReq;
import com.gamul.api.request.MyRecipeRegisterPostReq;
import com.gamul.api.response.*;
import com.gamul.api.service.DailyPriceService;
import com.gamul.api.service.MyRecipeService;
import com.gamul.api.service.UserService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<?> RegistMyRecipe(@RequestBody @ApiParam(value = "마이레시피 정보", required = true) MyRecipeRegisterPostReq myRecipeRegisterPostReq) {
        try {
            User user = userService.getUserByUsername(myRecipeRegisterPostReq.getUserName());
            if (user == null) return ResponseEntity.ok(BaseResponseBody.of(404, "사용자 없음"));
            MyRecipe myRecipe = MyRecipe.builder().name(myRecipeRegisterPostReq.getMyRecipeName())
                    .user(user).build();
            if (!myRecipeRegisterPostReq.getImageDataUrl().equals(""))
                myRecipe = myRecipeService.saveMyRecipe(myRecipe, myRecipeRegisterPostReq.getImageDataUrl());
            else myRecipe = myRecipeService.saveMyRecipe(myRecipe);

            List<MyRecipeIngredient> list = new ArrayList<>();
            for (IngredientQuantityPostReq ingredient : myRecipeRegisterPostReq.getIngredientList()) {
                list.add(MyRecipeIngredient.builder().myRecipe(myRecipe).quantity(ingredient.getQuantity()).ingredient(ingredientRepository.getById(ingredient.getIngredientId())).build());
            }
            myRecipeService.saveMyRecipeIngredient(list);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }

        return ResponseEntity.status(200).body("Success");
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
    public ResponseEntity<?> editMyRecipe(@RequestBody @ApiParam(value = "마이레시피 정보", required = true) MyRecipeEditReq myRecipeEditReq) {
        try {
            User user = userService.getUserByUsername(myRecipeEditReq.getUserName());
            if (user == null) return ResponseEntity.status(404).body("사용자 정보 없음");
            MyRecipe myRecipe = myRecipeService.getMyRecipe(myRecipeEditReq.getMyRecipeId());
            if (myRecipe == null)
                return ResponseEntity.status(405).body("레시피 정보 없음");
            myRecipe.setName(myRecipeEditReq.getMyRecipeName());
            if (!myRecipeEditReq.getImageDataUrl().equals(""))
                myRecipe = myRecipeService.saveMyRecipe(myRecipe, myRecipeEditReq.getImageDataUrl());
            else myRecipe = myRecipeService.saveMyRecipe(myRecipe);

            myRecipeService.deleteMyRecipeIngredients(myRecipeEditReq.getMyRecipeId());
            List<MyRecipeIngredient> list = new ArrayList<>();
            for (IngredientQuantityPostReq ingredient : myRecipeEditReq.getIngredientList()) {
                list.add(MyRecipeIngredient.builder().myRecipe(myRecipe).quantity(ingredient.getQuantity()).ingredient(ingredientRepository.getById(ingredient.getIngredientId())).build());
            }
            myRecipeService.saveMyRecipeIngredient(list);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }

        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "나만의 레시피 목록 조회", notes = "<strong>나만의 레시피</strong>목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> showMyrecipeList(@PathVariable String userName) {
        List<MyRecipeInfoRes> myRecipeList = new ArrayList<>();
        try {
            User user = userService.getUserByUsername(userName);
            if (user == null) return ResponseEntity.status(404).body("사용자 없음");
            List<MyRecipe> list = myRecipeService.getMyRecipeList(user.getId());
            for (MyRecipe myRecipe : list) {
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
    public ResponseEntity<?> showMyrecipeInfo(@PathVariable String userName, @PathVariable Long myRecipeId) throws Exception {
//        try {
        int total_price = 0;
        MyRecipe myRecipe = myRecipeService.getMyRecipe(myRecipeId);
        if (!myRecipe.getUser().getUsername().equals(userName)) return ResponseEntity.status(401).body("인증 실패");
        MyRecipeDetailRes myRecipeDetailRes = new MyRecipeDetailRes();
        List<MyRecipeIngredient> myRecipeIngredientList = myRecipeService.getMyRecipeIngredientList(myRecipeId);
        List<MyRecipeIngredientInfoRes> ingreidentlist = new ArrayList<>();
        PriceTransitionInfoRes priceTransitionInfoRes = new PriceTransitionInfoRes();
        ArrayList<PriceInfoRes> dayWholePrice = new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> monthWholePrice = new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> dayRetailPrice = new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> yearRetailPrice = new ArrayList<PriceInfoRes>(10);
        ArrayList<PriceInfoRes> monthRetailPrice = new ArrayList<PriceInfoRes>(10);

        for (int i = 0; i < 10; i++) {
            dayRetailPrice.add(new PriceInfoRes(null, 0));
            monthRetailPrice.add(new PriceInfoRes(null, 0));
            yearRetailPrice.add(new PriceInfoRes(null, 0));
            dayWholePrice.add(new PriceInfoRes(null, 0));
            monthWholePrice.add(new PriceInfoRes(null, 0));
        }

        boolean setDate = true;
        int dailySize = 10, monthlySize = 10, dailyWholeSize = 10, monthlyWholeSize = 10, yearSize = 10;

        for (MyRecipeIngredient myRecipeIngredient : myRecipeIngredientList) {
            List<Day> dailyPrice = dailyPriceService.findDailyPrices(myRecipeIngredient.getIngredient().getId(), 1);
            List<Month> monthlyPrice = dailyPriceService.findMonthlyPrices(myRecipeIngredient.getIngredient().getId(), 1);
            List<Year> yearlyPrice = dailyPriceService.findYearlyPrices(myRecipeIngredient.getIngredient().getId(), 1);
            List<Day> dailyWholePrice = dailyPriceService.findDailyPrices(myRecipeIngredient.getIngredient().getId(), 0);
            List<Month> monthlyWholePrice = dailyPriceService.findMonthlyPrices(myRecipeIngredient.getIngredient().getId(), 0);
            dailySize = Math.min(dailySize, dailyPrice.size());
            monthlySize = Math.min(monthlySize, monthlyPrice.size());
            dailyWholeSize = Math.min(dailyWholeSize, dailyWholePrice.size());
            monthlyWholeSize = Math.min(monthlyWholeSize, dailyWholePrice.size());
            yearSize = Math.min(monthlySize, Math.min(yearSize, yearlyPrice.size()));

            int price = 0;
            for (int i = 0; i < 10; i++) {
                if (i < dailyPrice.size()) {
                    price = (int) (dailyPrice.get(i).getPrice() * ((1.0) * myRecipeIngredient.getQuantity() / dailyPrice.get(i).getQuantity()));
                    dayRetailPrice.get(i).setPrice(dayRetailPrice.get(i).getPrice() + price);
                }
                if (i < monthlyPrice.size()) {
                    price = (int) (monthlyPrice.get(i).getPrice() * ((1.0) * myRecipeIngredient.getQuantity() / monthlyPrice.get(i).getQuantity()));
                    monthRetailPrice.get(i).setPrice(dayRetailPrice.get(i).getPrice() + price);
                }
                if (i < dailyWholePrice.size()) {
                    price = (int) (dailyWholePrice.get(i).getPrice() * ((1.0) * myRecipeIngredient.getQuantity() / dailyWholePrice.get(i).getQuantity()));
                    dayWholePrice.get(i).setPrice(dayWholePrice.get(i).getPrice() + price);
                }
                if (i < monthlyWholePrice.size()) {
                    price = (int) (monthlyWholePrice.get(i).getPrice() * ((1.0) * myRecipeIngredient.getQuantity() / monthlyWholePrice.get(i).getQuantity()));
                    monthWholePrice.get(i).setPrice(monthWholePrice.get(i).getPrice() + price);
                }
                if (i < yearlyPrice.size() && i < monthlyPrice.size()) {
                    price = (int) (yearlyPrice.get(i).getPrice() * ((1.0) * myRecipeIngredient.getQuantity() / monthlyPrice.get(i).getQuantity()));
                    yearRetailPrice.get(i).setPrice(monthRetailPrice.get(i).getPrice() + price);
                }
                if (setDate) {
                    for (int j = 0; j < 10; j++) {
                        if (dailyPrice != null && dailyPrice.size() > j)
                            dayRetailPrice.get(j).setDate(dailyPrice.get(j).getDatetime());
                        if (monthlyPrice != null && monthlyPrice.size() > j)
                            monthRetailPrice.get(j).setDate(monthlyPrice.get(j).getDatetime());
                        if (dailyWholePrice != null && dailyWholePrice.size() > j)
                            dayWholePrice.get(j).setDate(dailyWholePrice.get(j).getDatetime());
                        if (monthlyWholePrice != null && monthlyWholePrice.size() > j)
                            monthWholePrice.get(j).setDate(monthlyWholePrice.get(j).getDatetime());
                        if (j >= yearlyPrice.size()) continue;
                        yearRetailPrice.get(j).setDate(yearlyPrice.get(j).getDatetime());
                    }
//                        setDate = false;
                }
            }

            if (dailyPrice.size() > 0) {
                double volatility =  (1.0 *(dailyPrice.get(0).getPrice() - dailyPrice.get(1).getPrice()) / dailyPrice.get(1).getPrice()) * 100;
                volatility = Math.round(volatility * 100) / 100.0;
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(dailyPrice.get(0).getPrice())
                        .unit(dailyPrice.get(0).getUnit())
                        .quantity(dailyPrice.get(0).getQuantity())
                        .myQuantity(myRecipeIngredient.getQuantity())
                        .volatility(volatility)
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();
                ingreidentlist.add(ingredientInfoRes);
            } else if (dailyWholePrice.size() > 0) {
                double volatility = ((1.0 * dailyWholePrice.get(0).getPrice() - dailyWholePrice.get(1).getPrice()) / dailyWholePrice.get(1).getPrice()) * 100;
                volatility = Math.round(volatility * 100) / 100.0;
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(dailyWholePrice.get(0).getPrice())
                        .unit(dailyWholePrice.get(0).getUnit())
                        .quantity(dailyWholePrice.get(0).getQuantity())
                        .myQuantity(myRecipeIngredient.getQuantity())
                        .volatility(volatility)
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();
                ingreidentlist.add(ingredientInfoRes);
            } else if (monthlyPrice.size() > 0) {
                double volatility = (1.0 * (monthlyPrice.get(0).getPrice() - monthlyPrice.get(1).getPrice()) / monthlyPrice.get(1).getPrice()) * 100;
                volatility = Math.round(volatility * 100) / 100.0;
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(monthlyPrice.get(0).getPrice())
                        .unit(monthlyPrice.get(0).getUnit())
                        .quantity(monthlyPrice.get(0).getQuantity())
                        .myQuantity(myRecipeIngredient.getQuantity())
                        .volatility(volatility)
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();
                ingreidentlist.add(ingredientInfoRes);
            } else if (monthlyWholePrice.size() > 0) {
                double volatility = (1.0 * (monthlyWholePrice.get(0).getPrice() - monthlyWholePrice.get(1).getPrice()) / monthlyWholePrice.get(1).getPrice()) * 100;
                volatility = Math.round(volatility * 100) / 100.0;
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(monthlyWholePrice.get(0).getPrice())
                        .unit(monthlyWholePrice.get(0).getUnit())
                        .quantity(monthlyWholePrice.get(0).getQuantity())
                        .myQuantity(myRecipeIngredient.getQuantity())
                        .volatility(volatility)
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();
                ingreidentlist.add(ingredientInfoRes);
            } else {
                MyRecipeIngredientInfoRes ingredientInfoRes = MyRecipeIngredientInfoRes.builder()
                        .ingredientId(myRecipeIngredient.getIngredient().getId())
                        .name(myRecipeIngredient.getIngredient().getMidClass())
                        .price(0)
                        .myQuantity(myRecipeIngredient.getQuantity())
                        .allergy(allergyRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .favorite(ingredientSelectedRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .basket(basketRepository.existsByUserIdAndIngredientId(myRecipe.getUser().getId(), myRecipeIngredient.getIngredient().getId()))
                        .highClassId(myRecipeIngredient.getIngredient().getHighClass())
                        .highClassName(highClassRepository.findById(myRecipeIngredient.getIngredient().getHighClass()).get().getName())
                        .build();
                ingreidentlist.add(ingredientInfoRes);
            }
            if(ingreidentlist.get(ingreidentlist.size()-1).getPrice()!= 0) total_price += (ingreidentlist.get(ingreidentlist.size()-1).getPrice()/ingreidentlist.get(ingreidentlist.size()-1).getQuantity()) * myRecipeIngredient.getQuantity();
        }


        yearRetailPrice.removeIf(item -> item.getDate() == null);

        if (dailySize != 0) {
            priceTransitionInfoRes.setBeforePrice(dayRetailPrice.get(1).getPrice());
            priceTransitionInfoRes.setPrice(dayRetailPrice.get(0).getPrice());
            if (dayRetailPrice.get(1).getPrice() == 0) {
                priceTransitionInfoRes.setTodayvol(0);
                priceTransitionInfoRes.setPastvol(0);
            } else {
                double todayvol = (1.0 * (dayRetailPrice.get(0).getPrice() - dayRetailPrice.get(1).getPrice()) / dayRetailPrice.get(1).getPrice()) * 100;
                priceTransitionInfoRes.setTodayvol(Math.round(todayvol * 100) / 100.0);
                if (dayRetailPrice.get(2).getPrice() == 0) {
                    priceTransitionInfoRes.setPastvol(0);
                } else {
                    double pastvol = (1.0 * (dayRetailPrice.get(1).getPrice() - dayRetailPrice.get(2).getPrice()) / dayRetailPrice.get(2).getPrice()) * 100;
                    priceTransitionInfoRes.setPastvol(Math.round(pastvol * 100) / 100.0);
                }
            }
        }
        priceTransitionInfoRes.setWholesales(new SaleInfoRes());
        priceTransitionInfoRes.getWholesales().setDaily(reverseList(new ArrayList<>(dayWholePrice.subList(0, dailyWholeSize))));
        priceTransitionInfoRes.getWholesales().setYearly(reverseList(new ArrayList<>(yearRetailPrice.subList(0, yearSize))));
        priceTransitionInfoRes.getWholesales().setMonthly(reverseList(new ArrayList<>(monthWholePrice.subList(0, monthlyWholeSize))));
        priceTransitionInfoRes.setRetailsales(new SaleInfoRes());
        priceTransitionInfoRes.getRetailsales().setDaily(reverseList(new ArrayList<>(dayRetailPrice.subList(0, dailySize))));
        priceTransitionInfoRes.getRetailsales().setYearly(reverseList(new ArrayList<>(yearRetailPrice.subList(0, yearSize))));
        priceTransitionInfoRes.getRetailsales().setMonthly(reverseList(new ArrayList<>(monthRetailPrice.subList(0, monthlySize))));

        if (priceTransitionInfoRes.getRetailsales().getDaily().size() > 0)
            myRecipeDetailRes.setTotalPrice(priceTransitionInfoRes.getRetailsales().getDaily().get(priceTransitionInfoRes.getRetailsales().getDaily().size() - 1).getPrice());
        else if (priceTransitionInfoRes.getWholesales().getDaily().size() > 0)
            myRecipeDetailRes.setTotalPrice(priceTransitionInfoRes.getWholesales().getDaily().get(priceTransitionInfoRes.getWholesales().getDaily().size() - 1).getPrice());
        else myRecipeDetailRes.setTotalPrice(total_price);
        myRecipeDetailRes.setIngredientList(ingreidentlist);
        myRecipeDetailRes.setPriceTransitionInfo(priceTransitionInfoRes);
        myRecipeDetailRes.setImagePath(myRecipe.getImageURL());
        myRecipeDetailRes.setName(myRecipe.getName());

        return ResponseEntity.status(200).body(myRecipeDetailRes);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Internal Server Error" + e);
//        }
    }

    public List<PriceInfoRes> reverseList(List<PriceInfoRes> list) {
        Collections.reverse(list);
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
    public ResponseEntity<?> showMyrecipeIngredientList(@PathVariable String userName, @PathVariable Long myRecipeId) {
        List<MyRecipeIngredient> MyRecipeIngredientList = myRecipeService.getMyRecipeIngredientList(myRecipeId);
        List<MyRecipeIngredientRes> list = new ArrayList<>();
        for (MyRecipeIngredient myRecipeIngredient : MyRecipeIngredientList) {
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
    public ResponseEntity<?> deleteMyRecipe(@PathVariable String userName, @PathVariable Long myRecipeId) {
        try {
            if (myRecipeService.getRecipeOwner(myRecipeId).equals(userName))
                myRecipeService.deleteMyRecipe(myRecipeId);
            else return ResponseEntity.status(405).body("해당 유저의 레시피가 아닙니다");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("존재하지 않는 id");
        }
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestPart MultipartFile file) {
        return ResponseEntity.status(200).body(file.getName());
    }

}
