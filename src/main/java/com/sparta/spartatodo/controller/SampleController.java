package com.sparta.spartatodo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2

@RequestMapping("/api/sample")
public class SampleController {

    @ApiOperation("Sample GET Test")
    @GetMapping("/test")
    public List<String> doTest() {
        return Arrays.asList("AAA", "BBB", "CCC");
    }


}
