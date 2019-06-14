import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public MyPanel() 
	{
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

	    GridBagConstraints c = new GridBagConstraints();
	    c.insets = new Insets(5,5,5,5);

	    c.gridx = 0;
	    c.gridy = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    add(new JButton("Button 1"), c);

	    c.gridx = 1;
	    c.gridy = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    add(new JButton("Button 2"), c);

	    c.gridx = 2;
	    c.gridy = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    add(new JButton("Button 3"), c);

	    c.gridx = 0;
	    c.gridy = 1;
	    add(new JButton("Button 4"), c);

	    c.gridx = 1;
	    c.gridy = 1;
	    add(new JButton("B4"), c);

	    c.gridx = 2;
	    c.gridy = 1;
	    add(new JButton("Button 5"), c);

	    c.gridx = 1;
	    c.gridy = 2;
	    add(new JButton("6"), c);
	}
}