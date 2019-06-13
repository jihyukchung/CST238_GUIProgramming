import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public MyPanel() 
	{
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
	    // Turn on automatically adding gaps between components
		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
	
		// Group Items
		JButton find = new JButton("Find");
		JButton replace_find = new JButton("Replace Find");
		JButton replace = new JButton("Replace");
		JButton replace_all = new JButton("Replace All");
		JButton close = new JButton("Close");
		
		JLabel find_label = new JLabel("Find:");
		JLabel replace_with = new JLabel("Replace with:");
		JLabel direction = new JLabel("Direction");
		JLabel scope = new JLabel("Scope");
		JLabel options = new JLabel("Options");
		
		// Input Text Fields
		JTextField findString = new JTextField(20);
		JTextField replaceString = new JTextField(20);
		
		JRadioButton forward = new JRadioButton("Forward");
		JRadioButton backward = new JRadioButton("Backward");
		JRadioButton all = new JRadioButton("All");
		JRadioButton selected = new JRadioButton("Selected lines");
		
		// Group Buttons
		ButtonGroup directionGroup = new ButtonGroup();
		directionGroup.add(forward);
		directionGroup.add(backward);
		
		ButtonGroup scopeGroup = new ButtonGroup();
		scopeGroup.add(all);
		scopeGroup.add(selected);

		// Add Group Items
		layout.setHorizontalGroup(layout.createParallelGroup()
			// Top layout
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(find_label)
					.addComponent(replace_with)	
				)	
				// Middle layout
				.addGroup(layout.createParallelGroup()
					.addComponent(findString)
					.addComponent(replaceString)
				)
			)
			// Bottom layout
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(direction)
					.addComponent(forward)
					.addComponent(backward)
				)
				// Top - Right layout
				.addGroup(layout.createParallelGroup()
					.addComponent(scope)
					.addComponent(all)
					.addComponent(selected)
				)
			)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			// Top - Right layout
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(findString)
						.addComponent(find_label)
				)	
					// Middle layout
				.addGroup(layout.createParallelGroup()
					.addComponent(replaceString)
					.addComponent(replace_with)	
				)
			)
			
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(scope)
					.addComponent(direction)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(all)
					.addComponent(forward)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(selected)
					.addComponent(backward)
				)
			)
		);
	}
}