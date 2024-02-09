package rs.ac.uns.ftn.siit.op.yaml.task1;

public class Address {
	public String lines;
	public String city;
	public String state;
	public String postal;

	public Address() {
	}

	public Address(String lines, String city, String state, String postal) {
		super();
		this.lines = lines;
		this.city = city;
		this.state = state;
		this.postal = postal;
	}

	@Override
	public String toString() {
		return "Address [lines=" + lines + ", city=" + city + ", state=" + state + ", postal=" + postal + "]";
	}

}
