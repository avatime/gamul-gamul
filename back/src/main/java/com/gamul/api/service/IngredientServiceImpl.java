package com.gamul.api.service;

import com.gamul.api.request.IngredientListReq;
import com.gamul.api.request.OfflineMartDetailInfoReq;
import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.*;
import com.gamul.common.util.NaverShopSearch;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;

@Service("ingredientService")
@RequiredArgsConstructor
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
    @Autowired
    DayRepository dayRepository;

    @Override
    public List<IngredientInfoRes> getIngredientList(IngredientListReq ingredientListReq){
        Long highClassId = ingredientListReq.getHighClassId();
        int orderType = ingredientListReq.getOrderType();
        User user = userRepository.findByUsername(ingredientListReq.getUserName()).orElse(null);
        System.out.println("user 있나연~: " + user.getUsername());

        // 대분류 정보 가지고 재료 목록 리스트 가져오기
        List<Ingredient> ingredientList = ingredientRepository.findAllByHighClass(highClassId).orElse(null);
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        // 대분류 객체 가져오기
        HighClass highClass = highClassRepository.findById(highClassId.intValue()).get();

        for (Ingredient ingredient : ingredientList){
            System.out.println("for 문 돌아?: " + ingredient.getMidClass());
            // 가격 객체 가져오기
            Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            System.out.println("재료: " + ingredient.getMidClass());
            System.out.println("가격: " + day.getPrice());
            System.out.println("대분류: "+ highClass.getName());
            // 가격 변동률
            List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            int today = dayList.get(0).getPrice();
            int yesterday = dayList.get(1).getPrice();
            int volatility = (today - yesterday) / 100;

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, highClass, volatility);
            System.out.println("처음: " + ingredientInfoRes);
            if (user != null){
                // 알러지 객체 가져오기
                Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
                System.out.println("알러지 문제야? " + allergy);
                // 재료 찜 객체 가져오기
                IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
                System.out.println("재료찜 문제야? " + ingredientSelected);
                // 바구니 객체 가져오기
                Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);;
                System.out.println("베스킷 문제야? " + basket);
                if (allergy != null & allergy.isActiveFlag() == true){
                    ingredientInfoRes.setAllergy(true);
                } else if (ingredientSelected != null & ingredientSelected.isActiveFlag() == true){
                    ingredientInfoRes.setFavorite(true);
                } else if (basket != null & basket.isActiveFlag() == true){
                    ingredientInfoRes.setBasket(true);
                }
            }

            System.out.println("과연?: " + ingredientInfoRes);

            // 추가하기
            ingredientInfoResList.add(ingredientInfoRes);
        }
        System.out.println("중간: " + ingredientList);
        // 사전 순서
        if (orderType == 1){
            System.out.println("정렬: " + ingredientList);
            Collections.sort(ingredientInfoResList, new NameSortComparator());
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
        System.out.println("반환값나와?: "+ ingredientList);
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
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).orElse(null);
            // 가격 객체 가져오기
            Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);;
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            // 가격 변동률
            List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            int today = dayList.get(0).getPrice();
            int yesterday = dayList.get(1).getPrice();
            int volatility = (today - yesterday) / 100;

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, highClass, volatility);
            System.out.println("얘조 ㅁ나와라 제발!!!!" + ingredientInfoRes);
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
        // 처음 버튼 눌렀음
        if (!ingredientSelectedRepository.existsByUserIdAndIngredientId(user.getId(), ingredientId)){
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
            IngredientSelected ingredientSelected = new IngredientSelected(user, ingredient);
            ingredientSelectedRepository.saveAndFlush(ingredientSelected);
        } else{
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredientId).get();
            ingredientSelected.setActiveFlag(!ingredientSelected.isActiveFlag());
            ingredientSelectedRepository.saveAndFlush(ingredientSelected);
        }
    }

    @Override
    public void ingredientBasket(String userName, Long ingredientId){
        User user = userRepository.findByUsername(userName).get();

        if (!basketRepository.existsByUserIdAndIngredientId(user.getId(), ingredientId)){
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
            Basket basket = new Basket(user, ingredient);
            basketRepository.saveAndFlush(basket);
        }else{
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(), ingredientId).get();
            basket.setActiveFlag(!basket.isActiveFlag());
            basketRepository.saveAndFlush(basket);
        }
    }

    @Override
    public List<OfflineMartInfoRes> getStoreList(OfflineMartInfoReq offlineMartInfoReq){

        List<OfflineMartInfoRes> offlineMartInfoResList = new ArrayList<>();
        // 주어진 위경도 내 store 객체
        List<Store> storeList = storeRepository.findByLatitudeAndLongitude(offlineMartInfoReq.getSouthWestLatitude(), offlineMartInfoReq.getNorthEastLatitude(), offlineMartInfoReq.getSouthWestLongitude(), offlineMartInfoReq.getNorthEastLongitude());
        for (Store x: storeList){
            System.out.println("store : " + x);
            System.out.println("store name: " + x.getName() + " " + x.getId());
            OfflineMartInfoRes offlineMartInfoRes = new OfflineMartInfoRes();
            int price = priceRepository.findByIngredientIdAndStoreId(offlineMartInfoReq.getIngredientId(), x.getId()).orElse(null).getPrice();
            System.out.println("price: " + price);
            offlineMartInfoRes.setStoreId(x.getId());
            offlineMartInfoRes.setName(x.getName());
            offlineMartInfoRes.setPrice(price);
            offlineMartInfoRes.setLatitude(x.getLatitude());
            offlineMartInfoRes.setLongitude(x.getLongitude());
            System.out.println("이건 있음?? " + offlineMartInfoRes);
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
            System.out.println("오프라인마트: " + offlineMartInfoRes);

            offlineMartInfoResList.add(offlineMartInfoRes);
        }
        return offlineMartInfoResList;

    }
    @Override
    public List<IngredientInfoRes> getStoreIngredientList(OfflineMartDetailInfoReq offlineMartDetailInfoReq){
        Long storeId = offlineMartDetailInfoReq.getStoreId();
        User user = userRepository.findByUsername(offlineMartDetailInfoReq.getUserName()).orElse(null);

        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        // 오늘 날짜
        LocalDate today = LocalDate.now();
        // 오늘 상점 id와 오늘 날짜를 가지고 물가 객체 가져오기
        List<Price> priceList = priceRepository.findByStoreIdAndDateTime(storeId, today);

        for (Price x : priceList){
            // 식재료 객체 가져오기
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();
            // 가격 객체 가져오기
            Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            // 가격 변동률
            List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            int today1 = dayList.get(0).getPrice();
            int yesterday = dayList.get(1).getPrice();
            int volatility = (today1 - yesterday) / 100;

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, highClass, volatility);

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
            Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            // 알러지 객체 가져오기
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            // 재료 찜 객체 가져오기
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            // 바구니 객체 가져오기
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            // 가격 변동률
            List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            int today = dayList.get(0).getPrice();
            int yesterday = dayList.get(1).getPrice();
            int volatility = (today - yesterday) / 100;

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, highClass, volatility);

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

    @Override
    public void addIngredientViews(Long ingredientId){
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        ingredient.setViews(ingredient.getViews()+1);
        ingredientRepository.saveAndFlush(ingredient);
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

