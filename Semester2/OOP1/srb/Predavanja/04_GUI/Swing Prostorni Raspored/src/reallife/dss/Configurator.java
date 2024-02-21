package reallife.dss;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configurator extends JFrame {
	private static final long serialVersionUID = -8148132222526267715L;

	JPanel pnlPickWf = new JPanel();
	JLabel lblWf = new JLabel("Wildfly file path: ");
	JTextField tfWfPath = new JTextField("");
	JButton btnBrowse = new JButton("Browse");

	JPanel pnlSettings = new JPanel();
	JLabel lblEar = new JLabel("DSS EAR path: ");
	JTextField tfEarPath = new JTextField("");

	JLabel lblScriptsPath = new JLabel("Scripts path:");
	JTextField tfScriptsPath = new JTextField();

	JLabel lblMwUrl = new JLabel("MW URL:");
	JTextField tfMwUrl = new JTextField();

	JPanel pnlBottom = new JPanel();
	JButton btnImport = new JButton("Import");
	JButton btnExport = new JButton("Export");
	JButton btnSave = new JButton("Save");
	JButton btnExit = new JButton("Exit");

	JSeparator sep1 = new JSeparator(JSeparator.HORIZONTAL);
	JLabel lblStdDbUrl = new JLabel("DSS DB URL: ");
	JTextField tfStdUrl = new JTextField("");
//	JLabel lblStdDbDriver = new JLabel("DSS DB driver: ");
//	JTextField tfStdDriver = new JTextField("");
	JLabel lblStdDbUsr = new JLabel("DSS DB username: ");
	JTextField tfStdDbUsr = new JTextField("");
	JLabel lblStdDbPass = new JLabel("DSS DB password: ");
	JPasswordField tfStdDbPass = new JPasswordField("");
	JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
	JLabel lblJavaHome = new JLabel("JAVA_HOME: ");
	JTextField tfJavaHome = new JTextField("");
	JButton btnJHBrowse = new JButton("Browse");
	JLabel lblIp = new JLabel("DSS Server IP: ");
	JTextField tfIp = new JTextField("");

	INIFile ini = new INIFile("dss.ini");
	File earFile;
	File wfFile;
	File standaloneFile;
	File startupBatFile;

	File jarFile;
	File propFile;

	PropFileData propFileData = new PropFileData();
	StdDbData db = new StdDbData();
	StartupBatData startupBatConfig;
	Document standaloneXmlDocument;

	public Configurator() {
		setTitle("DSS Configurator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(new Dimension(ini.getInt("main", "width", 800), ini.getInt("main", "height", 400)));
		this.setLocation(ini.getInt("main", "x", 100), ini.getInt("main", "y", 100));

		findWildFly();

		addWfComponents();

		addDssComponents();

		addStandaloneComponents();

		getContentPane().add(pnlSettings, BorderLayout.CENTER);

		((FlowLayout) (pnlBottom.getLayout())).setAlignment(FlowLayout.RIGHT);
		pnlBottom.add(btnImport);
		pnlBottom.add(btnExport);
		pnlBottom.add(new JSeparator());
		pnlBottom.add(new JSeparator());
		pnlBottom.add(btnSave);
		pnlBottom.add(btnExit);
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);

		// JOptionPane.showMessageDialog(this, "Make sure that Wildfly is NOT
		// working.");
		openDSSConfig();
		openStandalone();
		openStartupBat();

		btnBrowse.addActionListener(e -> {
			browseWildFly();
			openDSSConfig();
			openStandalone();
			openStartupBat();
		});

		btnSave.addActionListener(e -> {
			saveDssConfig();
			try {
				saveStandaloneXml();
				saveStartupBat();
				JOptionPane.showMessageDialog(this, "Saved settings.");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error while saving standalone-prod.xml: " + ex.getMessage(),
						"Save error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnExit.addActionListener(e -> {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});


		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				configuratorWindowClosing();
			}
		});
	}


	private void findWildFly() {
		String wfPath = ini.getString("wf", "filePath", "");
		wfFile = new File(wfPath);
		if (wfFile.exists() && wfFile.isDirectory()) {
			try {
				ini.setString("wf", "filePath", wfFile.getCanonicalPath());
				findDssEar();
				findStandalone();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error while reading configuration files: " + e.getMessage(),
						"Reading configuration", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Could not find WildFly.", "Finding WildFly",
					JOptionPane.ERROR_MESSAGE);
			wfFile = null;
		}
	}

	private void saveStandaloneXml() throws Exception {
		NodeList datasources = standaloneXmlDocument.getDocumentElement().getElementsByTagName("datasource");
		for (int i = 0; i < datasources.getLength(); i++) {
			Node n = datasources.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) n;
				String poolName = n.getAttributes().getNamedItem("pool-name").getNodeValue();
				if (poolName.equals("TestDS")) {
					elem.getElementsByTagName("connection-url").item(0).getChildNodes().item(0)
							.setNodeValue(tfStdUrl.getText());
//					elem.getElementsByTagName("driver").item(0).getChildNodes().item(0)
//							.setNodeValue(tfStdDriver.getText());
					elem.getElementsByTagName("user-name").item(0).getChildNodes().item(0)
							.setNodeValue(tfStdDbUsr.getText());
					try {
						elem.getElementsByTagName("password").item(0).getChildNodes().item(0)
								.setNodeValue(new String(tfStdDbPass.getPassword()));
					} catch (Exception ex) {
						Element password = (Element) elem.getElementsByTagName("password").item(0);
						if (password == null) {
							password = standaloneXmlDocument.createElement("password");
							elem = (Element) elem.getElementsByTagName("security").item(0);
							elem.appendChild(password);
						}
						password.appendChild(standaloneXmlDocument.createTextNode(new String(tfStdDbPass.getPassword())));
					}
				}
			}
		}
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		//trimWhitespace(standaloneXmlDocument.getDocumentElement());
		StreamResult result = new StreamResult(new FileOutputStream(standaloneFile));
		transformer.transform(new DOMSource(standaloneXmlDocument), result);
	}

	public static void trimWhitespace(Node node) {
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				child.setTextContent(child.getTextContent().trim());
			}
			trimWhitespace(child);
		}
	}

	private void saveStartupBat() {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(startupBatFile));
			startupBatConfig.JAVA_HOME = tfJavaHome.getText();
			startupBatConfig.IP = tfIp.getText();
			out.println(startupBatConfig.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error while saving startup bat file: " + e.getMessage(),
					"Saving startup bat file", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void openStandalone() {
		if (standaloneFile == null) {
			return;
		}
		try {
			standaloneXmlDocument = readStandalone(standaloneFile);
			NodeList datasources = standaloneXmlDocument.getDocumentElement().getElementsByTagName("datasource");
			for (int i = 0; i < datasources.getLength(); i++) {
				Node n = datasources.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) n;
					String poolName = n.getAttributes().getNamedItem("pool-name").getNodeValue();
					if (poolName.equals("TestDS")) {
						db.url = elem.getElementsByTagName("connection-url").item(0).getChildNodes().item(0)
								.getNodeValue();
						db.driver = elem.getElementsByTagName("driver").item(0).getChildNodes().item(0).getNodeValue();
						db.username = elem.getElementsByTagName("user-name").item(0).getChildNodes().item(0)
								.getNodeValue();
						try {
							db.password = elem.getElementsByTagName("password").item(0).getChildNodes().item(0)
									.getNodeValue();
						} catch (Exception ex) {
							db.password = "";
						}
						db.content = standaloneXmlDocument;

						tfStdUrl.setText(db.url);
//						tfStdDriver.setText(db.driver);
						tfStdDbUsr.setText(db.username);
						tfStdDbPass.setText(db.password);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error while reading standalone-prod.xml: " + e.getMessage(),
					"Open standalone-prod.xml", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openStartupBat() {
		startupBatFile = new File(wfFile, "start-prod.bat");
		if (startupBatFile.exists() && startupBatFile.isFile()) {
			try {
				startupBatConfig = new StartupBatData(startupBatFile);
				tfJavaHome.setText(startupBatConfig.JAVA_HOME);
				tfIp.setText(startupBatConfig.IP);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error reading start-prod.bat: " + e.getMessage(),
						"Read start-prod.bat", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Could not find start-prod.bat", "Open start-prod.bat",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addWfComponents() {
		GridBagConstraints gbc;
		pnlPickWf.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlPickWf.add(lblWf, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		// gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		tfWfPath.setText(ini.getString("wf", "filePath", ""));
		pnlPickWf.add(tfWfPath, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		pnlPickWf.add(btnBrowse, gbc);

		getContentPane().add(pnlPickWf, BorderLayout.NORTH);

	}

	private void addDssComponents() {
		GridBagConstraints gbc;
		pnlSettings.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlSettings.add(lblEar, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		tfEarPath.setText(ini.getString("ear", "filePath", ""));
		pnlSettings.add(tfEarPath, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlSettings.add(lblScriptsPath, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		pnlSettings.add(tfScriptsPath, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		pnlSettings.add(lblMwUrl, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		pnlSettings.add(tfMwUrl, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		pnlSettings.add(sep1, gbc);

	}

	private void addStandaloneComponents() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		pnlSettings.add(lblStdDbUrl, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		tfStdUrl.setText(db.url);
		pnlSettings.add(tfStdUrl, gbc);

//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 5;
//		pnlSettings.add(lblStdDbDriver, gbc);
//
//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 5;
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.anchor = GridBagConstraints.LINE_START;
//		gbc.weightx = 1;
//		tfStdDriver.setText(db.driver);
//		pnlSettings.add(tfStdDriver, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 5;
		pnlSettings.add(lblStdDbUsr, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		tfStdDbUsr.setText(db.username);
		pnlSettings.add(tfStdDbUsr, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 6;
		pnlSettings.add(lblStdDbPass, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		tfStdDbPass.setText(db.password);
		pnlSettings.add(tfStdDbPass, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		pnlSettings.add(sep2, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 8;
		pnlSettings.add(lblJavaHome, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		pnlSettings.add(tfJavaHome, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 8;
		pnlSettings.add(btnJHBrowse, gbc);

		btnJHBrowse.addActionListener(e -> findJavaHome());

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 9;
		pnlSettings.add(lblIp, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		pnlSettings.add(tfIp, gbc);

	}

	private void findJavaHome() {
		try {
			JFileChooser fc = new JFileChooser();
			String dir = tfJavaHome.getText();
			fc.setCurrentDirectory(new File(dir));
			fc.setAcceptAllFileFilterUsed(false);
			fc.setDialogTitle("Find the Java JDK home folder");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			Integer ret = fc.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				tfJavaHome.setText(f.getCanonicalPath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void configuratorWindowClosing() {
		System.out.println("Closing");
		try {
			ini.setInt("main", "width", getWidth());
			ini.setInt("main", "height", getHeight());
			ini.setInt("main", "x", getX());
			ini.setInt("main", "y", getY());
			ini.saveINI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (jarFile != null && jarFile.exists()) {
				jarFile.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (propFile != null && propFile.exists()) {
				propFile.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void browseWildFly() {
		try {
			JFileChooser fc = new JFileChooser();
			String dir = ini.getString("wf", "filePath", ".");
			fc.setCurrentDirectory(new File(dir));
			fc.setAcceptAllFileFilterUsed(false);
			fc.setDialogTitle("Find the WildFly folder");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			Integer ret = fc.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				wfFile = fc.getSelectedFile();
				ini.setString("wf", "filePath", wfFile.getCanonicalPath());
				findDssEar();
				findStandalone();
				ini.saveINI();
				tfEarPath.setText(earFile.getCanonicalPath());
				tfWfPath.setText(wfFile.getCanonicalPath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void findStandalone() throws Exception {
		if (wfFile == null)
			return;
		standaloneFile = new File(wfFile, "standalone/configuration/standalone-prod.xml");
		if (standaloneFile.exists() && standaloneFile.isFile()) {
			ini.setString("standalone", "filePath", standaloneFile.getCanonicalPath());
		} else {
			JOptionPane.showMessageDialog(this, "Could not find the standalone/configuration/standalone-prod.xml file!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void findDssEar() throws IOException {
		if (wfFile == null)
			return;
		earFile = new File(wfFile, "standalone/deployments/");
		if (earFile.exists() && earFile.isDirectory()) {
			File[] files = earFile.listFiles();
			for (File f : files) {
				if (f.isFile() && f.getName().startsWith("DSS-") && f.getName().endsWith(".ear")) {
					// FOUND EAR
					earFile = f;
					ini.setString("ear", "filePath", earFile.getCanonicalPath());
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "Could not find the DSS EAR file!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Could not find the standalone/deployments folder!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openDSSConfig() {
		if (earFile == null)
			return;
		try {
			ZipFile zf = new ZipFile(earFile);
			Enumeration<? extends ZipEntry> entries = zf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry ze = entries.nextElement();
				if (ze.getName().startsWith("DSS-jar")) {
					System.out.println(ze.getName());
					InputStream in = zf.getInputStream(ze);
					jarFile = new File(ze.getName());
					System.out.println(jarFile.getCanonicalPath());
					FileOutputStream out = new FileOutputStream(jarFile);
					byte[] buff = new byte[65536];
					int read;
					while ((read = in.read(buff)) != -1) {
						out.write(buff, 0, read);
					}
					out.close();

					extractPropertyFile(jarFile);
				}
			}
			zf.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error while reading DSS configuration: " + e1.getMessage(),
					"Open DSS configuration", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveDssConfig() {
		try {
			OutputStream out = null;
			Properties prop = new Properties();

			out = new FileOutputStream(propFile);

			prop.setProperty("scriptsPath", tfScriptsPath.getText());
			propFileData.scriptsPath = tfScriptsPath.getText();
			prop.setProperty("MW_Url", tfMwUrl.getText());
			propFileData.MW_Url = tfMwUrl.getText();
			prop.setProperty("templateExcludeList", propFileData.templateExcludeList);
			if (propFileData.cluster == null) {
				prop.setProperty("cluster", "false");
			} else {
				prop.setProperty("cluster", propFileData.cluster);
			}
			prop.store(out, null);
			out.close();

			storeProperties(propFile, jarFile);
			storeJar(jarFile, earFile);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void storeJar(File jarFile, File earFile) {
		try {
			Path path = Paths.get(jarFile.toURI());
			Path ear = Paths.get(earFile.toURI());
			Map<String, String> env = new HashMap<>();
			env.put("create", "true");
			URI uri = URI.create("jar:" + ear.toUri());
			try (FileSystem earFs = FileSystems.newFileSystem(uri, env)) {
				Path pathInZipFile = earFs.getPath("/" + path.getFileName());
				Files.copy(path, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void storeProperties(File propFile, File jarFile) {
		try {
			Path path = Paths.get(propFile.toURI());
			Path jar = Paths.get(jarFile.toURI());
			Map<String, String> env = new HashMap<>();
			env.put("create", "true");
			URI uri = URI.create("jar:" + jar.toUri());
			try (FileSystem jarFs = FileSystems.newFileSystem(uri, env)) {
				Path pathInZipFile = jarFs.getPath("/" + path.getFileName());
				Files.copy(path, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void extractPropertyFile(File tempFile) throws Exception {
		ZipFile zf = new ZipFile(tempFile);
		Enumeration<? extends ZipEntry> entries = zf.entries();
		while (entries.hasMoreElements()) {
			ZipEntry ze = entries.nextElement();
			if (ze.getName().equals("DssBean.properties")) {
				System.out.println(ze.getName());
				InputStream in = zf.getInputStream(ze);
				propFile = new File(ze.getName());
				FileOutputStream out = new FileOutputStream(propFile);
				byte[] buff = new byte[65536];
				int read;
				while ((read = in.read(buff)) != -1) {
					out.write(buff, 0, read);
				}
				out.close();

				extractProperties(propFile);

			}
		}
		zf.close();
	}

	private void extractProperties(File propFile) throws Exception {
		FileInputStream in = new FileInputStream(propFile);
		Properties prop = new Properties();

		prop.load(in);
		
		propFileData.scriptsPath = prop.getProperty("scriptsPath");
		propFileData.MW_Url = prop.getProperty("MW_Url");
		propFileData.templateExcludeList = prop.getProperty("templateExcludeList");
		propFileData.cluster = prop.getProperty("cluster");

		System.out.println(propFileData.templateExcludeList);

		tfScriptsPath.setText(propFileData.scriptsPath);
		tfMwUrl.setText(propFileData.MW_Url);

		in.close();
	}

	private Document readStandalone(File wfFile) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream(standaloneFile));
		return document;
	}

	public static void main(String[] args) {
		new Configurator().setVisible(true);

	}

}
