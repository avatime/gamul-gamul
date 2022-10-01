package com.gamul.api.controller;

import com.gamul.api.request.*;
import com.gamul.api.response.AllergyAlarmRes;
import com.gamul.api.response.IngredientLimitPriceAlarmRes;
import com.gamul.api.response.IngredientLimitPriceRes;
import com.gamul.api.response.NoticeRes;
import com.gamul.api.service.AlarmService;
import com.gamul.api.service.DailyPriceService;
import com.gamul.api.service.UserService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.*;
import com.gamul.db.repository.IngredientRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 알람 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "알람 API", tags = {"Alarm."})
@RestController
@RequestMapping("/api/v1/user")
public class AlarmController {
    @Autowired
    AlarmService alarmService;
    @Autowired
    UserService userService;
    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DailyPriceService dailyPriceService;

    @PostMapping("/allergy")
    @ApiOperation(value = "알러지 등록/해제", notes = "식재료별 <strong>알러지</strong>를 등록 혹은 해제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> allergyAlarm(@RequestBody @ApiParam(value="알러지 정보", required = true)IngredientAllergyRegisterPostReq ingredientAllergyRegisterPostReq){
        try{
            alarmService.deleteMyAllergy(ingredientAllergyRegisterPostReq.getUserName());
            alarmService.saveAllAlergy(changeAllergy(ingredientAllergyRegisterPostReq.getUserName(), ingredientAllergyRegisterPostReq.getIngredientList()));
        } catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error" + e));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    List<Allergy> changeAllergy(String username, List<Long> list){
        List<Allergy> allergyList = new ArrayList<>();
        User user = userService.getUserByUsername(username);
        for(Long ingredientId : list){
            allergyList.add(new Allergy(user, ingredientRepository.findById(ingredientId).orElseGet(null)));
        }
        return allergyList;
    }

