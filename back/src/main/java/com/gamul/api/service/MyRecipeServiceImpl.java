package com.gamul.api.service;

import com.gamul.db.entity.MyRecipe;
import com.gamul.db.repository.MyRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MyRecipeService")
@RequiredArgsConstructor
public class MyRecipeServiceImpl implements MyRecipeService {

    @Autowired
    MyRecipeRepository myRecipeRepository;

    public MyRecipe saveMyRecipe(MyRecipe myRecipe) throws Exception {
        return myRecipeRepository.save(myRecipe);
    }

    public List<MyRecipe> getMyRecipeList(Long userId) throws Exception {
        return myRecipeRepository.findAllByUserId(userId).get();
    }
}
