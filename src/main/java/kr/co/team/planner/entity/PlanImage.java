package kr.co.team.planner.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "plan")
//항상 Plan 안에서 생성되므로 Embeddable을 추가
@Embeddable
//Plan 와 함께 생성되고 수정되므로 별도의 생성 날짜 와
//수정 날짜를 가질 필요가 없음
public class PlanImage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;
    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

}