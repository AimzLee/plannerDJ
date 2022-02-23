package kr.co.team.planner.controller;

import kr.co.team.planner.dto.ReviewDTO;
import kr.co.team.planner.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{pno}/list")
    public ResponseEntity<List<ReviewDTO>> list(@PathVariable("pno") Long mno){
        List<ReviewDTO> reviewDTOList = reviewService.getList(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{pno}")//'후기 작성을 위한 모달창'으로 부터 전달받은 데이터를 DB로 보내는 method
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO){
        log.info("----------------------add MovieReview------------------------");
        log.info("reviewDTO: " + movieReviewDTO);
        Long reviewnum = reviewService.register(movieReviewDTO);
        log.info("reviewnum: " + reviewnum);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO movieReviewDTO){
        log.info("------------------------modify MovieReview-----------------------" + reviewnum);
        log.info("reviewDTO: " + movieReviewDTO);
        reviewService.modify(movieReviewDTO);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> removeReview( @PathVariable Long reviewnum){
        log.info("-----------------modify removeReview-----------------------");
        log.info("reviewnum: " + reviewnum);
        reviewService.remove(reviewnum);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

}