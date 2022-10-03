package com.gamul.api.service;

import com.gamul.api.request.RecipeBasketReq;
import com.gamul.api.request.RecipeDetailReq;
import com.gamul.api.request.RecipeListReq;
import com.gamul.api.response.RecipeDetailRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.RecipeProcedureRes;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RecipeService {
    List<RecipeInfoRes> getRecipeList(int orderType, int page, int size);
    List<RecipeInfoRes> getRecipeBasket(RecipeBasketReq recipeBasketReq);

    List<RecipeInfoRes> getRecipeSelected(String userName);

    RecipeDetailRes getRecipeDetail(Long recipeId, String userName);

    void recipeSelected(String userName, Long recipeId);

    List<RecipeProcedureRes> getRecipeOrder(Long recipeId);

    void addRecipeIngredientBasket(String userName, Long recipeId);

    void addRecipeViews(Long recipeId);
}
