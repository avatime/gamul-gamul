package com.gamul.api.service;

import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.RecipeInfoRes;
import com.gamul.api.response.SearchRes;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
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
    @Autowired
    MonthRepository monthRepository;

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
                if (day == null){
                    day = day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 0);
                    if(day == null){
                        day = new Day();
                        Month month = monthRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
                        if(month != null){
                            day.setPrice(month.getPrice());
                            day.setUnit(month.getUnit());
                            day.setQuantity(month.getQuantity());
                        }else{
                            month = monthRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 0);
                            if(month != null){
                                day.setPrice(month.getPrice());
                                day.setUnit(month.getUnit());
                                day.setQuantity(month.getQuantity());
                            }else{
                                day.setPrice(0);
                                day.setUnit("");
                                day.setQuantity(0);
                            }
                        }
                    }
                }

                // 가격 변동률
                List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);

                int today = 0;
                int yesterday = 0;
                double volatility = 0.0;

                if(dayList.size() == 0){
                    today = 0;
                    yesterday = 0;
                    volatility = 0.0;
                }
                else if (dayList.size() == 1){
                    volatility = 0.0;
                }else {
                    today = dayList.get(0).getPrice();
                    yesterday = dayList.get(1).getPrice();
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
        List<Recipe> recipeList = recipeRepository.findTop30ByNameLike(result);
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
