package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.ProductAmountDetail;
import com.wellbeeing.wellbeeing.domain.diet.ProductMacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.ProductMineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.ProductVitaminDetail;
import com.wellbeeing.wellbeeing.domain.diet.type.EWeightMeasure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("elementDetailService")
public class ElementDetailServiceImpl implements ElementDetailService{
    public ElementDetailServiceImpl(){

    }
    @Override
    public void countProdDetails(List<? extends ProductAmountDetail> details, Map<String, Double> reportMacroDetails,
                                 Map<String, Double> reportVitaminDetails, Map<String, Double> reportMineralDetails) {
        for(ProductAmountDetail dpd : details){
            double actProductGrams = dpd.getAmountOfProduct() * dpd.getMeasureType().getNumberOfGrams();

            List<ProductMacroDetail> actualProductMacroDetails = dpd.getProduct().getMacroDetails();
            List<ProductMineralDetail> actualProductMineralDetails = dpd.getProduct().getMineralDetails();
            List<ProductVitaminDetail> actualProductVitaminDetails = dpd.getProduct().getVitaminDetails();

            actualProductMacroDetails
                    .forEach(md -> reportMacroDetails.put(md.getDetailedMacroType().toString(), (
                            reportMacroDetails.get(md.getDetailedMacroType().toString()) == null
                                    ?
                                    actProductGrams * md.countAmountInGramsPerGram()
                                    :
                                    reportMacroDetails.get(md.getDetailedMacroType().toString())
                                            + actProductGrams * md.countAmountInGramsPerGram())));
            actualProductMineralDetails
                    .forEach(m -> reportMineralDetails.put(m.getMineralType().toString(), (
                            reportMineralDetails.get(m.getMineralType().toString()) == null
                                    ?
                                    actProductGrams * m.countAmountInGramsPerGram() / EWeightMeasure.MILLI_GRAM.getNumberOfGrams()
                                    :
                                    reportMineralDetails.get(m.getMineralType().toString())
                                            + actProductGrams * m.countAmountInGramsPerGram() / EWeightMeasure.MILLI_GRAM.getNumberOfGrams())));
            actualProductVitaminDetails
                    .forEach(v -> reportVitaminDetails.put(v.getVitaminType().toString(), (
                            reportVitaminDetails.get(v.getVitaminType().toString()) == null
                                    ?
                                    actProductGrams * v.countAmountInGramsPerGram() / EWeightMeasure.MICRO_GRAM.getNumberOfGrams()
                                    :
                                    reportVitaminDetails.get(v.getVitaminType().toString())
                                            + actProductGrams * v.countAmountInGramsPerGram() / EWeightMeasure.MICRO_GRAM.getNumberOfGrams())));
        }
    }
}
