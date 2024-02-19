package rs.ac.uns.ftn.siit.op.yaml.task1;

public class Product {
	public String sku;
	public Integer quantity;
	public String description;
	public Float price;

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", quantity=" + quantity + ", description=" + description + ", price=" + price
				+ "]";
	}

}
