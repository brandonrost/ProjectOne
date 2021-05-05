package com.revature.dto;

import java.util.Arrays;

public class addReimbursementDTO {
	private float amount;
	private String type;
    private String description;
    private byte[] receipt;
    private int submitter;
    
    public addReimbursementDTO() {
    	super();
    }
    
	public addReimbursementDTO(float amount, String type, String description, byte[] receipt, int submitter) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.receipt = receipt;
		this.submitter = submitter;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getSubmitter() {
		return submitter;
	}

	public void setSubmitter(int submitter) {
		this.submitter = submitter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + submitter;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		addReimbursementDTO other = (addReimbursementDTO) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (submitter != other.submitter)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "addReimbursementDTO [amount=" + amount + ", type=" + type + ", description=" + description
				+ ", receipt=" + Arrays.toString(receipt) + ", submitter=" + submitter + "]";
	}    
    
}
