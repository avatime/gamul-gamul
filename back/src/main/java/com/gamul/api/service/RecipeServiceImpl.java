package com.gamul.api.service;

import com.gamul.api.response.*;
import com.gamul.common.util.SubRecipePage;
import com.gamul.common.util.YoutubeChannelSearch;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    IngredientNotneedRepository ingredientNotneedRepository;
    @Autowired
    DayRepository dayRepository;
    @Autowired
    MonthRepository monthRepository;
    private final YoutubeChannelSearch youtubeChannelSearch;
    private final SubRecipePage subRecipePage;


    @Override
    public List<RecipeInfoRes> getRecipeList(int orderType, int page, int size){
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();

        if(orderType == 1){
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                boolean bookmark = false;

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }else {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "views"));
            Page<Recipe> recipeList = recipeRepository.findAll(pageRequest);
            for (Recipe x : recipeList.getContent()){

                Recipe recipe = recipeRepository.findById(x.getId()).get();
                boolean bookmark = false;

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }

        return recipeInfoResList;
    }

    @Override
    public List<RecipeInfoRes> getRecipeBasket(int orderType, int page, int size, String userName){

        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        User user = userRepository.findByUsername(userName).orElse(null);

        if (user == null){
            return recipeInfoResList;
        }
        List<Basket> basketList = basketRepository.findAllByUserId(user.getId());
        List<Long> recipeList1 = new ArrayList<>();

        if(basketList != null){
            for(Basket basket : basketList){

                Long ingredientId = basket.getIngredient().getId();
                List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findTop10ByIngredientIdOrderByRecipeViewsDesc(ingredientId);

                for (RecipeIngredient recipeIngredient : recipeIngredientList){
                    Long recipeId = recipeRepository.findById(recipeIngredient.getRecipe().getId()).get().getId();
                    recipeList1.add(recipeId);
                }
            }
            Set<Long> set = new HashSet<Long>(recipeList1);
            List<Long> newList = new ArrayList<Long>(set);
            List<Recipe> recipeList = new ArrayList<>();
            for (Long num : newList){
                Recipe recipe = recipeRepository.findById(num).get();
                recipeList.add(recipe);
            }

            // ?????? ??????

            if(orderType == 1){
                Collections.sort(recipeList, new IdSortComparator());
            }else{
                Collections.sort(recipeList, new ViewsSortComparator());
            }
            List<Recipe> newRecipeList = subRecipePage.getPage(recipeList, page, size);


            for (Recipe x : newRecipeList){
                Recipe recipe = recipeRepository.findById(x.getId()).get();
                // ????????? ??? ????????????
                RecipeSelected recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipe.getId()).orElse(null);
                boolean bookmark = true;
                if (recipeSelected == null){
                    bookmark = false;
                }

                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }

        return recipeInfoResList;

    }

    @Override
    public List<RecipeInfoRes> getRecipeSelected(String userName){
        // ????????? ??????
        List<RecipeInfoRes> recipeInfoResList = new ArrayList<>();
        User user = userRepository.findByUsername(userName).get();
        List<RecipeSelected> recipeSelectedList = recipeSelectedRepository.findByUserIdOrderByCreatedTimeDesc(user.getId());
        for(RecipeSelected recipeSelected : recipeSelectedList){
            if (recipeSelected.isActiveFlag()){
                Recipe recipe = recipeRepository.findById(recipeSelected.getRecipe().getId()).get();
                RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), recipeSelected.isActiveFlag(), recipe.getViews());
                recipeInfoResList.add(recipeInfoRes);
            }
        }
        return recipeInfoResList;
    }

    @Override
    public RecipeDetailRes getRecipeDetail(Long recipeId, String userName){
        // recipeinfoRes ?????? ??????
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        User user = new User();
        boolean allergyStatus = false;
        boolean selectedStatus = false;
        boolean basketStatus = false;

        if (userName != null){
            user = userRepository.findByUsername(userName).orElse(null);
        }else {
            user = null;
        }

        RecipeSelected recipeSelected = new RecipeSelected();
        if (user == null){
            recipeSelected = null;
        }else{
            recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipeId).orElse(null);
        }

        boolean bookmark;
        if (recipeSelected != null){
            if(recipeSelected.isActiveFlag()){
                bookmark = true;
            }else{
                bookmark = false;
            }
        }else{
            bookmark = false;
        }

        RecipeInfoRes recipeInfoRes = new RecipeInfoRes(recipe.getId(), recipe.getThumbnail(), recipe.getInformation(), recipe.getName(), bookmark, recipe.getViews());

        // ingredientList ??????
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByRecipeId(recipe.getId()).get();
        ;
        if (recipeIngredientList.size() >= 1){
            for (RecipeIngredient recipeIngredient : recipeIngredientList){
                Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId()).get();

                // ?????? ?????? ????????????
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

                // ?????? ?????????
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
                    volatility = Math.round(volatility * 100)/ 100.0;
                }

                Allergy allergy = new Allergy();
                IngredientSelected ingredientSelected = new IngredientSelected();
                Basket basket = new Basket();
                if (user != null){
                    allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
                    ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
                    basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
                }else{
                    allergy = null;
                    ingredientSelected = null;
                    basket = null;
                }

                // ????????? ?????? ????????????
                if (allergy == null){
                    allergyStatus = false;
                }else{
                    allergyStatus = allergy.isActiveFlag();
                }

                // ?????? ??? ?????? ????????????
                if (ingredientSelected == null){
                    selectedStatus = false;
                }else{
                    selectedStatus = ingredientSelected.isActiveFlag();
                }

                // ????????? ?????? ????????????
                if (basket == null){
                    basketStatus = false;
                }else{
                    basketStatus = basket.isActiveFlag();
                }

                // ????????? ?????? ????????????
                HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

                // ?????? ??????
                String name = ingredient.getMidClass();

                if(recipeIngredient.getQuantity().length() != 0){
                    name = name + " (" + recipeIngredient.getQuantity() + ")";
                }

                IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, name, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
                ingredientInfoResList.add(ingredientInfoRes);
            }
        }


        // extraIngredientList ??????
        List<String> extraIngredientList = new ArrayList<>();
        List<IngredientNotneed> ingredientNotNeedList = ingredientNotneedRepository.findAllByRecipeId(recipeId).get();
        for (IngredientNotneed ingredientNotneed : ingredientNotNeedList){
            String name = ingredientNotneed.getIngredient();
            if (ingredientNotneed.getQuantity().length() != 0){
                name = name + "(" + ingredientNotneed.getQuantity() + ")";
            }
            extraIngredientList.add(name);
        }
        List<YoutubeInfoRes> youtubeInfoResList = youtubeChannelSearch.get(recipe.getName());
        RecipeDetailRes recipeDetailRes = new RecipeDetailRes(recipeInfoRes, ingredientInfoResList, extraIngredientList, youtubeInfoResList);
        return recipeDetailRes;
    }

    @Override
    public void recipeSelected(String userName, Long recipeId){
        User user = userRepository.findByUsername(userName).get();

        if(!recipeSelectedRepository.existsRecipeSelectedByUserIdAndRecipeId(user.getId(), recipeId)){
            System.out.println("????????? ?????????: " + recipeId);
            Recipe recipe = recipeRepository.findById(recipeId).get();
            RecipeSelected recipeSelected = new RecipeSelected(user, recipe);
            recipeSelectedRepository.save(recipeSelected);
        }else{
            System.out.println("????????? ?????????: " + recipeId);
            RecipeSelected recipeSelected = recipeSelectedRepository.findByUserIdAndRecipeId(user.getId(), recipeId).get();
            recipeSelected.setActiveFlag(!recipeSelected.isActiveFlag());
            recipeSelectedRepository.save(recipeSelected);
        }
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
            if(basketRepository.existsByUserIdAndIngredientId(user.getId(), ingredient.getId())) continue;
            basketRepository.saveAndFlush(basket);
        }
    }

    @Override
    public void addRecipeViews(Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.setViews(recipe.getViews()+1);
        recipeRepository.saveAndFlush(recipe);
    }
}

// ??????
class IdSortComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe r1, Recipe r2){
        if(r1.getId() > r2.getId()){
            return 1;
        } else if (r1.getId() < r2.getId()) {
            return -1;
        }
        return 0;
    }
}

class ViewsSortComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe r1, Recipe r2){
        if(r1.getViews() > r2.getViews()){
            return 1;
        } else if (r1.getViews() < r2.getViews()) {
            return -1;
        }
        return 0;
    }
}


