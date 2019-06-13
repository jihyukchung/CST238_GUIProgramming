/************************************************************************
 * File	Name	Lab1/src/CalcPanel.java
 * Author		Jihyuk Chung
 * Date			April 09, 2018 Tue
 * Modified		April 11, 2018 Sun - Fixed Frame & Calculation Bugs
 * 				April 13, 2018 Sat - First version fully working
 * 				April 14, 2018 Sun - Changed Listener Classes
 * 				April 15, 2018 Mon - Edited Frame
 *				April 15, 2018 Tue - Second version fully working
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 ************************************************************************/

import java.awt.*;
import javax.swing.*;

/************************************************************************
* Class: CalcPanel extends JPanel
*
* Purpose: 
*	Initialize the text area and buttons
*
* Manager functions:
*	+ static void InitializeFrame()
*	+ static void CreateTextArea()
*
* Methods:
*	- GridBagConstraints m_gbCon
*	- JTextField m_textField
*	- JButton m_button
*	- int m_row
*	- NumberListeners m_numberListener
*	- MathListeners m_mathListener
*	- final String[][] m_numberButtons
* 	- final String[][] m_mathButtons
*
*************************************************************************/
public class CalcPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints m_gbCon;
	private JTextField m_textField;
	private JButton m_button;
	private int m_row;
	private NumberListeners m_numberListener;
	private MathListeners m_mathListener;
	
	private final String[][] m_numberButtons = 
	{
		{ "7", "8", "9" },
		{ "4", "5", "6" },
		{ "1", "2", "3" },
		{ "0", "." }
	};
	private final String[][] m_mathButtons = 
	{
		{ "C", "+/-", "/", "X" },
		{ "-" },
		{ "+" },
		{ "=" },
	};
	

	/**********************************************************************
	* Purpose:
	*		Default Constructor for CalcPanel
	*
	* Precondition:
	*		Called Directly from JavaCalculator.
	*
	* Postcondition:
	*		Set variables to default values and add layouts.
	*
	************************************************************************/
	public CalcPanel()
	{
		// Initialize
		m_gbCon = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		// Add Items
		DefaultSettings();
		AddTextField();
		AddCalcUI();
	}
	
	/**********************************************************************
	* Purpose:
	*		Set Settings to Default
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Reset m_gbCon settings
	*
	************************************************************************/
	private void DefaultSettings()
	{
		m_gbCon.insets = new Insets(2,2,2,2);
		m_gbCon.fill = GridBagConstraints.BOTH;
		m_gbCon.weightx = 1;
		m_gbCon.weighty = 1;
		m_gbCon.gridwidth = 1;
		m_gbCon.gridheight = 1;
		m_gbCon.ipady = 1;
	}
	
	/**********************************************************************
	* Purpose:
	*		Add a Text Field
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Add a text Field to the top of panel
	*
	************************************************************************/
	private void AddTextField()
	{
		// Top TextField
		m_textField = new JTextField("");
		m_textField.setHorizontalAlignment(JTextField.RIGHT);	
		m_textField.setEnabled(false);
		
		Font m_font = new Font("arian", Font.PLAIN, 20);
		m_textField.setFont(m_font);
		
		AddComp(m_textField, 0, 0, 4);
	}
	
	/**********************************************************************
	* Purpose:
	*		Add button UI
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Number and Operation buttons added
	*
	************************************************************************/
	private void AddCalcUI()
	{
		// Add First Row
		for (int i = 0; i < m_mathButtons[m_row].length; i++)
		{
			m_button = new JButton(m_mathButtons[m_row][i]);
			AddComp(m_button, i, m_row + 1, 1);		
			AddMathListener();
		}
		
		// Add Row 2 ~ 4
		AddRowTwoToFour();

		// Add 0
		m_button = new JButton(m_numberButtons[m_row - 1][0]);
		AddComp(m_button, 0, m_row + 1, 2);
		AddNumberListener();
		
		// Add .
		m_button = new JButton(m_numberButtons[m_row - 1][1]);
		AddComp(m_button, 2, m_row + 1, 1);
		AddMathListener();
		
		// Add =
		m_gbCon.ipady = 40;
		m_gbCon.gridheight = 2;
		m_button = new JButton(m_mathButtons[m_row - 1][0]);
		AddComp(m_button, 3, m_row, 1);
		AddMathListener();
	}
	
	/**********************************************************************
	* Purpose:
	*		Add Rows Two To Four
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Add 7 8 9 4 5 6 1 2 3 buttons and - + buttons
	*
	************************************************************************/
	private void AddRowTwoToFour()
	{
		m_row++;
		
		for (int i = 0; i < 3; i++)
		{
			// Add 	7 8 9
			//		4 5 6
			//		1 2 3
			for (int j = 0; j < m_numberButtons[m_row - 1].length; j++)
			{
				m_button = new JButton(m_numberButtons[m_row - 1][j]);
				AddComp(m_button, j, m_row + 1, 1);			
				AddNumberListener();
			}
			
			// Add - and +
			if (i < 2)
			{
				m_button = new JButton(m_mathButtons[m_row][0]);
				AddComp(m_button, 3, m_row + 1, 1);
				AddMathListener();
			}

			m_row++;
		}
	}
	
	/**********************************************************************
	* Purpose:
	*		Add a Component
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Add buttons or a text field
	*
	************************************************************************/
	private void AddComp(Component comp, int x, int y, int w)
	{
		m_gbCon.gridx = x;
		m_gbCon.gridy = y;
		m_gbCon.gridwidth = w;
		
		this.add(comp, m_gbCon);
	}
	
	/**********************************************************************
	* Purpose:
	*		Add a Number Listener
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Create a Number Listener and add to the button
	*
	************************************************************************/
	private void AddNumberListener()
	{
		m_numberListener = new NumberListeners(m_textField, m_button);
		m_button.addActionListener(m_numberListener);
	}
	
	/**********************************************************************
	* Purpose:
	*		Add a Math Listener
	*
	* Precondition:
	*		Called Directly from this class
	*
	* Postcondition:
	*		Create a Math Listener and add to the button
	*
	************************************************************************/
	private void AddMathListener()
	{
		m_mathListener = new MathListeners(m_textField, m_button);
		m_button.addActionListener(m_mathListener);
	}
}
