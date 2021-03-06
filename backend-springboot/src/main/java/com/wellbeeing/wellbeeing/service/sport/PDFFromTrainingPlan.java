package com.wellbeeing.wellbeeing.service.sport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class PDFFromTrainingPlan {

    private static final ArrayList<String> WEEK_DAYS = new ArrayList<String>() {
        {
            add("Poniedziałek");
            add("Wtorek");
            add("Środa");
            add("Czwartek");
            add("Piątek");
            add("Sobota");
            add("Niedziela");
        }
    };
    //    private static final BaseFont base = FontFactory.getFont("/backend-springboot/fonts/calibri.ttf", BaseFont.WINANSI).getBaseFont();
    private static final String base = BaseFont.COURIER;
    private static final Font dayPartFont = FontFactory.getFont(base, BaseFont.CP1250, 8, Font.NORMAL);
    private static final Font nameFont = FontFactory.getFont(base, BaseFont.CP1250, 12, Font.BOLD);
    private static final Font descriptionFont = FontFactory.getFont(base, BaseFont.CP1250, 10, Font.NORMAL, BaseColor.DARK_GRAY);
    private static final Font trainingFont = FontFactory.getFont(base, BaseFont.CP1250, 16, Font.NORMAL, BaseColor.RED);
    private static final Font time_kcalFont = FontFactory.getFont(base, BaseFont.CP1250, 8, Font.ITALIC);
    private static final Font ultimateHeaderFont = FontFactory.getFont(base, BaseFont.CP1250, 32, Font.BOLD);
    private static final Font headerFont = FontFactory.getFont(base, BaseFont.CP1250, 28, Font.BOLD);
    private static final Font secondHeaderFont = FontFactory.getFont(base, BaseFont.CP1250, 12, Font.BOLD);
    public static InputStream fileStream;
    private static String weekRange = "08.11 - 14.11";

    public static void main(String[] args) {

        Document doc = new Document();
        doc.setPageSize(PageSize.A4.rotate());

        try {
            File file = new File("backend-springboot/data");
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsolutePath() + "/NewTraining.pdf"));
            System.out.println("PDF created.");
//opens the PDF
            doc.open();
            Font font = FontFactory.getFont(BaseFont.COURIER, BaseFont.CP1250);
            Font headerFont = FontFactory.getFont(BaseFont.COURIER, 28, Font.BOLD);
            Paragraph paragraph = new Paragraph("Plan treningowy na tydzień: " + weekRange, headerFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(12f);
            doc.add(paragraph);
            PdfPTable table = new PdfPTable(7);
            PDFFromTrainingPlan.addTableHeader(table, font);
            table.setWidthPercentage(95);
            PDFFromTrainingPlan.addRows(table);
            PDFFromTrainingPlan.addCustomRows(table);

            doc.add(table);
//adds paragraph to the PDF file
//close the PDF file
            doc.close();
//closes the writer
            writer.close();
        } catch (DocumentException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Document generatePDFFromTrainingPlan(TrainingPlan trainingPlan, String name) {
        weekRange = PDFFromTrainingPlan.getDateRangeOfWeek(trainingPlan.getBeginningDate());
        String filename = name.equals("") ? "/NewTrainingPlan.pdf" : "/" + name + ".pdf";
        Document doc = new Document();
        doc.setPageSize(PageSize.A4.rotate());

        try {
//generate a PDF at the specified location
            File file = new File("backend-springboot/data");
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsolutePath() + filename));
            fileStream = new FileInputStream(file.getAbsolutePath() + filename);
            System.out.println("PDF created.");
            Paragraph paragraph = new Paragraph("Plan treningowy na tydzień: " + weekRange, ultimateHeaderFont);
//prepares trainingPlan
            Map<String, Object> tableMap = PDFFromTrainingPlan.formatTrainingPlan(trainingPlan);
            TrainingPosition[][] formattedTable = (TrainingPosition[][]) tableMap.get("table");
            int max_size = (int) tableMap.get("maxSize");
            PdfPTable table = new PdfPTable(7);
            Font font = FontFactory.getFont(BaseFont.COURIER, BaseFont.CP1250, 12, Font.BOLD);
            PDFFromTrainingPlan.addTableHeader(table, font);
            table.setWidthPercentage(95);
//opens the PDF
            doc.open();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(12f);
            doc.add(paragraph);
            for (int i = 0; i < max_size; i++) {
                for (int j = 0; j < 7; j++) {
                    PdfPCell cell = new PdfPCell();
                    if (formattedTable[i][j] != null)
                        cell = PDFFromTrainingPlan.CellFromPosition(formattedTable[i][j]);
                    table.addCell(cell);
                }
            }
//            PDFFromTrainingPlan.addRows(table);
//            PDFFromTrainingPlan.addCustomRows(table);

            doc.add(table);
            doc.setPageSize(PageSize.A4);

            doc.newPage();
//adds paragraph to the PDF file
            Paragraph newParagraph = new Paragraph("Szczegóły planu:", ultimateHeaderFont);
            newParagraph.setSpacingBefore(20);
            newParagraph.setSpacingAfter(20);
            doc.add(newParagraph);

            PDFFromTrainingPlan.addTrainingPlanContent(doc, tableMap);

//close the PDF file
            doc.close();
//closes the writer
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private static Map<String, Object> formatTrainingPlan(TrainingPlan plan) {
//        ArrayList<ArrayList<TrainingPosition>> positionsMatrix = new ArrayList<>();
        Map<String, ArrayList<TrainingPosition>> daysPositions = new HashMap<>();
        WEEK_DAYS.forEach(day -> daysPositions.put(day, new ArrayList<>()));
        for (TrainingPosition pos :
                plan.getTrainingPositions()) {
            daysPositions.get(pos.getDayOfTheWeek()).add(pos);
        }
        int max_pos = Collections.max(daysPositions.values(), Comparator.comparing(ArrayList::size)).size();

        TrainingPosition[][] table = new TrainingPosition[max_pos][7]; // Rows = trainings, cols = days
        int day_num = 0;
        for (String day :
                WEEK_DAYS) {
            ArrayList<TrainingPosition> poss = daysPositions.get(day);
            for (int i = 0; i < poss.size(); i++) {
                table[i][day_num] = poss.get(i);
            }
            day_num++;
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("table", table);
        resultMap.put("maxSize", max_pos);
        return resultMap;
    }

    private static PdfPCell CellFromPosition(TrainingPosition position) {
        PdfPCell positionCell = new PdfPCell();

        Paragraph dayTime = new Paragraph(position.getTimeOfDay().toString(), dayPartFont);
        dayTime.setAlignment(Element.ALIGN_CENTER);

        positionCell.addElement(dayTime);

        Paragraph name = new Paragraph(position.getTraining().getName(), nameFont);

        positionCell.addElement(name);
        String description = position.getTraining().getDescription() != null ? position.getTraining().getDescription() : "Brak opisu";
        Paragraph desc = new Paragraph(description.subSequence(0, Math.min(description.length(), 20)).toString(), descriptionFont);

        positionCell.addElement(desc);


        Paragraph calories = new Paragraph(PDFFromTrainingPlan.getTimePrettyFromSeconds(position.getTraining().getTotalTrainingTimeSeconds())
                + " | " +
                position.getTraining().getCaloriesBurned()
                + " kcal", time_kcalFont);

        calories.setAlignment(Element.ALIGN_BOTTOM);
        positionCell.addElement(calories);

        return positionCell;
    }

    private static void addTrainingPlanContent(Document doc, Map<String, Object> formattedTrainingPlan) throws DocumentException {
        int max_size = (int) formattedTrainingPlan.get("maxSize");
        TrainingPosition[][] formattedTable = (TrainingPosition[][]) formattedTrainingPlan.get("table");
        for (int j = 0; j < 7; j++) {
            Paragraph newParagraph = new Paragraph(WEEK_DAYS.get(j), headerFont);
            newParagraph.setSpacingBefore(20);
            newParagraph.setSpacingAfter(10);
            doc.add(newParagraph);
            for (int i = 0; i < max_size; i++) {
                TrainingPosition position = formattedTable[i][j];
                if (position == null) break;
                Paragraph name = new Paragraph(position.getTraining().getName(), trainingFont);
                name.setSpacingAfter(10);
                doc.add(name);

                Paragraph dayTime = new Paragraph(position.getTimeOfDay().toString(), dayPartFont);
                dayTime.setSpacingAfter(5);
                doc.add(dayTime);

                String description = position.getTraining().getDescription() != null ? position.getTraining().getDescription() : "Brak";
                Paragraph desc = new Paragraph(String.format("%s - %s", "Opis:", description), descriptionFont);
                desc.setSpacingAfter(5);
                doc.add(desc);

                String instruction = position.getTraining().getInstruction() != null ? position.getTraining().getInstruction() : "Brak";
                Paragraph inst = new Paragraph(String.format("%s - %s", "Wskazówki do treningu:", instruction), descriptionFont);
                inst.setSpacingAfter(5);
                doc.add(inst);

                Paragraph calories = new Paragraph("Trening powinien trwać " + PDFFromTrainingPlan.getTimePrettyFromSeconds(position.getTraining().getTotalTrainingTimeSeconds())
                        + " i powinieneś podczas niego spalić " +
                        position.getTraining().getCaloriesBurned()
                        + " kcal", descriptionFont);
                calories.setSpacingAfter(10);
                doc.add(calories);

                if (position.getTraining().getExerciseInTrainings().size() == 0)
                    break;
                Paragraph exercises = new Paragraph("Ćwiczenia w treningu:", secondHeaderFont);
                exercises.setSpacingAfter(5);
                doc.add(exercises);

                int exCounter = 1;
                for (ExerciseInTraining ex :
                        position.getTraining().getExerciseInTrainings()) {
                    Paragraph exName = new Paragraph(String.format("%d. %s", exCounter++, ex.getExercise().getName()), nameFont);
                    exName.setSpacingAfter(5);
                    doc.add(exName);

                    Paragraph exUtils = new Paragraph(String.format("Zakres: %s serii po %s powtórzeń. Każda seria powinna trwać %s",
                            ex.getSeries(),
                            ex.getRepetitions(),
                            PDFFromTrainingPlan.getTimePrettyFromSeconds(ex.getTime_seconds())), descriptionFont);
                    exUtils.setSpacingAfter(5);
                    doc.add(exUtils);

                    String exDescription = ex.getExercise().getDescription() != null ? ex.getExercise().getDescription() : "Brak";
                    Paragraph exDesc = new Paragraph(String.format("%s - %s", "Opis:", exDescription), descriptionFont);
                    exDesc.setSpacingAfter(5);
                    doc.add(exDesc);

                    String exInstruction = ex.getExercise().getInstruction() != null ? ex.getExercise().getInstruction() : "Brak";
                    Paragraph exInstr = new Paragraph(String.format("%s - %s", "Instrukcja:", exInstruction), descriptionFont);
                    exInstr.setSpacingAfter(5);
                    doc.add(exInstr);

                    String labels = ex.getExercise().getLabels().stream()
                            .map(SportLabel::getName)
                            .collect(Collectors.joining(", "));

                    Paragraph exLabels = new Paragraph(String.format("Etykiety: %s", labels), descriptionFont);
                    exLabels.setSpacingAfter(10);
                    doc.add(exLabels);
                }
            }
        }


    }

    private static void addTableHeader(PdfPTable table, Font font) {
        PDFFromTrainingPlan.WEEK_DAYS.forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.setMinimumHeight(30);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle, font));
            table.addCell(header);
        });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private static void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
