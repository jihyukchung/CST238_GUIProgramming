/**************************************************************************
 * File	Name	Lab1/src/MyTextArea.java
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
import javax.swing.JTextArea;

/************************************************************************
* Class: MyTextArea extends JTextArea 
*
* Purpose: 
*	A custom JTextArea
*
* Manager functions:
*	+ MyTextArea(int rows, int columns)
*
*	+ void setEdit(boolean editable)
*	+ void setFamily(String family)
*	+ public void setStyle(int style)
*
* Methods:
*	- String _family
*	- int _style
*	- int _size
*
*************************************************************************/
public class MyTextArea extends JTextArea 
{
	private static final long serialVersionUID = 1L;
	
	protected String _family;
	protected int _style;
	protected int _size;

	/**********************************************************************
	* Purpose:
	*		Default Constructor
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	* 		Call JTextArea's default super Constructor and set variables to 
	* 		default values.
	*
	************************************************************************/
	public MyTextArea() 
	{
		super();
		setDefault();
	}
	
	/**********************************************************************
	* Purpose:
	*		2 Arg Constructor
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	* 		Call JTextArea's 2 Arg super Constructor and set variables to 
	* 		default values.
	*
	************************************************************************/
	public MyTextArea(int rows, int columns) 
	{
		super(rows, columns);
		setDefault();
	}
	
	/**********************************************************************
	* Purpose:
	*		Setters
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	* 		Set editable or instance variables
	*
	************************************************************************/
	public void setDefault()
	{
		_family = "Monospaced";
		_style = 0;
		_size = 12;
		setFont(new Font(_family, _style, _size));
	}
	
	public void setEdit(boolean editable) 
	{
		setEditable(editable);
	}
	
	public void setFamily(String family) 
	{ 	
		_family = family;
		setFont(new Font(_family, _style, _size));
	}
	
	public void setStyle(int style) 
	{ 	
		_style = style;
		setFont(new Font(_family, _style, _size));
	}
	
	public void setSize(int size) 
	{ 	
		_size = size;
		setFont(new Font(_family, _style, _size));
	}
}
