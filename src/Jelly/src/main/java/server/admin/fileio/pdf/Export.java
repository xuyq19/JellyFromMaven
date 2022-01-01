package server.admin.fileio.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import server.main.User;

import java.io.FileOutputStream;
/**
 * export conclusion to pdf
 * conclusion content:sum of balance,sum of users
 */


public class Export {

    public static void createPDF() throws Exception {
        final String DEST = "/home/xizhilang/bank/conclusion.pdf";
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("conclusion"));
        document.add(new Paragraph("sum of balance:"));

        /**
         * get sum of balance from database
         */
        User[] users;
        users = server.admin.database.ReadAll.readAll();
        double sumOfBalance = 0;
        for (int i = 0; i < server.admin.database.Counter.count(); i++) {
            sumOfBalance += users[i].getBankAccountBalance();
        }

        document.add(new Paragraph(String.valueOf(sumOfBalance)));


        document.add(new Paragraph("sum of users:"));
        document.add(new Paragraph(String.valueOf(server.admin.database.Counter.count())));
        // step 5
        document.close();
    }


}
