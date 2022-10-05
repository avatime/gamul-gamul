package com.gamul.api.controller;

import com.gamul.api.request.OcrRegistReq;
import com.gamul.api.service.OcrService;
import com.gamul.api.service.UserService;
import com.gamul.db.entity.Basket;
import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.User;
import com.gamul.db.repository.BasketRepository;
import com.gamul.db.repository.IngredientRepository;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ocr 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "orc API", tags = {"OCR."})
@RestController
@RequestMapping("/api/v1/ocr")
public class OcrController {

    @Autowired
    OcrService ocrService;

    @Autowired
    UserService userService;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @PostMapping("")
    @ApiOperation(value = "영수증을 통한 장바구니 등록", notes = "<strong>영수증</strong>에 있는 품목을 장바구니에 등록한다")
    public ResponseEntity<?> getNoticeDetail(@RequestBody @ApiParam(value="영수증 정보", required = true) OcrRegistReq ocrRegisterReq){
        try{
            String json = ocrService.getItemsFromRecipe(ocrRegisterReq.getImageData());
//            String json = "{\"version\":\"V2\",\"requestId\":\"25e323a7-6ee4-44ae-b564-165f192f9add\",\"timestamp\":1664730300335,\"images\":[{\"uid\":\"990fe6c6e26444ff96a62f70627d190a\",\"name\":\"demo\",\"inferResult\":\"SUCCESS\",\"message\":\"SUCCESS\",\"validationResult\":{\"result\":\"NO_REQUESTED\"},\"convertedImageInfo\":{\"width\":341,\"height\":586,\"pageIndex\":0,\"longImage\":false},\"fields\":[{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":125.0,\"y\":21.0},{\"x\":216.0,\"y\":23.0},{\"x\":216.0,\"y\":51.0},{\"x\":125.0,\"y\":49.0}]},\"inferText\":\"영수증\",\"inferConfidence\":0.999,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":78.0},{\"x\":74.0,\"y\":78.0},{\"x\":74.0,\"y\":97.0},{\"x\":14.0,\"y\":97.0}]},\"inferText\":\"[매장명]\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":77.0,\"y\":78.0},{\"x\":188.0,\"y\":78.0},{\"x\":188.0,\"y\":97.0},{\"x\":77.0,\"y\":97.0}]},\"inferText\":\"싸피마트 역삼점\",\"inferConfidence\":0.9995,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":100.0},{\"x\":102.0,\"y\":100.0},{\"x\":102.0,\"y\":119.0},{\"x\":14.0,\"y\":119.0}]},\"inferText\":\"[사업자번호]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":122.0},{\"x\":60.0,\"y\":122.0},{\"x\":60.0,\"y\":141.0},{\"x\":14.0,\"y\":141.0}]},\"inferText\":\"[주소]\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":93.0,\"y\":126.0},{\"x\":105.0,\"y\":126.0},{\"x\":105.0,\"y\":137.0},{\"x\":93.0,\"y\":137.0}]},\"inferText\":\"도\",\"inferConfidence\":0.9993,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":116.0,\"y\":126.0},{\"x\":143.0,\"y\":126.0},{\"x\":143.0,\"y\":136.0},{\"x\":116.0,\"y\":136.0}]},\"inferText\":\"×××\",\"inferConfidence\":0.8857,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":154.0,\"y\":124.0},{\"x\":184.0,\"y\":124.0},{\"x\":184.0,\"y\":138.0},{\"x\":154.0,\"y\":138.0}]},\"inferText\":\"××동\",\"inferConfidence\":0.8381,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":192.0,\"y\":124.0},{\"x\":268.0,\"y\":124.0},{\"x\":268.0,\"y\":138.0},{\"x\":192.0,\"y\":138.0}]},\"inferText\":\"1159(XX동)\",\"inferConfidence\":0.9968,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":144.0},{\"x\":73.0,\"y\":144.0},{\"x\":73.0,\"y\":163.0},{\"x\":14.0,\"y\":163.0}]},\"inferText\":\"[대표자]\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":149.0,\"y\":145.0},{\"x\":187.0,\"y\":145.0},{\"x\":187.0,\"y\":160.0},{\"x\":149.0,\"y\":160.0}]},\"inferText\":\"[TEL]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":166.0},{\"x\":74.0,\"y\":166.0},{\"x\":74.0,\"y\":185.0},{\"x\":14.0,\"y\":185.0}]},\"inferText\":\"[매출일]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":140.0,\"y\":171.0},{\"x\":148.0,\"y\":171.0},{\"x\":148.0,\"y\":179.0},{\"x\":140.0,\"y\":179.0}]},\"inferText\":\"-\",\"inferConfidence\":0.9985,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":174.0,\"y\":169.0},{\"x\":235.0,\"y\":168.0},{\"x\":236.0,\"y\":182.0},{\"x\":174.0,\"y\":183.0}]},\"inferText\":\"11:43:33\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":13.0,\"y\":207.0},{\"x\":59.0,\"y\":207.0},{\"x\":59.0,\"y\":226.0},{\"x\":13.0,\"y\":226.0}]},\"inferText\":\"상품명\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":155.0,\"y\":207.0},{\"x\":189.0,\"y\":207.0},{\"x\":189.0,\"y\":226.0},{\"x\":155.0,\"y\":226.0}]},\"inferText\":\"단가\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":219.0,\"y\":207.0},{\"x\":253.0,\"y\":207.0},{\"x\":253.0,\"y\":226.0},{\"x\":219.0,\"y\":226.0}]},\"inferText\":\"수량\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":280.0,\"y\":207.0},{\"x\":311.0,\"y\":207.0},{\"x\":311.0,\"y\":226.0},{\"x\":280.0,\"y\":226.0}]},\"inferText\":\"금액\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":13.0,\"y\":245.0},{\"x\":46.0,\"y\":245.0},{\"x\":46.0,\"y\":264.0},{\"x\":13.0,\"y\":264.0}]},\"inferText\":\"양파\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":155.0,\"y\":246.0},{\"x\":188.0,\"y\":246.0},{\"x\":188.0,\"y\":263.0},{\"x\":155.0,\"y\":263.0}]},\"inferText\":\"800\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":230.0,\"y\":247.0},{\"x\":243.0,\"y\":247.0},{\"x\":243.0,\"y\":262.0},{\"x\":230.0,\"y\":262.0}]},\"inferText\":\"2\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":281.0,\"y\":246.0},{\"x\":324.0,\"y\":246.0},{\"x\":324.0,\"y\":263.0},{\"x\":281.0,\"y\":263.0}]},\"inferText\":\"1,600\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":12.0,\"y\":264.0},{\"x\":46.0,\"y\":264.0},{\"x\":46.0,\"y\":283.0},{\"x\":12.0,\"y\":283.0}]},\"inferText\":\"감자\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":155.0,\"y\":265.0},{\"x\":188.0,\"y\":265.0},{\"x\":188.0,\"y\":281.0},{\"x\":155.0,\"y\":281.0}]},\"inferText\":\"800\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":230.0,\"y\":266.0},{\"x\":243.0,\"y\":266.0},{\"x\":243.0,\"y\":281.0},{\"x\":230.0,\"y\":281.0}]},\"inferText\":\"2\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":281.0,\"y\":266.0},{\"x\":325.0,\"y\":266.0},{\"x\":325.0,\"y\":281.0},{\"x\":281.0,\"y\":281.0}]},\"inferText\":\"1,600\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":112.0,\"y\":302.0},{\"x\":159.0,\"y\":302.0},{\"x\":160.0,\"y\":321.0},{\"x\":113.0,\"y\":322.0}]},\"inferText\":\"부과세\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":162.0,\"y\":302.0},{\"x\":252.0,\"y\":302.0},{\"x\":252.0,\"y\":321.0},{\"x\":162.0,\"y\":321.0}]},\"inferText\":\"과세물품가액\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":279.0,\"y\":303.0},{\"x\":324.0,\"y\":303.0},{\"x\":324.0,\"y\":319.0},{\"x\":279.0,\"y\":319.0}]},\"inferText\":\"2,909\",\"inferConfidence\":0.9997,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":112.0,\"y\":325.0},{\"x\":131.0,\"y\":325.0},{\"x\":131.0,\"y\":343.0},{\"x\":112.0,\"y\":343.0}]},\"inferText\":\"부\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":174.0,\"y\":324.0},{\"x\":194.0,\"y\":324.0},{\"x\":194.0,\"y\":343.0},{\"x\":174.0,\"y\":343.0}]},\"inferText\":\"가\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":233.0,\"y\":324.0},{\"x\":253.0,\"y\":324.0},{\"x\":253.0,\"y\":343.0},{\"x\":233.0,\"y\":343.0}]},\"inferText\":\"세\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":294.0,\"y\":326.0},{\"x\":323.0,\"y\":326.0},{\"x\":323.0,\"y\":340.0},{\"x\":294.0,\"y\":340.0}]},\"inferText\":\"291\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":12.0,\"y\":362.0},{\"x\":75.0,\"y\":362.0},{\"x\":75.0,\"y\":381.0},{\"x\":12.0,\"y\":381.0}]},\"inferText\":\"신용카드\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":274.0,\"y\":364.0},{\"x\":320.0,\"y\":364.0},{\"x\":320.0,\"y\":380.0},{\"x\":274.0,\"y\":380.0}]},\"inferText\":\"3,200\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":94.0,\"y\":405.0},{\"x\":102.0,\"y\":405.0},{\"x\":102.0,\"y\":411.0},{\"x\":94.0,\"y\":411.0}]},\"inferText\":\"*\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":124.0,\"y\":399.0},{\"x\":216.0,\"y\":399.0},{\"x\":216.0,\"y\":418.0},{\"x\":124.0,\"y\":418.0}]},\"inferText\":\"신용승인정보\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":441.0},{\"x\":88.0,\"y\":441.0},{\"x\":88.0,\"y\":459.0},{\"x\":14.0,\"y\":459.0}]},\"inferText\":\"[카드종류]\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":92.0,\"y\":441.0},{\"x\":154.0,\"y\":441.0},{\"x\":154.0,\"y\":459.0},{\"x\":92.0,\"y\":459.0}]},\"inferText\":\"삼성카드\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":179.0,\"y\":441.0},{\"x\":254.0,\"y\":441.0},{\"x\":254.0,\"y\":459.0},{\"x\":179.0,\"y\":459.0}]},\"inferText\":\"[할부개월]\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":258.0,\"y\":441.0},{\"x\":305.0,\"y\":441.0},{\"x\":305.0,\"y\":459.0},{\"x\":258.0,\"y\":459.0}]},\"inferText\":\"일시불\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":463.0},{\"x\":88.0,\"y\":463.0},{\"x\":88.0,\"y\":481.0},{\"x\":14.0,\"y\":481.0}]},\"inferText\":\"[카드번호]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":91.0,\"y\":464.0},{\"x\":244.0,\"y\":464.0},{\"x\":244.0,\"y\":479.0},{\"x\":91.0,\"y\":479.0}]},\"inferText\":\"0000-0000-xxxx-xxxx\",\"inferConfidence\":0.9666,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":484.0},{\"x\":88.0,\"y\":484.0},{\"x\":88.0,\"y\":503.0},{\"x\":14.0,\"y\":503.0}]},\"inferText\":\"[유효기간]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":92.0,\"y\":487.0},{\"x\":130.0,\"y\":488.0},{\"x\":130.0,\"y\":499.0},{\"x\":92.0,\"y\":499.0}]},\"inferText\":\"xx/xx\",\"inferConfidence\":0.9703,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":507.0},{\"x\":88.0,\"y\":507.0},{\"x\":88.0,\"y\":526.0},{\"x\":14.0,\"y\":526.0}]},\"inferText\":\"[승인금액]\",\"inferConfidence\":0.9998,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":92.0,\"y\":509.0},{\"x\":136.0,\"y\":509.0},{\"x\":136.0,\"y\":524.0},{\"x\":92.0,\"y\":524.0}]},\"inferText\":\"3,200\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":529.0},{\"x\":89.0,\"y\":529.0},{\"x\":89.0,\"y\":548.0},{\"x\":14.0,\"y\":548.0}]},\"inferText\":\"[승인번호]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":90.0,\"y\":531.0},{\"x\":164.0,\"y\":531.0},{\"x\":164.0,\"y\":546.0},{\"x\":90.0,\"y\":546.0}]},\"inferText\":\"00000000\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":true},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":14.0,\"y\":551.0},{\"x\":88.0,\"y\":551.0},{\"x\":88.0,\"y\":570.0},{\"x\":14.0,\"y\":570.0}]},\"inferText\":\"[승인일시]\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":91.0,\"y\":553.0},{\"x\":181.0,\"y\":553.0},{\"x\":181.0,\"y\":568.0},{\"x\":91.0,\"y\":568.0}]},\"inferText\":\"2020-08-22\",\"inferConfidence\":1.0,\"type\":\"NORMAL\",\"lineBreak\":false},{\"valueType\":\"ALL\",\"boundingPoly\":{\"vertices\":[{\"x\":185.0,\"y\":554.0},{\"x\":247.0,\"y\":553.0},{\"x\":247.0,\"y\":567.0},{\"x\":185.0,\"y\":567.0}]},\"inferText\":\"11:43:50\",\"inferConfidence\":0.9999,\"type\":\"NORMAL\",\"lineBreak\":true}]}]}";
            Gson gson = new Gson();
            Map<String, Object> tmpMap = gson.fromJson(json, Map.class);
            ArrayList<Object> tmpList = (ArrayList<Object>) tmpMap.get("images");
            Map<String, Object> tmpMap2 = (Map<String, Object>) tmpList.get(0);
            ArrayList<Map<String, Object>> tmpList2 = (ArrayList<Map<String, Object>>) tmpMap2.get("fields");

            List<String> st = new ArrayList<>();

            for(Map<String, Object> word : tmpList2) {
                st.add((String) word.get("inferText"));
            }

            List<item> list = new ArrayList<>();
            for(int i = 0; i<st.size(); i++){
                try {
                    int price = Integer.parseInt(st.get(i + 1).replace(",",""));
                    int quantity = Integer.parseInt(st.get(i + 2).replace(",",""));
                    int total = Integer.parseInt(st.get(i + 3).replace(",",""));
                    if(total == price*quantity) {
                        list.add(new item(st.get(i), price));
                    }
                } catch (Exception e){
                    continue;
                }
            }

            User user = userService.getUserByUsername(ocrRegisterReq.getUserName());

            for(item i:list){
                Ingredient ingredient = ingredientRepository.findByMidClass(i.name).orElseGet(null);
                if(ingredient != null && basketRepository.existsByUserIdAndIngredientId(user.getId(), ingredient.getId())) {
                    basketRepository.save(new Basket(user,ingredient));
                }
            }

            return ResponseEntity.status(200).body("Success");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Invalid Server Error");
        }
    }

    public class item {
        String name;
        int price;

        item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    }
