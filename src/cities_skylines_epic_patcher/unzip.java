package cities_skylines_epic_patcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class unzip {
	public void do_unzip(String directory, String code) throws IOException {
		String document = System.getProperty("user.home") + "\\Documents\\cs_patch_temp\\";
		String filename = code + ".zip";
		ZipInputStream zin = new ZipInputStream(new FileInputStream(document + filename));
		ZipEntry entry = null;
		while ((entry = zin.getNextEntry()) != null) {
			main_ui.log("Unzip file (" + entry.getName() + ") to path : " + directory);
			File file = new File(directory + entry.getName());
			if (entry.isDirectory()) {
				file.mkdir();
			} else {
				OutputStream zout = new FileOutputStream(file);
				byte[] buf = new byte[4096];
				int len;
				while ((len = zin.read(buf)) > 0) {
					zout.write(buf, 0, len);
				}
				zout.close();
			}
			zin.closeEntry();
		}
		zin.close();
	}
}
