package com.jianggua.util;

import java.awt.Font;

import javax.swing.UIManager;

/**
 * @ClassName FontFix
 * @Description 
 * @author Ray Wei
 * @Date 2019-5-14
 */
public class FontFix {
	
	public static String[] DEFAULT_FONT  = new String[]{
	    "Table.font"
	    ,"TableHeader.font"
	    ,"CheckBox.font"
	    ,"Tree.font"
	    ,"Viewport.font"
	    ,"ProgressBar.font"
	    ,"RadioButtonMenuItem.font"
	    ,"ToolBar.font"
	    ,"ColorChooser.font"
	    ,"ToggleButton.font"
	    ,"Panel.font"
	    ,"TextArea.font"
	    ,"Menu.font"
	    ,"TableHeader.font"
	    // ,"TextField.font"
	    ,"OptionPane.font"
	    ,"MenuBar.font"
	    ,"Button.font"
	    ,"Label.font"
	    ,"PasswordField.font"
	    ,"ScrollPane.font"
	    ,"MenuItem.font"
	    ,"ToolTip.font"
	    ,"List.font"
	    ,"EditorPane.font"
	    ,"Table.font"
	    ,"TabbedPane.font"
	    ,"RadioButton.font"
	    ,"CheckBoxMenuItem.font"
	    ,"TextPane.font"
	    ,"PopupMenu.font"
	    ,"TitledBorder.font"
	    ,"ComboBox.font"
	};
	
	private FontFix(){}
	
	public static void initFont(){
		for (int i = 0; i < DEFAULT_FONT.length; i++)
		    UIManager.put(DEFAULT_FONT[i],new Font("SimHei", Font.PLAIN,14));
	}
	
}
