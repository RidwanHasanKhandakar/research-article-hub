package com.example.researcharticlehub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

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
    ArrayList<Article> articleList = new ArrayList<>();
    public void handleAddArticleButton(ActionEvent actionEvent) {

        String noOfAuthorsInput = noOfAuthorTextField.getText().trim();
        if (noOfAuthorTextField.getText().trim().isEmpty()){
            errorText.setText("Plz enter number of authors");
            return;
        }
        try{
            Integer.parseInt(noOfAuthorsInput);
        }catch (NumberFormatException e){
            errorText.setText("number of authors must take int number");;
            return;
        }
        for(Article a:articleList){
            if(a.getTitle().equals(titleTextField.getText())){
                errorText.setText("Same Title!");
                return;
            }
        }
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String articleType = articleTypeComboBox.getValue();
        LocalDate publicationDAte = publicationDatePicker.getValue();
        String citationStatus = searchArticleTypeComboBox.getValue();
        int noOfAuthor = Integer.parseInt(noOfAuthorTextField.getText());
        Article a = new Article(title,author,articleType,publicationDAte,citationStatus,noOfAuthor);
        articleList.add(a);
        errorText.setText("Article Saved!!");
        articleTableView.getItems().clear();
        articleTableView.getItems().addAll(articleList);
    }

    public void handleYearButtonButton(ActionEvent actionEvent) {
        String startInput = yearOneTextField.getText().trim();
        String endInput = yearSecondTextField.getText().trim();
        if(startInput.isEmpty()||endInput.isEmpty()){
            errorText.setText("Plz enter voth years");
        }
        int startYear;
        int endYear;
        try{
            startYear=Integer.parseInt(startInput);
            endYear=Integer.parseInt(endInput);
        }catch (NumberFormatException e){
            errorText.setText("Years must contain only numbers");
            return;
        }
        if(startYear>endYear){
            errorText.setText("Start year cannot be greater than end year");;
            return;
        }
        ArrayList<Article> filteredList = new ArrayList<>();
        for(Article a: articleList){
            int publicationYear = a.getPublicationDate().getYear();
            if(publicationYear>=startYear&&publicationYear<=endYear){
                filteredList.add(a);
            }
        }
        articleTableView.getItems().clear();
        articleTableView.getItems().addAll(filteredList);
        errorText.setText("Filter applied!");
    }

    public void handleArticleWithHighestNumberOfAuthorButton(ActionEvent actionEvent) {
    }

    public void handleSearchArticleButton(ActionEvent actionEvent) {
    }
}
