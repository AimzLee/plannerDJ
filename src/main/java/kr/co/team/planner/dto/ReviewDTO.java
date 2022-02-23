package kr.co.team.planner.dto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long reviewnum;

    private Long pno;

    //private String mid;
    private String email;
    private String name;

    private int grade;

    private String text;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

}