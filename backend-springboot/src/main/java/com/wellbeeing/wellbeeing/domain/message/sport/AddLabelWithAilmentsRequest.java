package com.wellbeeing.wellbeeing.domain.message.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLabelWithAilmentsRequest {
    private SportLabel sportLabel;
    private ArrayList<Map<String, Object>> ailments;
}
