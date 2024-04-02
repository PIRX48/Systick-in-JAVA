package systick_v4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTEventMulticaster;

public class Generator_v4 extends Thread{
	ActionListener al;
	
	boolean reddy = false;
	boolean alive = false;
	boolean burst = true;
	long frequency = 1000;
	
	public Generator_v4() {
		start();
	}

	boolean get_alive()
	{
		return alive;
	}
	
	void set_alive(boolean tmp)
	{
		alive = tmp;
	}
	
	boolean get_burst()
	{
		return burst;
	}
	
	void set_burst(boolean tmp)
	{
		burst = tmp;
		alive = burst;
	}
	
	double set_frequency(double tmp)
	{
		frequency=Math.round(1000/tmp);
		return frequency;
	}
	
    public void run() {
		int licznik=0;
		while(true) {
			if(alive){
				//setPulseCount(100);
				licznik++;
				try {
					Thread.sleep(frequency);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "tick"));		
			}
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			}
	}
    
	public void addActionListener(ActionListener l) {
		al = AWTEventMulticaster.add(al, l);
	}
	
	public void removeActionListener(ActionListener l) {
		al = AWTEventMulticaster.remove(al, l);
	}
	
	public static void main(String[] args) {
		Generator_v4 g = new Generator_v4();
		g.run();
		
	}

}
