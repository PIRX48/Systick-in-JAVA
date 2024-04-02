package systick_v4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

public class Okno_v4 extends JFrame {

	private JPanel contentPane;
	private JTextField text_set_iterrupt;
	private JTextField text_internal_external;
	private JTextField text_enable_disable;
	private JTextField text_RVR;
	private JTextField text_CVR;
	private JTextField text_CSR;
	
	final static byte BURST_MODE = 0;
    final static byte CONTINOUS_MODE = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno_v4 frame = new Okno_v4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Okno_v4() {
		
		Systick_v4 s = new Systick_v4();
		Generator_v4 g = new Generator_v4();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JButton btn_RVR = new JButton("RVR");
		panel.add(btn_RVR);
		
		JSpinner spinner_RVR = new JSpinner();
		panel.add(spinner_RVR);
		
		text_RVR = new JTextField();
		panel.add(text_RVR);
		text_RVR.setColumns(10);
		
		JButton btn_CVR = new JButton("CVR");
		panel.add(btn_CVR);
		
		JSpinner spinner_CVR = new JSpinner();
		panel.add(spinner_CVR);
		
		text_CVR = new JTextField();
		panel.add(text_CVR);
		text_CVR.setColumns(10);
		
		JButton btn_CSR = new JButton("CSR");
		panel.add(btn_CSR);
		
		JSpinner spinner_CSR = new JSpinner();
		panel.add(spinner_CSR);
		
		text_CSR = new JTextField();
		panel.add(text_CSR);
		text_CSR.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		Knob k = new Knob();
		panel_4.add(k);
			
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btn_tick = new JButton("Tick");
		panel_5.add(btn_tick);
		
		JButton btn_reset = new JButton("Reset");
		panel_5.add(btn_reset);
		
		JButton btn_Burst = new JButton("Burst");
		panel_5.add(btn_Burst);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btn_Set_Interrupt = new JButton("Set interrupt");
		panel_2.add(btn_Set_Interrupt);
		
		JButton btn_internal_external = new JButton("External");
		panel_2.add(btn_internal_external);
		
		JButton btn_enable_disable = new JButton("Enable");
		panel_2.add(btn_enable_disable);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 2, 0, 0));
		
		text_set_iterrupt = new JTextField();
		panel_3.add(text_set_iterrupt);
		text_set_iterrupt.setColumns(10);
		
		text_internal_external = new JTextField();
		panel_3.add(text_internal_external);
		text_internal_external.setColumns(10);
		
		text_enable_disable = new JTextField();
		panel_3.add(text_enable_disable);
		text_enable_disable.setColumns(10);
		
		btn_RVR.addActionListener(e->{
			s.setRVR((Integer)spinner_RVR.getValue());
			spinner_RVR.setValue(0);
			
		});
		
		btn_CVR.addActionListener(e->{
			s.setCVR((Integer)spinner_CVR.getValue());
			spinner_CVR.setValue(0);
		});
		
		btn_CSR.addActionListener(e->{
			s.setCSR((Integer)spinner_CSR.getValue());
			spinner_CSR.setValue(0);
		});
		
		k.addActionListener(e->{
			if(e.getActionCommand()=="new_value") {
				g.set_frequency((int)k.get_value());
			}
		});
		
		btn_tick.addActionListener(e->{
			if(s.getSource()==true)
			{
				s.tickExternal();
			}
			else
			{
				if(g.get_alive()==false)
				{
					g.set_alive(true);
					btn_tick.setText("Halt");
				}
				else
				{
					g.set_alive(false);
					btn_tick.setText("Tick");
				}
			}
		});
		
		btn_reset.addActionListener(e->{
			s.reset();
		});
		
		btn_Burst.addActionListener(e->{
			
			if(g.get_burst()==true)
			{
				

				g.set_burst(false);
				btn_Burst.setText("Continious");
			}
			else
			{
				g.set_burst(true);
				btn_Burst.setText("Burst");
			}
			

		});
		
		btn_Set_Interrupt.addActionListener(e->{
			if(s.isInterruptFlag()==false)
			{
				s.setInterruptEnable();
			}
			else
			{
				s.setInterruptDisable();
			}

		});
		
		btn_internal_external.addActionListener(e->{
			if(s.getSource()==false)
			{
				s.setSourceExternal();
				btn_internal_external.setText("Internal");
			}
			else
			{
				s.setSourceInternal();
				btn_internal_external.setText("External");
			}
		});
		
		btn_enable_disable.addActionListener(e->{
			if(s.getEnabled()==true)
			{
				s.setDisable();
				btn_enable_disable.setText("Enable");
			}
			else
			{
				s.setEnable();
				btn_enable_disable.setText("Disable");
			}
		});
		
		g.addActionListener(e->{
			if(e.getActionCommand()=="tick") {
	
					s.tickInternal();
				}
		});
		
		s.addActionListener(e->{
			if(s.isInterruptFlag()==false)
			{
				text_set_iterrupt.setText("Enabled");
			}
			else
			{
				text_set_iterrupt.setText("Disabled");
			}
			if(s.getSource()==true)
			{
				text_internal_external.setText("External");
			}
			else
			{
				text_internal_external.setText("Internal");
			}
			if(s.getEnabled()==true)
			{
				text_enable_disable.setText("Enable");
			}
			else
			{
				text_enable_disable.setText("Disable");
			}
			text_RVR.setText(Integer.toString(s.getRVR()));
			text_CVR.setText(Integer.toString(s.getCVR()));
			text_CSR.setText(Integer.toString(s.getCSR()));
		});
		
	}
}

