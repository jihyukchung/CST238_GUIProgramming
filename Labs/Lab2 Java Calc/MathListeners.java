/************************************************************************
 * File	Name	Lab1/src/MathListeners.java
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/************************************************************************
* Class: MathListeners implements ActionListener
*
* Purpose: 
*	Initialize the text area and buttons
*
* Manager functions:
*	+ MathListeners()
*	+ actionPerformed(ActionEvent e)
*	- EqualOperation()
*	- SignOperation()
*	- DotOperation()
*	- MathOperation()
*
* Methods:
*	- JTextField m_textField;
*	- JButton m_button;
*	- static double m_hiddenNumber;
*	- static String m_hiddenOperation;
*
*************************************************************************/
public class MathListeners implements ActionListener
{
	private JTextField m_textField;
	private JButton m_button;
	private static double m_hiddenNumber;
	private static String m_hiddenOperation;
	
	/**********************************************************************
	* Purpose:
	*		2 arg Constructor for MathListeners
	*
	* Precondition:
	*		Called Directly from CalcPanel
	*
	* Postcondition:
	*		Set variables to input values
	*
	************************************************************************/
	MathListeners(JTextField textField, JButton button)
	{
		m_textField = textField;
		m_button = button;
		m_hiddenNumber = 0;
	}
		
	/**********************************************************************
	* Purpose:
	*		actionPerformed for ActionListener
	*
	* Precondition:
	*		Called Directly from CalcPanel
	*
	* Postcondition:
	*		Do following actions when called by a math button
	*
	************************************************************************/
	public void actionPerformed(ActionEvent e)
    {
		String buttonText = m_button.getText();
		
		if (buttonText.equals("C"))
		{
			m_textField.setText("");
		}
		else if (buttonText.equals("+/-"))
		{
			SignOperation();
		}
		else if (buttonText.equals("="))
		{
			EqualOperation();
		}
		// Add dot notation to the system
		else if (buttonText.equals("."))
		{
			DotOperation();
		}
		else if (buttonText.equals("+") || buttonText.equals("-") || 
			buttonText.equals("/") || buttonText.equals("X"))
		{
			MathOperation();
		}
    }
	
	private void EqualOperation()
	{
		double newText = 0.0d;
		boolean isOperated = true;
		
		if (m_hiddenNumber == 0.0d)
		{
			return;
		}
		
		if (m_hiddenOperation.equals("+"))
		{
			newText = m_hiddenNumber + Double.parseDouble(m_textField.getText());
		}	
		else if (m_hiddenOperation.equals("-"))
		{
			newText = m_hiddenNumber - Double.parseDouble(m_textField.getText());
		}		
		else if (m_hiddenOperation.equals("X"))
		{
			newText = m_hiddenNumber * Double.parseDouble(m_textField.getText());
		}		
		else if (m_hiddenOperation.equals("/"))
		{
			newText = m_hiddenNumber / Double.parseDouble(m_textField.getText());
		}
		else
		{
			isOperated = false;
		}
		
		if (isOperated == true)
		{
			m_textField.setText(Double.toString(newText));
		}
		
		m_hiddenNumber = 0.0d;
		m_hiddenOperation = "";
	}
	
	private void SignOperation()
	{
		double newText = Double.parseDouble(m_textField.getText()) * -1;
		m_textField.setText(Double.toString(newText));
	}
	
	private void DotOperation()
	{
		// disable double dot annotation
		if (!m_textField.getText().contains("."))
		{
			m_textField.setText(m_textField.getText() + ".");
		}
	}
	
	private void MathOperation()
	{
		m_hiddenNumber = 0;
		
		// If TextField is empty
		if (!m_textField.getText().isEmpty())
		{
			m_hiddenNumber = Double.parseDouble(m_textField.getText());
		}
		
		m_hiddenOperation = m_button.getText();
		m_textField.setText("");
	}
}