package vendingmachine;

public enum Bill
	implements Money
{
	ONE(100),
	TWO(200),
	FIVE(500),
	TEN(1000),
	TWENTY(2000),
	FIFTY(5000),
	HUNDRED(10000);

	private long cents;

	Bill (long cents)
	{
		this.cents = cents;
	}

	public long cents ()
	{
		return cents;
	}
}