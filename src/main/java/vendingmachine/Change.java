package vendingmachine;

import java.util.*;

public class Change
{
	private List<Money> supportedMoney;
	private List<Money> unsupportedMoney;

	public Change ()
	{
		this.supportedMoney = new ArrayList<Money>();
		this.unsupportedMoney = new ArrayList<Money>();
	}

	public void addSupported (Money money)
	{
		supportedMoney.add(money);
	}

	public void addUnsupported (Money money)
	{
		unsupportedMoney.add(money);
	}

	public Money[] takeChange (long cents)
	{
		List<Money> change = new ArrayList<Money>();
		supportedMoney.sort((Money m1, Money m2) -> (int)(m2.cents() - m1.cents()));
		Iterator<Money> it = supportedMoney.iterator();
		while (it.hasNext() && cents > 0)
		{
			Money money = it.next();
			if (money.cents() <= cents)
			{
				change.add(money);
				it.remove();
				cents -= money.cents();
			}
		}
		change.addAll(unsupportedMoney);
		unsupportedMoney.clear();
		return change.toArray(new Money[0]);
	}
}