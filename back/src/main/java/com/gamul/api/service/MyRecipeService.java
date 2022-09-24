package com.gamul.api.service;

import com.gamul.db.entity.MyRecipe;
import com.gamul.db.entity.MyRecipeIngredient;

import java.util.List;

public interface MyRecipeService {
    MyRecipe saveMyRecipe(MyRecipe myRecipe, String imageData) throws Exception;
    MyRecipe saveMyRecipe(MyRecipe myRecipe) throws Exception;
    List<MyRecipeIngredient> saveMyRecipeIngredient(List<MyRecipeIngredient> myRecipeIngredient);
    List<MyRecipe> getMyRecipeList(Long userId) throws Exception;
    void deleteMyRecipe(Long myRecipeId) throws Exception;
    String getRecipeOwner(Long myRecipeId) throws Exception;
    List<MyRecipeIngredient> getMyRecipeIngredientList(Long myRecipeId);
    MyRecipe getMyRecipe(Long myRecipeId);
}
