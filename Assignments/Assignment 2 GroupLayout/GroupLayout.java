import javax.swing.JFrame;

public class GroupLayout 
{
	public static void main(String[] args) 
	{
		try
		{
			JFrame _frame = new JFrame();
			InitializeFrame(_frame);
			_frame.add(new MyPanel());
			_frame.setVisible(true);
		}
		catch(Exception e) 
		{
			System.out.println(e.toString());
		}
	}
	
	public static void InitializeFrame(JFrame frame)
	{
		frame.setSize(400,300);
		frame.setTitle("Group Layout by Jihyuk Chung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}