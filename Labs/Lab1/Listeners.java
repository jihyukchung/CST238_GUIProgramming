/**************************************************************************
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
 *************************************************************************/
/**************************************************************************
 * File			Lab1/src/Listeners.java
 * Created By	Jihyuk Chung
 * Date			April 2, 2018 Tue
 * Modified		April 3, 2018 Wed - 
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 *************************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

/************************************************************************
* Class: Listeners
*
* Purpose: 
*	A listener class for custom constructors and action listeners.
*
* Manager functions:
*	+ Listeners()
*	+ Listeners(JTextArea textArea)
*	+ Listeners(JTextArea textArea, String font)
*	+ Listeners(JTextArea textArea, int style)
*	- FileChooser
*
* Methods:
*	- JTextArea _textArea
*	- String _family
*	- int _style
*	- int _size
*
*	# ActionListener openListener
*	# ActionListener saveListener
*	# ActionListener fontListener
*	# ActionListener styleListener
*	# ActionListener exitListener
*
*************************************************************************/
public class Listeners 
{
	private MyTextArea _textArea;
	private JFrame _frame;
	private String _family;
	private int _style;
	//private int _size;
	
	/**********************************************************************
	* Purpose:
	*		File Open ActionListener
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Save a text file to the text area.
	*
	************************************************************************/
	protected ActionListener openListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg) 
		{
			try (FileReader open = new FileReader( FileChooser(true) ))
			{
				// Had problems with _TextArea.setText()
				BufferedReader buffered = new BufferedReader(open);
				System.out.println(buffered);
				_textArea.read( buffered, null );
				buffered.close();
				_textArea.requestFocus();
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	};
	
	/**********************************************************************
	* Purpose:
	*		File Save ActionListener
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Save the text area as a text file.
	*
	************************************************************************/
	protected ActionListener saveListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg) 
		{   
			try (PrintWriter out = new PrintWriter( FileChooser(false) )) 
			{
				_textArea.write(out);
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
	};
	
	/**********************************************************************
	* Purpose:
	*		Font ActionListener
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Change to the desired font on the menu.
	*
	************************************************************************/
	protected ActionListener fontListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg) 
		{
			_textArea.setFamily(_family);
		}
	};
	
	/**********************************************************************
	* Purpose:
	*		Style ActionListener
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Change to the desired style on the menu.
	*
	************************************************************************/
	protected ActionListener styleListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg) 
		{
			_textArea.setStyle(_style);
		}
	};
	
	/**********************************************************************
	* Purpose:
	*		Exit ActionListener
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Exit the client.
	*
	************************************************************************/
	protected ActionListener exitListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg) 
		{
			_frame.dispatchEvent(new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING));
		}
	};
	
	/**********************************************************************
	* Purpose:
	*		Default Constructor for exit
	*
	* Precondition:
	*		Called Directly.
	*
	* Postcondition:
	*		Set variables to default values.
	*
	************************************************************************/
	public Listeners()
	{
		_textArea = new MyTextArea();
	}
	
	/**********************************************************************
	* Purpose:
	*		1 Arg Constructor for open, save and exit
	*
	* Precondition:
	*		Called Directly from main
	*
	* Postcondition:
	* 		Copy the textArea and set variables to default values.
	*
	************************************************************************/
	public Listeners(MyTextArea textArea)
	{
		_textArea = textArea;
		_family = "";
		_style = 0;
		//_size =0;
	}
	
	public Listeners(JFrame frame)
	{
		_textArea = new MyTextArea();
		_family = "";
		_style = 0;
		//_size = 0;
		_frame = frame;
	}
	
	/**********************************************************************
	* Purpose:
	*		2 Arg Constructor for font input
	*
	* Precondition:
	*		Called Directly from main with MyTextArea and family input
	*
	* Postcondition:
	*		Copy the textArea and the new font.
	*
	************************************************************************/
	public Listeners(MyTextArea textArea, String family)
	{
		_textArea = textArea;
		_family = family;
		_style = 0;
		//_size = 0;
	}
	
	/**********************************************************************
	* Purpose:
	*		2 Arg Constructor for style input
	*
	* Precondition:
	*		Called Directly from main with MyTextArea and size input
	*
	* Postcondition:
	*		Copy the textArea and the new style.
	*
	************************************************************************/
	public Listeners(MyTextArea textArea, int style)
	{
		_textArea = textArea;
		_family = "";
		_style = style;
		//_size = 0;
	}
	
	/**********************************************************************
	* Purpose:
	*		FileChooser
	*
	* Precondition:
	*		Called Directly with save or open boolean
	*
	* Postcondition:
	*		Copy the textArea and the new style.
	*
	************************************************************************/
	private String FileChooser(boolean open)
	{
		final JFileChooser fileChooser = new JFileChooser
		(
			FileSystemView.getFileSystemView().getHomeDirectory()
		);
		
		int returnValue = 0;
		
		if (open == true)
		{
			returnValue = fileChooser.showOpenDialog(null);
		}
		else if (open == false)
		{
			returnValue = fileChooser.showSaveDialog(null);
		}
		else
		{
			System.out.println("FileChooser - Input Error.");
		}
		
		File selectedFile = null;

		if (returnValue == JFileChooser.APPROVE_OPTION) 
		{
			selectedFile = fileChooser.getSelectedFile();
		}
		else
		{
			System.out.println("FileChooser - Select File Error.");
		}
		
		return selectedFile.getAbsolutePath();
	}
	
}

