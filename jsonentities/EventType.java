package aping.jsonentities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventType implements Serializable{
	private String id;
	private String name;

	public EventType() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "EventType{" + "id=" + id + ", name=" + name + '}';
    }

	

}
