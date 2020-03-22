package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThisApps {

    public static void main(String[] args) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        String text = "zażółcić gęślą jaźń";
        PDFont font = PDType1Font.SYMBOL;
//        PDType0Font font1 = PDType0Font.load(document, new File("c:/windows/fonts/arial.ttf"));
        PDType0Font font1 = PDType0Font.load(document, new ThisApps().getfile());
//        PDFont font = new PDType1AfmPfbFont(doc,"cfm.afm");
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont( font1, 12 );
        contentStream.moveTextPositionByAmount( 100, 700 );
        contentStream.drawString( text );
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();

// Save the results and ensure that the document is properly closed:
        document.save( "Hello World.pdf");
        document.close();
    }

    private  File getfile() {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource("arial.ttf")).getFile());
    }
}
