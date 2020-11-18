import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BlankPageFiller {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\lukas\\OneDrive - stud.h-da.de\\Master"));
        // Dialog zum Oeffnen von Dateien anzeigen

        int rueckgabeWert = chooser.showOpenDialog(null);

        /* Abfrage, ob auf "Öffnen" geklickt wurde */
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getName();
            String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

            if (fileExtension.equals(".pdf")) {
                PdfReader inputPdfReader = null;
                try {
                    inputPdfReader = new PdfReader(chooser.getSelectedFile().getAbsolutePath());
                    PdfStamper stamper = new PdfStamper(inputPdfReader, new FileOutputStream(chooser.getSelectedFile().getAbsolutePath() + "Test.pdf"));
                    for (int i = 2; i <= inputPdfReader.getNumberOfPages() + 1; i=i+2){
                        stamper.insertPage(i, inputPdfReader.getPageSizeWithRotation(1));
                        System.out.println("Inserted Page at: "+ i);
                    }
                    //stamper.insertPage(inputPdfReader.getNumberOfPages() + 1, inputPdfReader.getPageSizeWithRotation(1));
                    stamper.close();
                    inputPdfReader.close();
                    JOptionPane.showMessageDialog(null, "Done!");
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
