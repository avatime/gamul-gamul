package com.gamul.api.service;

import com.gamul.db.entity.MyRecipe;

import java.util.List;

public interface MyRecipeService {
    MyRecipe saveMyRecipe(MyRecipe myRecipe, String imageData) throws Exception;
    MyRecipe saveMyRecipe(MyRecipe myRecipe) throws Exception;
    List<MyRecipe> getMyRecipeList(Long userId) throws Exception;
}
