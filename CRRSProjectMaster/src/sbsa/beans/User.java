package sbsa.beans;

import java.io.Serializable;

import sbsa.exceptions.CRRSException;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String personID;
	private String name;
	private String password;
	private String telephone;
	private String title;
	private String department;
	private String email;
	private boolean admin = false;
	private boolean exco = false;
	
	public User(String personID, String name, String password, String telephone, String title, String department, String email, boolean admin, boolean exco) throws CRRSException {
		setPersonID(personID);
		setName(name);
		setPassword(password);
		setTelephone(telephone);
		setTitle(title);
		setDepartment(department);
		setEmail(email);
		setAdmin(admin);
		setExco(exco);
		
	}
	
	
	public User(String name, String password, String telephone, String title, String department, String email) throws CRRSException {
		this("",name,password,telephone,title,department, email, false,false);
	}
	
	
	
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws CRRSException{
		if (name.trim().length() > 0) {
			this.name = name;
			} else {
				throw new CRRSException("User - Name can't be blank");
			}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws CRRSException {
		if (password.trim().length() > 0) {
			this.password = password;
			} else {
				throw new CRRSException("User - password can't be blank");
			}
	}
	@Override
	public String toString() {
		return "User [personID=" + personID + ", name=" + name + ", telephone=" + telephone + ", title=" + title
				+ ", department=" + department + ", email=" + email + ", admin=" + admin + ", exco=" + exco + "]";
	}


	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) throws CRRSException {
		if (telephone.trim().length() > 0) {
			this.telephone = telephone;
			} else {
				throw new CRRSException("User - telephone can't be blank");
			}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) throws CRRSException {
		if (department.trim().length() > 0) {
			this.department = department;
			} else {
				throw new CRRSException("User - department can't be blank");
			}

	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws CRRSException {
		if (email.trim().length() > 0) {
			this.email = email;
			} else {
				throw new CRRSException("User - email can't be blank");
			}
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isExco() {
		return exco;
	}
	public void setExco(boolean exco) {
		this.exco = exco;
	}
	
}
