package core;

public class Employee {
	private String eid;
	private String name;
	private String phone;
	private Integer salary;
	
	public Employee(String eid, String name,String phone,Integer salary){
		this.eid = eid;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}
	
	public String getEID(){
		return eid;
	}

	public String getName(){
		return name;
	}

	public String getPhone(){
		return phone;
	}

	public Integer getSalary(){
		return salary;
	}
}

