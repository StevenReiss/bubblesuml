package com.baselet.gui.filedrop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.baselet.control.Main;
import com.baselet.diagram.Notifier;
import com.baselet.generator.ClassDiagramConverter;

public class FileDropListener implements FileDrop.Listener {

	@Override
	public void filesDropped(File[] files) {
		boolean generateDiagram = false;
		List<String> filenames = new ArrayList<String>();
		for (File file : files) {
			try {
				String filename = file.getCanonicalPath();
				filenames.add(filename);
				if (isJavaFile(filename)) {
					generateDiagram = true;
				}
			} catch (IOException e) {
				// log.error("Cannot open file dropped", e);
				Notifier.getInstance().showError("Cannot open file dropped");
			}
		}
        
		if (generateDiagram) {
			new ClassDiagramConverter().createClassDiagrams(filenames);
		}
		else {
			for (String filename : filenames) {
				Main.getInstance().doOpen(filename);
			}
		}
	}

	private boolean isJavaFile(String filename) {
		int dotPosition = filename.lastIndexOf(".");
		String extension = filename.substring(dotPosition + 1, filename.length());
		if (extension.equals("class") || extension.equals("java")) {
			return true;
		}
		return false;
	}
}
