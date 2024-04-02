package systick_v4;

public interface Cortex_M0_SysTick_Interface {
	public void tickInternal();
    public void tickExternal();
    
    public void setRVR(int value); //
    public void setCVR(int value);
    public void setCSR(int value);
    
    public void reset(); //resetuje rejestr statutowy, nie rusza rejestr�w cvr i rvr
    public void setEnable(); //w��cza timer zr ob bit enable ustaw ma jedem
    public void setDisable(); //to samo co wy�ej tylko ustaw na zero
    public void setSourceExternal();  //odpala tickexternal
    public void setSourceInternal();  //odpala tickinternal mo�na to zrobi� jako boola
    public void setInterruptEnable();  //ustawienie bitu interupt
    public void setInterruptDisable();  //tutaj te�
    
    public int getCVR(); //
    public int getRVR(); //zwraca warto�� rejestru
    public int getCSR(); //
    
    public boolean getEnabled(); //
    public boolean getInterrupt(); //
    public boolean getSource(); //    odczytywanie danych z rejestru statutowego
    public boolean getCountFlag(); //
    
    public boolean isCountFlag();  //
    public boolean isEnableFlag(); //
    public boolean isInterruptFlag();  //  to samo tylko bez zmiany rejestru
    public boolean isInterrupt();    //
}