package com.rhizome.dto;

/**
 * EmployeeテーブルのDTO
 * @author IT-College
 *
 */
public class Employee {
	private Integer idEmployee;
	private String nmEmployee;
	private String knEmployee;
	private String mail;
	private String password;
	private Integer idDepartment;

	public Integer getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getNmEmployee() {
		return nmEmployee;
	}
	public void setNmEmployee(String nmEmployee) {
		this.nmEmployee = nmEmployee;
	}
	public String getKnEmployee() {
		return knEmployee;
	}
	public void setKnEmployee(String knEmployee) {
		this.knEmployee = knEmployee;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}
}
