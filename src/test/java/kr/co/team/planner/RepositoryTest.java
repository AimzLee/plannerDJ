package kr.co.team.planner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.co.team.planner.entity.*;
import kr.co.team.planner.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;

    //@Test
    public void insertDummyMembers() {
        for(int i=1; i<=100; i++) {
            Member member = Member.builder()
                    .email("member"+i+"@study.hard")
                    .name("name"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("name"+i))
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(i > 80) member.addMemberRole((MemberRole.MANAGER));
            if(i > 90) member.addMemberRole((MemberRole.ADMIN));
            memberRepository.save(member);
        }
    }

    //@Test
    public void testFindMemberByEmail() {
        Optional<Member> member = memberRepository.findByEmail("member95@study.hard", false);
        System.out.println(member.get());
    }

    //@Test
    public void insertDummyMember() {
        Member member = Member.builder()
                .email("member200@study.hard")
                .name("name200")
                .fromSocial(false)
                .password(passwordEncoder.encode("name200"))
                .build();
        member.addMemberRole(MemberRole.USER);
        member.addMemberRole((MemberRole.MANAGER));
        member.addMemberRole((MemberRole.ADMIN));
        memberRepository.save(member);
    }

    //@Test //-> 이거 안됨! PK 관련된 문제같은데...  '방금만든놈!'은 지워지지만, '꼬리가 긴 놈!'은 삭제가 안됨! 공부하자!
    public void testDeleteMemberById() {
        String email = "member200@study.hard";
        memberRepository.deleteById(email);
    }




}