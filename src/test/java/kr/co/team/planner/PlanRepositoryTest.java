package kr.co.team.planner;

import kr.co.team.planner.entity.Member;
import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.entity.PlanImage;
import kr.co.team.planner.entity.Review;
import kr.co.team.planner.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class PlanRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanImageRepository planImageRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    //plan 정보 100개를 삽입하는 메서드
    //@Test
    @Transactional
    @Commit
    public void insertPlans() {
        Random r = new Random();

        IntStream.rangeClosed(1, 101).forEach(i -> {
            Plan plan = Plan.builder()
                    .title("Plan_" + i)
                    .description("Description_" + i)
                    .location("Location_" + i)
                    .build();
            planRepository.save(plan);

            int count = r.nextInt(5);
            for (int j = 0; j < count; j++) {
                PlanImage planImage = PlanImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .imgName("test_" + j + ".png")
                        .plan(plan)
                        .build();
                planImageRepository.save(planImage);
            }
        });
    }

    //Review 데이터를 200개 삽입하는 메서드
    //@Test
    public void reviewTest() {
        Random r = new Random();
        for (int i = 1; i <= 200; i++) {
            //Review는 Member 와 Plan 에 존재하는 데이터를 기반으로 생성
            Long mid = (long) (r.nextInt(100) + 1);
            Long pno = (long) (r.nextInt(100) + 1);

            Member member = Member.builder()
                    .email("member" + mid + "@study.hard")
                    .name("name" + mid)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("name" + mid))
                    .build();

            Plan plan = Plan.builder()
                    .pno(pno)
                    .build();

            Review review = Review.builder()
                    .member(member)
                    .plan(plan)
                    .grade(r.nextInt(5) + 1)
                    .text("리뷰_" + i)
                    .build();
            reviewRepository.save(review);
        }
    }

    //Plan 목록 가져오는 메서드
    //@Test
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("pno").descending());

        Page<Object [] > result = planRepository.getListPage(pageRequest);
        for(Object [] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

    //특정 Plan 에 대한 정보를 가져오는 메서드
    //@Test
    public void testGetMovie(){
        List<Object []> result = planRepository.getPlanWithAll(38L);
        for(Object [] r : result){
            System.out.println(Arrays.toString(r));
        }
    }

    //특정 영화에 해당하는 모든 리뷰 가져오기
    //@Test
    public void testGetReviews(){
        List<Review> list =
                reviewRepository.findByPlan(
                        Plan.builder()
                                .pno(78L)
                                .build());
        for(Review r : list) {
            //회원의 이메일을 출력
            System.out.println(r.getMember().getEmail());
//            System.out.println(r.getPlan());
            System.out.println(r.getGrade());
            System.out.println(r.getText());
        }
    }

}
