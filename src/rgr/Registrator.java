package rgr;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;

public class Registrator extends Actor{
	private QueueForTransactions qVib;
	private QueueForTransactions qCab;
	double modelling_time;
	private Main gui;
	private Model model;
	ChooseRandom rnd;
	private BooleanSupplier  isViborets;
	

	public Registrator(String string, Main gui, Model model) {
		this.setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
	} 
	protected void rule() {
		init_fields();	
		//init_condition();
		while (getDispatcher().getCurrentTime() <modelling_time){
			try {
				double time = getDispatcher().getCurrentTime();
				//waitForCondition(isViborets);
				waitForCondition(()-> qVib.size()>0 && !((Viborets)(qVib.peekFirst())).isBul(), "Має бути виборець");
				double waitTime = getDispatcher().getCurrentTime() - time;
				model.getHistoWaitForViborets().add(waitTime);
				Viborets viborets = (Viborets) model.getqViborets().removeFirst();
				holdForTime(rnd.next());
				viborets.setBul(true);
				getDispatcher().printToProtocol("Регістратор видає бюлетень виборцю");		
						
			} catch (DispatcherFinishException e) {
				return;
			}
			
		}
	}

//	private void init_condition() {
//		isViborets = new IWaitCondition() {
//			
//			@Override
//			public boolean testCondition() {
//				return qVib.size()>0 && !((Viborets)(qVib.peekFirst())).isBul();
//			}
//		};
//		
//	}
	private void init_fields() {
		rnd = gui.getChooseRandom();
		modelling_time = gui.getChooseData_3().getDouble();
		qVib = model.getqViborets();
		qCab = model.getqCab();
		
	}
}
