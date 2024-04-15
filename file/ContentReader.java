package az.developia.notepad.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ContentReader {
	private FileInputStream fis;

	public String read(File selectedFile) {
		String text = "";
		try {
			fis = new FileInputStream(selectedFile);
			byte[] simvollar = fis.readAllBytes();
			text = new String(simvollar);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

}
