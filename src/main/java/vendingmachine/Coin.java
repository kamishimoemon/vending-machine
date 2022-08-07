package vendingmachine;

public enum Coin
	implements Money
{
	PENNY(1),
	NICKEL(5),
	DIME(10),
	QUARTER(25),
	HALF(50),
	FULL(100);

	private long cents;

	Coin (long cents)
	{
		this.cents = cents;
	}

	public long cents ()
	{
		return this.cents;
	}
}