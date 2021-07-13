package PaDi;

import PaDi.backend.app.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static java.lang.System.gc;
import static java.lang.System.out;

public class Controller implements Initializable {

    //Declarações
    @FXML
    public DatePicker dataNasc;

    @FXML
    private ComboBox tipoDoc;

    @FXML
    private TextField nome, morada, codDoc, email, tel, nomeEscola, anoEscola, zonas, valor, codigo,codigoOutro;

    @FXML
    private Button criarButton, voltarButton, studentButton, jovemButton,
            funcioButton, terIdadeButton, festButton, turButton, carregarButton, pagarButton,
            passaSaldo, listaPasses, iniciar, criar,alterar;

    @FXML
    private ListView<EpassStandard> lista = new ListView<>();

    @FXML
    private TextArea detalhesPass;

    @FXML
    private Label infoSucess, infoInsucess, infoInexistente;

    private static EpassesData dados;

    //Metodo para inicializar
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] filename = location.toString().split("/");

        if (filename[filename.length - 1].equals("iniciar.fxml")) {
            dados = new EpassesData();
            dados.carregar();
        }
    }


    // Metodos para reendirecionar para paginas
    public void iniciar()throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) iniciar.getScene().getWindow();
        window.setScene(new Scene(root));
    } //

    public void criarPasse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/criar/criarPasse.fxml"));
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
    public void tranferir() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/passaSaldo/passaSaldo.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) passaSaldo.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void listar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/listar/listar.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) listaPasses.getScene().getWindow();
        window.setScene(new Scene(root));
        listarPasses();
    }
    public void alterar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/alterar/alterar.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) alterar.getScene().getWindow();
        window.setScene(new Scene(root));
        listarPasses();
    }

    // Métodos para criar Epasses
    public void criarEpass() throws IOException {
        try {
            String nome = this.nome.getText().trim();
            String morada = this.morada.getText().trim();
            String codigoID = codDoc.getText().trim();
            String email = this.email.getText().trim();
            LocalDate ddn = dataNasc.getValue();

            int telef = Integer.parseInt(tel.getText().trim());

            String tipoDoc = (String) this.tipoDoc.getValue();
            TipoDocumento tipoDocumento;

            if (tipoDoc.equals("BI")){
                tipoDocumento = TipoDocumento.BI;
            } else if (tipoDoc.equals("CNI")){
                tipoDocumento = TipoDocumento.CNI;
            } else
                tipoDocumento =TipoDocumento.PASSAPORTE;

            if(criar.getText().equalsIgnoreCase("CRIAR JOVEM")){
                for (int i = 0; i < dados.tamanho(); i++) {
                    if((dados.getTodoItems().get(i).getTitular().getdocID().getCodigo()).equals(codigoID)){
                        infoSucess.setText("");
                        infoInexistente.setText("ESTE PASSE JA EXISTE!");
                        infoInsucess.setText("");
                        return;
                    }
                }
                EpasseJovem novo = new EpasseJovem(new DocumentoID(codigoID, tipoDocumento),nome, ddn, morada, email, telef);
                dados.addEpass(novo);
                writeCsv(dados.getTodoItems().getClass().getSimpleName(),codigoID,nome,email,telef,ddn,morada);

            } else if (criar.getText().equalsIgnoreCase("CRIAR TERCEIRA IDADE")){
                for (int i = 0; i < dados.tamanho(); i++) {
                    if((dados.getTodoItems().get(i).getTitular().getdocID().getCodigo()).equals(codigoID)){
                        infoSucess.setText("");
                        infoInexistente.setText("ESTE PASSE JA EXISTE!");
                        infoInsucess.setText("");
                        return;
                    }
                }
                EpasseTI novo = new EpasseTI(new DocumentoID(codigoID, tipoDocumento),nome, ddn, morada, email, telef);
                dados.addEpass(novo);
                writeCsv(dados.getTodoItems().getClass().getSimpleName(),codigoID,nome,email,telef,ddn,morada);

            } else if (criar.getText().equalsIgnoreCase("CRIAR FUNCIONARIO")){
                for (int i = 0; i < dados.tamanho(); i++) {
                    if((dados.getTodoItems().get(i).getTitular().getdocID().getCodigo()).equals(codigoID)){
                        infoSucess.setText("");
                        infoInexistente.setText("ESTE PASSE JA EXISTE!");
                        infoInsucess.setText("");
                        return;
                    }
                }
                EpasseFuncionario novo = new EpasseFuncionario(new DocumentoID(codigoID, tipoDocumento),nome, ddn, morada, email, telef);
                dados.addEpass(novo);
                writeCsv(dados.getTodoItems().getClass().getSimpleName(),codigoID,nome,email,telef,ddn,morada);
            }

            infoInexistente.setText("");
            infoInsucess.setText("");
            infoSucess.setText("EPASS CRIADO!");
            this.nome.setText("");
            this.morada.setText("");
            this.tel.setText("");
            codDoc.setText("");
            this.email.setText("");
            dataNasc.setValue(null);

            dados.listar();
        } catch (ArrayIndexOutOfBoundsException e){
            infoInexistente.setText("");
            infoInsucess.setText("NOME ESTÁ SEM APELIDO!");
            infoSucess.setText("");
        } catch (NullPointerException e){
            infoInexistente.setText("INSIRA OS DADOS!");
            infoInsucess.setText("");
            infoSucess.setText("");
        } catch(NumberFormatException e){
            infoInexistente.setText("");
            infoInsucess.setText("DADOS INCORRETOS!");
            infoSucess.setText("");
        }
    }

    public void criarEpassEstudante(){
        try{
            String nome = this.nome.getText().trim();
            String morada = this.morada.getText().trim();
            String codigoID = codDoc.getText().trim();
            String email = this.email.getText().trim();
            LocalDate ddn = dataNasc.getValue();
            String nEscola = nomeEscola.getText().trim();

            String aEsc = anoEscola.getText().trim();
            int aEscola = Integer.parseInt(aEsc);

            int telef = Integer.parseInt(tel.getText().trim());

            String tipoDoc = (String) this.tipoDoc.getValue();
            TipoDocumento tipoDocumento;

            if (tipoDoc.equals("BI")){
                tipoDocumento = TipoDocumento.BI;
            } else if (tipoDoc.equals("CNI")){
                tipoDocumento = TipoDocumento.CNI;
            } else
                tipoDocumento =TipoDocumento.PASSAPORTE;

            for (int i = 0; i < dados.tamanho(); i++) {
                if((dados.getTodoItems().get(i).getTitular().getdocID().getCodigo()).equals(codigoID)){
                    infoSucess.setText("");
                    infoInexistente.setText("ESTE PASSE JÁ EXISTE!");
                    infoInsucess.setText("");
                    return;
                }
            }
            EpasseStudent novo = new EpasseStudent(new DocumentoID(codigoID, tipoDocumento), nome, ddn, morada, email, telef, nEscola, aEscola);
            dados.addEpass(novo);
            writeCsv(dados.getTodoItems().getClass().getSimpleName(),codigoID,nome,email,telef,ddn,morada);


            infoInexistente.setText("");
            infoInsucess.setText("");
            infoSucess.setText("EPASS CRIADO!");
            this.nome.setText("");
            this.morada.setText("");
            this.tel.setText("");
            codDoc.setText("");
            this.email.setText("");
            dataNasc.setValue(null);

            dados.listar();

        }catch (ArrayIndexOutOfBoundsException e){
            infoInexistente.setText("");
            infoInsucess.setText("NOME ESTÁ SEM APELIDO!");
            infoSucess.setText("");
        } catch (NullPointerException e){
            infoInexistente.setText("INSIRA OS DADOS!");
            infoInsucess.setText("");
            infoSucess.setText("");
        } catch(NumberFormatException e){
            infoInexistente.setText("");
            infoInsucess.setText("DADOS INCORRETOS!");
            infoSucess.setText("");
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCsv(String tipo, String codigoID, String nome, String email, int telef, LocalDate ddn, String morada) throws IOException {
        List<List<String>> rows = Arrays.asList(Arrays.asList(tipo,String.valueOf(codigoID),nome, email, String.valueOf(telef) ,ddn.toString(),morada));

        FileWriter csvWriter = new FileWriter("src/Padi/data/data.csv", true);


        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

    }


    //Metodo para pagar um Epasse
    public void pagarEpass(){
        try {
            int codigo =Integer.parseInt(this.codigo.getText().trim());
            String nome = this.nome.getText().trim();
            for (int i = 0; i < dados.tamanho() ; i++) {
                if((dados.getTodoItems().get(i).getCodigo()==codigo && dados.getTodoItems().get(i).getTitular().getNome().equalsIgnoreCase(nome))) {
                    if(dados.getTodoItems().get(i).getSaldo()>40){
                        if(dados.getTodoItems().get(i).getClass().getSimpleName().equalsIgnoreCase("EpasseStudent")){
                            dados.getTodoItems().get(i).setSaldo(dados.getTodoItems().get(i).getSaldo()-20);
                            dados.getTodoItems().get(i).setNumeroViagens(dados.getTodoItems().get(i).getNumeroViagens()+1);
                            System.out.println("Epass Pagado");
                            infoInsucess.setText("");
                            infoInexistente.setText("");
                            infoSucess.setText("PAGO!");
                            this.codigo.setText("");
                            this.nome.setText("");
                            return;

                        }else
                            dados.getTodoItems().get(i).setSaldo(dados.getTodoItems().get(i).getSaldo()-40);
                            dados.getTodoItems().get(i).setNumeroViagens(dados.getTodoItems().get(i).getNumeroViagens()+1);
                            System.out.println("Epass Pagado");
                            infoInsucess.setText("");
                            infoInexistente.setText("");
                            infoSucess.setText("PAGO!");
                            this.codigo.setText("");
                            this.nome.setText("");
                            return;
                    }
                    infoSucess.setText("");
                    infoInexistente.setText("");
                    infoInsucess.setText("PASSE SEM SALDO!");
                    this.codigo.setText("");
                    this.nome.setText("");
                    System.out.println("Passe sem saldo");
                    return;
                }
            }
            infoInsucess.setText("");
            infoSucess.setText("");
            this.codigo.setText("");
            this.nome.setText("");
            infoInexistente.setText("PASSE NÃO EXISTE");
            System.out.println("Epass não existe");
        }catch (NullPointerException | NumberFormatException e ){
            this.codigo.setText("");
            this.nome.setText("");
            infoSucess.setText("");
            infoInsucess.setText("");
            infoInexistente.setText("INSIRA OS DADOS!");

        }
    }


    // Metodo para Carregar um Epasse
    public void carregarEpass(){
        try{
            int codigo =Integer.parseInt(this.codigo.getText().trim());
            String nome = this.nome.getText().trim();
            int valor = Integer.parseInt(this.valor.getText().trim());
            for (int i = 0; i < dados.tamanho() ; i++) {
                if((dados.getTodoItems().get(i).getCodigo()==codigo && dados.getTodoItems().get(i).getTitular().getNome().equalsIgnoreCase(nome))){
                    if(valor>0){
                        dados.getTodoItems().get(i).setSaldo(dados.getTodoItems().get(i).getSaldo()+valor);
                        dados.getTodoItems().get(i).setCarregamento(LocalDate.now());
                        dados.getTodoItems().get(i).setValidade(LocalDate.now().plusDays(30));
                        infoInsucess.setText("");
                        infoInexistente.setText("");
                        infoSucess.setText("PASSE CARREGADO!");
                        this.codigo.setText("");
                        this.nome.setText("");
                        this.valor.setText("0");
                        return;
                    }
                    infoSucess.setText("");
                    infoInexistente.setText("");
                    infoInsucess.setText("SALDO NEGATIVO!");
                    System.out.println("Saldo n pode ser negativo");
                    return;
                }
            }
            this.codigo.setText("");
            this.nome.setText("");
            infoInsucess.setText("");
            infoSucess.setText("");
            infoInexistente.setText("INSIRA OS DADOS!");

        }catch(NullPointerException | NumberFormatException e){
            infoInsucess.setText("");
            infoSucess.setText("");
            infoInexistente.setText("INSIRA OS DADOS!");
        }


    }


    //Metodos para ver Epasses no Listar
    public void listarPasses(){
        System.out.println(lista.getItems());
        lista.getItems().setAll(dados.getTodoItems());
        lista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lista.getSelectionModel().selectFirst();
    }

    public void verDetalhes(){
        EpassStandard item = (EpassStandard) lista.getSelectionModel().getSelectedItem();
        String tipo = null;

        System.out.println(item.getClass().getSimpleName());

        if(item.getClass().getSimpleName().equalsIgnoreCase("EPASSEJOVEM")){
            tipo="jovem";
        }else if(item.getClass().getSimpleName().equalsIgnoreCase("EPASSESTUDENT")){
            tipo="estudante";
        }else if(item.getClass().getSimpleName().equalsIgnoreCase("EPASSETI")){
            tipo="terceira idade";
        }else if(item.getClass().getSimpleName().equalsIgnoreCase("EPASSEFUNCIONARIO")){
            tipo="funcionário";
        }
        detalhesPass.setText(
                "\nTipo de Epass: "+ tipo.toUpperCase() +
                "\nNome: "+ item.getTitular().getNome().toUpperCase() +
                "\nEmail: " + item.getTitular().getEmail() +
                "\nMorada: "+ item.getTitular().getMorada().toUpperCase() +
                "\nCódigo Documento: " + item.getTitular().getdocID().getCodigo().toUpperCase() +
                "\nIdade: " + item.getTitular().getDdn() +
                "\nData de emissão: " + item.getEmissao() +
                "\nSaldo Atual: " + item.getSaldo() +
                "\nPontos: " + item.getPontos() +
                "\nNumero de Viagens: " + item.getNumeroViagens() +
                "\nData Carregamento: " + item.getCarregamento() +
                 "\nValido até: " + item.getValidade()) ;
    }


    //Metodopara transferir saldo
    public void transferirSaldo(){
       try {
           int codigo= Integer.parseInt(this.codigo.getText().trim());
           String nome = this.nome.getText().trim();
           int valor = Integer.parseInt(this.valor.getText().trim());
           int outroCodigo = Integer.parseInt(codigoOutro.getText().trim());

           for (int i = 0; i < dados.tamanho(); i++) {
               if (dados.getTodoItems().get(i).getTitular().getNome().equalsIgnoreCase(nome) && dados.getTodoItems().get(i).getCodigo()==codigo){
                   for (int j = 0; j < dados.tamanho(); j++) {
                      if(dados.getTodoItems().get(j).getCodigo()==outroCodigo){
                          if(dados.getTodoItems().get(i).getSaldo()>valor && valor>0){
                            dados.getTodoItems().get(i).setSaldo(dados.getTodoItems().get(i).getSaldo()-valor);
                            dados.getTodoItems().get(j).setSaldo(dados.getTodoItems().get(j).getSaldo()+valor);

                            this.nome.setText("");
                            this.codigo.setText("");
                            this.valor.setText("");
                            this.codigoOutro.setText("");

                            infoInexistente.setText("");
                            infoInsucess.setText("");
                            infoSucess.setText("SALDO ENVIADO!");
                            return;
                          }else
                            infoInexistente.setText("");
                            infoInsucess.setText("SALDO INVÁLIDO!");
                            infoSucess.setText("");
                            return;
                      }else
                        infoInexistente.setText("DADOS INVÁLIDOS");
                        infoInsucess.setText("");
                        infoSucess.setText("");
                   }

               }
               infoInexistente.setText("EPASS NÃO EXISTE");
               infoInsucess.setText("");
               infoSucess.setText("");
           }
       }catch(NullPointerException | NumberFormatException e){
           infoInsucess.setText("");
           infoSucess.setText("");
           infoInexistente.setText("INSIRA OS DADOS!");
       }
         
    }

    public void alterarEpass(){
        try {
            int codigo = Integer.parseInt(this.codigo.getText().trim());
            String nome = this.nome.getText().trim();

            for (int i = 0; i < dados.tamanho(); i++){
                if(dados.getTodoItems().get(i).getCodigo()==codigo && dados.getTodoItems().get(i).getTitular().getNome().equalsIgnoreCase(nome)){
                    dados.getTodoItems().get(i).getTitular().setEmail(this.email.getText());
                    dados.getTodoItems().get(i).getTitular().setMorada(this.morada.getText());
                    dados.getTodoItems().get(i).getTitular().setTelef(Integer.parseInt(tel.getText()));
                }

            }

        }catch (Exception e){

        }

    }

    public void readCsv() throws IOException {
        try (Scanner scanner = new Scanner(new File("src/Padi/data/data.csv"))) {

            // Set the delimiter used in file
            scanner.useDelimiter(",");

            // Get all tokens and store them in some data structure
            // I am just printing them
            while (scanner.hasNext()) {
                System.out.print(scanner.next() + " ");
            }

            // Do not forget to close the scanner
            scanner.close();
        }
    }

}
