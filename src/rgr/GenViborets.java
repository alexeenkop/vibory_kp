package rgr;

import process.Actor;
import process.QueueForTransactions;
import widgets.ChooseData;
import widgets.ChooseRandom;

public class GenViborets extends Actor{

	double modelling_time;
	private Main gui;
	private Model model;
	ChooseRandom rnd;
	

	public GenViborets(String string, Main gui, Model model) {
		this.setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
	}

	@Override
	protected void rule() {
		init_fields();
		int i = 1;
		while (getDispatcher().getCurrentTime() <modelling_time){
			holdForTime(rnd.next());
			String name = "viborets" + String.valueOf(i++);
			Viborets viborets = new Viborets(name, gui, model);
			getDispatcher().addStartingActor(viborets);
		}
			
		
	}

	private void init_fields() {
		rnd = gui.getChooseRandom_3();
		modelling_time = gui.getChooseData_3().getDouble();
		
	}

}
