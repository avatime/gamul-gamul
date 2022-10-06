package com.gamul.api.controller;

import com.gamul.api.service.DailyPriceService;
import com.gamul.db.entity.RecipeIngredient;
import com.gamul.db.repository.PriceRepository;
import com.gamul.db.repository.RecipeIngredientRepository;
import com.gamul.db.repository.TestRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "테스트 API", tags = {"Test."})
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    TestRepository testRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    DailyPriceService dailyPriceService;

    @Autowired
    RecipeIngredientRepository repository;

    @GetMapping("/{testnum}")
    public ResponseEntity<?> test(@RequestParam Long testnum) throws Exception{
        List<RecipeIngredient> list = repository.findTop10ByIngredientIdOrderByRecipeViewsDesc(testnum);

        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/error")
    public ResponseEntity<?> error() throws Exception {
        int list[] = new int[]{ 11, 25, 34, 54, 57, 61, 63};
        return ResponseEntity.status(200).body(list);
    }
}
