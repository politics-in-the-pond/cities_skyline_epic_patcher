package cities_skylines_epic_patcher;

import java.io.File;

public class temp_remover {
	public void remove(String code) {
		String document = System.getProperty("user.home") + "\\Documents\\cs_patch_temp\\";
		File file = new File(document, code + ".zip");
		if(file.exists()) {
			if(file.delete()) {
				main_ui.log("temporary file deletion completed");
			}else {
				main_ui.log("temporary file deletion failed");
			}
		}else {
			main_ui.log("temporary file does not exist");
		}
	}
}
