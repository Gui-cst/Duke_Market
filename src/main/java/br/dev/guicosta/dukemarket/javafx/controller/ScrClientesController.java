package br.dev.guicosta.dukemarket.javafx.controller;

import br.dev.guicosta.dukemarket.javafx.DAO.ClienteDAO;
import br.dev.guicosta.dukemarket.javafx.model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sesi2dia
 */
public class ScrClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tblCliente;

    @FXML
    private TableColumn<Cliente, Integer> tcoId;

    @FXML
    private TableColumn<Cliente, String> tcoCpf;

    @FXML
    private TableColumn<Cliente, String> tcoNome;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnSair;

    private Cliente clienteSelecionado;
    private boolean flagNovo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindColumns();
        carregaDados();
        flagNovo = false;
    }

    public void bindColumns() {

        tcoId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        tcoCpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));

        tcoNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
    }

    public void carregaDados() {
        ClienteDAO cDAO = new ClienteDAO();
        tblCliente.setItems(cDAO.getResults());
    }

    @FXML
    public void btnNovoClick() {

        txtId.setText("");
        txtCpf.setText("");
        txtNome.setText("");

        txtId.setDisable(true);

        clienteSelecionado = null;
        flagNovo = true;

        tblCliente.getSelectionModel().clearSelection();

        txtCpf.requestFocus();
    }

    @FXML
    public void btnSalvarClick() {

        if (txtCpf.getText().isEmpty()
                || txtNome.getText().isEmpty()) {

            System.out.println("Preencha todos os campos!");
            return;
        }

        Cliente c = new Cliente();

        c.setCpf(txtCpf.getText());
        c.setNome(txtNome.getText());

        ClienteDAO cDAO = new ClienteDAO();

        if (flagNovo) {

            cDAO.create(c);

        } else {

            if (clienteSelecionado == null) {
                System.out.println("Selecione um cliente!");
                return;
            }

            c.setId(clienteSelecionado.getId());
            cDAO.update(c);
        }

        carregaDados();

        txtId.setText("");
        txtCpf.setText("");
        txtNome.setText("");

        txtId.setDisable(true);

        clienteSelecionado = null;
        flagNovo = false;
    }

    @FXML
    public void tblClienteOnMouseClicked() {

        clienteSelecionado = tblCliente
                .getSelectionModel()
                .getSelectedItem();

        if (clienteSelecionado != null) {

            txtId.setText(
                    String.valueOf(clienteSelecionado.getId()));

            txtCpf.setText(clienteSelecionado.getCpf());

            txtNome.setText(clienteSelecionado.getNome());

            txtId.setDisable(true);

            flagNovo = false;
        }
    }

    @FXML
    public void btnSairClick() {


        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}