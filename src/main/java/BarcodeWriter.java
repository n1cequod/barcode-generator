import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeWriter {

    private static final String FONT = "src/main/resources/fonts/FreeSans.ttf";

    public static void writePng(ByteArrayOutputStream byteOut, String filePath){
        try(FileOutputStream fos = new FileOutputStream(filePath + ".png")) {
            fos.write(byteOut.toByteArray());
            fos.flush();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void writeToPdf(ByteArrayOutputStream byteOut, String filePath, String article, String productName, int quantity) {

        try {
            Image png = Image.getInstance(byteOut.toByteArray());
            png.setAbsolutePosition(0, 705);
            png.scalePercent(25);
            Font font = FontFactory.getFont(FONT, "Cp1251", true);
            Document document = new Document();
            PdfPTable table = new PdfPTable(3);

            for (int i = 0; i < quantity; i++) {
                Paragraph p = new Paragraph("       " + productName, font);
                p.setAlignment(1);
                p.add("\n       Art.: " + article);
                PdfPTable inTable = new PdfPTable(1);
                inTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                inTable.addCell(p);
                inTable.addCell(png);
                inTable.getDefaultCell().setBorder(0);
                table.addCell(inTable);

            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
            document.open();
            document.add(table);
            document.close();

            writer.close();
        }
        catch (IOException | DocumentException err) {
            err.printStackTrace();
        }
    }

}
