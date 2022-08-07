package vendingmachine;

import java.util.*;

public class Credit
{
	private long credit;

	public Credit ()
	{
		this.credit = 0;
	}

	public void add (Money money)
	{
		credit += money.cents();
	}

	public void reduce (long price)
	{
		credit -= price;
	}

	public long cents ()
	{
		return credit;
	}

	public long clear ()
	{
		long remaining = credit;
		credit = 0;
		return remaining;
	}
}