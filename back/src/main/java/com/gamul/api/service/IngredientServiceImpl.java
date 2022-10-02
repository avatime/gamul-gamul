package com.gamul.api.service;

import com.gamul.api.request.IngredientDetailReq;
import com.gamul.api.request.IngredientListReq;
import com.gamul.api.request.OfflineMartDetailInfoReq;
import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.*;
import com.gamul.common.util.LocationDistance;
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
    @Autowired
    MonthRepository monthRepository;
    @Autowired
    YearRepository yearRepository;

    private final LocationDistance locationDistance;

    @Override
    public List<IngredientInfoRes> getIngredientList(IngredientListReq ingredientListReq){
        int highClassId = ingredientListReq.getHighClassId();
        int orderType = ingredientListReq.getOrderType();
        User user = new User();
        boolean allergyStatus = false;
        boolean selectedStatus = false;
        boolean basketStatus = false;
        if (ingredientListReq.getUserName() == ""){
            user = null;
        }else{
            user = userRepository.findByUsername(ingredientListReq.getUserName()).get();
        }

        // 대분류 정보 가지고 재료 목록 리스트 가져오기
        List<Ingredient> ingredientList = ingredientRepository.findAllByHighClass(highClassId).get();
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        // 대분류 객체 가져오기
        HighClass highClass = highClassRepository.findById(highClassId).get();

        for (Ingredient ingredient : ingredientList){

            // 가격 객체 가져오기
            Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            if (day == null){
                day = new Day();
                day.setPrice(0);
                day.setUnit("");
                day.setQuantity(0);
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
                volatility = 100.0;
            }else {
                today = dayList.get(0).getPrice();
                yesterday = dayList.get(1).getPrice();
                volatility = (today - yesterday) * 100.0 / today ;
                volatility = Math.round((volatility * 100) / 100.0);
            }

            // 알러지 객체 가져오기
            if (user != null){
                Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
                if (allergy != null){
                    allergyStatus = allergy.isActiveFlag();
                }
            }

            // 재료 찜 객체 가져오기
            if (user != null){
                IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
                if (ingredientSelected != null){
                    selectedStatus = ingredientSelected.isActiveFlag();
                }
            }

            // 바구니 객체 가져오기
            if (user != null){
                Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
                if (basket != null){
                    basketStatus = basket.isActiveFlag();
                }
            }

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
            ingredientInfoResList.add(ingredientInfoRes);

        }

        // 사전 순서
        if (orderType == 1){
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

        return ingredientInfoResList;
    }

    @Override
    public List<IngredientInfoRes> getIngredientSelectedList(String userName){
        // 사용자 정보
        User user = userRepository.findByUsername(userName).get();
        // 사용자 pk로 찜한 재료 객체들 가져오기
        List<IngredientSelected> ingredientSelectedList = ingredientSelectedRepository.findByUserIdOrderByCreatedTimeDesc(user.getId());
        // response dto 생성 해서 값 넣기
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        for (IngredientSelected x : ingredientSelectedList){

            // 식재료 객체 가져오기
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();

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
            // 알러지 객체 가져오기
            boolean allergyStatus;
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            if (allergy == null){
                allergyStatus = false;
            }else{
                allergyStatus = allergy.isActiveFlag();
            }
            // 재료 찜 객체 가져오기
            boolean selectedStatus;
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            if (ingredientSelected == null){
                selectedStatus = false;
            }else{
                selectedStatus = ingredientSelected.isActiveFlag();
            }
            // 바구니 객체 가져오기
            boolean basketStatus;
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
            if (basket == null){
                basketStatus = false;
            }else{
                basketStatus = basket.isActiveFlag();
            }
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
            ingredientInfoResList.add(ingredientInfoRes);
        }
        return ingredientInfoResList;
    }

    @Override
    public IngredientDetailRes getIngredientDetailInfo(IngredientDetailReq ingredientDetailReq){
        // 반환해야할 객체
        IngredientDetailRes ingredientDetailRes = new IngredientDetailRes();
        Ingredient ingredient = ingredientRepository.findById(ingredientDetailReq.getIngredientId()).get();
        User user = new User();
        if (ingredientDetailReq.getUserName() != ""){
            user = userRepository.findByUsername(ingredientDetailReq.getUserName()).orElse(null);
        }else {
            user = null;
        }

        // 평균 가격
        Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);

        // daily 소매
        List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        List<PriceInfoRes> dailySo = new ArrayList<>();
        for (Day day1 : dayList){
            PriceInfoRes priceInfoRes = new PriceInfoRes(day1.getDatetime(), day1.getPrice());
            priceInfoRes.setUnit(day1.getUnit());
            priceInfoRes.setQuantity(day1.getQuantity());
            dailySo.add(priceInfoRes);
        }
        // month 소매
        List<Month> monthList = monthRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        List<PriceInfoRes> monthSo = new ArrayList<>();
        for (Month month1 : monthList){
            PriceInfoRes priceInfoRes = new PriceInfoRes(month1.getDatetime(), month1.getPrice());
            priceInfoRes.setUnit(month1.getUnit());
            priceInfoRes.setQuantity(month1.getQuantity());
            monthSo.add(priceInfoRes);
        }
        // year 소매
        List<Year> yearList = yearRepository.findTop10ByIngredientIdOrderByDatetimeDesc(ingredient.getId());
        List<PriceInfoRes> yearSo = new ArrayList<>();
        for (Year year1 : yearList){
            PriceInfoRes priceInfoRes = new PriceInfoRes(year1.getDatetime(), year1.getPrice());
            priceInfoRes.setUnit(day.getUnit());
            priceInfoRes.setQuantity(day.getQuantity());
            yearSo.add(priceInfoRes);
        }

        // day 도매
        List<Day> dayList1 = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 0);
        List<PriceInfoRes> dailyDo = new ArrayList<>();
        for (Day day2 : dayList1){
            PriceInfoRes priceInfoRes = new PriceInfoRes(day2.getDatetime(), day2.getPrice());
            priceInfoRes.setUnit(day2.getUnit());
            priceInfoRes.setQuantity(day2.getQuantity());
            dailyDo.add(priceInfoRes);
        }
        // month 도매
        List<Month> monthList1 = monthRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 0);
        List<PriceInfoRes> monthDo = new ArrayList<>();
        for (Month month2 : monthList1){
            PriceInfoRes priceInfoRes = new PriceInfoRes(month2.getDatetime(), month2.getPrice());
            priceInfoRes.setUnit(month2.getUnit());
            priceInfoRes.setQuantity(month2.getQuantity());
            monthDo.add(priceInfoRes);
        }
        // year 도매
        List<Year> yearList1 = yearRepository.findTop10ByIngredientIdOrderByDatetimeDesc(ingredient.getId());
        List<PriceInfoRes> yearDo = new ArrayList<>();
        for (Year year2 : yearList1){
            PriceInfoRes priceInfoRes = new PriceInfoRes(year2.getDatetime(), year2.getPrice());
            priceInfoRes.setUnit(day.getUnit());
            priceInfoRes.setQuantity(day.getQuantity());
            yearDo.add(priceInfoRes);
        }
        SaleInfoRes wholeSales = new SaleInfoRes();
        wholeSales.setDaily(dailyDo);
        wholeSales.setMonthly(monthDo);
        wholeSales.setYearly(yearDo);
        SaleInfoRes retailSales = new SaleInfoRes();
        retailSales.setDaily(dailySo);
        retailSales.setMonthly(monthSo);
        retailSales.setYearly(yearSo);
        int beforePrice = 0;
        int todayPrice = 0;
        int pastPrice = 0;
        List<Day> dayList2 = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        if (dayList2.get(0) != null){
            todayPrice = dayList2.get(0).getPrice();
        }
        if (dayList2.get(1) != null){
            beforePrice = dayList2.get(1).getPrice();
        }
        if (dayList2.get(2) != null) {
            pastPrice = dayList2.get(2).getPrice();
        }

        double todayvol = (todayPrice - beforePrice) * 100.0 / todayPrice;
        todayvol = Math.round(todayvol * 100) / 100.0;
        double pastvol = (beforePrice - pastPrice)  * 100.0 / beforePrice;
        pastvol = Math.round(pastvol * 100) / 100.0;

        PriceTransitionInfoRes priceTransitionInfoRes = new PriceTransitionInfoRes();
        priceTransitionInfoRes.setPrice(todayPrice);
        priceTransitionInfoRes.setBeforePrice(beforePrice);
        priceTransitionInfoRes.setTodayvol(todayvol);
        priceTransitionInfoRes.setPastvol(pastvol);
        priceTransitionInfoRes.setWholesales(wholeSales);
        priceTransitionInfoRes.setRetailsales(retailSales);

        // 가격 변동률
        double volatility = 0.0;
        if (day == null){
            day = new Day();
            day.setPrice(0);
            day.setUnit("");
            day.setQuantity(0);
        }else{
            // 가격 변동률
            List<Day> dayListV = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            int today = dayListV.get(0).getPrice();
            int yesterday = dayListV.get(1).getPrice();
            volatility = (today - yesterday) * 100.0 / today ;
            volatility = Math.round(volatility * 100) / 100.0;
        }

        // 알러지
        boolean allergyStatus;
        // 재료 찜
        boolean selectedStatus;
        // 바구니
        boolean basketStatus;
        if (user == null){
            allergyStatus = false;
            selectedStatus = false;
            basketStatus = false;
        }else{
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
            // 알러지
            if (allergy == null){
                allergyStatus = false;
            }else{
                allergyStatus = allergy.isActiveFlag();
            }
            // 재료 찜
            if (ingredientSelected == null){
                selectedStatus = false;
            }else{
                selectedStatus = ingredientSelected.isActiveFlag();
            }
            // 바구니
            if (basket == null){
                basketStatus = false;
            }else{
                basketStatus = basket.isActiveFlag();
            }
        }
        HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();
        IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);

        ingredientDetailRes.setIngredientInfo(ingredientInfoRes);
        ingredientDetailRes.setPriceTransitionInfo(priceTransitionInfoRes);
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
            basketRepository.deleteById(basket.getId());
        }
    }

    @Override
    public List<OfflineMartInfoRes> getStoreList(OfflineMartInfoReq offlineMartInfoReq){

        List<OfflineMartInfoRes> offlineMartInfoResList = new ArrayList<>();
        // 주어진 위경도 내 store 객체
        List<Store> storeList = storeRepository.findByLatitudeAndLongitude(offlineMartInfoReq.getSouthWestLatitude(), offlineMartInfoReq.getNorthEastLatitude(), offlineMartInfoReq.getSouthWestLongitude(), offlineMartInfoReq.getNorthEastLongitude());

        for (Store store: storeList){
            OfflineMartInfoRes offlineMartInfoRes = new OfflineMartInfoRes();
            int priceStatus = 0;

            Price price = priceRepository.findTop1ByIngredientIdAndStoreIdOrderByDateTimeDesc(offlineMartInfoReq.getIngredientId(), store.getId());
            if (price != null){
                priceStatus = price.getPrice();
            }
            offlineMartInfoRes.setStoreId(store.getId());
            offlineMartInfoRes.setName(store.getName());
            offlineMartInfoRes.setPrice(priceStatus);
            offlineMartInfoRes.setLatitude(store.getLatitude());
            offlineMartInfoRes.setLongitude(store.getLongitude());

            // 거리 계산
            double lat1 = offlineMartInfoReq.getLatitude();
            double lon1 = offlineMartInfoReq.getLongitude();
            double lat2 = store.getLatitude();
            double lon2 = store.getLongitude();

            int distance = (int) locationDistance.distance(lat1, lon1, lat2, lon2, "meter");


            offlineMartInfoRes.setDistance(distance);

            if (priceStatus != 0){
                offlineMartInfoResList.add(offlineMartInfoRes);
            }
        }

        Collections.sort(offlineMartInfoResList, new DistanceSortComparator());

        return offlineMartInfoResList;

    }
    @Override
    public List<IngredientInfoRes> getStoreIngredientList(OfflineMartDetailInfoReq offlineMartDetailInfoReq){
        Long storeId = offlineMartDetailInfoReq.getStoreId();
        User user = userRepository.findByUsername(offlineMartDetailInfoReq.getUserName()).orElse(null);

        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();

        // 최근 날짜 가져오기
        String dateTime = priceRepository.findTop1ByStoreIdOrderByDateTimeDesc(storeId).getDateTime();

        // 상점 id를 가지고 물가 객체 가져오기
        List<Price> priceList = priceRepository.findByStoreIdAndDateTime(storeId, dateTime);
        for (Price price : priceList){
            // 식재료 객체 가져오기
            Ingredient ingredient = ingredientRepository.findById(price.getIngredient().getId()).get();

            // 가격 객체 가져오기
            Price priceStatus = priceRepository.findTop1ByIngredientIdAndStoreIdOrderByDateTimeDesc(ingredient.getId(), storeId);
            Day day = new Day();
            day.setPrice(0);
            day.setUnit("");
            day.setQuantity(0);

            // 가격 변동률
            double volatility = 0.0;
            if (priceStatus == null){
                priceStatus = new Price();
                priceStatus.setPrice(0);
                priceStatus.setUnit("");
                priceStatus.setQuantity(0.0);
            }else{
                // 가격 변동률
                List<Price> priceList1 = priceRepository.findTop10ByIngredientIdAndStoreIdOrderByDateTimeDesc(ingredient.getId(), storeId);
                int today = priceList1.get(0).getPrice();

                int yesterday = priceList1.get(1).getPrice();
                volatility = (today - yesterday) * 100.0 / today;
                volatility = Math.round((volatility * 100) / 100.0);
            }
            // 알러지
            boolean allergyStatus;
            // 재료 찜
            boolean selectedStatus;
            // 바구니
            boolean basketStatus;
            if (user == null){
                allergyStatus = false;
                selectedStatus = false;
                basketStatus = false;
            }else{
                Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
                IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
                Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
                // 알러지
                if (allergy == null){
                    allergyStatus = false;
                }else{
                    allergyStatus = allergy.isActiveFlag();
                }
                // 재료 찜
                if (ingredientSelected == null){
                    selectedStatus = false;
                }else{
                    selectedStatus = ingredientSelected.isActiveFlag();
                }
                // 바구니
                if (basket == null){
                    basketStatus = false;
                }else{
                    basketStatus = basket.isActiveFlag();
                }
            }

            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
            ingredientInfoRes.setPrice(priceStatus.getPrice());
            ingredientInfoRes.setUnit(price.getUnit());
            ingredientInfoRes.setQuantity(price.getQuantity());

            ingredientInfoResList.add(ingredientInfoRes);
        }

        Collections.sort(ingredientInfoResList, new NameSortComparator());

        return ingredientInfoResList;
    }

    @Override
    public List<IngredientInfoRes> getBasketList(String userName){
        User user = userRepository.findByUsername(userName).get();
        List<Basket> basketList = basketRepository.findByUserIdOrderByCreatedTimeDesc(user.getId());
        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
        for (Basket x : basketList){

            // 식재료 객체 가져오기
            Ingredient ingredient = ingredientRepository.findById(x.getIngredient().getId()).get();

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
                volatility = (today - yesterday) * 100.0 / today;
                volatility = Math.round((volatility * 100) / 100.0);
            }
            // 알러지 객체 가져오기
            boolean allergyStatus;
            Allergy allergy = allergyRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId()).orElse(null);
            if (allergy == null){
                allergyStatus = false;
            }else{
                allergyStatus = allergy.isActiveFlag();
            }
            // 재료 찜 객체 가져오기
            boolean selectedStatus;
            IngredientSelected ingredientSelected = ingredientSelectedRepository.findByUserIdAndIngredientId(user.getId(), ingredient.getId()).orElse(null);
            if (ingredientSelected == null){
                selectedStatus = false;
            }else{
                selectedStatus = ingredientSelected.isActiveFlag();
            }
            // 바구니 객체 가져오기
            boolean basketStatus;
            Basket basket = basketRepository.findByUserIdAndIngredientId(user.getId(),ingredient.getId()).orElse(null);
            if (basket == null){
                basketStatus = false;
            }else{
                basketStatus = basket.isActiveFlag();
            }
            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);

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

class DistanceSortComparator implements Comparator<OfflineMartInfoRes>{
    @Override
    public int compare(OfflineMartInfoRes o1, OfflineMartInfoRes o2){
        if (o1.getDistance() > o2.getDistance()){
            return 1;
        } else if (o1.getDistance() < o2.getDistance()){
            return -1;
        }
        return 0;
    }
}

