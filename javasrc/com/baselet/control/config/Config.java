package com.baselet.control.config;

import java.awt.Font;
import java.awt.Point;
import java.io.File;

import javax.swing.UIManager;

import com.baselet.control.basics.geom.Dimension;
import com.baselet.control.constants.SystemInfo;
import com.baselet.control.enums.Os;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;

public class Config {

	private static Config instance = new Config();

	public static Config getInstance() {
		return instance;
	}

	private final String DEFAULT_FILE_HOME = System.getProperty("user.dir");
        
	private String uiManager;
	private String openFileHome = DEFAULT_FILE_HOME;
	private String saveFileHome = DEFAULT_FILE_HOME;
	private String programVersion;
        
	private String lastUsedPalette = ""; // default is empty string not null because null cannot be stored as property
	private String pdfExportFont = ""; // eg in Windows: "pdf_export_font = c:/windows/fonts/msgothic.ttc,1"
	private String pdfExportFontBold = "";
	private String pdfExportFontItalic = "";
	private String pdfExportFontBoldItalic = "";
	private String lastExportFormat = "";
	private boolean checkForUpdates = true;
	private int printPadding = 20;
	private Point program_location = new Point(5, 5);
	private Dimension program_size = new Dimension(960, 750);
	private int mail_split_position = 250;
	private int right_split_position = 400;
	private int main_split_position = 600;
	private boolean enable_custom_elements = true;
	private boolean show_grid = false;
	private boolean start_maximized = false;
	private boolean secureXmlProcessing = true;
	private String defaultFontFamily = Font.SANS_SERIF;
	private Integer defaultFontsize = 14;
	private Integer propertiesPanelFontsize = 11;

	public Config() {
		if (Program.getInstance().getRuntimeType() != RuntimeType.BATCH) { // batchmode shouldn't access UIManager.class
			initUiManager();
		}
	}

	private void initUiManager() {
		// The default MacOS theme looks ugly, therefore we set metal
		if (SystemInfo.OS == Os.MAC) {
			uiManager = "javax.swing.plaf.metal.MetalLookAndFeel";
		}
		else if (Program.getInstance().getRuntimeType() == RuntimeType.ECLIPSE_PLUGIN && UIManager.getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
			uiManager = "javax.swing.plaf.metal.MetalLookAndFeel";
		}
		else {
			uiManager = UIManager.getSystemLookAndFeelClassName();
		}
	}

	public String getUiManager() {
		return uiManager;
	}

	public void setUiManager(String uimanager) {
		this.uiManager = uimanager;
	}

	public String getOpenFileHome() {
		return getFileHelper(openFileHome, DEFAULT_FILE_HOME);
	}

	public void setOpenFileHome(String openfilehome) {
		this.openFileHome = openfilehome;
	}

	public String getSaveFileHome() {
		return getFileHelper(saveFileHome, DEFAULT_FILE_HOME);
	}

	private String getFileHelper(String fileLocToCheck, String defaultValue) {
		try {
			if (new File(fileLocToCheck).exists()) {
				return fileLocToCheck;
			}
		} catch (Exception e) {/* nothing to do */}

		// if stored location doesn't exist or there is an exception while accessing the location, return default value
		return defaultValue;
	}

	public void setSaveFileHome(String savefilehome) {
		this.saveFileHome = savefilehome;
	}

	public void setProgramVersion(String cfgVersion) {
		programVersion = cfgVersion;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public String getLastUsedPalette() {
		return lastUsedPalette;
	}

	public void setLastUsedPalette(String lastusedpalette) {
		this.lastUsedPalette = lastusedpalette;
	}

	public String getPdfExportFont() {
		return pdfExportFont;
	}

	public void setPdfExportFont(String pdfexportfont) {
		this.pdfExportFont = pdfexportfont;
	}

	public String getPdfExportFontBold() {
		return pdfExportFontBold;
	}

	public void setPdfExportFontBold(String pdfexportfontbold) {
		this.pdfExportFontBold = pdfexportfontbold;
	}

	public String getPdfExportFontItalic() {
		return pdfExportFontItalic;
	}

	public void setPdfExportFontItalic(String pdfexportfontitalic) {
		this.pdfExportFontItalic = pdfexportfontitalic;
	}

	public String getPdfExportFontBoldItalic() {
		return pdfExportFontBoldItalic;
	}

	public void setPdfExportFontBoldItalic(String pdfexportfontbolditalic) {
		this.pdfExportFontBoldItalic = pdfexportfontbolditalic;
	}

	public String getLastExportFormat() {
		return lastExportFormat;
	}

	public void setLastExportFormat(String lastexportfont) {
		this.lastExportFormat = lastexportfont;
	}

	public boolean isCheckForUpdates() {
		return checkForUpdates;
	}

	public void setCheckForUpdates(boolean checkforupdates) {
		this.checkForUpdates = checkforupdates;
	}

	public boolean isSecureXmlProcessing() {
		return secureXmlProcessing;
	}

	public void setSecureXmlProcessing(boolean securexmlprocessing) {
		this.secureXmlProcessing = securexmlprocessing;
	}

	public int getPrintPadding() {
		return printPadding;
	}

	public void setPrintPadding(int printpadding) {
		this.printPadding = printpadding;
	}

	public Point getProgram_location() {
		return program_location;
	}

	public void setProgram_location(Point programlocation) {
		this.program_location = programlocation;
	}

	public Dimension getProgram_size() {
		return program_size;
	}

	public void setProgram_size(Dimension programsize) {
		this.program_size = programsize;
	}

	public int getMail_split_position() {
		return mail_split_position;
	}

	public void setMail_split_position(int mailsplitposition) {
		this.mail_split_position = mailsplitposition;
	}

	public int getRight_split_position() {
		return right_split_position;
	}

	public void setRight_split_position(int rightsplitposition) {
		this.right_split_position = rightsplitposition;
	}

	public int getMain_split_position() {
		return main_split_position;
	}

	public void setMain_split_position(int mainsplitposition) {
		this.main_split_position = mainsplitposition;
	}

	public boolean isEnable_custom_elements() {
		return enable_custom_elements;
	}

	public void setEnable_custom_elements(boolean enablecustomelements) {
		this.enable_custom_elements = enablecustomelements;
	}

	public boolean isShow_grid() {
		return show_grid;
	}

	public void setShow_grid(boolean showgrid) {
		this.show_grid = showgrid;
	}

	public boolean isStart_maximized() {
		return start_maximized;
	}

	public void setStart_maximized(boolean startmaximized) {
		this.start_maximized = startmaximized;
	}

	public String getDefaultFontFamily() {
		return defaultFontFamily;
	}

	public void setDefaultFontFamily(String defaultfontfamily) {
		this.defaultFontFamily = defaultfontfamily;
	}

	public Integer getDefaultFontsize() {
		return defaultFontsize;
	}

	public void setDefaultFontsize(Integer defaultfontsize) {
		this.defaultFontsize = defaultfontsize;
	}

	public Integer getPropertiesPanelFontsize() {
		return propertiesPanelFontsize;
	}

	public void setPropertiesPanelFontsize(Integer propertiespanelfontsize) {
		this.propertiesPanelFontsize = propertiespanelfontsize;
	}
}
