package rgr;

import java.util.StringTokenizer;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;
import java.util.function.BooleanSupplier;

public class Viborets extends Actor{

	private Model model;
	private Main gui;
	private QueueForTransactions qReg;
	private boolean bul;
	private BooleanSupplier bool;
	private BooleanSupplier freeCabs;
	private ChooseRandom rnd;
	private ChooseRandom rnd1;
	private BooleanSupplier freeBoxs ;
	
	public boolean isBul() {
				return bul;
	}

	public void setBul(boolean b) {
		this.bul = b;
	}

	public Viborets(String name, Main gui, Model model) {
		this.setNameForProtocol(name);
		this.model = model;
		this.gui = gui;
	}

	@Override
	protected void rule() {
		init_fields();
		//initCondition();
		model.getqViborets().add(this);
		try {
			double time = getDispatcher().getCurrentTime();
			//waitForCondition(bool);
			waitForCondition(() -> isBul(), "Отримати бюлетень");
			double waitTime = getDispatcher().getCurrentTime() - time;
			model.getHistoWaitForBool().add(waitTime);
			model.getqCab().add(this);
			double time2 = getDispatcher().getCurrentTime();
			//waitForCondition(freeCabs);
			waitForCondition(() -> model.isCabsFree(), "Зайти в кабіну");
			double waitTime2 = getDispatcher().getCurrentTime() - time2;
			model.getHistoWaitForCab().add(waitTime2);
			model.getqCab().remove(this);
			model.getCab();
			holdForTime(rnd.next());
			model.realizeCab();
			
			model.getqBox().add(this);
			double time3 = getDispatcher().getCurrentTime();
			//waitForCondition(freeBoxs);
			waitForCondition(() -> model.isBoxsFree(), "Покласти в урну бюлетень");
			double waitTime3 = getDispatcher().getCurrentTime() - time3;
			model.getHistoWaitForBox().add(waitTime3);
			model.getqBox().remove(this);
			model.getBox();
			holdForTime(rnd1.next());
			model.realizeBox();
				
		} catch (DispatcherFinishException e) {
			return;
		
		}
		
		}

//	private void initCondition() {
//		 bool = new IWaitCondition() {
//			
//			@Override
//			public boolean testCondition() {
//				// TODO Auto-generated method stub
//				return isBul();
//			}
//			public String toString(){				
//				return "Отримати бюлетень";
//			}
//		};
//		freeCabs = new IWaitCondition() {
//			
//			@Override
//			public boolean testCondition() {
//				return model.isCabsFree();
//			}
//		};
//		freeBoxs = new IWaitCondition() {
//			
//			@Override
//			public boolean testCondition() {
//				return model.isBoxsFree();
//			}
//		};
//		
//	}

	private void init_fields() {
		rnd = gui.getChooseRandom_1();
		rnd1 = gui.getChooseRandom_2();
	}

}
