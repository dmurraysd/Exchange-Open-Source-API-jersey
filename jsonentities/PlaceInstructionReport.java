package aping.jsonentities;

import aping.enums.InstructionReportErrorCode;
import aping.enums.InstructionReportStatus;
import java.beans.Transient;
import java.io.Serializable;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class PlaceInstructionReport implements Serializable{
    
        private static final long serialVersionUID = 1L;
	private InstructionReportStatus status;
	private InstructionReportErrorCode errorCode;
        private PlaceInstruction instruction;
	private String betId;
	private Date placedDate;
	private double averagePriceMatched;
	private double sizeMatched;

	public InstructionReportStatus getStatus() {
		return status;
	}

	public void setStatus(InstructionReportStatus status) {
		this.status = status;
	}

	public InstructionReportErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(InstructionReportErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public PlaceInstruction getInstruction() {
		return instruction;
	}

	public void setInstruction(PlaceInstruction instruction) {
		this.instruction = instruction;
	}

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public double getAveragePriceMatched() {
		return averagePriceMatched;
	}

	public void setAveragePriceMatched(double averagePriceMatched) {
		this.averagePriceMatched = averagePriceMatched;
	}

	public double getSizeMatched() {
		return sizeMatched;
	}

	public void setSizeMatched(double sizeMatched) {
		this.sizeMatched = sizeMatched;
	}

}
