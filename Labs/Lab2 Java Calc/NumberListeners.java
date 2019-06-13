/************************************************************************
 * File	Name	Lab1/src/NumberListeners.java
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
* Class: NumberListeners implements ActionListener
*
* Purpose: 
*	Initialize the text area and buttons
*
* Manager functions:
*	+ NumberListeners(JTextField textField, JButton button)
*	+ actionPerformed(ActionEvent e)
*
* Methods:
*	- JTextField m_textField;
*	- JButton m_button;
*
*************************************************************************/
public class NumberListeners implements ActionListener
{
	private JTextField m_textField;
	private JButton m_button;
	
	/**********************************************************************
	* Purpose:
	*		2 arg Constructor for NumberListeners
	*
	* Precondition:
	*		Called Directly from CalcPanel
	*
	* Postcondition:
	*		Set variables to input values
	*
	************************************************************************/
	NumberListeners(JTextField textField, JButton button)
	{
		m_textField = textField;
		m_button = button;
	}
		
	/**********************************************************************
	* Purpose:
	*		actionPerformed for ActionListener
	*
	* Precondition:
	*		Called Directly from CalcPanel
	*
	* Postcondition:
	*		Do following actions when called by a number button
	*
	************************************************************************/
	public void actionPerformed(ActionEvent e)
    {
		String newText = m_textField.getText() + m_button.getText();
		
		// 05 -> 5
		if (m_textField.getText().equals("0"))
		{
			m_textField.setText(m_button.getText());
			return;
		}
		
		// Don't make user randomly type 00000000000000
		if (!newText.equals("00"))
		{
			m_textField.setText(newText);
		}
    }
}