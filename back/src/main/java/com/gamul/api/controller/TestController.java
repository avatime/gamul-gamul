package com.gamul.api.controller;

import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.db.entity.Price;
import com.gamul.db.repository.PriceRepository;
import com.gamul.db.repository.TestRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "테스트 API", tags = {"Test."})
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    TestRepository testRepository;

    @Autowired
    PriceRepository priceRepository;

    @GetMapping("/{testnum}")
    public ResponseEntity<BaseResponseBody> test(@RequestParam Long testnum){
        Price price = priceRepository.findById(testnum).get();

        return ResponseEntity.ok(BaseResponseBody.of(200, price.getDateTime().toString()));
    }
}
