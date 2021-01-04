package app;

import java.util.Comparator;

class NutzwertComperator implements Comparator<Hardware>{
	 
	@Override
    public int compare(Hardware hardwareZwei, Hardware hardwareEins)
    {
        return hardwareEins.getNutzwertJeKg().compareTo(hardwareZwei.getNutzwertJeKg());
    }
}