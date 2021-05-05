package com.revature.dto;

public class ReimbursementActionDTO {
	
	private int id;
	private int status_id;
	private int resolver; 
	
	public ReimbursementActionDTO() {
		super();
	}
	
	public ReimbursementActionDTO(int id, int status_id, int resolver) {
		super();
		this.id = id;
		this.status_id = status_id;
		this.resolver = resolver; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + resolver;
		result = prime * result + status_id;
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
		ReimbursementActionDTO other = (ReimbursementActionDTO) obj;
		if (id != other.id)
			return false;
		if (resolver != other.resolver)
			return false;
		if (status_id != other.status_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementActionDTO [id=" + id + ", status_id=" + status_id + ", resolver=" + resolver + "]";
	}

	
}
