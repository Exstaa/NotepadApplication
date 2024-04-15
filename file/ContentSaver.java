package az.developia.notepad.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ContentSaver {
     private FileOutputStream fos;
     public void save(File selectedFile, String content) {
    	 try {
			fos = new FileOutputStream(selectedFile);
			byte[] simvollar = content.getBytes();
			fos.write(simvollar);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
     }
}
