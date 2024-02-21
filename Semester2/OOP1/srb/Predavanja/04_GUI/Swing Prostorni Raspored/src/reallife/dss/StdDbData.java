package reallife.dss;

import org.w3c.dom.Document;

public class StdDbData {

	public String url;

	public String driver;
	
	public String username;
	
	public String password;

	public Document content;
	
	public StdDbData() {
		
	}
	
	public StdDbData(StdDbData db) {
		this.url = db.url;
		this.driver = db.driver;
		this.username = db.username;
		this.password = db.password;
	}
	
}
