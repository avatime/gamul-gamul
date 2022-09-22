package com.gamul.api.service;

import com.gamul.db.entity.MyRecipe;

public interface MyRecipeService {
    MyRecipe saveMyRecipe(MyRecipe myRecipe) throws Exception;
}
