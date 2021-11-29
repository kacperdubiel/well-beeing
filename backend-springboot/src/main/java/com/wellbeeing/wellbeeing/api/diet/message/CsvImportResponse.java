package com.wellbeeing.wellbeeing.api.diet.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class CsvImportResponse {
    int numberOfErrors;
    ArrayList<String> errorMessages;
}
