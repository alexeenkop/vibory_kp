package rgr;

import java.util.HashMap;
import java.util.Map;

import process.Actor;
import process.Dispatcher;
import process.QueueForTransactions;
import process.MultiActor;
import stat.DiscretHisto;
import stat.Histo;
import widgets.experiments.IExperimentable;
import widgets.trans.ITransProcesable;
//import widgets.trans.TransProcessQueue;

public class Model implements IExperimentable, ITransProcesable{
	
	Dispatcher dispatcher;
	Main gui;
	private GenViborets genViborets;
	private Registrator reg;
	 MultiActor multiReg;
    int nCabs;
    int nBoxs;
	private QueueForTransactions qViborets;
	private QueueForTransactions qCab;
	private QueueForTransactions qBox;
	private DiscretHisto histoToReg;
	private  DiscretHisto histoToCab;
	private  DiscretHisto histoToBox;
	private Histo histoWaitForBool;
	private Histo histoWaitForCab;
	private Histo histoWaitForBox;
	private Histo histoWaitForViborets;

	
	public QueueForTransactions getqViborets() {
		if(qViborets == null){
			qViborets = new QueueForTransactions();
			qViborets.setDispatcher(dispatcher);			
			qViborets.setDiscretHisto(getHistoToReg());
		}
		return qViborets;
	}
	
	public DiscretHisto getHistoToReg() {		
		if (histoToReg ==null){
			histoToReg = new DiscretHisto();
		}
		return histoToReg;
		
	}

	public DiscretHisto getHistoToCab() {		
		if (histoToCab ==null){
			histoToCab = new DiscretHisto();
		}
		return histoToCab;
		
	}
	
	public DiscretHisto getHistoToBox() {
		if (histoToBox ==null){
			histoToBox = new DiscretHisto(); 
			}
		return histoToBox;
	}
	
	public Model(Dispatcher d, Main g) {
		if (d == null || g == null) {
			System.out.println("Не визначено диспетчера або GUI для Model");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		 dispatcher = d; gui = g;
		 nCabs = gui.getChooseData_1().getInt();
		 nBoxs = gui.getChooseData_2().getInt();
		//Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	private void componentsToStartList() {
		dispatcher.addStartingActor(getGenViborets());
		dispatcher.addStartingActor(getMulr());
	}

	private GenViborets getGenViborets() {
		if (genViborets == null){
			genViborets = new GenViborets("GenViborets", gui, this);
		}
		return genViborets;
	}
	
	private Registrator getReg(){
		if (reg == null){
			reg = new Registrator("Registrator", gui, this);
		}
		return reg;
	}
	
	private MultiActor getMulr(){
		if ( multiReg== null){
			multiReg = new MultiActor();
			multiReg.setNameForProtocol("MultiReg для регістрації");
			multiReg.setOriginal(new Registrator("Регістратор", gui, this));
			multiReg.setNumberOfClones(gui.getChooseData().getInt());
		}
		return multiReg;	
	}
	
	public boolean isCabsFree() {
		return nCabs>0;
	}

	public boolean isBoxsFree() {
		return nBoxs>0;
	}

	public void getBox() {
		 nBoxs--;
	}
	public void realizeBox() {
		 nBoxs++;
	}
	public void getCab() {
		 nCabs--;
	}
	public void realizeCab() {
		 nCabs++;
	}
	
	public void initForTest() {
		dispatcher.setProtocolFileName("Console");
	getqViborets().setPainter(gui.getDiagram().getPainter());
	getqCab().setPainter(gui.getDiagram_2().getPainter());
	getqBox().setPainter(gui.getDiagram_1().getPainter());
	}

	public QueueForTransactions getqCab() {
		if(qCab == null){
			qCab = new QueueForTransactions();
			qCab.setDispatcher(dispatcher);
			qCab.setDiscretHisto(getHistoToCab());
		}
		return qCab;
	}
	
	public QueueForTransactions getqBox() {
		if(qBox == null){
			qBox = new QueueForTransactions();
			qBox.setDispatcher(dispatcher);			
			qBox.setDiscretHisto(getHistoToBox());
			
		}
		return qBox;
	}


	public void initForStat() {
		dispatcher.setProtocolFileName("");
	
	    	}

	public Histo getHistoWaitForBool() {
		if (histoWaitForBool ==null){
			histoWaitForBool = new Histo();
		}
		return histoWaitForBool;
	}

	public Histo getHistoWaitForCab() {
		if (histoWaitForCab ==null){
			histoWaitForCab = new Histo();
		}
		return histoWaitForCab;
	}

	public Histo getHistoWaitForBox() {
		if (histoWaitForBox ==null){
			histoWaitForBox = new Histo();
		}
		return histoWaitForBox;
	}

	public Histo getHistoWaitForViborets() {
		if (histoWaitForViborets ==null){
			histoWaitForViborets = new Histo();
		}
		return histoWaitForViborets;
	}

	@Override
	public void initForExperiment(double factor) {
		getMulr().setNumberOfClones((int)factor);
	}

	@Override
	public Map<String, Double> getResultOfExperiment() {
		
		Map<String, Double> transMap = new HashMap<>();
		transMap.put("Середнє значення черги на реєстрацію", getHistoToReg().getAverage());
		transMap.put("Середнє значення черги до кабінок", getHistoToCab().getAverage());
		transMap.put("Середнє значення черги до урн", getHistoToBox().getAverage());
		
		return transMap;
		
		//return getHistoWaitForBool().getAverage();
	}

	@Override
	public void initForTrans(double finishTime) {
		gui.getChooseData_3().setDouble(finishTime);
		
	}

	@Override
	public void resetTransAccum() {
		getqViborets().resetAccum();
		
	}

	@Override
	public Map<String, Double> getTransResult() {
		
		Map<String, Double> transMap = new HashMap<>();
		transMap.put("Черга на реєстрацію", getqViborets().getAccumAverage());
		transMap.put("Черга до кабінок", getqCab().getAccumAverage());
		transMap.put("Черга до урн", getqBox().getAccumAverage());
		return transMap;
		
		//return getqViborets().getAvg();
	}	
}
