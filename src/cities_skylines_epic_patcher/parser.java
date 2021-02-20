package cities_skylines_epic_patcher;

public class parser {
	public String link_parser(String link) {
		link = link.replace("&searchtext=", "");
		link = link.replace("https://", "");
		link = link.replace("steamcommunity.com/sharedfiles/filedetails/?id=", "");
		main_ui.log("parsing complete.");
		return link;
	}
}
