package kr.co.team.planner.repository;

import kr.co.team.planner.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    //가져오는 데이터 4개로 맞춰서 수정
    @Query(value="select p, w, count(c), count(s) from Plan p left join p.writer w " +
        "left join Share s on s.plan = p " +
        "left join CheckList c on c.plan = p group by p",
        countQuery = "select count(p) from Plan p")
    Page<Object []> getListPage(Pageable pageable);
//
//    //특정 plan 에 해당하는 데이터를 가져오는 메서드를 선언
//    @Query("select p, pi , avg(coalesce(r.grade, 0)), count(r) " +
//            "from Plan p left outer join PlanImage pi on pi.plan = p " +
//            "left outer join Review r on r.plan = p " +
//            "where p.pno = :pno " +
//            "group by pi")
//    List<Object[]> getPlanWithAll(Long pno);

    //from LDJ
    @Query(value="select p, w, count(c), count(s) from Plan p left join p.writer w " +
            "left join Share s on s.plan = p " +
            "left join CheckList c on c.plan = p group by p",
            countQuery = "select count(p) from Plan p")
    Page<Object[]> getPlanWithCheckListCount(Pageable pageable);

    @Query("select p, w, count(c), count(s) " +
            "from Plan p left join p.writer w left outer join CheckList c on c.plan = p " +
            "left outer join Share s on s.plan = p " +
            "where p.pno = :pno")
    List<Object[]> getPlanWithAll(Long pno);

}
