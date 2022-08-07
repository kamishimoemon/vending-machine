package vendingmachine;

import java.util.*;

public class VendingMachine
{
	private SupportedMoney supportedMoney;
	private Credit credit;
	private Change change;
	private HashMap<Class<? extends Product>, Long> prices;
	private Dispenser dispenser;

	public VendingMachine ()
	{
		this(SupportedMoney.ALL);
	}

	public VendingMachine (Money[] supportedMoney)
	{
		this(SupportedMoney.only(supportedMoney));
	}

	public VendingMachine (SupportedMoney supportedMoney)
	{
		this.supportedMoney = supportedMoney;
		this.credit = new Credit();
		this.change = new Change();
		this.prices = new HashMap<>();
		this.dispenser = new Dispenser();
	}

	public void insertMoney (Money money)
	{
		if (supportedMoney.isSupported(money))
		{
			credit.add(money);
			change.addSupported(money);
		}
		else
		{
			change.addUnsupported(money);
		}
	}

	public long currentCredit ()
	{
		return credit.cents();
	}

	public Money[] takeChange ()
	{
		return change.takeChange(credit.clear());
	}

	public void setPrice (Class<? extends Product> productType, long price)
	{
		prices.put(productType, price);
	}

	public void loadProduct (Product product)
	{
		dispenser.add(product);
	}

	public Product selectProduct (Class<? extends Product> productType)
	{
		if (isValidProduct(productType) && hasEnoughCreditFor(productType) && isInStock(productType))
		{
			credit.reduce(prices.get(productType));
			return dispenser.get(productType);
		}
		return null;
	}

	private boolean isValidProduct (Class<? extends Product> productType)
	{
		return prices.containsKey(productType);
	}

	private boolean hasEnoughCreditFor (Class<? extends Product> productType)
	{
		return credit.cents() >= prices.get(productType);
	}

	private boolean isInStock (Class<? extends Product> productType)
	{
		return dispenser.isInStock(productType);
	}
}