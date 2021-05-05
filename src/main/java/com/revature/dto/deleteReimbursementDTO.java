package com.revature.dto;

public class deleteReimbursementDTO {
	
	private int id;
	private int author;
	
	public deleteReimbursementDTO() {
		super();
	}
	
	public deleteReimbursementDTO(int id, int author) {
		super();
		this.id = id;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getauthor() {
		return author;
	}

	public void setauthor(int author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + author;
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
		deleteReimbursementDTO other = (deleteReimbursementDTO) obj;
		if (id != other.id)
			return false;
		if (author != other.author)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "deleteReimbursementDTO [id=" + id + ", author=" + author + "]";
	} 

}
