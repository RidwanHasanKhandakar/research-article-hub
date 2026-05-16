package com.example.researcharticlehub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ArticleController {
    public TextField titleTextField;
    public TextField authorTextField;
    public ComboBox <String> articleTypeComboBox;
    public ComboBox <String> searchCitationStatusComboBox;
    public TextField noOfAuthorTextField;
    public Label errorText;
    public TableView <Article> articleTableView;
    public TableColumn <Article,String> titleCol;
    public TableColumn <Article,String> authorCol;
    public TableColumn <Article,String> articleTypeCol;
    public TableColumn <Article, LocalDate> publicationDateCol;
    public TableColumn <Article,Integer> noOfAuthorCol;
    public TableColumn <Article,String> citationStatusCol;
    public ComboBox <String> searchArticleTypeComboBox;
    public ComboBox <String> citationStatusComboBox;
    public TextField yearOneTextField;
    public TextField yearSecondTextField;
    public TextArea highestNumberOFAuthorTextArea;
    public DatePicker publicationDatePicker;
    //public Button handleSearchrticleButton;

    public void initialize(){
        articleTypeComboBox.getItems().addAll(
                "Review",
                "Book",
                "Case");
        citationStatusComboBox.getItems().addAll("Yes","No");
        searchArticleTypeComboBox.getItems().addAll("Review",
                "Book",
                "Case");
        searchCitationStatusComboBox.getItems().addAll("Yes","No");

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        articleTypeCol.setCellValueFactory(new PropertyValueFactory<>("articleType"));
        publicationDateCol.setCellValueFactory(new PropertyValueFactory<>("citationStatus"));
        noOfAuthorCol.setCellValueFactory(new PropertyValueFactory<>("noOfAuthor"));
    }

    public void handleAddArticleButton(ActionEvent actionEvent) {
    }

    public void handleSearchrticleButton(ActionEvent actionEvent) {
    }

    public void handleYearButtonButton(ActionEvent actionEvent) {
    }

    public void handleArticleWithHighestNumberOfAuthorButton(ActionEvent actionEvent) {
    }
}
