package kr.co.team.planner.controller;

import kr.co.team.planner.dto.PlanDTO;
import kr.co.team.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planner")
@RequiredArgsConstructor
@Log4j2
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("/register")//'후기 작성을 위한 모달창'으로 부터 전달받은 데이터를 DB로 보내는 method
    public ResponseEntity<Long> addPlan(@RequestBody PlanDTO planDTO){
        System.out.println("디버깅을 위한 메세지!#@^^&%@$#!$^컨트롤러로 왔다!");
        log.info("----------------------add MovieReview------------------------");
        log.info("planDTO: " + planDTO);
        Long pno = plannerService.register(planDTO);
        log.info("pno: " + pno);
        return new ResponseEntity<>(pno, HttpStatus.OK);
    }

}
