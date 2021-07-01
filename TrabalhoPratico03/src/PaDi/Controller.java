package PaDi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button criarButton, voltarButton, studentButton, jovemButton,
            funcioButton, terIdadeButton, festButton, turButton, carregarButton, pagarButton,
            passaSaldo;

    @FXML
    public void criarPasse() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarPasse.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) criarButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void voltarMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) voltarButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void voltarCriar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarPasse.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) voltarButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarStudent.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) studentButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarJovem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarJovem.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) jovemButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarFuncio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarFuncio.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) funcioButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarTeridade() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarTerIdade.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) terIdadeButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarFest() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarFest.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) festButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void criarTur() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/criar/criarTur.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) turButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void carregar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/carregar/carregarEpass.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) carregarButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void pagar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/pagar/pagarEpass.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) pagarButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void trasferir() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/passaSaldo/passaSaldo.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) passaSaldo.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
