package com.gamul.api.service;

import com.gamul.api.response.*;
import com.gamul.common.util.LocationDistance;
import com.gamul.db.entity.*;
import com.gamul.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    EntireRepository entireRepository;
    private final LocationDistance locationDistance;

    @Override
    public List<IngredientInfoRes> getIngredientList(int orderType, int highClassId){
        boolean allergyStatus = false;
        boolean selectedStatus = false;
        boolean basketStatus = false;

        // 대분류 정보 가지고 재료 목록 리스트 가져오기
        List<Ingredient> ingredientList = new ArrayList<>();
        if (highClassId == 0){
            ingredientList = ingredientRepository.findAll();
        }else{
            ingredientList = ingredientRepository.findAllByHighClass(highClassId).get();
        }

        List<IngredientInfoRes> ingredientInfoResList = new ArrayList<>();
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
                volatility = Math.round(volatility * 100)/ 100.0;
            }
            // 대분류 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, ingredient.getMidClass(), day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
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
            int today = 0;
            int yesterday = 0;
            double volatility = 0.0;
            if (day == null){
                day = new Day();
                day.setPrice(0);
                day.setUnit("");
                day.setQuantity(0);
            }else{
                // 가격 변동률
                List<Day> dayList = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
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

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, ingredient.getMidClass(), day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
            ingredientInfoResList.add(ingredientInfoRes);
        }
        return ingredientInfoResList;
    }

    @Override
    public IngredientDetailRes getIngredientDetailInfo(Long ingredientId, String userName){
        // 반환해야할 객체
        IngredientDetailRes ingredientDetailRes = new IngredientDetailRes();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();

        User user = new User();
        if (userName != null){
            user = userRepository.findByUsername(userName).orElse(null);
        }else {
            user = null;
        }

        // 평균 가격
        Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);

        // daily 소매
        List<Day> dayList = dayRepository.findTop7ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        List<PriceInfoRes> dailySo = new ArrayList<>();

        if (dayList.size() > 0){
            for (Day day1 : dayList){
                PriceInfoRes priceInfoRes = new PriceInfoRes(day1.getDatetime(), day1.getPrice());
                priceInfoRes.setUnit(day1.getUnit());
                priceInfoRes.setQuantity(day1.getQuantity());
                dailySo.add(priceInfoRes);
            }
            Collections.reverse(dailySo);
        }

         // daily 소매 테스트 용

