import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

/**
 * @class ThreadPanel
 * @brief Creates a JPanel object
 * @access public
 */

public class ThreadPanel extends JPanel{
	private int id;
	/** Time when the first thread started to run */
	private long initTime; //ns
	/** Time when the last thread finished the execution */
	private long endTime; //ns
	private long maxTime; //ns
	private ArrayList<ArrayList<Long>> threadTimes;
	
	/**
     * @brief ThreadPanel Constructor 
     * @param id,init,end,max,times
     * @access public
     */
	   
	public ThreadPanel(int id, long init,long end,long max,ArrayList<ArrayList<Long>> times){
		this.id = id;
		this.initTime = init;
		this.endTime = end;
		this.maxTime = max;
		this.threadTimes = times;
	}
	
	/**
     * @brief void paintComponent. Creates the Graphic components on the Frame object
     * @param g
     * @return void
     * @access public
     */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.id == 0){
			/** Shows the Default panel information */
			g.drawString("-|Details|-", 20, 20);
			g.drawString("Started on ns: "+this.initTime, 10, 40);
			g.drawString("Finished on ns: "+this.endTime, 300, 40);
			g.drawString("Runned in "+this.maxTime+"ns", 600, 40);
		}else{
			/** Shows the panel information and builds the graphic components */
			
			/** Get the Thread time information */
			long tS = this.threadTimes.get(this.id-1).get(0);
			long tE = this.threadTimes.get(this.id-1).get(1);
			long tD = this.threadTimes.get(this.id-1).get(2);
			
			/** Builds a graphic string on the panel */
	        g.drawString("Thread #"+this.id, 20, 20);
	        g.drawString("Started on ms: "+tS, 10, 40);
	        g.drawString("Finished on ms: "+tE, 300, 40);
	        g.drawString("Duration: "+tD+" ms.", 600, 40);

	        
	        /** Draws the x-axis line on each panel */
	        int Xleft = 15;
	        int Xright = 950;

	        Graphics2D g2 = (Graphics2D) g;
	        g2.setColor(Color.BLUE);
	        BasicStroke pen = new BasicStroke(4F);
	        g2.setStroke(pen);
	        g2.drawLine(Xleft,110,Xright,110);
	        
	        /** Draws the x-axis values on each panel */
	        Graphics2D g3 = (Graphics2D) g;
	        Graphics2D g4 = (Graphics2D) g;
	        g3.setColor(Color.RED);
	        g4.setColor(Color.BLACK);
	        
	        ArrayList<Long> values = new ArrayList<Long>();
        	for(int i=0;i<threadTimes.size();i++){
        		values.add(threadTimes.get(i).get(0));
        	}
        	for(int i=0;i<threadTimes.size();i++){
        		values.add(threadTimes.get(i).get(1));
        	}
	        	
	      	Collections.sort(values);
	      	   
	      	Graphics2D g2d = (Graphics2D) g.create();
	      	float[] dash3 = { 4f, 0f, 2f };
	      	BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f );
	      	g2.setColor(Color.BLACK);
	      	
	      	int xOn = 15;
	      	int xPos1=0;
	      	int yPos1=80;
	      	int xPos2=0;
		   for(long counter: values){
			g4.drawString(String.valueOf(counter)+"ms", xOn, 130);
			/** Creates the first point on the x-axis */
			if(tS == counter){
			    xPos1 = xOn;
			    g.drawOval( xPos1, yPos1, 2, 2);
			}
			/** Creates the second point on the x-axis */
			if(tE == counter){
		        xPos2 = xOn;
		        g.drawOval( xPos2, yPos1, 2, 2);
	        }
			xOn = xOn + 160;
		   }
		   /** Unification of first and second point on the x-axis */
   	        g2d.setStroke(bs1);
	      	g2d.drawLine(xPos1, yPos1, xPos2, yPos1);
	      	g2d.dispose();
		}
    }
}
