package reallife.dss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StartupBatData {

	public String JAVA_HOME;
	public String IP;
	public String text;

	public StartupBatData(File startupBatFile) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(startupBatFile));
		this.JAVA_HOME = extract(in.readLine());
		this.IP = extract(in.readLine());
		this.text = in.readLine() + "\n";
		this.text += in.readLine();
		in.close();
	}

	public String extract(String s) {
		int idx = s.indexOf("=");
		return s.substring(idx + 1).trim();
	}
	
	public String toString() {
		return "SET JAVA_HOME=" + this.JAVA_HOME + "\nSET IP=" + this.IP + "\n" + this.text + "\n";
	}
}
