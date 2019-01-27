package fr.nmocs.library.batch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtils {

	public static Properties getProps(String fileUrl) {
		Properties props = new Properties();
		InputStream fileInput = null;

		try {
			fileInput = new FileInputStream(fileUrl);
			props.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

}
