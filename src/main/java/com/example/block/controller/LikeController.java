package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.LikeConverter;
import com.example.block.domain.mapping.Likes;
import com.example.block.dto.LikeResponseDTO;
import com.example.block.service.AuthService;
import com.example.block.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest/{contestId}")
public class LikeController {
    private final LikeService likeService;
    private final AuthService authService;

    @PostMapping("/applicant/{applicantId}")
    public ApiResponse<LikeResponseDTO.LikeResultDTO> like(@Valid @PathVariable("contestId") Integer contestId,
                                                           @PathVariable("applicantId") Integer applicantId){
        Integer userId = authService.getUserIdFromSecurity();
        likeService.likeUser(userId, applicantId, contestId);
        return processLikeResult(userId, applicantId, contestId);
    }
    private ApiResponse<LikeResponseDTO.LikeResultDTO> processLikeResult(Integer userId, Integer applicantId, Integer contestId) {
        if(likeService.hasMatched(userId, applicantId , contestId)){
            return ApiResponse.onSuccess(LikeConverter.toAlreadyMatchedDTO());
        }
        else {
            if(likeService.hasLiked(userId, applicantId, contestId)) {
                likeService.match(userId, applicantId, contestId);
                return ApiResponse.onSuccess(LikeConverter.toEachLikeDTO(likeService.findEmail(userId), likeService.findEmail(likeService.findUser(applicantId, contestId))));
            }
            else{
                return ApiResponse.onSuccess(LikeConverter.toSingleLikeDTO());
            }
        }
    }
    @PostMapping("/applicant/{applicantId}/delete")
    public void deleteLike(@Valid @PathVariable("contestId") Integer contestId,
                                                                        @PathVariable("applicantId") Integer applicantId){
        Integer userId = authService.getUserIdFromSecurity();
//       내가 삭제시킬 엔티티 생성이후 삭제 로직
        likeService.deleteLike(userId, applicantId, contestId);
//        엔티티에서 응답 dto로 변환이후 응답
    }
}
