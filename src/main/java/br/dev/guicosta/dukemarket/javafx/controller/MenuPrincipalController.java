package br.dev.guicosta.dukemarket.javafx.controller;

import br.dev.guicosta.dukemarket.javafx.App;
import br.dev.guicosta.dukemarket.javafx.model.Produto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sesi2dia
 */
public class MenuPrincipalController implements Initializable {

    private Object txtCodBarras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void btnProdutosClick() throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("scrProdutos.fxml"));
            /*
            *if "fx:controller" is not set in fxml
            *fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 900, 400);
            Stage stage = new Stage();

            stage.setTitle("CRUD Produtos");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            
            Logger.getLogger(
                    MenuPrincipalController.class.getName()).
                    log(Level.SEVERE, "Falha ao criar a janela scrProdutos.", e);
            
        }

    }



}
