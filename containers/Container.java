package aping.containers;

import aping.jsonentities.Error;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Container implements Serializable{
	
	private Error error;
	
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}

    @Override
    public String toString() {
        return "Container{" + "error=" + error + '}';
    }
        
	
}
