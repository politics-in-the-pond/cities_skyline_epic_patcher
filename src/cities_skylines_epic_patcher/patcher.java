package cities_skylines_epic_patcher;

import java.io.IOException;

public class patcher {
	public void do_patch(String directory, String code) throws IOException {
		main_ui.log("start patch.");
		downloader dl = new downloader();
		unzip uz = new unzip();
		temp_remover tr = new temp_remover();
		dl.download(code);
		uz.do_unzip(directory, code);
		tr.remove(code);
	}
}
