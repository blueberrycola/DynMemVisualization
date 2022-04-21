Welcome to Chase Johnston's Dynamic Memory Visualization
	Done thanks to javax.swing and microsoft paint!

How to run yourself:

	compile MainFrame.java and AlgoPanel.java
	run MainFrame.java main method in terminal or visual studio code
	!!!
	Only one line of code needs to be placed. A string of your working directory with instructions.txt
	on line 22 with variable name called path.
	!!!
	You can easily replace line 22 with the string wrapped in single quotes but it MUST be ran
	in terminal: 'String path = "" + System.getProperty("user.dir") + "instructions.txt";'

How it works:

AlgoPanels are a fixed size of 3800 bytes. Each block in the panel is 100 bytes.
To the right of the memory tiles is the labels which help visualize what partitions of memory are free/allocated.
The data in these tiles is also used for the scheduling algorithms

Helper functions for each different algorithm help decide where the labels and colors go.
	Helper functions: recvInstr() -> |
					   ffIndex()
					   bfIndex()
					   wfIndex()
				         | -> setPanel || freePanel()

MainFrame has the main method: it loads the instruction text file and sends a string buffer to each panel
It is also responsible for keeping everything in the program window.

No pending queue has been implemented but everything else is present.