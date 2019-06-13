92
Included comments
File menu did not include Exit

Your JavaGUI class should not include static data members. The need for "static" implies a flaw in your object design.
Your mechanism for constructing action listeners seemed awkward to me.
Your listeners class had constructors that were never used. Why did you include them?
