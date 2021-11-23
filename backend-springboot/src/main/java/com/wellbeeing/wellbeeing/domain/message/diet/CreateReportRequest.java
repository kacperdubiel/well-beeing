package com.wellbeeing.wellbeeing.domain.message.diet;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReportRequest {
    LocalDate reportDate;
}
