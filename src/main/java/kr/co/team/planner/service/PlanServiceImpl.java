package kr.co.team.planner.service;
//LDJ 수정
import kr.co.team.planner.dto.PageRequestDTO;
import kr.co.team.planner.dto.PageResponseDTO;
import kr.co.team.planner.dto.PlanDTO;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.User;
import kr.co.team.planner.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository; //final

    @Transactional
    @Override
    public Long register(PlanDTO planDTO) {
        Plan plan = dtoToEntity(planDTO);
        log.info(plan.getStart());
        planRepository.save(plan);
        return plan.getPno();
    }

    @Override
    public PageResponseDTO<PlanDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
        Page<Object[]> result = planRepository.getListPage(pageable);

        Function<Object[], PlanDTO> fn = (arr -> entityToDTO(
                (Plan)arr[0] ,
                (User)arr[1],
                (Long)arr[2],   //integer 일수도..
                (Long)arr[3])
        );
        System.out.println("#$%^*&^%$#여기까지 잘 오는지 확인!ㄸ$#$");
        System.out.println("result : " + result);
        System.out.println("fn : " + fn);
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public List<Plan> getOnlyPlanList() {
        List<Plan> plans = planRepository.findAll();
        return plans;
    }

    @Override
    public PlanDTO getPlan(Long pno) {
        List<Object[]> result = planRepository.getPlanWithAll(pno);
        Plan plan = (Plan)result.get(0)[0];
        User user = (User)result.get(0)[1];
        Long CheckListCount = (Long)result.get(0)[2];
        Long ShareCount = (Long)result.get(0)[3];
        return entityToDTO(plan, user, CheckListCount, ShareCount);
    }

}