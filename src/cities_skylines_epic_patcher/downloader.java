package cities_skylines_epic_patcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class downloader {
	public void download(String code) throws IOException {
		String urlstr = "http://workshop8.abcvg.info/archive/255710/" + code + ".zip";
		URL url = new URL(urlstr);
		String document = System.getProperty("user.home") + "\\Documents\\cs_patch_temp\\";
		File Folder = new File(document);
		if (!Folder.exists()) {
			try {
				Folder.mkdir();
				main_ui.log("directory created successfully. " + document);
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			main_ui.log("the Directory already exists. " + document);
		}
		File file = new File(document, code + ".zip");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = httpConn.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(file);
			int ReadCount = -1;
			byte[] buffer = new byte[4096];
			while ((ReadCount = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, ReadCount);
			}
			outputStream.close();
			inputStream.close();
			main_ui.log("File downloaded to " + file);
		} else {
			main_ui.log("download failed. HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}
}
