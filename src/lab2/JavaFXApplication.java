/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;


/**
 *
 * @author maesl
 */
public class JavaFXApplication extends Application{

    
    File currentFile = null;
    
    
    
    MenuBar bar;
    TextArea textArea;
    
    Boolean isSaved = true;
    
    Menu file;
    Menu edit;
    Menu help;
    
    BorderPane root;
    
    MenuItem newItem1;
    MenuItem newItem2;
    MenuItem newItem3;
    MenuItem newItem4;

    MenuItem editUndo;
    MenuItem editCut;
    MenuItem editCopy;
    MenuItem editPaste;
    MenuItem editDelete;
    MenuItem editSelectAll;
    
    SeparatorMenuItem separator = new SeparatorMenuItem();
    SeparatorMenuItem separator1 = new SeparatorMenuItem();
    SeparatorMenuItem separator2 = new SeparatorMenuItem();
    SeparatorMenuItem separator3 = new SeparatorMenuItem();
    SeparatorMenuItem separator4 = new SeparatorMenuItem();
    SeparatorMenuItem separator5 = new SeparatorMenuItem();

    MenuItem helpItem1;

    TextArea area;
    Dialog<String> dialog;
    
    
    public void saveToFile(Stage stage) {
        if (currentFile == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Text File");
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                currentFile = file;
            }
        }
        if (!isSaved) {
            try (FileWriter fileWriter = new FileWriter(currentFile, false)) {
                fileWriter.write(textArea.getText());
                isSaved = true;
                setTitle(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
       public void loadFileContent(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            currentFile = selectedFile;
            setTitle(stage);
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String currentLine, content = "";
                while ((currentLine = reader.readLine()) != null) {
                    content += currentLine + "\n";
                }
                textArea.setText(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentFile = selectedFile;
            isSaved = true;
            setTitle(stage);
        }
    }
    
    
    
    
    public void setTitle(Stage s) {
        String title;
        if (currentFile == null)
            title = (!isSaved ? "*" : "") + "Untitled - Islam Sulaiman:Notepad";
        else
            title = (!isSaved ? "*" : "") + currentFile.getName() + "- Notepad";
        s.setTitle(title);
    }
    
    
    
    @Override
    public void init() throws Exception{
        
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        
        textArea = new TextArea();

        textArea.setPrefRowCount(35);
        textArea.setPrefColumnCount(50);

        
        
        
        
        bar = new MenuBar();
        
        file = new Menu("File");
        
        newItem1 = new MenuItem("New");
        newItem1.setStyle("-fx-font:normal bold 12px 'serif'");
        newItem1.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));

        newItem2 = new MenuItem("Open");
        newItem2.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        
        
        newItem3 = new MenuItem("Save");
        newItem3.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));     
        
        
        newItem4 = new MenuItem("Exit");
        newItem4.setStyle("-fx-font:normal bold 12px 'serif'");
        
        
        edit = new Menu("Edit");
        
        editUndo = new MenuItem("Undo");
        editUndo.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        editUndo.addEventHandler(ActionEvent.ACTION , (e)-> textArea.undo());
        
        editCut = new MenuItem("Cut");
        editCut.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        editCut.addEventHandler(ActionEvent.ACTION, (e) -> textArea.cut());
        
        editCopy = new MenuItem("Copy");
        editCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+c"));
        editCopy.addEventHandler(ActionEvent.ACTION, (e) -> textArea.copy());
        
        
        editPaste = new MenuItem("Paste");
        editPaste.setAccelerator(KeyCombination.keyCombination("Ctrl+v"));
        editPaste.addEventHandler(ActionEvent.ACTION, (e) -> textArea.paste());    
        
        editDelete = new MenuItem("Delete");
        editDelete.setAccelerator(KeyCombination.keyCombination("Ctrl+d"));
        editDelete.addEventHandler(ActionEvent.ACTION, (e) -> textArea.deleteText(textArea.getSelection()));

        
        editSelectAll = new MenuItem("Select All");
        editSelectAll.setAccelerator(KeyCombination.keyCombination("Ctrl+a"));
        editSelectAll.addEventHandler(ActionEvent.ACTION, (e) -> textArea.selectAll());
        
        
        help = new Menu("Help");
        
        MenuItem helpAbout = new MenuItem("About Notepad");
        helpAbout.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notepad Section");
                alert.setHeaderText("Notepad");
                String s ="Islam Suliaman  ";
                alert.setContentText(s);
                alert.show();
            }
        });
        help.getItems().add(helpAbout);        
        
        
        

        file.getItems().addAll(newItem1, newItem2, newItem3, newItem4);
        edit.getItems().addAll(editUndo, editCut, editCopy, editPaste, editDelete, editSelectAll);
        
        bar.getMenus().addAll(file, edit, help);
        

    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       

//      Event handler for file tab 

        newItem1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        
            public void handle(ActionEvent e) {
            if (textArea.getText().trim().isEmpty()) {
                textArea.clear();
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save?", ButtonType.YES,
                        ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    textArea.setText("");
                } 
                else if (alert.getResult() == ButtonType.YES) {
                    saveToFile(primaryStage);
                }
            }
        }
    });
        
    
        newItem2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (textArea.getText().trim().isEmpty() || isSaved) {
                    loadFileContent(primaryStage);
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save before opening new file?",
                            ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.NO) {
                        loadFileContent(primaryStage);
                    } else if (alert.getResult() == ButtonType.YES) {
                        saveToFile(primaryStage);
                    }
                }
            }
        });
        
        
        newItem3.addEventHandler(ActionEvent.ACTION, (e)->{
            if(isSaved) return;
            saveToFile(primaryStage);
        });
        
        
        
        newItem4.addEventHandler(ActionEvent.ACTION, (e)->{
            primaryStage.close();
        });
        
        

        textArea = new TextArea();

        textArea.setPrefRowCount(35);
        textArea.setPrefColumnCount(50);
        textArea.textProperty().addListener((observer, oldValue, newValue) -> {
            isSaved = false;
            setTitle(primaryStage);
        });

        
        
//        BorderPane pane = new BorderPane();
//        pane.setTop(bar);

        
        
        VBox vbox = new VBox(bar);
        vbox.getChildren().add(textArea);
        
        Scene scene = new Scene(vbox, 600, 600);      
        
        primaryStage.setTitle("Islam Sulaiman's Notepad");
        primaryStage.setScene(scene);
        primaryStage.show();
       
        
    }
    
}
