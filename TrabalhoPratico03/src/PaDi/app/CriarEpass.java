package PaDi.app;


import javafx.fxml.FXML;
import PaDi.app.EPasses;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CriarEpass {
    @FXML
    private TextField nomeJovem;
    @FXML
    private TextField moradaJovem;
    @FXML
    private TextField codDoc;
    @FXML
    private ComboBox selecao;

    String nome = nomeJovem.getText().trim();
    String morada = moradaJovem.getText().trim();
    String codigoDoc = codDoc.getText().trim();
    String tipoDoc = selecao.toString();

   /// DocumentoID doc = new DocumentoID(codigoDoc,tipoDoc);
    //EpassJovem novo = new EpassJovem(doc,nome,);

}
