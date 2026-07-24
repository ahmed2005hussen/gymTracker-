package com.ahmed.Hadidy.dto.response;

import com.ahmed.Hadidy.entity.DietPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DietPlanResponse {

    private Long id;

    private String title;

    private String description;

    public DietPlanResponse(DietPlan d) {
        this.id = d.getId();
        this.title = d.getTitle();
        this.description = d.getDescription();
    }

}
