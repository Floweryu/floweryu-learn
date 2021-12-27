package com.floweryu.example.validation.controller;

import com.floweryu.example.validation.controller.param.PersonParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangjunfeng
 * @date 2021/11/18 19:50
 */
@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
    @PostMapping("/add")
    public ResponseEntity<PersonParam> savePerson(@Valid @RequestBody PersonParam param) {
        return ResponseEntity.ok().body(param);
    }
}
