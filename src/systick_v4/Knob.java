package systick_v4;

import java.awt.AWTEventMulticaster;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Knob extends JComponent 
	implements MouseMotionListener{
	
	ActionListener al;

	int xKlik;
	int yKlik;
	double kat2;
	public Knob() {
		addMouseMotionListener(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.GRAY);
		
		int r=Math.min(getWidth(), getHeight())/2;
		int x=getWidth()/2;
		int y=getHeight()/2;
		g.fillOval(x-r, y-r, r*2, r*2);
		
		g.setColor(Color.LIGHT_GRAY);
		int rm=r/5;
		//int xm=x;
		//int ym=y-r+rm;
		//int xm=xKlik;
		//int ym=yKlik;
		
		double kat=(Math.atan2((yKlik-y),(xKlik-x)));
		//System.out.println(kat);
		int xm=x+(int)(Math.cos(kat)*0.75*r);
		int ym=y+(int)(Math.sin(kat)*0.75*r);
		kat2=kat+1.5;
		//System.out.println(kat2);
		
		
		
		g.fillOval(xm-rm, ym-rm, rm*2, rm*2);
		
		if(al!=null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "new_value"));
		
	}
	
	public double get_value()
	{
		return kat2;
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(()->{
			JFrame okno=new JFrame();
			
			Knob k=new Knob();
			
			okno.add(k);
			
			okno.setSize(600, 400);
			okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			okno.setVisible(true);
		});
		

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		xKlik=e.getX();
		yKlik=e.getY();
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addActionListener(ActionListener l) {
		al = AWTEventMulticaster.add(al, l);
	}
	
	public void removeActionListener(ActionListener l) {
		al = AWTEventMulticaster.remove(al, l);
	}

}