    @PostMapping("/notice")
    @ApiOperation(value = "상하한가 알림 등록/해제", notes = "식재료별 <strong>상하한가</strong>를 등록 혹은 해제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> noticeAlarm(@RequestBody @ApiParam(value="가격 알림 정보", required = true)IngredientLimitPricePostReq ingredientLimitPricePostReq){
        try{
            alarmService.deleteMyNotice(ingredientLimitPricePostReq.getUserName());
            alarmService.saveAllIngredientPriceNotice(changeIngredientPriceNotice(ingredientLimitPricePostReq.getUserName(), ingredientLimitPricePostReq.getIngredientList()));
        } catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    List<IngredientPriceNotice> changeIngredientPriceNotice(String username, List<IngredientPricePostReq> priceList){
        List<IngredientPriceNotice> list = new ArrayList<>();
        User user = userService.getUserByUsername(username);
        for(IngredientPricePostReq ingredient : priceList){
            list.add(new IngredientPriceNotice(user, ingredientRepository.findById(ingredient.getIngredientId()).orElseGet(null), ingredient.getLowerLimitPrice(), ingredient.getUpperLimitPrice()));
        }
        return list;
    }

    @GetMapping("/allergy/{userName}")
    @ApiOperation(value = "알러지 전체 조회", notes = "유저별 <strong>알러지</strong>를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<AllergyAlarmRes> getAllergyList(@PathVariable String userName){
        AllergyAlarmRes allergyAlarmRes = new AllergyAlarmRes();
        User user = new User();
        try{
            user = userService.getUserByUsername(userName);
        } catch (Exception e){
            return ResponseEntity.ok(AllergyAlarmRes.of(401, "인증 실패", null));
        }
        try{
            List<Allergy> allergyList = alarmService.getAllergyList(user);
            List<IngredientPostReq> list = new ArrayList<>();
            for(Allergy allergy : allergyList) {
                list.add(new IngredientPostReq(allergy.getIngredient().getId()));
            }
            allergyAlarmRes.setIngredientList(list);
        } catch (Exception e){
            return ResponseEntity.ok(AllergyAlarmRes.of(500, "Internal Server Error", null));
        }
        return ResponseEntity.ok(AllergyAlarmRes.of(200, "Success", allergyAlarmRes));
    }

    @GetMapping("/notice/{userName}")
    @ApiOperation(value = "상하한가 알림 전체 조회", notes = "유저별 <strong>상하한가 알림</strong>를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<IngredientLimitPriceAlarmRes> getNoticeList(@PathVariable String userName){
        IngredientLimitPriceAlarmRes ingredientLimitPriceAlarmRes = new IngredientLimitPriceAlarmRes();
        User user = new User();
        try{
            user = userService.getUserByUsername(userName);
        } catch (Exception e){
            return ResponseEntity.ok(IngredientLimitPriceAlarmRes.of(401, "인증 실패", null));
        }
        try{
            List<IngredientPriceNotice> allergyList = alarmService.getNoticeList(user);
            List<IngredientLimitPriceRes> list = new ArrayList<>();
            for(IngredientPriceNotice notice : allergyList) {
                list.add(IngredientLimitPriceRes.builder().ingredientId(notice.getIngredient().getId())
                        .lowerLimitPrice(notice.getLowerLimitPrice())
                        .upperLimitPrice(notice.getUpperLimitPrice()).build());
            }
            ingredientLimitPriceAlarmRes.setIngredientList(list);
        } catch (Exception e){
            return ResponseEntity.ok(IngredientLimitPriceAlarmRes.of(500, "Internal Server Error", null));
        }
        return ResponseEntity.ok(IngredientLimitPriceAlarmRes.of(200, "Success", ingredientLimitPriceAlarmRes));
    }

    @GetMapping("/notice/{userName}/{ingredientId}")
    @ApiOperation(value = "상하한가 알림 상세 조회", notes = "유저별 <strong>상하한가 알림</strong>를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<IngredientLimitPriceRes> getNoticeDetail(@PathVariable String userName, @PathVariable Long ingredientId){
        User user = new User();
        try{
            user = userService.getUserByUsername(userName);
        } catch (Exception e){
            return ResponseEntity.ok(IngredientLimitPriceRes.of(401, "인증 실패", null));
        }
        IngredientPriceNotice ingredientPriceNotice = alarmService.getNoticeDetail(user, ingredientId);
        IngredientLimitPriceRes ingredientLimitPriceRes;
        if(ingredientPriceNotice == null) {
            ingredientLimitPriceRes = IngredientLimitPriceRes.builder()
                    .ingredientId(ingredientId)
                    .upperLimitPrice(0)
                    .lowerLimitPrice(0).build();
        }
        else ingredientLimitPriceRes = IngredientLimitPriceRes.builder()
                .ingredientId(ingredientPriceNotice.getIngredient().getId())
                .upperLimitPrice(ingredientPriceNotice.getUpperLimitPrice())
                .lowerLimitPrice(ingredientPriceNotice.getLowerLimitPrice()).build();
        return ResponseEntity.ok(IngredientLimitPriceRes.of(200, "Success", ingredientLimitPriceRes));
    }

    @PostMapping("/notice/regist")
    @ApiOperation(value = "알림을 위한 정보 등록", notes = "유저별 <strong>알람 정보</strong>를 등록한다")
    public ResponseEntity<BaseResponseBody> getNoticeDetail(@RequestBody  @ApiParam(value="알람 설정 정보", required = true) AlarmRegisterReq alarmRegisterReq){

        User user = userService.getUserByUsername(alarmRegisterReq.getUserName());
        user.setSubscription(alarmRegisterReq.getSubscription());
        userService.saveUser(user);
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping ("/notice/send")
    @ApiOperation(value = "모든 유저에게 알림 전송", notes = "유저별 <strong>알람</strong>을 전송한다")
    public ResponseEntity<BaseResponseBody> sendNoticeToAllUsers(){

        // ??
        return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("notice/list")
    @ApiOperation(value = "유저별 알람 조회", notes = "유저별 <strong>알람</strong>을 조회한다")
    public ResponseEntity<?> getNoticeList(@RequestBody @ApiParam(value="알람 설정 정보", required = true)IngredientAllergyListReq usernameReq){
        DecimalFormat decFormat = new DecimalFormat("###,###");
        try{
            User user = userService.getUserByUsername(usernameReq.getUserName());
            List<Notice> noticeList = alarmService.getAllNoticeByUser(user);
            List<NoticeRes> list = new ArrayList<>();
            for(Notice notice : noticeList){
                Day day = dailyPriceService.findDailyPrice(notice.getIngredientPriceNotice().getIngredient().getId(), 1);
                String info = " - " +  decFormat.format(day.getPrice()) + "원/" + day.getQuantity() + day.getUnit();
                list.add(new NoticeRes(notice, info));
            }

            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(200).body("Internal Server Error");
        }
    }

//    @Scheduled(fixedRate = 10000)
//    @GetMapping("/send")
//    public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException {
//        String notifications = new Date().toString();
//        HttpEntity<String> request = new HttpEntity<>(notifications);
//        CompletableFuture<String> pushNotification = CompletableFuture.supplyAsync(() -> notifications);
//        CompletableFuture.allOf(pushNotification).join();
//
//        try{
//            String firebaseResponse = pushNotification.get();
//            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//        }
//        catch (InterruptedException e){
//            System.out.println("got interrupted!");
//            throw new InterruptedException();
//        }
//        catch (ExecutionException e){
//            System.out.println("execution error!");
//        }
//
//        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
//    }
}
