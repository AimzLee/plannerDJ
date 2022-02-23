package kr.co.team.planner.repository;

import kr.co.team.planner.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    //plan 목록 보기를 위한 메서드
    //Plan 과 Review 를 join 하고 Plan 으로 그룹화해서
    //Plan 정보 와 grade 의 평균 과 Review 의 개수를 구해주는 메서드
    @Query("select p, max(pi), avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from Plan p " +
            "left outer join PlanImage pi on pi.plan = p " +
            "left outer join Review r on r.plan=p " +
            "group by p")
    Page<Object []> getListPage(Pageable pageable);

    //특정 plan 에 해당하는 데이터를 가져오는 메서드를 선언
    @Query("select p, pi , avg(coalesce(r.grade, 0)), count(r) " +
            "from Plan p left outer join PlanImage pi on pi.plan = p " +
            "left outer join Review r on r.plan = p " +
            "where p.pno = :pno " +
            "group by pi")
    List<Object[]> getPlanWithAll(Long pno);


}
