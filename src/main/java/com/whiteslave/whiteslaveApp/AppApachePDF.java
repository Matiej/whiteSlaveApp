package com.whiteslave.whiteslaveApp;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

public class AppApachePDF {
    private static String FILE_DEST_PATH = System.getProperty("user.dir") + File.separator + "pdfBOX-dir";
    private static String LOGO_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/fa.png";
    private static String LOGO_SZW_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/logo_dobra.jpg";
    private static String REJECT = System.getProperty("user.dir") + File.separator + "src/main/resources/img/success.jpg";
    private static String ACCEPT = System.getProperty("user.dir") + File.separator + "src/main/resources/img/reject.jpg";

    public static void main(String[] args) {

        try {
            startPDFBOX("PDFBPX_EXAMPLE");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void startPDFBOX(String fileBasicName) throws IOException {

        String fileName = fileBasicName;
        String ext = ".pdf";
        File file = new File(FILE_DEST_PATH);
        boolean mkdir = file.getParentFile().mkdir();
//        System.out.println(mkdir);
        if (!Files.isDirectory(Path.of(FILE_DEST_PATH))) {
            System.out.println("nie ma katalogu");
            Files.createDirectory(Path.of(FILE_DEST_PATH));

        } else {
            System.out.println("Jest katalog " + FILE_DEST_PATH);

        }
        File pathAndFile = null;
        String[] pathnames = new File(FILE_DEST_PATH).list();
        if (pathnames.length > 0) {
            for (String pathname : pathnames) {
                if ((fileName + ext).equals(pathname)) {
                    pathAndFile = new File(FILE_DEST_PATH + File.separator + fileName + 1 + ext);
                } else {

                    pathAndFile = new File(FILE_DEST_PATH + File.separator + fileName + (1 + pathnames.length) + ext);
                }
            }
        } else {
            pathAndFile = new File(FILE_DEST_PATH + File.separator + fileName + ext);
        }
        createPdf(pathAndFile);
    }

    private static void createPdf(File file) throws IOException {
        PDDocument document = new PDDocument();
        pdfDocProperites(document);
        PDPage page = new PDPage();
        document.addPage(page);

        PDRectangle pdRectangle = page.getMediaBox();
        String title = "Raport z weryfikacji kontrahenta VAT";

        PDFont font = PDType1Font.TIMES_BOLD_ITALIC;
        int titleFontSize = 22;
        int marginTop = 110;
        float titleWidth = font.getStringWidth(title) / 1000 * titleFontSize;
        float titleheight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleFontSize;
        float ytitlePos = 650L;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        PDImageXObject logoArchimat = prepareImage(document, LOGO_IMAGE);
        PDImageXObject logoSzwalnia = prepareImage(document, LOGO_SZW_IMAGE);

        contentStream.drawImage(logoArchimat, (pdRectangle.getWidth()) / 4, 700, logoArchimat.getWidth() / 5, logoArchimat.getHeight() / 5);
        contentStream.drawImage(logoSzwalnia, ((pdRectangle.getWidth()) / 4) * 3 - (logoSzwalnia.getWidth() / 10), 685, logoSzwalnia.getWidth() / 10, logoSzwalnia.getHeight() / 10);

        contentStream.beginText();
        contentStream.setFont(font, titleFontSize);
//            contentStream.setLeading(21.5F);
        contentStream.setNonStrokingColor(Color.DARK_GRAY);
        contentStream.newLineAtOffset((pdRectangle.getWidth() - titleWidth) / 2, ytitlePos);
        contentStream.showText(title);
        contentStream.endText();

        contentStream.setStrokingColor(Color.LIGHT_GRAY);
        contentStream.setLineWidth(1);
        contentStream.moveTo(100, ytitlePos + titleheight);
        contentStream.lineTo(pdRectangle.getWidth() - 100, ytitlePos + titleheight);
        contentStream.closeAndStroke();

        contentStream.setStrokingColor(Color.LIGHT_GRAY);
        contentStream.setLineWidth(1);
        contentStream.moveTo(50, ytitlePos - 10);
        contentStream.lineTo(pdRectangle.getWidth() - 50, ytitlePos - 10);
        contentStream.closeAndStroke();

        prepareTable(page, document, ytitlePos - 10).draw();
        contentStream.close();

        document.save(file);
        document.close();

    }

    private static void pdfDocProperites(PDDocument document) {
        PDDocumentInformation documentInformation = document.getDocumentInformation();
        documentInformation.setAuthor("Maciek");
        documentInformation.setTitle("Raport z białej listy podatników VAT");
        documentInformation.setCreator("whiteSlave APP");
        documentInformation.setSubject("Raport z weryfikacji kontrahenta VAT");

        documentInformation.setModificationDate(Calendar.getInstance());
    }

    private static PDImageXObject prepareImage(PDDocument pdDocument, String imagePath) {
        PDImageXObject img = null;
        try {
            img = PDImageXObject.createFromFile(imagePath, pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private static BaseTable prepareTable(PDPage page, PDDocument document, float yLastPos) throws IOException {
        final String positiveVeryfication = "POZYTYWNIE";
        final String negativeVeryfication = "NEGATYWNIE";
        float margin = 50;
        float yStartNewPage = page.getMediaBox().getHeight() - 100; //2xmargin
        float tableWidth = page.getMediaBox().getWidth() - 100;

        boolean drawContent = true;
        float bottomMargin = 70;


        BaseTable table = new BaseTable(yLastPos, yStartNewPage,
                bottomMargin, tableWidth, margin, document, page, true, drawContent);

        // the parameter is the row height
        Row<PDPage> headerRow = table.createRow(40);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(80, "Kontrahent zweryfikowany " + positiveVeryfication);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(20);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 10));
        BufferedImage image = prepareImage(document, ACCEPT).getImage();
        Image statusImg = new Image(image);
        cell = headerRow.createImageCell(20, statusImg, HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);


        table.addHeaderRow(headerRow);


        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(30, "black left plain");
        cell.setFontSize(15);
        cell = row.createCell(70, "black left bold");
        cell.setFontSize(15);
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        row = table.createRow(20);
        cell = row.createCell(50, "red right mono");
        cell.setTextColor(Color.RED);
        cell.setFontSize(15);
        cell.setFont(PDType1Font.COURIER);
        // horizontal alignment
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
        cell = row.createCell(50, "green centered italic");
        cell.setTextColor(Color.GREEN);
        cell.setFontSize(15);
        cell.setFont(PDType1Font.TIMES_ITALIC);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

        row = table.createRow(20);
        cell = row.createCell(40, "rotated");
        cell.setFontSize(15);
        // rotate the text
        cell.setTextRotated(true);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        // long text that wraps
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        // long text that wraps, with more line spacing
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setFontSize(12);
        cell.setLineSpacing(2);

        return table;

    }
}
