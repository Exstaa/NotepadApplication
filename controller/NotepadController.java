package az.developia.notepad.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import az.developia.notepad.file.ContentReader;
import az.developia.notepad.file.ContentSaver;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class NotepadController {

	@FXML
	private Menu fontFamilyMenu;

	@FXML
	private MenuItem addFontMenuItem;

	@FXML
	private ColorPicker fontColorPicker;

	@FXML
	private MenuItem fontSizeMenuItem;

	@FXML
	private Slider fontSizeSlider;

	@FXML
	private TextArea contentTextArea;

	@FXML
	private MenuItem openMenuItem;

	@FXML
	private MenuItem saveMenuItem;

	ArrayList<KeyCode> codes = new ArrayList<>();

	@FXML
	void addFontMenuItemClicked(ActionEvent event) {
		boolean allow = true;
		String fontName = JOptionPane.showInputDialog(null, "Type font family name");
		if (fontName != null) {
			if (fontName.trim().length() > 0) {
				ObservableList<MenuItem> menuItems = fontFamilyMenu.getItems();
				for (int i = 0; i < menuItems.size(); i++) {
					if (menuItems.get(i).getText().equals(fontName)) {
						allow = false;
					}
				}
				if (allow) {
					MenuItem menuItem = new MenuItem();
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent handleEvent) {
							fontFamilyClicked(handleEvent);
						}
					});
					menuItem.setText(fontName);
					fontFamilyMenu.getItems().add(menuItem);
				}
			}
		}
	}

	@FXML
	void fontFamilyClicked(ActionEvent event) {
		MenuItem fontFamilyMenuItem = (MenuItem) event.getTarget();
		contentTextArea.setStyle(contentTextArea.getStyle() + "-fx-font-family: " + fontFamilyMenuItem.getText() + ";");
	}

	@FXML
	void fontColorPickerPicked(ActionEvent event) {
		String color = "#" + fontColorPicker.getValue().toString().substring(2);
		contentTextArea.setStyle(contentTextArea.getStyle() + "-fx-text-fill:" + color + ";");
	}

	@FXML
	void fontSizeSliderDragged(MouseEvent event) {
		Integer size = (int) fontSizeSlider.getValue();
		fontSizeMenuItem.setText(size.toString());
		contentTextArea.setStyle(contentTextArea.getStyle() + "-fx-font-size:" + size + ";");
	}

	@FXML
	void openMenuItemClicked(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(extFilter);
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			String fileName = selectedFile.getName().toString();
			String extension = fileName.substring(fileName.length() - 4, fileName.length());
			if (extension.equals(".txt") && fileName.trim().length() > 4) {
				ContentReader reader = new ContentReader();
				String text = reader.read(selectedFile);
				contentTextArea.setText(text);
			}

		}
	}

	@FXML
	void saveMenuItemClicked(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(extFilter);
		File selectedFile = chooser.showSaveDialog(null);
		if (selectedFile != null) {
			String fileName = selectedFile.getName().toString();
			String extension = fileName.substring(fileName.length() - 4, fileName.length());
			if (extension.equals(".txt") && fileName.trim().length() > 4) {
				ContentSaver saver = new ContentSaver();
				saver.save(selectedFile, contentTextArea.getText());
			}
		}
	}

	@FXML
	void stageKeyReleased(KeyEvent event) {
		KeyCode code = event.getCode();
		codes.add(code);
		KeyCode ctrl = KeyCode.CONTROL;
		KeyCode s = KeyCode.S;
		KeyCode o = KeyCode.O;
		KeyCode plus = KeyCode.EQUALS;
		KeyCode minus = KeyCode.MINUS;
		if (codes.size() >= 2) {
			if (codes.get(codes.size() - 2) == ctrl && codes.get(codes.size() - 1) == s) {
				saveMenuItemClicked(null);
			}
			if (codes.get(codes.size() - 2) == ctrl && codes.get(codes.size() - 1) == o) {
				openMenuItemClicked(null);
			}
			if (codes.get(codes.size() - 2) == ctrl && codes.get(codes.size() - 1) == plus) {
				fontSizeSlider.setValue(fontSizeSlider.getValue() + 5);
				fontSizeSliderDragged(null);
			}
			if (codes.get(codes.size() - 2) == ctrl && codes.get(codes.size() - 1) == minus) {
				fontSizeSlider.setValue(fontSizeSlider.getValue() - 5);
				fontSizeSliderDragged(null);
			}
		}
	}
}
