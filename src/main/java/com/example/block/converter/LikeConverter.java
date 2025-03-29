package com.example.block.converter;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Likes;
import com.example.block.domain.mapping.Matches;
import com.example.block.dto.LikeResponseDTO;

public class LikeConverter {

//    요청형 DTO
    public static Likes likeDTO(User user1, User user2, Contest contest){
        return Likes.builder()
                .userLiker(user1)
                .userLiked(user2)
                .contest(contest)
                .build();
    }
    public static Matches matchDTO(User user1, User user2, Contest contest){
        return Matches.builder()
                .user1(user1)
                .user2(user2)
                .contest(contest)
                .build();
    }

//    반응형 DTO
    public static LikeResponseDTO.LikeResultDTO toAlreadyMatchedDTO(){
        return LikeResponseDTO.LikeResultDTO.builder()
                .user1Email(null)
                .user2Email(null)
                .message("Already Matched!")
                .build();
    }
    public static LikeResponseDTO.LikeResultDTO toSingleLikeDTO(){
        return LikeResponseDTO.LikeResultDTO.builder()
                .user1Email(null)
                .user2Email(null)
                .message("SingleLike")
                .build();
    }
    public static LikeResponseDTO.LikeResultDTO toEachLikeDTO(String user1Email, String user2Email){
        return LikeResponseDTO.LikeResultDTO.builder()
                .user1Email(user1Email)
                .user2Email(user2Email)
                .message("Matching!")
                .build();
    }
    public static LikeResponseDTO.DeleteLikeResultDTO toDeleteLikeDTO(Likes deletedLike){
        return LikeResponseDTO.DeleteLikeResultDTO.builder()
                .userLiker(deletedLike.getUserLiker())
                .userLiked(deletedLike.getUserLiked())
                .contest(deletedLike.getContest())
                .message("좋아요 취소!")
                .build();
    }
}
