package com.example.block.service;

import com.example.block.converter.PointConverter;
import com.example.block.converter.TransactionReviewConverter;
import com.example.block.domain.PointDetail;
import com.example.block.domain.User;
import com.example.block.domain.enums.PointType;
import com.example.block.domain.mapping.Review;
import com.example.block.domain.mapping.TransactionReview;
import com.example.block.dto.PointRequestDTO;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import com.example.block.repository.PointDetailRepository;
import com.example.block.repository.ReviewRepository;
import com.example.block.repository.TransactionReviewRepository;
import com.example.block.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {

    private final UserRepository userRepository;
    private final PointDetailRepository pointDetailRepository;

    @Transactional
    public PointDetail chargePoint(Integer userId,PointRequestDTO.PointCharge request) {
        //포인트 충전
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        //pointDetail 추가
        PointDetail pointDetail = PointConverter.toPointDetail(request.getPoint(),PointType.EARN,request.getReason());
        pointDetail.setUser(user);

        updateMyPoint(pointDetail);

        return pointDetail;
    }

    @Transactional
    public PointDetail usePoint(Integer userId, PointRequestDTO.PointUse request, PointType pointType) {
        //포인트 사용
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));
        //pointDetail 추가 -> 포인트 사용은 -로 표시
        PointDetail pointDetail = PointConverter.toPointDetail(-request.getPoint(),pointType,request.getReason());
        pointDetail.setUser(user);

        updateMyPoint(pointDetail);

        return pointDetail;
    }

    @Transactional
    public Long getMyPoint(Integer userId) {
        //내 포인트 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        return user.getPoint();
    }

    public List<PointDetail> getMyPointDetail(Integer userId) {
        //내 포인트 상세 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, 5);

        return pointDetailRepository.findTopByUserIdOrderByCreatedAtDesc(userId,pageable);
    }

    @Transactional
    public void updateMyPoint(PointDetail pointDetail) {
        userRepository.calculateUserPoints(pointDetail.getUser().getId(),pointDetail.getAmount());
    }

}
