public class GeneratedClass {
	public Object spouse { get; set; }
	public int age { get; set; }
	public Boolean isAlive { get; set; }
	public String lastName { get; set; }
	public String firstName { get; set; }
	public childrenClass[] children { get; set; }
	public phoneNumbersClass[] phoneNumbers { get; set; }
	public addressClass address { get; set; }
	public GeneratedClass() {}
}
public class childrenClass {
	public childrenClass() {}
}
public class phoneNumbersClass {
	public String number { get; set; }
	public String type { get; set; }
	public phoneNumbersClass() {}
}
public class addressClass {
	public String postalCode { get; set; }
	public String state { get; set; }
	public String city { get; set; }
	public String streetAddress { get; set; }
	public addressClass() {}
}
