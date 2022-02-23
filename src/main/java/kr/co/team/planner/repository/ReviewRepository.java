package kr.co.team.planner.repository;

import kr.co.team.planner.entity.Member;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //Plan 정보를 가지고 모든 Plan 의 모든 리뷰를 가져오는 메서드
    @EntityGraph(attributePaths = {"member"}, type= EntityGraph.EntityGraphType.FETCH)
    List<Review> findByPlan(Plan plan);

    //member 에 해당하는 데이터를 삭제하는 메서드
    @Modifying
    @Query("delete from Review pr where pr.member = :member")
    void deleteByMember(Member member);

    //Movie 가 지워질 때 같이 데이터를 지우는 메서드
    void deleteByPlan(Plan plan);

}
