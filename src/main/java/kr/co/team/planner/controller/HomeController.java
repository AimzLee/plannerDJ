package kr.co.team.planner.controller;

import kr.co.team.planner.entity.Plan;
import kr.co.team.planner.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PlanService planService; //final

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/planner/main")
    public void planner(Model model) {
        List<Plan> plans = planService.getOnlyPlanList();
//        Map<String, Object> map;
//        List<Map<String, Object>> li = new ArrayList<>();
//        for (Plan plan : plans) {
//            map = new HashMap<>();
//            map.put("pno", plan.getPno());
//            map.put("title", plan.getTitle());
//            map.put("description", plan.getDescription());
//            map.put("location", plan.getLocation());
//            map.put("grade", plan.getGrade());
//            map.put("start", plan.getStart());
//            map.put("end", plan.getEnd());
//            li.add(map);
//        }
//        System.out.println(li);
//        model.addAttribute("result", li);
        model.addAttribute("result", plans);
    }

}
