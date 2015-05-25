package myRegress;

import widgets.regres.Regres2;

public class RegressExp extends Regres2 {

	@Override
	public double fi1(double x) {
		return Math.exp(x)/(1-x);
	}

	@Override
	public double fi2(double x) {
		return 1;
	}

	@Override
	public String getLabelName() {
		return "q=a*exp(w)/(1-w)+b";
	}

}
