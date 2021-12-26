package main.server.fileio.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import main.server.user.User;

import java.io.FileOutputStream;

/**
 * export conclusion to pdf
 * conclusion content:sum of balance,sum of users
 */


public class Export {
    public static final String DEST = "/home/xizhilang/bank/conclusion.pdf";

    public static void main() throws Exception {
        new Export().createPDF(DEST);
    }

    public void createPDF(String dest) throws Exception {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("conclusion"));
        document.add(new Paragraph("sum of balance:"));

        /**
         * get sum of balance from database
         */
        User[] users;
        users = main.server.database.ReadAll.readAll();
        double sumOfBalance = 0;
        for (int i = 0; i < main.server.database.Counter.count(); i++) {
            sumOfBalance += users[i].getBankAccountBalance();
        }

        document.add(new Paragraph(String.valueOf(sumOfBalance)));


        document.add(new Paragraph("sum of users:"));
        document.add(new Paragraph(String.valueOf(main.server.database.Counter.count())));
        // step 5
        document.close();
    }


}
