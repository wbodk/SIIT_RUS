package rs.ac.uns.ftn.siit.op.yaml.task1;

public class Person {
	public String given;
	public String family;
	public Address address;

	@Override
	public String toString() {
		return "Person [given=" + given + ", family=" + family + ", address=" + address + "]";
	}

}
