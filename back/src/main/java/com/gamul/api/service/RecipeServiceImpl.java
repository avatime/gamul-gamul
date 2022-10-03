package com.gamul.api.service;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.*;
import com.gamul.common.util.YoutubeChannelSearch;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service("RecipeService")
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeSelectedRepository recipeSelectedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    AllergyRepository allergyRepository;
    @Autowired
    IngredientSelectedRepository ingredientSelectedRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    HighClassRepository highClassRepository;
    @Autowired
    RecipeOrderRepository recipeOrderRepository;
    @Autowired
    IngredientNotneedRepository ingredientNotneedRepository;
    @Autowired
    DayRepository dayRepository;

    private final YoutubeChannelSearch youtubeChannelSearch;
    @Override
    public List<RecipeInfoRes> getRecipeList(int orderType, int page, int size){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        if(orderType == 1){
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }else {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "views"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){
//            System.out.println("X: "+ x.getId());
                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }

        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeBasket(RecipeBasketReq recipeBasketReq){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();

        if(recipeBasketReq.getOrderType() == 1){
            PageRequest pageRequest = PageRequest.of(recipeBasketReq.getPage(), recipeBasketReq.getSize(), Sort.by(Sort.Direction.DESC, "id"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }else {
            PageRequest pageRequest = PageRequest.of(recipeBasketReq.getPage(), recipeBasketReq.getSize(), Sort.by(Sort.Direction.DESC, "views"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){
//            System.out.println("X: "+ x.getId());
                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }

        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeSelected(String userName){
        // 반환할 객체
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        User user = userRepository.findByUsername(userName).get();
        List<RecipeSelected> recipeSelectedList = recipeSelectedRepository.findByUserIdOrderByCreatedTimeDesc(user.getId());
        for(RecipeSelected recipeSelected : recipeSelectedList){
            if (recipeSelected.isActiveFlag()){
                Recipe recipe = recipeRepository.findById(recipeSelected.getRecipe().getId()).get();
                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), recipeSelected.isActiveFlag(), recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }
        return recipeInfoResList;
    }

    @Override
    public RecipeDetailRes getRecipeDetail(Long recipeId, String userName){
        // recipeinfoRes 객체 생성
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        User user = new User();
        boolean allergyStatus = false;
        boolean selectedStatus = false;
        boolean basketStatus = false;
        if (userRepository.existsByUsername(userName)){
            user = userRepository.findByUsername(userName).orElse(null);
        }else{
            user = null;
        }
        RecipeSelected recipeSelected = new RecipeSelected();
        if (user == null){
            recipeSelected = null;
        }else{
            recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipeId).orElse(null);
        }

        boolean bookmark;
        if (recipeSelected != null){
            if(recipeSelected.isActiveFlag()){
                bookmark = true;
            }else{
                bookmark = false;
            }
        }else{
            bookmark = false;
        }

        RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());

        // ingredientList 생성
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByRecipeId(recipe.getId()).get();
        ;
        if (recipeIngredientList.size() >= 1){
            for (RecipeIngredient recipeIngredient : recipeIngredientList){
                Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId()).get();

                // 가격 객체 가져오기
                Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
                // 가격 변동률
                double volatility = 0;
                if (day == null){
                    day = new Day();
                    day.setPrice(0);
                    day.setUnit("");
                    day.setQuantity(0);
                }else{
                    // 가격 변동률
                    List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
                    int today = dayList.get(0).getPrice();

                    int yesterday = dayList.get(1).getPrice();
                    volatility = (today - yesterday) * 100.0 / today ;
                    volatility = Math.round((volatility * 100) / 100.0);
                }

                Allergy allergy = new Allergy();
                IngredientSelected ingredientSelected = new IngredientSelected();
                Basket basket = new Basket();
                if (user != null){
                    allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
                    ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
                    basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
                }else{
                    allergy = null;
                    ingredientSelected = null;
                    basket = null;
                }

                // 알러지 객체 가져오기
                if (allergy == null){
                    allergyStatus = false;
                }else{
                    allergyStatus = allergy.isActiveFlag();
                }

                // 재료 찜 객체 가져오기
                if (ingredientSelected == null){
                    selectedStatus = false;
                }else{
                    selectedStatus = ingredientSelected.isActiveFlag();
                }

                // 바구니 객체 가져오기
                if (basket == null){
                    basketStatus = false;
                }else{
                    basketStatus = basket.isActiveFlag();
                }

                // 대분류 객체 가져오기
                HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

                IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
                ingredientInfoResList.add(ingredientInfoRes);
            }
        }


        // extraIngredientList 생성
        List<String> extraIngredientList = new ArrayList<>();
        List<IngredientNotneed> ingredientNotNeedList = ingredientNotneedRepository.findAllByRecipeId(recipeId).get();
        for (IngredientNotneed ingredientNotneed : ingredientNotNeedList){
            extraIngredientList.add(ingredientNotneed.getIngredient());
        }
        List<YoutubeInfoRes> youtubeInfoResList = youtubeChannelSearch.get(recipe.getName());
        RecipeDetailRes recipeDetailRes = new RecipeDetailRes(recipeInfoRes, ingredientInfoResList, extraIngredientList, youtubeInfoResList);
        return recipeDetailRes;
    }

    @Override
    public void recipeSelected(String userName, Long recipeId){
        User user = userRepository.findByUsername(userName).get();

        if(!recipeSelectedRepository.existsRecipeSelectedByUserIdAndRecipeId(user.getId(), recipeId)){
            System.out.println("레시피 아이디: " + recipeId);
            Recipe recipe = recipeRepository.findById(recipeId).get();
            RecipeSelected recipeSelected = new RecipeSelected(user, recipe);
            recipeSelectedRepository.save(recipeSelected);
        }else{
            System.out.println("레시피 아이디: " + recipeId);
            RecipeSelected recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipeId).get();
            recipeSelected.setActiveFlag(!recipeSelected.isActiveFlag());
            recipeSelectedRepository.save(recipeSelected);
        }
    }

    @Override
    public List<RecipeProcedureRes> getRecipeOrder(Long recipeId){
        List<RecipeOrder> recipeOrderList = recipeOrderRepository.findAllByRecipeId(recipeId).get();

        List<RecipeProcedureRes> recipeProcedureResList = new ArrayList<>();
        for (int i=0; i<recipeOrderList.size(); i++){
            RecipeOrder recipeOrder = recipeOrderList.get(i);
            RecipeProcedureRes recipeProcedureRes = new RecipeProcedureRes(recipeOrder);
            recipeProcedureResList.add(recipeProcedureRes);
        }
        return recipeProcedureResList;
    }

    @Override
    public void addRecipeIngredientBasket(String userName, Long recipeId){
        User user = userRepository.findByUsername(userName).get();
        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByRecipeId(recipeId).get();
        for (RecipeIngredient recipeIngredient : recipeIngredientList){
            Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId()).get();
            Basket basket = new Basket(user, ingredient);
            basketRepository.saveAndFlush(basket);
        }
    }

    @Override
    public void addRecipeViews(Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.setViews(recipe.getViews()+1);
        recipeRepository.saveAndFlush(recipe);
    }
}
