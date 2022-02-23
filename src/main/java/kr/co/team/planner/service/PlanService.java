package kr.co.team.planner.service;

import kr.co.team.planner.dto.PageRequestDTO;
import kr.co.team.planner.dto.PageResponseDTO;
import kr.co.team.planner.dto.PlanDTO;
import kr.co.team.planner.dto.PlanImageDTO;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.PlanImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PlanService {
    Long register(PlanDTO planDTO);

    PageResponseDTO<PlanDTO, Object[]> getList(PageRequestDTO requestDTO);

    List<Plan> getOnlyPlanList();

    PlanDTO getPlan(Long pno);

    default Map<String, Object> dtoToEntity(PlanDTO planDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Plan plan = Plan.builder()
                .pno(planDTO.getPno())
                .title(planDTO.getTitle())
                .description(planDTO.getDescription())
                .location(planDTO.getLocation())
                .grade(planDTO.getGrade())
                .start(planDTO.getStart())
                .end(planDTO.getEnd())
                .build();
        entityMap.put("plan", plan);

        List<PlanImageDTO> imageDTOList = planDTO.getImageDTOList();

        //PlanImageDTO 처리
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<PlanImage> planImageList = imageDTOList.stream(). map(planImageDTO ->{
                PlanImage planimage = PlanImage.builder()
                        .path(planImageDTO.getPath())
                        .imgName(planImageDTO.getImgName())
                        .uuid(planImageDTO.getUuid())
                        .plan(plan)
                        .build();
                return planimage;
            }).collect(Collectors.toList());
            entityMap.put("imgList", planImageList);
        }
        return entityMap;
    }

    default PlanDTO entitiesToDTO(Plan plan, List<PlanImage> planImages, double avg, long reviewCnt) {

        PlanDTO planDTO = PlanDTO.builder()
                .pno(plan.getPno())
                .title(plan.getTitle())
                .description(plan.getDescription())
                .location(plan.getLocation())
                .grade(plan.getGrade())
                .start(plan.getStart())
                .end(plan.getEnd())
                .regDate(plan.getRegDate())
                .modDate(plan.getModDate())
                .build();

        List<PlanImageDTO> planImageDTOList = planImages.stream().map(planImage -> {
            System.out.println("이것이 null 이란 말이냐?" + planImage);
            if(planImage == null) return null;
            else return PlanImageDTO.builder()
                        .imgName(planImage.getImgName())
                        .path(planImage.getPath())
                        .uuid(planImage.getUuid())
                        .build();
        }).collect(Collectors.toList());

        planDTO.setImageDTOList(planImageDTOList);

        planDTO.setAvg(avg);
        planDTO.setReviewCnt(reviewCnt);

        return planDTO;
    }
}