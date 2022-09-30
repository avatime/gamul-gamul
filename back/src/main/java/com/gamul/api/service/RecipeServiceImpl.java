package com.gamul.api.service;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.RecipeDetailRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
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


    @Override
    public List<RecipeInfoRes> getRecipeList(RecipeListReq recipeListReq){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();

        if(recipeListReq.getOrderType() == 1){
            PageRequest pageRequest = PageRequest.of(recipeListReq.getPage(), recipeListReq.getSize(), Sort.by(Sort.Direction.ASC, "name"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            System.out.println(recipeList.getContent().size());
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark);
                recipeInfoResList.add(recipeInfoRes);
            }
        }else {
            PageRequest pageRequest = PageRequest.of(recipeListReq.getPage(), recipeListReq.getSize(), Sort.by(Sort.Direction.DESC, "views"));
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

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark);
                recipeInfoResList.add(recipeInfoRes);
            }
        }

        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeBasket(RecipeBasketReq recipeBasketReq){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();

        if(recipeBasketReq.getOrderType() == 1){
            PageRequest pageRequest = PageRequest.of(recipeBasketReq.getPage(), recipeBasketReq.getSize(), Sort.by(Sort.Direction.ASC, "name"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            System.out.println(recipeList.getContent().size());
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // 레시피 찜 가져오기
                RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark);
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

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark);
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
        List<RecipeSelected> recipeSelectedList = recipeSelectedRepository.findByUserId(user.getId()).orElse(null);
        for(RecipeSelected recipeSelected : recipeSelectedList){
            boolean bookmark = true;
            if (recipeSelected == null){
                bookmark = false;
            }
            Recipe recipe = recipeRepository.findById(recipeSelected.getRecipe().getId()).orElse(null);
            RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark);
            recipeInfoResList.add(recipeInfoRes);
        }
        return recipeInfoResList;
    }

//    @Override
//    public RecipeDetailRes getRecipeDetail(RecipeDetailReq recipeDetailReq){
//        // recipeinfoRes 객체 생성
//        Recipe recipe = recipeRepository.findById(recipeDetailReq.getRecipeId()).get();
//        RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).get();
//        RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe, recipeSelected);
//
//        // ingredientList 생성
//        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
//        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByIngredientId(recipe.getId()).get();
//        List<String> recipeIngredientName = new ArrayList<>();
//        for (RecipeIngredient recipeIngredient : recipeIngredientList){
//            // 식재료 객체 생성
//            Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId()).get();
//            // 가격 객체 가져오기
//            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
//            // 알러지 객체 가져오기
//            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
//            // 재료 찜 객체 가져오기
//            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
//            // 바구니 객체 가져오기
//            Basket basket = basketRepository.findByIngredientId(ingredient.getId());
//            // 대분류 객체 가져오기
//            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();
//
//            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, price, allergy, ingredientSelected, basket, highClass);
//
//            ingredientInfoResList.add(ingredientInfoRes);
//
//            recipeIngredientName.add(ingredient.getMidClass());
//
//        }
//
//        // extraIngredientList 생성
//        List<String> extraIngredientList = new ArrayList<>();
//        for (String item : recipeIngredientName){
//
//        }
//
//    }

    @Override
    public void recipeSelected(String userName, Long recipeId){
        User user = userRepository.findByUsername(userName).get();
        RecipeSelected recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipeId).get();
        recipeSelected.setActiveFlag(!recipeSelected.isActiveFlag());
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
        }

    }

    @Override
    public void addRecipeViews(Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId).get();

    }
}
