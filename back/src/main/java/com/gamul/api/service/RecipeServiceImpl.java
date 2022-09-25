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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RecipeService")
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeImageRepository recipeImageRepository;
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
    public List<RecipeInfoRes> getRecipeList(int orderType, int page, RecipeListReq recipeListReq){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        List<Recipe> recipeList = recipeRepository.findAll();
        for (Recipe recipe : recipeList){
            // 레시피 찜 가져오기
            RecipeSelected recipeSelected = recipeSelectedRepository.findByRecipeId(recipe.getId()).get();

            RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe, recipeSelected);

//            recipeInfoRes.setBookmark();

            recipeInfoResList.add(recipeInfoRes);
        }
        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeBasket(RecipeBasketReq recipeBasketReq){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();


        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeSelected(String userName){
        // 반환할 객체
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        User user = userRepository.findByUsername(userName).get();
        List<RecipeSelected> recipeSelectedList = recipeSelectedRepository.findByUserId(user.getId()).get();
        for(RecipeSelected recipeSelected : recipeSelectedList){
            Recipe recipe = recipeRepository.findById(recipeSelected.getRecipe().getId()).get();
            RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe, recipeSelected);
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
        List<RecipeImage> recipeImageList = recipeImageRepository.findAllByRecipeId(recipeId).get();
        List<RecipeProcedureRes> recipeProcedureResList = new ArrayList<>();
        for (int i=0; i<recipeOrderList.size(); i++){
            RecipeOrder recipeOrder = recipeOrderList.get(i);
            RecipeImage recipeImage = recipeImageList.get(i);
            RecipeProcedureRes recipeProcedureRes = new RecipeProcedureRes(recipeOrder, recipeImage);
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
