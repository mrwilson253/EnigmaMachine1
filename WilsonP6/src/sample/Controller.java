package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    Enigma enigma = new Enigma();
    private int key = 1;
    private String message, codedMessage;
    GEnigma gEnigma = new GEnigma();
    PrimesEnigma primesEnigma = new PrimesEnigma();
    Highlander highlander = new Highlander();
    Enigma[] e = {gEnigma, primesEnigma, highlander};

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private RadioButton rbtCustomKey;

    @FXML
    private ToggleGroup rbtgrpKey;

    @FXML
    private RadioButton rbtGenKey;

    @FXML
    private TextField txtCustomKey;

    @FXML
    private Button btnDecode;

    @FXML
    private TextField txtMessage;

    @FXML
    private RadioButton rbtGEnigma;

    @FXML
    private ToggleGroup rbtgrpEnigmaType;

    @FXML
    private RadioButton rbtPrimesEnigma;

    @FXML
    private RadioButton rbtHighEnigma;

    @FXML
    private Button btnEncode;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtMessageSpecial;

    @FXML
    private TextField txtCoded;

    @FXML
    private TextField txtSpecialEnigma;

    @FXML
    private TextField txtKey;

    public void clear(){
        txtMessage.setText(null);
        txtKey.setText(null);
        txtCoded.setText(null);
        txtCustomKey.setText(null);
        txtMessageSpecial.setText(null);
        txtSpecialEnigma.setText(null);
        rbtGenKey.setSelected(true);
        rbtGEnigma.setSelected(true);
    }

    @FXML
    void onActionClear(ActionEvent event) {
        clear();
    }

    @FXML
    void onActionDecode(ActionEvent event) {
        enigma.setCodedMessage(codedMessage, key);
        txtMessage.setText(enigma.getMessage());
    }

    @FXML
    void onActionEncode(ActionEvent event) {
        //Get the message from the form and assign into message
        try {
            message = txtMessage.getText();
            //check if radio button for enterKey isSelected()
            //if yes, then get the key and parse it and then you can
            if (rbtCustomKey.isSelected()) {
                key = Integer.parseInt(txtCustomKey.getText());
                //call enigma.setMessage(message, key);
                enigma.setMessage(message, key);
            } else {
                //otherwise, just call enigma.setMessage(message);
                enigma.setMessage(message);
            }
            codedMessage = enigma.getCodedMessage();
            //set codedMessage and key into the display
            txtCoded.setText(enigma.getCodedMessage());
            String sKey = Integer.toString(enigma.getKey());
            txtKey.setText(sKey);
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, "Error!\n\"Custom Key\" must have an integer value if selected.");
            clear();
        }
    }

    @FXML
    void onActionMenuAbout(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "A modern and simplified twist based on the Enigma Machine of WWII.");
    }

    @FXML
    void onActionMenuOpen(ActionEvent event) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);

        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
                codedMessage = inputFile.nextLine();
                key = inputFile.nextInt();
                inputFile.close();
                txtCoded.setText(codedMessage);
                String sKey = Integer.toString(key);
                txtKey.setText(sKey);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    void onActionMenuSave(ActionEvent event) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Save File Dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(filename);
                outputFile.println(enigma.getCodedMessage());
                outputFile.println(enigma.getKey());

                outputFile.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clear();
    }
}
