/************************************************************************
 * File	Name	Lab1/src/JavaGUI.java
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
 ************************************************************************/
/************************************************************************
 * 
 * Lab/Assignment: Lab 1 - Java GUI
 *
 * Overview:
 *	This program will create a java text editor with a scrollable text 
 *	area and menu items.
 *
 * Input:
 *	The text area and clickable menu options.
 *
 * Output:
 *	Open, save, change fonts and style, exit.
 *	Save and open file: Lab 1 Java Editor/save.txt
 *
 ************************************************************************/

import java.awt.*;
import javax.swing.*;

/************************************************************************
* Class: JavaGUI
*
* Purpose: 
*	The main class initialize the text area and menu bar 
*
* Manager functions:
*	+ static void InitializeFrame()
*	+ static void CreateTextArea()
*
* Methods:
*	- static JFrame _frame;
*	- static MyTextArea _textArea;
*
*************************************************************************/
public class JavaGUI 
{
	private static JFrame _frame;
	private static MyTextArea _textArea;

	public static void main(String[] args) 
	{
		try 
		{
			// Default
			_frame = new JFrame();
			InitializeFrame();
			
			// Drawing Surface and Menu Bar
			CreateTextArea();
			_frame.setJMenuBar(new MyMenuBar(_textArea));
			
			// Set Visible
			_frame.setVisible(true);
		}
		catch(Exception e) 
		{
			System.out.println(e.toString());
		}
	}
	
	/**********************************************************************
	* Purpose:
	*		Initialize
	*
	* Precondition:
	*		Called Directly from main
	*
	* Postcondition:
	*		Dynamically allocate m_msg pointer and copy the new const char 
	*		pointer to the current data member.
	*
	************************************************************************/
	public static void InitializeFrame()
	{
		_frame.setSize(600,400);
		_frame.setTitle("Java GUI by Jihyuk Chung");
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**********************************************************************
	* Purpose:
	*		DrawingSurface
	*
	* Precondition:
	*		Called Directly from main
	*
	* Postcondition:
	*		Dynamically allocate m_msg pointer and copy the new const char 
	*		pointer to the current data member.
	*
	************************************************************************/
	public static void CreateTextArea()
	{
		// Scrolling Text Area
		_textArea = new MyTextArea(5, 30);
		_textArea.setEdit(true);
		
		JScrollPane scrollPane = new JScrollPane(_textArea);
		_frame.setPreferredSize(new Dimension(450, 110));
		_frame.add(scrollPane, BorderLayout.CENTER);
	}
}