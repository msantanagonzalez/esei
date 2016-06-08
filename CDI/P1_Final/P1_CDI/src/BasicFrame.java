import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @class BasicFrame
 * @brief Creates a JFrame object to build the main windows for the Graphics
 * @access public
 */

public class BasicFrame extends JFrame{
    
    /**
     * @brief BasicFrame Constructor 
     * @param type,initTime,finalTime,maxTime,times
     * @access public
     */
    
	public BasicFrame(int type,long initTime,long finalTime,long maxTime, ArrayList<ArrayList<Long>> times){
		/** Makes a layout with x rows but 1 col */
		GridLayout layout = new GridLayout(0, 1);
		
		/** Set the layout to the JFrame object */
		this.setLayout(layout);
		
		/** Configuration of the window */
		setSize(1024,600);
		setVisible(true);
		if(type==1){
			setTitle("Threads with text");	
		}else{
			setTitle("Threads with math operation");
		}
		
		/** Adjust dimension */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		
		/** Set main windows location and allow user to finish the execution by closing the window */
		setLocation(x,y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/** Creates Panel object, the amount of panels it´s equal to the ArrayList size */
		for(int i=0;i<times.size();i++){
			this.add(new ThreadPanel(i+1,initTime,finalTime,maxTime,times));
		}
		/** Creates a default panel to show general information about the Threads */
		this.add(new ThreadPanel(0,initTime,finalTime,maxTime,null));
	}
}
