package com.example.researcharticlehub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArticleController {
    @javafx.fxml.FXML
    public TextField titleTextField;
    @javafx.fxml.FXML
    public TextField authorTextField;
    @javafx.fxml.FXML
    public ComboBox <String> articleTypeComboBox;
    //public ComboBox <String> searchCitationStatusComboBox;
    @javafx.fxml.FXML
    public TextField noOfAuthorTextField;
    @javafx.fxml.FXML
    public Label errorText;
    @javafx.fxml.FXML
    public TableView <Article> articleTableView;
    @javafx.fxml.FXML
    public TableColumn <Article,String> titleCol;
    @javafx.fxml.FXML
    public TableColumn <Article,String> authorCol;
    @javafx.fxml.FXML
    public TableColumn <Article,String> articleTypeCol;
    @javafx.fxml.FXML
    public TableColumn <Article, LocalDate> publicationDateCol;
    @javafx.fxml.FXML
    public TableColumn <Article,Integer> noOfAuthorCol;
    @javafx.fxml.FXML
    public TableColumn <Article,Boolean> citationStatusCol;
    @javafx.fxml.FXML
    public ComboBox <String> searchArticleTypeComboBox;
    public ComboBox <String> citationStatusComboBox;
    @javafx.fxml.FXML
    public TextField yearOneTextField;
    @javafx.fxml.FXML
    public TextField yearSecondTextField;
    @javafx.fxml.FXML
    public TextArea highestNumberOFAuthorTextArea;
    @javafx.fxml.FXML
    public DatePicker publicationDatePicker;
    @javafx.fxml.FXML
    private CheckBox searchYesCheckBox;
    @javafx.fxml.FXML
    private CheckBox citationYesCheckBox;
    @javafx.fxml.FXML
    private CheckBox citationNoCheckBox;
    @javafx.fxml.FXML
    private CheckBox searchNoCheckBox;
    //public Button handleSearchrticleButton;

    public void initialize(){
        articleTypeComboBox.getItems().addAll(
                "Review",
                "Book",
                "Case");
        //citationStatusComboBox.getItems().addAll("Yes","No");
        searchArticleTypeComboBox.getItems().addAll("Review",
                "Book",
                "Case");
        //searchCitationStatusComboBox.getItems().addAll("Yes","No");

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        articleTypeCol.setCellValueFactory(new PropertyValueFactory<>("articleType"));
        publicationDateCol.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        citationStatusCol.setCellValueFactory(new PropertyValueFactory<>("citationStatus"));
        noOfAuthorCol.setCellValueFactory(new PropertyValueFactory<>("noOfAuthor"));
    }
    ArrayList<Article> articleList = new ArrayList<>();
    @javafx.fxml.FXML
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
        Boolean citationStatus = citationYesCheckBox.isSelected();
        int noOfAuthor = Integer.parseInt(noOfAuthorTextField.getText());
        Article a = new Article(title,author,articleType,publicationDAte,citationStatus,noOfAuthor);
        articleList.add(a);
        articleTableView.getItems().clear();
        articleTableView.getItems().addAll(articleList);
        errorText.setText("Article Saved!!");
    }

    @javafx.fxml.FXML
    public void handleYearButtonButton(ActionEvent actionEvent) {
        String startInput = yearOneTextField.getText().trim();
        String endInput = yearSecondTextField.getText().trim();
        if(startInput.isEmpty()||endInput.isEmpty()){
            errorText.setText("Plz enter both years");
            return;
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

    @javafx.fxml.FXML
    public void handleArticleWithHighestNumberOfAuthorButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSearchArticleButton(ActionEvent actionEvent) {
    String selectedType = searchArticleTypeComboBox.getValue();
    Boolean selectedCitationStatus = null;
    if(searchYesCheckBox.isSelected()){
        selectedCitationStatus=true;
    } else if (searchNoCheckBox.isSelected()) {
        selectedCitationStatus=false;
    }
    ArrayList<Article> filteredList = new ArrayList<>();
    for(Article a : articleList){
        boolean matchedType = a.getArticleType().equals(selectedType);
        boolean matchedCitation = a.getCitationStatus().equals(selectedCitationStatus);
        if(matchedType&&matchedCitation){
            filteredList.add(a);
        }
    }
    articleTableView.getItems().clear();
    articleTableView.getItems().addAll(filteredList);
    errorText.setText("Search Complete!");
    }
}
