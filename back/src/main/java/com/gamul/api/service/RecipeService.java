package com.gamul.api.service;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.RecipeDetailRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipeService {
    List<RecipeInfoRes> getRecipeList(RecipeListReq recipeListReq);
    List<RecipeInfoRes> getRecipeBasket(RecipeBasketReq recipeBasketReq);

    List<RecipeInfoRes> getRecipeSelected(String userName);

    RecipeDetailRes getRecipeDetail(Long recipeId, String userName);

    void recipeSelected(String userName, Long recipeId);

    List<RecipeProcedureRes> getRecipeOrder(Long recipeId);

    void addRecipeIngredientBasket(String userName, Long recipeId);

    void addRecipeViews(Long recipeId);
}
