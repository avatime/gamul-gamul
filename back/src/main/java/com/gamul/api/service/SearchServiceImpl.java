package com.gamul.api.service;

import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.SearchRes;
import com.gamul.db.entity.Day;
import com.gamul.db.entity.HighClass;
import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.Recipe;
import com.gamul.db.repository.DayRepository;
import com.gamul.db.repository.HighClassRepository;
import com.gamul.db.repository.IngredientRepository;
import com.gamul.db.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SearchService")
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    DayRepository dayRepository;
    @Autowired
    HighClassRepository highClassRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public SearchRes search(String keyword){
        String result = "%" + keyword + "%";
        SearchRes searchRes = new SearchRes();
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        List<Ingredient> ingredientList = ingredientRepository.findByMidClassLike(result).orElse(null);
        boolean allergyStatus = false;
        boolean selectedStatus = false;
        boolean basketStatus = false;

        if (ingredientList != null){
            for (Ingredient ingredient : ingredientList){
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

                // 대분류 객체 가져오기
                HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();
                IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
                ingredientInfoResList.add(ingredientInfoRes);
            }
        }
        searchRes.setIngredientList(ingredientInfoResList);

        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        List<Recipe> recipeList = recipeRepository.findByNameLike(result).orElse(null);
        if (recipeList != null){
            for (Recipe recipe: recipeList){

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), false, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }
        searchRes.setRecipeList(recipeInfoResList);
        
        return searchRes;
    }
}
