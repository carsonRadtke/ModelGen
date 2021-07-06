# ModelGen
Generate model objects based on JSON API responses


## Example (JSON -> C#)
### JSON ([test_input.json](https://github.com/carsonRadtke/ModelGen/blob/main/test_input.json))
```json
{
    "firstName": "John",
    "lastName": "Smith",
    "isAlive": true,
    "age": 27,
    "address": {
      "streetAddress": "21 2nd Street",
      "city": "New York",
      "state": "NY",
      "postalCode": "10021-3100"
    },
    "phoneNumbers": [
      {
        "type": "home",
        "number": "212 555-1234"
      },
      {
        "type": "office",
        "number": "646 555-4567"
      }
    ],
    "children": [],
    "spouse": null
  }
```
### C# ([test_output.cs](https://github.com/carsonRadtke/ModelGen/blob/main/test_output.cs))
```csharp
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
```
