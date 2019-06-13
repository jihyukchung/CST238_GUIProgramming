/**************************************************************************
 * File	Name	Lab1/src/MyMenuBar.java
 * Author		Jihyuk Chung
 * Date			April 2, 2018 Tue
 * Modified		April 3, 2018 Wed - Fixed Listeners
 * 				April 6, 2018 Wed - Added Save and Open
 * 				April 7, 2018 Wed - Changed JTextArea to MyTextArea 
 * 									Added FileChooser
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 *************************************************************************/

import java.awt.Font;
import javax.swing.*;

/************************************************************************
* Class: MyMenuBar extends JMenuBar 
*
* Purpose: 
*	A custom JMenuBar class
*
* Manager functions:
*	+ MyMenuBar(MyTextArea textArea)
*
*	- void FileMenu() 
*	- void Fonts() 
*	- void Style()
*	- void Exit()
*
* Methods:
*	- MyTextArea _textArea
*
*************************************************************************/
public class MyMenuBar extends JMenuBar 
{
	private static final long serialVersionUID = 1L;
	private MyTextArea _textArea;
	
	/**********************************************************************
	* Purpose:
	*		1 Arg Constructor
	*
	* Precondition:
	*		Called Directly from this constructor.
	*
	* Postcondition:
	*		Copy the textArea and call menu functions.
	*
	************************************************************************/
	public MyMenuBar(MyTextArea textArea)
	{
		_textArea = textArea;
		
		FileMenu();
		Fonts();
		Style();
		Exit();
	}
	
	/**********************************************************************
	* Purpose:
	*		Initialzie the File Menu and the items inside
	*
	* Precondition:
	*		Called Directly from this constructor.
	*
	* Postcondition:
	*		Change to the desired Fonts.
	*
	************************************************************************/
	private void FileMenu() 
	{
		// File Menu
		JMenu _file = new JMenu("File");
		JMenuItem _open = new JMenuItem("Open");
		JMenuItem _save = new JMenuItem("Save");
		
		// Action Listeners
		_open.addActionListener(new Listeners(_textArea).openListener);
		_save.addActionListener(new Listeners(_textArea).saveListener);
		
		// Add to Menu
		_file.add(_open);
		_file.add(_save);
		add(_file);
	}
	
	/**********************************************************************
	* Purpose:
	*		Initialzie the Fonts Menu and the items inside
	*
	* Precondition:
	*		Called Directly from this constructor.
	*
	* Postcondition:
	*		Add the Font items and menu.
	*
	************************************************************************/
	private void Fonts() 
	{
		// Fonts Menu
		JMenu _fonts = new JMenu("Fonts");
		JMenuItem _mono = new JMenuItem("Mono");
		JMenuItem _serif = new JMenuItem("Serif");
		JMenuItem _san_serif = new JMenuItem("San-Serif");
		
		// Action Listeners
		_mono.addActionListener(new Listeners(_textArea, Font.MONOSPACED).fontListener);
		_serif.addActionListener(new Listeners(_textArea, Font.SERIF).fontListener);
		_san_serif.addActionListener(new Listeners(_textArea, Font.SANS_SERIF).fontListener);
		
		// Add to Menu
		_fonts.add(_mono);
		_fonts.add(_serif);
		_fonts.add(_san_serif);
		add(_fonts);
	}
	
	/**********************************************************************
	* Purpose:
	*		Initialzie the Style Menu and the items inside
	*
	* Precondition:
	*		Called Directly from this constructor.
	*
	* Postcondition:
	*		Add the Style items and menu.
	*
	************************************************************************/
	private void Style()
	{
		// Style Menu
		JMenu _style = new JMenu("Style");
		JMenuItem _plain = new JMenuItem("Plain");
		JMenuItem _italic = new JMenuItem("Italics");
		JMenuItem _bold = new JMenuItem("Bold");
	
		// Action Listeners
		_plain.addActionListener(new Listeners(_textArea, Font.PLAIN).styleListener);
		_italic.addActionListener(new Listeners(_textArea, Font.ITALIC).styleListener);
		_bold.addActionListener(new Listeners(_textArea, Font.BOLD).styleListener);
		
		// Add to Menu
		_style.add(_plain);
		_style.add(_italic);
		_style.add(_bold);
		add(_style);
	}
	
	/**********************************************************************
	* Purpose:
	*		Initialzie the Exit Menu
	*
	* Precondition:
	*		Called Directly from this constructor.
	*
	* Postcondition:
	*		Add the Exit menu.
	*
	************************************************************************/
	private void Exit()
	{
		// Exit Menu
		JMenu _exit = new JMenu("Exit");
		_exit.addActionListener(new Listeners().exitListener);
		
		// Add to Menu
		add(Box.createHorizontalGlue());
		add(_exit);
	}
}