//        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell();
        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }

    private static String getTimePrettyFromSeconds(int seconds) {
        if (seconds < 60) {
            return seconds + "s";
        } else if (seconds < 3600) {
            return (int) Math.floor(seconds / 60) + "min";
        } else {
            int hours = (int) Math.floor(seconds / 3600);
            int minutes = (int) Math.floor((seconds - hours * 3600) / 60);
            return hours + "h " + (minutes != 0 ? minutes + "min" : "");
        }
    }


    private static String getDateRangeOfWeek(Date date) {
        LocalDate week = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate start = week.with(DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(6);
        System.out.println(start + " - " + end);
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd.MM");
        return ft.format(start) + " - " + ft.format(end);

//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(Calendar.YEAR, year);
//
//
//        calendar.set(Calendar.WEEK_OF_YEAR, week);
//        calendar.set(Calendar.DAY_OF_WEEK, 1);
//        // Now get the first day of week.
//        Date sDate = calendar.getTime();
//        System.out.println(sDate);
//
//        calendar.set(Calendar.WEEK_OF_YEAR, (week));
//        calendar.set(Calendar.DAY_OF_WEEK, 7);
//        Date eDate = calendar.getTime();
//        System.out.println(eDate);
    }
//    private static ArrayList<String> getDatesArrayOfWeek(int weekNo){
//        var d1 = new Date();
//        var numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
//        d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
//        var weekNoToday = d1.getWeek();
//        var weeksInTheFuture = eval( weekNo - weekNoToday );
//        d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
//        let weekDays = []
//        for (let i = 0; i < 7; i++) {
//            weekDays.push({
//                    day: this.days[i],
//                    date: d1.addDays(i)
//            })
//        }
//        return weekDays;
//    },
}
