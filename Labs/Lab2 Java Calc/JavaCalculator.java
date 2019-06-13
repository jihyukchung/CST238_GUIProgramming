/************************************************************************
 * File	Name	Lab1/src/JavaCalculator.java
 * Author		Jihyuk Chung
 * Date			April 09, 2018 Tue
 * Modified		April 11, 2018 Sun - Fixed Frame & calculation bugs
 * 				April 13, 2018 Sat - First version fully working
 * 				April 14, 2018 Sun - Changed Listener classes
 * 				April 15, 2018 Mon - Edited Frame & Removed most static
 *				April 15, 2018 Tue - Second version fully working
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 ************************************************************************/
/************************************************************************
 * 
 * Lab/Assignment: Lab 2 - Java Calculator
 *
 * Overview:
 *	This program will create a calculator using Grid Layout.
 *
 * Input:
 *	The text area and clickable menu options.
 *
 * Output:
 *	Open, save, change fonts and style, exit.
 *	Save and open file: Lab 1 Java Editor/save.txt
 *
 ************************************************************************/

import javax.swing.*;

/************************************************************************
* Class: JavaCalculator
*
* Purpose: 
*	The most outer class that combine all elements
*
* Manager functions:
*	+ JavaCalculator()
*	+ static void main(String[] args) 
*
* Methods:
*	- JFrame m_frame
*	- CalcPanel m_panel
*
*************************************************************************/
public class JavaCalculator 
{ 
	private JFrame m_frame;
	private CalcPanel m_panel;
	
	/**********************************************************************
	* Purpose:
	*		Default Constructor
	*
	* Precondition:
	*		Called Directly from main
	*
	* Postcondition:
	*		Set Frame settings and add Panel
	*
	************************************************************************/
	public JavaCalculator()
	{
		m_frame = new JFrame();
		m_panel = new CalcPanel();
		
		m_frame.setSize(400,400);
		m_frame.setTitle("Java Calculator by Jihyuk Chung");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		m_frame.add(m_panel);
		m_frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new JavaCalculator();
	}
}