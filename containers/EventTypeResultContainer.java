package aping.containers;

import aping.jsonentities.EventTypeResult;
import java.io.Serializable;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventTypeResultContainer //extends Container
{
	//@XmlElement
	private List<EventTypeResult> result;
		
	public List<EventTypeResult> getResult() {
		return result;
	}
	public void setResult(List<EventTypeResult> result) {
		this.result = result;
	}

    @Override
    public String toString() {
        return "EventTypeResultContainer{" + "result=" + result + '}';
    }
        
}
