package kr.co.team.planner.service;

import kr.co.team.planner.dto.PlanDTO;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlannerServiceImpl implements PlannerService {

    private final PlanRepository planRepository;

    @Override
    public Long register(PlanDTO planDTO) {
        Plan plan = dtoToEntity(planDTO);
        planRepository.save(plan);
        return plan.getPno();
    }
}
