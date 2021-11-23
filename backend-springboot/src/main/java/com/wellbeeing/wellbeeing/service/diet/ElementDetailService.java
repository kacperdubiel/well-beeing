package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.ProductAmountDetail;

import java.util.List;
import java.util.Map;

public interface ElementDetailService {
    void countProdDetails(List<? extends ProductAmountDetail> details, Map<String, Double> reportMacroDetails,
                          Map<String, Double> reportVitaminDetails, Map<String, Double> reportMineralDetails);
}
