package com.sdlc.skillMatrix.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

public class StyleBean {

	// browser types
	public static final int BROWSER_FIREFOX = 0;
	public static final int BROWSER_IE = 1;


	// possible theme choices
	private static final String RIME = "rime";
	private static final String XP = "xp";
	private static final String ROYALE = "royale";

	// browser type
	private int browserType = BROWSER_IE;

	
	// value for popup error
	private String popupErrorValue = null;
	private String popupErrorStyle = null;

	// default theme
	private String currentStyle = RIME;

	// available style list
	private ArrayList<SelectItem> styleList;

	private HashMap<String, StylePath> styleMap;

	public StyleBean() {

		// initialize the style list
		styleList = new ArrayList<SelectItem>();
		styleList.add(new SelectItem(RIME, RIME));
		styleList.add(new SelectItem(XP, XP));
		styleList.add(new SelectItem(ROYALE, ROYALE));

		styleMap = new HashMap<String, StylePath>(3);
		styleMap.put(RIME, new StylePath("/xmlhttp/css/rime/rime.css", "/xmlhttp/css/rime/css-images/"));
		styleMap.put(XP, new StylePath("/xmlhttp/css/xp/xp.css", "/xmlhttp/css/xp/css-images/"));
		styleMap.put(ROYALE, new StylePath("/xmlhttp/css/royale/royale.css", "/xmlhttp/css/royale/css-images/"));
	}

	

	public String getPopupErrorValue() {
		return popupErrorValue;
	}

	public String getPopupErrorStyle() {
		return popupErrorStyle;
	}

	public String getCurrentStyle() {
		return currentStyle;
	}

	public String getStylePart1() {
		return "<link rel=\"stylesheet\" type=\"text/css\" href=\"";
	}

	public String getStylePart2() {
		return ((StylePath) styleMap.get(currentStyle)).getCssPath() + "\"></link>";
	}

	public String getImageDirectory() {
		return ((StylePath) styleMap.get(currentStyle)).imageDirPath;
	}

	public List getStyleList() {
		return styleList;
	}

	public class StylePath {

		private String cssPath;
		private String imageDirPath;

		public StylePath(String cssPath, String imageDirPath) {
			this.cssPath = cssPath;
			this.imageDirPath = imageDirPath;
		}

		public String getCssPath() {
			return cssPath;
		}

		public String getImageDirPath() {
			return imageDirPath;
		}
	}


	public int getBrowserType() {
		return browserType;
	}

	public void setBrowserType(int browserType) {
		this.browserType = browserType;
	}

}