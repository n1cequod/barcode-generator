//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

    public static void writeToPdf(List<String> barcodeList, String filePath, List<String> articleList, List<String> productNameList, List<Integer> quantityList) {

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath + ".pdf"));
             Document document = new Document(pdfDoc)) {

            PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H, true);

            Table table = new Table(UnitValue.createPointArray(new float[]{200F, 200F, 200F}));
            table.setTextAlignment(TextAlignment.CENTER);
            table.setFont(font);

            for (int i = 0; i < barcodeList.size(); i++) {

                ByteArrayOutputStream byteOut = Generator.code(barcodeList.get(i));

                Image image = new Image(ImageDataFactory.create(byteOut.toByteArray()));
                image.setAutoScale(true);

                for (int j = 0; j < quantityList.get(i); j++) {
                    Table nestedTable = new Table(UnitValue.createPointArray(new float[]{200F}));
                    nestedTable.addCell(productNameList.get(i));
                    nestedTable.addCell("Арт.: " + articleList.get(i));
                    nestedTable.addCell(image);
                    Utils.removeBorder(nestedTable);
                    table.addCell(nestedTable);
                }
            }

            document.add(table);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
