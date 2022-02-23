package kr.co.team.planner.service;

import kr.co.team.planner.dto.PlanDTO;
import kr.co.team.planner.entity.Plan;

import java.text.SimpleDateFormat;

public interface PlannerService {

    //리뷰 등록
    Long register(PlanDTO planDTO);

    default Plan dtoToEntity(PlanDTO planDTO){
        Plan plan = Plan.builder()
                .pno(planDTO.getPno())
                .title(planDTO.getTitle())
                .description(planDTO.getDescription())
                .location(planDTO.getLocation())
                .grade(planDTO.getGrade())
                .start(planDTO.getStart())
                .end(planDTO.getEnd())
                .build();

        return plan;
    }

    default PlanDTO entityToDTO(Plan plan) {
        PlanDTO dto = PlanDTO.builder()
                .pno(plan.getPno())
                .title(plan.getTitle())
                .description(plan.getDescription())
                .location(plan.getLocation())
                .grade(plan.getGrade())
                .start(plan.getStart())
                .end(plan.getEnd())
                .build();

        return dto;
    }

}
