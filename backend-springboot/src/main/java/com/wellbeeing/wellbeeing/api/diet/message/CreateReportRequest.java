package com.wellbeeing.wellbeeing.api.diet.message;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReportRequest {
    LocalDate reportDate;
}
