package com.gamul.api.service;

import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.*;
import com.gamul.common.util.NaverShopSearch;
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
            // 가격 객체 가져오기
            Price price = priceRepository.findByIngredientId(ingredient.getId()).get();
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientId(ingredient.getId()).get();
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByIngredientId(ingredient.getId());
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByIngredientId(ingredient.getId());

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, price, allergy, ingredientSelected, basket, highClass);

//            ingredientInfoRes.setVolatility();

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

            // 식재료 객체 가져오기
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

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, price, allergy, ingredientSelected, basket, highClass);

//            ingredientInfoRes.setVolatility();

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

        List<OfflineMartInfoRes> offlineMartInfoResList = new ArrayList<>();
        // 주어진 위경도 내 store 객체
        List<Store> storeList = storeRepository.findByLatitudeAndLongitude(offlineMartInfoReq.getSouthWestLatitude(), offlineMartInfoReq.getNorthEastLatitude(), offlineMartInfoReq.getSouthWestLongitude(), offlineMartInfoReq.getNorthEastLongitude());
        for (Store x: storeList){
            OfflineMartInfoRes offlineMartInfoRes = new OfflineMartInfoRes();
            int price = priceRepository.findByIngredientIdAndStoreId(offlineMartInfoReq.getIngredientId(), x.getId()).get().getPrice();

            offlineMartInfoRes.setStoreId(x.getId());
            offlineMartInfoRes.setName(x.getName());
            offlineMartInfoRes.setPrice(price);
            offlineMartInfoRes.setLatitude(x.getLatitude());
            offlineMartInfoRes.setLongitude(x.getLongitude());

            // 거리 계산
            double lat1 = x.getLatitude();
            double lon1 = x.getLongitude();
            double lat2 = offlineMartInfoRes.getLatitude();
            double lon2 = offlineMartInfoRes.getLongitude();

            double theta = lon1 - lon2;
            double dist = Math.sin(lat1 * Math.PI / 180.0) * Math.sin(lat2 * Math.PI / 180.0) + Math.cos(lat1 * Math.PI / 180.0) * Math.cos(lat2 * Math.PI / 180.0) * Math.cos(theta * Math.PI / 180.0);
            dist = Math.acos(dist);
            dist = (dist * 180) / Math.PI;
            dist = dist * 60 * 1.1515;
            dist = dist * 1609.344;
            int distance = (int) dist;
            offlineMartInfoRes.setDistance(distance);

            offlineMartInfoResList.add(offlineMartInfoRes);
        }
        return offlineMartInfoResList;

    }
    @Override
    public List<IngredientInfoRes> getStoreIngredientList(Long storeId){
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        // 오늘 날짜
        LocalDate today = LocalDate.now();
        // 오늘 상점 id와 오늘 날짜를 가지고 물가 객체 가져오기
        List<Price> priceList = priceRepository.findByStoreIdAndDateTime(storeId, today);

        for (Price x : priceList){
            // 식재료 객체 가져오기
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

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, price, allergy, ingredientSelected, basket, highClass);

//            ingredientInfoRes.setVolatility();

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
            // 식재료 객체 가져오기
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

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, price, allergy, ingredientSelected, basket, highClass);

//            ingredientInfoRes.setVolatility();
            ingredientInfoResList.add(ingredientInfoRes);
        }
        return ingredientInfoResList;
    }

    @Override
    public String getOnlineIngredientInfo(Long ingredientId){
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        String query = ingredient.getMidClass();
        return query;
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

