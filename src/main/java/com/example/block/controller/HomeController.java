package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.dto.HomeRequestDTO;
import com.example.block.service.HomePageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomePageService homePageService;

    @GetMapping("/")
    @Operation(summary = "홈 화면")
    public ApiResponse<HomeRequestDTO.HomePageRequestDTO> home(@RequestParam Integer userId) {
        HomeRequestDTO.HomePageRequestDTO homePageRequestDTO = homePageService.getHomePageRequestDTO(userId);
        return ApiResponse.onSuccess(homePageRequestDTO);
    }
}
