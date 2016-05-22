package pack;

public class Person {
	private int id;
	private String name;
	private String surname;
	private String second_name;
	private String sex;
	private String phone;
	private String mail;
	private String password;
	
	
	
	
	Person(String name, String second_name, String surname, String sex, String phone, String mail, String password)
	{
		this.name = name;
		this.surname = surname;
		this.second_name = second_name;
		this.sex = sex;
		this.mail = mail;
		this.phone = phone;
		this.password  = password;
	}
	
	public void set_id(int id) {
		this.id = id;
	}
	
	public int get_id() {
		 return id;
	}
	
	public void set_name(String name) {
		this.name = name;
	}
	
	public String get_name() {
		 return name;
	}
	
	public void set_surname(String surname) {
		this.surname = surname;
	}
	
	public String get_surname() {
		 return surname;
	}
	
	public String get_phone() {
		 return phone;
	}
	
	public String get_mail() {
		 return mail;
	}
	
	public String get_password() {
		 return password;
	}
	
	public String get_second_name() {
		 return second_name;
	}
	
	public String get_sex() {
		 return sex;
	}
}