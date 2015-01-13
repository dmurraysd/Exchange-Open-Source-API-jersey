package aping.jsonentities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventTypeResult implements Serializable{
        //@XmlElement
	private EventType eventType ; 
	private int marketCount;
	
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
        
	public int getMarketCount() {
		return marketCount;
        }
	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

    @Override
    public String toString() {
        return "EventTypeResult{" + "eventType=" + eventType + ", marketCount=" + marketCount + '}';
    }
        
}
