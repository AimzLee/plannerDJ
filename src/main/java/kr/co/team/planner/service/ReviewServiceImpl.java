package kr.co.team.planner.service;

import kr.co.team.planner.dto.ReviewDTO;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.Review;
import kr.co.team.planner.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getList(Long pno) {
        log.info(pno);
        Plan plan = Plan.builder().pno(pno).build();
        List<Review> result = reviewRepository.findByPlan(plan);
        return result.stream().map(planReview -> entityToDTO(planReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
        return review.getReviewnum();
    }

    @Override
    public void remove(Long rnum) {
        log.info("remove " + rnum);
        reviewRepository.deleteById(rnum);
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Optional<Review> result = reviewRepository.findById(reviewDTO.getReviewnum());
        if(result.isPresent()){
            Review planReview = result.get(); planReview.changeGrade(reviewDTO.getGrade()); planReview.changeText(reviewDTO.getText());
            reviewRepository.save(planReview);
        }
    }
}
