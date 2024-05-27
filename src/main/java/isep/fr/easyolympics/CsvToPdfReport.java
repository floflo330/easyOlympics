package isep.fr.easyolympics;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvToPdfReport {

    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/medals_list.csv";  // Replace with your CSV file path relative to the project
        String pdfFilePath = "src/main/resources/medals_report.pdf";  // Replace with your desired PDF file path relative to the project
        List<String[]> data = readCsv(csvFilePath);

        if (data != null) {
            try {
                generatePdf(data, pdfFilePath);
                System.out.println("PDF report generated successfully at " + pdfFilePath);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String[]> readCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void generatePdf(List<String[]> data, String filePath) throws DocumentException, IOException {
        Document document = new Document();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            PdfWriter.getInstance(document, fos);
            document.open();

            PdfPTable table = new PdfPTable(data.get(0).length);
            addTableHeader(table, data.get(0));
            addRows(table, data);

            document.add(new Paragraph("Medals Report"));
            document.add(table);
        }
        document.close();
    }

    private static void addTableHeader(PdfPTable table, String[] header) {
        for (String column : header) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(column));
            table.addCell(cell);
        }
    }

    private static void addRows(PdfPTable table, List<String[]> data) {
        for (int i = 1; i < data.size(); i++) {
            for (String field : data.get(i)) {
                table.addCell(field);
            }
        }
    }
}
