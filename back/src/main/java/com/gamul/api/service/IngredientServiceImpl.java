package com.gamul.api.service;

import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.*;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientSelectedRepository ingredientSelectedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    AllergyRepository allergyRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    HighClassRepository highClassRepository;
    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<IngredientInfoRes> getIngredientList(int orderType, int highClassId){
        // 대분류 정보 가지고 재료 목록 리스트 가져오기
        List<Ingredient> ingredientList = ingredientRepository.findAllByHighClass(highClassId).get();
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        // 대분류 객체 가져오기
        HighClass highClass = highClassRepository.findById(highClassId);

        for (Ingredient ingredient : ingredientList){
            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes();
            // 가격 객체 가져오기
            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByIngredientId(ingredient.getId());

            ingredientInfoRes.setIngredientId(ingredient.getId());
            ingredientInfoRes.setName(ingredient.getMidClass());
            ingredientInfoRes.setPrice(price.getPrice()); // 지금은 그냥 각각
            ingredientInfoRes.setUnit(price.getUnit());
            ingredientInfoRes.setQuantity(price.getQuantity());
//            ingredientInfoRes.setVolatility();
            ingredientInfoRes.setAllergy(allergy.isActiveFlag());
            ingredientInfoRes.setFavorite(ingredientSelected.isActiveFlag());
            ingredientInfoRes.setBasket(basket.isActiveFlag());
            ingredientInfoRes.setHighClassId(ingredient.getHighClass());
            ingredientInfoRes.setHighClassName(highClass.getName());
            ingredientInfoRes.setViews(ingredient.getViews());

            // 추가하기
            ingredientInfoResList.add(ingredientInfoRes);
        }
        // 사전 순서
        if (orderType == 1){
//            Collections.sort(ingredientInfoResList, new NameSortComparator());
        }
        // 조회 큰 순서
        else if(orderType == 2){
            Collections.sort(ingredientInfoResList, new ViewSortComparator().reversed());
        }
        // 변동폭 절대값 큰 순서
        else if(orderType == 3){
            Collections.sort(ingredientInfoResList, new VolatilitySortComparator().reversed());
        }
        // 가격 싼 순서
        else if(orderType == 4){
            Collections.sort(ingredientInfoResList, new PriceSortComparator());
        }

        return ingredientInfoResList;
    }

    @Override
    public List<IngredientInfoRes> getIngredientSelectedList(String userName){
        // 사용자 정보
        User user = userRepository.findByUsername(userName).get();
        // 사용자 pk로 찜한 재료 객체들 가져오기
        List<IngredientSelected> ingredientSelectedList = ingredientSelectedRepository.findAllByUserId(user.getId());
        // response dto 생성 해서 값 넣기
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        for (IngredientSelected x : ingredientSelectedList){

            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes();

            // 가격 객체 가져오기
            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByIngredientId(ingredient.getId());
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            ingredientInfoRes.setIngredientId(ingredient.getId());
            ingredientInfoRes.setName(ingredient.getMidClass());
            ingredientInfoRes.setPrice(price.getPrice()); // 지금은 그냥 각각
            ingredientInfoRes.setUnit(price.getUnit());
            ingredientInfoRes.setQuantity(price.getQuantity());
//            ingredientInfoRes.setVolatility();
            ingredientInfoRes.setAllergy(allergy.isActiveFlag());
            ingredientInfoRes.setFavorite(ingredientSelected.isActiveFlag());
            ingredientInfoRes.setBasket(basket.isActiveFlag());
            ingredientInfoRes.setHighClassId(ingredient.getHighClass());
            ingredientInfoRes.setHighClassName(highClass.getName());
            ingredientInfoRes.setViews(ingredient.getViews());

            ingredientInfoResList.add(ingredientInfoRes);
        }
        return ingredientInfoResList;
    }

    @Override
    public IngredientDetailRes getIngredientDetailInfo(Long ingredientId){
        // 반환해야할 객체
        IngredientDetailRes ingredientDetailRes = new IngredientDetailRes();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        PriceTransitionInfoRes priceTransitionInfoRes = new PriceTransitionInfoRes();
        OnlineMartInfoRes onlineMartInfoRes = new OnlineMartInfoRes();


//        ingredientDetailRes.setOnlineMartInfo(onlineMartInfoRes);
        ingredientDetailRes.setViews(ingredient.getViews());
        return ingredientDetailRes;
    }

    @Override
    public List<HighClass> getHighClassList(){
        List<HighClass> highClassNameRes = highClassRepository.findAll();
        return highClassNameRes;
    }

    @Override
    public void ingredientSelected(String userName, Long ingredientId){
        User user = userRepository.findByUsername(userName).get();
        IngredientSelected ingredientSelected= ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredientId);
        ingredientSelected.setActiveFlag(!ingredientSelected.isActiveFlag());
    }

    @Override
    public void ingredientBasket(String userName, Long ingredientId){
        User user = userRepository.findByUsername(userName).get();
        Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(), ingredientId);
        basket.setActiveFlag(!basket.isActiveFlag());
    }

    @Override
    public List<OfflineMartInfoRes> getStoreList(OfflineMartInfoReq offlineMartInfoReq){
        List<OfflineMartInfoRes> offlineMartInfoRes = new ArrayList<>();
        return offlineMartInfoRes;
    }
    @Override
    public List<IngredientInfoRes> getStoreIngredientList(Long storeId){
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        // 오늘 날짜
        LocalDate today = LocalDate.now();
        // 오늘 상점 id와 오늘 날짜를 가지고 물가 객체 가져오기
        List<Price> priceList = priceRepository.findByStoreIdAndDateTime(storeId, today);

        for (Price x : priceList){
            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes();
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();

            // 가격 객체 가져오기
            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByIngredientId(ingredient.getId());
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            ingredientInfoRes.setIngredientId(ingredient.getId());
            ingredientInfoRes.setName(ingredient.getMidClass());
            ingredientInfoRes.setPrice(price.getPrice()); // 지금은 그냥 각각
            ingredientInfoRes.setUnit(price.getUnit());
            ingredientInfoRes.setQuantity(price.getQuantity());
//            ingredientInfoRes.setVolatility();
            ingredientInfoRes.setAllergy(allergy.isActiveFlag());
            ingredientInfoRes.setFavorite(ingredientSelected.isActiveFlag());
            ingredientInfoRes.setBasket(basket.isActiveFlag());
            ingredientInfoRes.setHighClassId(ingredient.getHighClass());
            ingredientInfoRes.setHighClassName(highClass.getName());
            ingredientInfoRes.setViews(ingredient.getViews());

            ingredientInfoResList.add(ingredientInfoRes);
        }

        return ingredientInfoResList;
    }

    @Override
    public List<IngredientInfoRes> getBasketList(String userName){
        User user = userRepository.findByUsername(userName).get();
        List<Basket> basketList = basketRepository.findAllByUserId(user.getId());
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        for (Basket x : basketList){
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes();

            // 가격 객체 가져오기
            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByIngredientId(ingredient.getId());
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            ingredientInfoRes.setIngredientId(ingredient.getId());
            ingredientInfoRes.setName(ingredient.getMidClass());
            ingredientInfoRes.setPrice(price.getPrice()); // 지금은 그냥 각각
            ingredientInfoRes.setUnit(price.getUnit());
            ingredientInfoRes.setQuantity(price.getQuantity());
//            ingredientInfoRes.setVolatility();
            ingredientInfoRes.setAllergy(allergy.isActiveFlag());
            ingredientInfoRes.setFavorite(ingredientSelected.isActiveFlag());
            ingredientInfoRes.setBasket(basket.isActiveFlag());
            ingredientInfoRes.setHighClassId(ingredient.getHighClass());
            ingredientInfoRes.setHighClassName(highClass.getName());
            ingredientInfoRes.setViews(ingredient.getViews());
        }
        return ingredientInfoResList;
    }
}


// 정렬
class NameSortComparator implements Comparator<IngredientInfoRes>{

    @Override
    public int compare(IngredientInfoRes o1, IngredientInfoRes o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class ViewSortComparator implements Comparator<IngredientInfoRes>{

    @Override
    public int compare(IngredientInfoRes o1, IngredientInfoRes o2) {
        if (o1.getViews() > o2.getViews()){
            return 1;
        } else if (o1.getViews() < o2.getViews()) {
            return -1;
        }
        return 0;
    }
}

class VolatilitySortComparator implements Comparator<IngredientInfoRes>{

    @Override
    public int compare(IngredientInfoRes o1, IngredientInfoRes o2) {
        if (Math.abs(o1.getVolatility()) > Math.abs(o2.getVolatility())){
            return 1;
        } else if (Math.abs(o1.getVolatility()) < Math.abs(o2.getVolatility())) {
            return -1;
        }
        return 0;
    }
}

class PriceSortComparator implements Comparator<IngredientInfoRes>{

    @Override
    public int compare(IngredientInfoRes o1, IngredientInfoRes o2) {
        if (o1.getPrice() > o2.getPrice()){
            return 1;
        } else if (o1.getPrice() < o2.getPrice()) {
            return -1;
        }
        return 0;
    }
}

