package com.gamul.api.controller;

import com.gamul.api.request.RecipeSelectPostReq;
import com.gamul.api.response.RecipeDetailRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
import com.gamul.api.response.YoutubeInfoRes;
import com.gamul.api.service.RecipeService;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.common.util.YoutubeChannelSearch;
import com.gamul.db.repository.RecipeRepository;
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

@Api(value = "레시피 API", tags = {"Recipe."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    private final YoutubeChannelSearch youtubeChannelSearch;

    @GetMapping("/{orderType}/{page}/{size}")
    @ApiOperation(value = "요리법 목록 반환", notes = "<strong>order type과 page</strong>에 따른 요리법 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeList(@PathVariable int orderType, @PathVariable int page, @PathVariable int size) {
        try{
            List<RecipeInfoRes> recipeInfoResList = recipeService.getRecipeList(orderType, page, size);
            return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoResList, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
    }

    @GetMapping("/basket/{orderType}/{page}/{size}/{userName}")
    @ApiOperation(value = "요리법 바구니 정보 반환", notes = "<strong>username</strong>에 따른 요리법 바구니 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeBasket(@PathVariable int orderType, @PathVariable int page, @PathVariable int size, @PathVariable String userName){
        try {
            if (!userRepository.existsByUsername(userName)){
                return ResponseEntity.ok(BaseResponseBody.of(404, "존재하지 않는 유저"));
            }
            List<RecipeInfoRes> recipeInfoResList = recipeService.getRecipeBasket(orderType, page, size, userName);
            return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoResList, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
    }

    @GetMapping("/bookmark/{userName}")
    @ApiOperation(value = "요리법 찜 목록 조회", notes = "<strong>username</strong>에 따른 요리법 찜 목록 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeSelected(@PathVariable String userName){
        try {
            if (!userRepository.existsByUsername(userName)){
                return ResponseEntity.ok(BaseResponseBody.of(404, "존재하지 않는 유저"));
            }
            List<RecipeInfoRes> recipeInfoRes = recipeService.getRecipeSelected(userName);
            return new ResponseEntity<List<RecipeInfoRes>>(recipeInfoRes, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }

    }

    @GetMapping("/youtube")
    @ApiOperation(value = "인기 요리법 유튜브", notes = "<strong>recipe id</strong>에 따른 인기 요리법 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeYoutube(){
        try {
            String query = "인기 요리법";
            List<YoutubeInfoRes> youtubeInfoResList = youtubeChannelSearch.get(query);
            if(youtubeInfoResList.size() == 0) return ResponseEntity.status(200).body("[\n" +
                    "  {\n" +
                    "    \"name\": \"후기가 증명하는 정~말 인기 많았던 가지요리 4가지!\",\n" +
                    "    \"view\": 1129284,\n" +
                    "    \"date\": \"2022-03-11\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=6dpSSANYF_s\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/6dpSSANYF_s/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"베지이즈 Vege is\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"2020년 인기있었던 저녁메뉴 10가지 요리모음영상 [만개의레시피]\",\n" +
                    "    \"view\": 290924,\n" +
                    "    \"date\": \"2021-01-04\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=-8Ey0X82eBE\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/-8Ey0X82eBE/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"만개의레시피 10K Recipe\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"인기있는 계란 요리 몰아보기! / Amazing Egg Dish!\",\n" +
                    "    \"view\": 23426667,\n" +
                    "    \"date\": \"2021-11-27\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=FKHQAy0ySmc\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/FKHQAy0ySmc/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"야미보이 Yummyboy\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"진미채무침 제일 쉽고 부드럽게 만드는법 : 밥 반찬 인기 메뉴 1위 진미채 무침 요리: 딱딱한 진미채볶음 이젠 안녕\",\n" +
                    "    \"view\": 34925,\n" +
                    "    \"date\": \"2021-03-17\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=H5zbfDXq1Nk\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/H5zbfDXq1Nk/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"2ddada 이따다 Korean food recipe\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"인기있는 한국 면요리 몰아보기 / Awesome! Korea's most popular noodles\",\n" +
                    "    \"view\": 197591,\n" +
                    "    \"date\": \"2022-07-16\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=xzoGbNlabBQ\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/xzoGbNlabBQ/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"푸드킹덤 Food Kingdom\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"생딸기우유 8년차 카페 레시피 / 인기만점 새콤달콤 정말 맛있엉  Strawberry milk :: 지니 Jiny\",\n" +
                    "    \"view\": 15691878,\n" +
                    "    \"date\": \"2021-02-26\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=j-dJizGwKJQ\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/j-dJizGwKJQ/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"J. Dessert 제이디저트\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"올리브쇼에서 인기 최고 메뉴♥ 양파피자 [만개의레시피]\",\n" +
                    "    \"view\": 11031,\n" +
                    "    \"date\": \"2017-04-25\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=6yOYfhbXnKs\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/6yOYfhbXnKs/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"만개의레시피 10K Recipe\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"지금 냉이 무조건 사 오세요/특별한 레시피 3가지#인기급상승동영상\",\n" +
                    "    \"view\": 423396,\n" +
                    "    \"date\": \"2022-03-05\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=nluhWIRB_iI\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/nluhWIRB_iI/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"주부나라\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"숙주와 차돌박이를 이렇게 만들면 중독성 있는 인기만점 요리로 변합니다\uD83D\uDC4D 평생 써 먹는 숙주차돌박이 볶음요리\",\n" +
                    "    \"view\": 96879,\n" +
                    "    \"date\": \"2022-03-21\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=ngnUT7X8dWs\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/ngnUT7X8dWs/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"엄마의손맛\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"150가지 에어프라이어 요리 중 인기메뉴 10가지 레시피모음 \uD83C\uDF89\",\n" +
                    "    \"view\": 67811,\n" +
                    "    \"date\": \"2021-02-17\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=yVZpR21jXkA\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/yVZpR21jXkA/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"만개의레시피 10K Recipe\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"[Eng sub]단체급식 인기 레시피 돼지고기 찹스테이크 만들기/단체급식, 구내식당 레시피/Pork Chop Steak ::63\",\n" +
                    "    \"view\": 39503,\n" +
                    "    \"date\": \"2020-07-08\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=EGq33hN1hok\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/EGq33hN1hok/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"향쿡 HYANG COOK\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"인기최고b!고기밑반찬~소고기장조림♥[만개의레시피]\",\n" +
                    "    \"view\": 32943,\n" +
                    "    \"date\": \"2017-02-10\",\n" +
                    "    \"url\": \"https://www.youtube.com/watch?v=yWgLM8ySdjM\",\n" +
                    "    \"thumbnail_path\": \"https://i.ytimg.com/vi/yWgLM8ySdjM/sddefault.jpg\",\n" +
                    "    \"channel_name\": \"만개의레시피 10K Recipe\"\n" +
                    "  }\n" +
                    "]");
            return ResponseEntity.status(200).body(youtubeInfoResList);
        }catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
    }

    @GetMapping(value = {"/{recipeId}/{userName}", "/{recipeId}"})
    @ApiOperation(value = "요리법 상세 조회", notes = "<strong>recipe id</strong>에 따른 요리법 상세 정보 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> getRecipeDetail(@PathVariable Long recipeId, @PathVariable(required = false) String userName){
        try {
            RecipeDetailRes recipeDetailRes = recipeService.getRecipeDetail(recipeId, userName);
            return ResponseEntity.status(200).body(recipeDetailRes);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PutMapping("/bookmark/{userName}/{recipeId}")
    @ApiOperation(value = "요리법 찜 등록 해제", notes = "<strong>username과 recipe id</strong>에 따른 요리법 찜 등록 해제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "존재하지 않는 user"),
            @ApiResponse(code = 405, message = "존재하지 않는 recipe"),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> recipeSelected(@RequestBody RecipeSelectPostReq recipeSelectPostReq){
        try{
            if (userRepository.existsByUsername(recipeSelectPostReq.getUserName())){
                if (!recipeRepository.existsById(recipeSelectPostReq.getRecipeId())) {
                    return ResponseEntity.status(405).body("레시피 없음");
                }
                recipeService.recipeSelected(recipeSelectPostReq.getUserName(), recipeSelectPostReq.getRecipeId());
            }
            else{
                return ResponseEntity.ok(BaseResponseBody.of(404, "존재하지 않는 유저"));
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
        try {
            List<RecipeProcedureRes> recipeProcedureResList = recipeService.getRecipeOrder(recipeId);
            return new ResponseEntity<List<RecipeProcedureRes>>(recipeProcedureResList, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(500, "Internal Server Error"));
        }
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