//        List<Object[]> entireList = entireRepository.findAllByIngredientIdAndType(ingredient.getId(), 1);
//        List<Object[]> entireList1 = entireRepository.findAllByIngredientIdAndType(ingredient.getId(), 0);
//
//        System.out.println("이거 나와??: " + entireList.size());
//        for(Object[] x : entireList){
//            System.out.println(x[0]);
//            System.out.println(x[1]);
//            System.out.println("-------------------------------");
//        }

        // month 소매
        String unitySo = "";
        int quantitySo = 0;
        List<Month> monthList = monthRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        List<PriceInfoRes> monthSo = new ArrayList<>();
        if (monthList.size() > 0){
            for (Month month1 : monthList){
                PriceInfoRes priceInfoRes = new PriceInfoRes(month1.getDatetime(), month1.getPrice());
                priceInfoRes.setUnit(month1.getUnit());
                unitySo = month1.getUnit();
                priceInfoRes.setQuantity(month1.getQuantity());
                quantitySo = month1.getQuantity();
                monthSo.add(priceInfoRes);
            }
            Collections.reverse(monthSo);
        }

        // year 소매
        List<Year> yearList = yearRepository.findTop10ByIngredientIdOrderByDatetimeDesc(ingredient.getId());
        List<PriceInfoRes> yearSo = new ArrayList<>();
        if (yearList.size() > 0){
            for (Year year1 : yearList){
                PriceInfoRes priceInfoRes = new PriceInfoRes(year1.getDatetime(), year1.getPrice());
                priceInfoRes.setUnit(unitySo);
                priceInfoRes.setQuantity(quantitySo);
                yearSo.add(priceInfoRes);
            }
            Collections.reverse(yearSo);
        }

        // day 도매
        Day dayTmp = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredient.getId(), 0);
        List<Day> dayList1 = new ArrayList<>();
        if (dayTmp != null){
            dayList1 = dayRepository.findTop7ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(ingredient.getId(), 0, dayTmp.getUnit());
        }
        List<PriceInfoRes> dailyDo = new ArrayList<>();
        if (dayList1.size() > 0){
            for (Day day2 : dayList1){
                PriceInfoRes priceInfoRes = new PriceInfoRes(day2.getDatetime(), day2.getPrice());
                priceInfoRes.setUnit(day2.getUnit());
                if (dayList.size() > 0 && dayList.get(0).getUnit().equals(day2.getUnit())){
                    priceInfoRes.setQuantity(dayList.get(0).getQuantity());
                    priceInfoRes.setPrice((day2.getPrice()/day2.getQuantity()) * dayList.get(0).getQuantity());
                }
                else {
                    priceInfoRes.setQuantity(day2.getQuantity());
                }
                dailyDo.add(priceInfoRes);
            }
            Collections.reverse(dailyDo);
        }

        // month 도매
        Month monthTmp = monthRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredient.getId(), 0);
        List<Month> monthList1 = new ArrayList<>();
        if(monthTmp != null){
            monthList1 = monthRepository.findTop10ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(ingredient.getId(), 0, monthTmp.getUnit());
        }
        List<PriceInfoRes> monthDo = new ArrayList<>();
        String unityDo = "";
        int quantitydo = 0;
        if (monthList1.size() > 0){
            for (Month month2 : monthList1){
                PriceInfoRes priceInfoRes = new PriceInfoRes(month2.getDatetime(), month2.getPrice());
                priceInfoRes.setUnit(month2.getUnit());
                unityDo = month2.getUnit();
                priceInfoRes.setQuantity(month2.getQuantity());
                quantitydo = month2.getQuantity();
                monthDo.add(priceInfoRes);
            }
            Collections.reverse(monthDo);
        }

        // year 도매
        List<Year> yearList1 = yearRepository.findTop10ByIngredientIdOrderByDatetimeDesc(ingredient.getId());
        List<PriceInfoRes> yearDo = new ArrayList<>();
        if (yearList1.size() > 0){
            for (Year year2 : yearList1){
                PriceInfoRes priceInfoRes = new PriceInfoRes(year2.getDatetime(), year2.getPrice());
                priceInfoRes.setUnit(unityDo);
                priceInfoRes.setQuantity(quantitydo);
                yearDo.add(priceInfoRes);
            }
            Collections.reverse(yearDo);
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
        double todayvol = 0.0;
        double pastvol = 0.0;
        List<Day> dayList2 = dayRepository.findTop7ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
        if (dayList2.size() > 0){
            todayPrice = dayList2.get(0).getPrice();
            if (dayList2.size() > 1 ){
                beforePrice = dayList2.get(1).getPrice();
            }
            if (dayList2.size() > 2 ) {
                pastPrice = dayList2.get(2).getPrice();
            }
        }
        if (beforePrice > 0){
            todayvol = (todayPrice - beforePrice) * 100.0 / todayPrice;
            todayvol = Math.round(todayvol * 100) / 100.0;
            if (pastPrice > 0){
                pastvol = (beforePrice - pastPrice)  * 100.0 / beforePrice;
                pastvol = Math.round(pastvol * 100) / 100.0;
            }
        }

        PriceTransitionInfoRes priceTransitionInfoRes = new PriceTransitionInfoRes();
        priceTransitionInfoRes.setPrice(todayPrice);
        priceTransitionInfoRes.setBeforePrice(beforePrice);
        priceTransitionInfoRes.setTodayvol(todayvol);
        priceTransitionInfoRes.setPastvol(pastvol);
        priceTransitionInfoRes.setWholesales(wholeSales);
        priceTransitionInfoRes.setRetailsales(retailSales);

        // 가격 변동률
        double volatility = 0.0;
        int today = 0;
        int yesterday = 0;
        if (day == null){
            day = new Day();
            day.setPrice(0);
            day.setUnit("");
            day.setQuantity(0);
        }else{
            // 가격 변동률
            List<Day> dayListV = dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 1);
            if(dayListV.size() == 0){
                today = 0;
                yesterday = 0;
                volatility = 0.0;
            }
            else if (dayListV.size() == 1){
                volatility = 0.0;
            }else {
                today = dayListV.get(0).getPrice();
                yesterday = dayListV.get(1).getPrice();
                volatility = (today - yesterday) * 100.0 / today ;
                volatility = Math.round(volatility * 100)/ 100.0;
            }
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
        IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, ingredient.getMidClass(), day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);

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
            ingredientSelectedRepository.deleteById(ingredientSelected.getId());
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
    public List<OfflineMartInfoRes> getStoreList(Long ingredientId, double southWestLatitude, double southWestLongitude, double northEastLatitude,double northEastLongitude, double latitude, double longitude){

        List<OfflineMartInfoRes> offlineMartInfoResList = new ArrayList<>();
        // 주어진 위경도 내 store 객체
        List<Store> storeList = storeRepository.findByLatitudeAndLongitude(southWestLatitude, northEastLatitude, southWestLongitude, northEastLongitude);

        for (Store store: storeList){
            OfflineMartInfoRes offlineMartInfoRes = new OfflineMartInfoRes();
            int priceStatus = 0;

            Price price = priceRepository.findTop1ByIngredientIdAndStoreIdOrderByDateTimeDesc(ingredientId, store.getId());
            if (price != null){
                priceStatus = price.getPrice();
            }
            offlineMartInfoRes.setStoreId(store.getId());
            offlineMartInfoRes.setName(store.getName());
            offlineMartInfoRes.setPrice(priceStatus);
            offlineMartInfoRes.setLatitude(store.getLatitude());
            offlineMartInfoRes.setLongitude(store.getLongitude());

            // 거리 계산
            double lat1 = latitude;
            double lon1 = longitude;
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
    public List<IngredientInfoRes> getStoreIngredientList(Long storeId){

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
            // 알러지
            boolean allergyStatus = false;
            // 재료 찜
            boolean selectedStatus = false;
            // 바구니
            boolean basketStatus = false;

            // 대분류 객체 가져오기
            HighClass highClass = highClassRepository.findById(ingredient.getHighClass()).get();

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, ingredient.getMidClass(), day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);
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
            if(day == null) day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredient.getId(), 0);

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
                volatility = Math.round(volatility * 100)/ 100.0;
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

            IngredientInfoRes ingredientInfoRes = new IngredientInfoRes(ingredient, ingredient.getMidClass(), day, allergyStatus, selectedStatus, basketStatus, highClass, volatility);

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

