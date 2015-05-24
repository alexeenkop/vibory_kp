package myRegress;

import widgets.regres.Regres2;

public class RegressLn extends Regres2 {

	@Override
	public double fi1(double x) {
		return Math.log(x)/Math.sqrt(x);
	}

	@Override
	public double fi2(double x) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabelName() {
		// TODO Auto-generated method stub
		return "q=a*ln(w)/sqrt(w)+b"; 
	}

}
