package systick_v4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTEventMulticaster;

public class Systick_v4 implements Cortex_M0_SysTick_Interface{

	int CVR = 0x000000;
	int CSR = 0x000000;
	int RVR = 0x000000;	
	
	ActionListener al;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Systick_v4 s = new Systick_v4();
		s.setRVR(10);
		s.setCSR(11);
		s.setRVR(12);

		
		for(int i=0; i<10; i++)
		{
			s.tickInternal();
			System.out.println(s.getRVR());
			System.out.println(s.getCVR());
			System.out.println(s.getCSR());
			System.out.println(s.getEnabled());
			System.out.println(s.getInterrupt());
			System.out.println(s.getSource());
			System.out.println(s.getCountFlag());
			
			System.out.println(s.isCountFlag());
			System.out.println(s.isEnableFlag());
			System.out.println(s.isInterruptFlag());
			System.out.println(s.isInterrupt());
		}
		
		s.setRVR(5);
		s.setCSR(1);
		for(int i=0; i<10; i++)
		{
			s.tickInternal();
			System.out.println("RVR");
			System.out.println(s.getRVR());
			System.out.println("CVR");
			System.out.println(s.getCVR());
			System.out.println("CSR");
			System.out.println(s.getCSR());
		}
	}
	
	
	
	@Override
	public void tickInternal() {
		// TODO Auto-generated method stub
		if ((CSR & (1 << 2)) == 0)
		{
			if ((CSR & (1 << 0)) != 0)
			{
				if(CVR != 0)
				{
					CVR = CVR - 1;
					if(CVR == 0)
					{
						CSR = CSR | (1<<16);
					}
				}
				else
				{
					CVR = RVR;
				}
			}
			if(al!=null)
				al.actionPerformed(new ActionEvent(this,
					ActionEvent.ACTION_PERFORMED,
					"tick_Internal"));
		}
	}

	@Override
	public void tickExternal() {
		// TODO Auto-generated method stub
		if ((CSR & (1 << 2)) == 1)
		{
			if ((CSR & (1 << 0)) != 0)
			{
				if(CVR != 0)
				{
					CVR = CVR - 1;
					if(CVR == 0)
					{
						CSR = CSR | (1<<16);
					}
				}
				else
				{
					CVR = RVR;
				}
			}
		}
		
		if(al!=null)
			al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"tick_External"));
	}

	@Override
	public void setRVR(int value) {
		// TODO Auto-generated method stub
		if(value == 0)
		{
			CSR = CSR & ~(1 << 0);
			RVR = 0x000000;
			CVR = 0x000000;
		}
		RVR = value & 0xFFFFFF;
		
		if(al!=null)
			al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_RVR"));
	}

	@Override
	public void setCVR(int value) {
		// TODO Auto-generated method stub
		CVR = 0x000000;
		CVR = CSR & ~(1 << 16);
		CVR = RVR;
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_CVR"));
	}

	@Override
	public void setCSR(int value) {
		// TODO Auto-generated method stub
		CSR = value & 0xFFFFFF;
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_CSR"));
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		CSR = 0x000000;
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"reset"));
	}

	@Override
	public void setEnable() {
		// TODO Auto-generated method stub
		CSR = CSR | (1 << 0);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_Enable"));
	}

	@Override
	public void setDisable() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 0);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_Disable"));
	}

	@Override
	public void setSourceExternal() {
		// TODO Auto-generated method stub
		CSR = CSR | (1 << 2);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_SourceExternal"));
	}

	@Override
	public void setSourceInternal() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 2);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_SourceInternal"));
	}

	@Override
	public void setInterruptEnable() {
		// TODO Auto-generated method stub
		CSR = CSR | (1 << 1);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_InterruptEnable"));
	}

	@Override
	public void setInterruptDisable() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 1);
		
		if(al!=null)
		al.actionPerformed(new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				"set_InterruptDisable"));
	}

	@Override
	public int getCVR() {
		// TODO Auto-generated method stub
		return CVR;
	}

	@Override
	public int getRVR() {
		// TODO Auto-generated method stub
		return RVR;
	}

	@Override
	public int getCSR() {
		// TODO Auto-generated method stub
		int CSR_tmp = CSR;
		CSR = CSR & ~ (1 << 16);
		return CSR_tmp;
	}

	@Override
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 16);
		int tmp = (CSR & (1 << 0));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean getInterrupt() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 16);
		int tmp = (CSR & (1 << 1));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean getSource() {
		// TODO Auto-generated method stub
		CSR = CSR & ~(1 << 16);
		int tmp = (CSR & (1 << 2));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean getCountFlag() {
		// TODO Auto-generated method stub
		int tmp = (CSR & (1 << 16));
		CSR = CSR & ~(1 << 16);
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isCountFlag() {
		// TODO Auto-generated method stub
		int tmp = (CSR & (1 << 16));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isEnableFlag() {
		// TODO Auto-generated method stub
		int tmp = (CSR & (1 << 0));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isInterruptFlag() {
		// TODO Auto-generated method stub
		int tmp = (CSR & (1 << 1));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isInterrupt() {
		// TODO Auto-generated method stub
		int tmp = (CSR & (1 << 2));
		if(tmp <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void addActionListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	
	public void removeActionListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}

}
