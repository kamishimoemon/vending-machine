package vendingmachine;

import java.util.*;

public class Dispenser
{
	private Map<Class<? extends Product>, Queue<Product>> products;

	public Dispenser ()
	{
		this.products = new HashMap<>();
	}

	public void add (Product product)
	{
		Class productType = product.getClass();
		if (!products.containsKey(productType)) products.put(productType, new PriorityQueue<Product>());
		Queue<Product> stock = products.get(productType);
		if (!stock.contains(product)) stock.add(product);
	}

	public boolean isInStock (Class<? extends Product> productType)
	{
		if (!products.containsKey(productType)) return false;
		return !products.get(productType).isEmpty();
	}

	public Product get (Class<? extends Product> productType)
	{
		if (!products.containsKey(productType)) return null;
		return products.get(productType).poll();
	}
}