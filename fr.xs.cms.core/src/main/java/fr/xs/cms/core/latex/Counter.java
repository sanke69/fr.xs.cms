package fr.xs.cms.core.latex;
import java.util.HashMap;


public class Counter {
	public HashMap<String, Integer> counters;
	
	Counter(){
		//initialize counters
		counters = new HashMap<String, Integer>();
		newCounter("part");
		newCounter("chapter");
		newCounter("section");
		newCounter("subsection");
		newCounter("subsubsection");
		newCounter("footnote");
		newCounter("table");
		newCounter("equation");
		newCounter("mpfootnote");
		newCounter("subparagraph");
		newCounter("page");
		newCounter("figure");
		newCounter("enumi");
		newCounter("enumii");
		newCounter("enumiii");
		newCounter("enumiv");
	}
	public void addToCounter(String counter, int value){
		if(!isCounter(counter)){
			setCounter(counter, value);
		}else{
			setCounter(counter, counters.get(counter)+value);
		}
	}
	public int value(String counter){
		if(!isCounter(counter)){
			return 0;
		}
		return counters.get(counter);
	}
	
	public void setCounter(String counter, int value){
		counters.put(counter,value);
	}
	
	public void newCounter(String counter){
		//set to 0 if and only if such counter
		//doesn't exist
		if(!isCounter(counter)){
			setCounter(counter, 0);
		}
	}
	
	public boolean isCounter(String counter){
		return counters.containsKey(counter);
	}
}