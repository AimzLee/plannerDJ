package kr.co.team.planner.service;

import kr.co.team.planner.dto.ReviewDTO;
import kr.co.team.planner.entity.Member;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.Review;

import java.util.List;

public interface ReviewService {
    //영화에 해당하는 리뷰를 가져오기
    List<ReviewDTO> getList(Long mno);

    //리뷰 등록
    Long register(ReviewDTO reviewDTO);

    //리뷰 삭제
    void remove(Long rnum);

    //리뷰 수정
    void modify(ReviewDTO reviewDTO);

    default Review dtoToEntity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewnum())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .plan(Plan.builder().pno(reviewDTO.getPno()).build())
                .member(Member.builder().email(reviewDTO.getEmail()).build())
                .build();

        return review;
    }

    default ReviewDTO entityToDTO(Review review) {
        ReviewDTO dto = ReviewDTO.builder()
                .reviewnum(review.getReviewnum())
                .pno(review.getPlan().getPno())
                .email(review.getMember().getEmail())
                .name(review.getMember().getName())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return dto;
    }
}
