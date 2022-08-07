package vendingmachine;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class VendingMachineTest
{
	@Test
	public void insertingOnePennyShouldGiveMeOneCredit ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.PENNY);
		assertEquals(1, vm.currentCredit());
	}

	@Test
	public void insertingOneNickelShouldGiveMeFiveCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.NICKEL);
		assertEquals(5, vm.currentCredit());
	}

	@Test
	public void insertingOneDimeShouldGiveMeTenCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.DIME);
		assertEquals(10, vm.currentCredit());
	}

	@Test
	public void insertingOneQuarterShouldGiveMeTwentyFiveCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.QUARTER);
		assertEquals(25, vm.currentCredit());
	}

	@Test
	public void insertingOneHalfShouldGiveMeFiftyCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.HALF);
		assertEquals(50, vm.currentCredit());
	}

	@Test
	public void insertingOneFullShouldGiveMeHundredCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.FULL);
		assertEquals(100, vm.currentCredit());
	}

	@Test
	public void insertingOneDollarBillShouldGiveMeOneHundredCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.ONE);
		assertEquals(100, vm.currentCredit());
	}

	@Test
	public void insertingTwoDollarBillShouldGiveMeTwoHundredCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.TWO);
		assertEquals(200, vm.currentCredit());
	}

	@Test
	public void insertingFiveDollarBillShouldGiveMeFiveHundredCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.FIVE);
		assertEquals(500, vm.currentCredit());
	}

	@Test
	public void insertingTenDollarBillShouldGiveMeThousandCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.TEN);
		assertEquals(1000, vm.currentCredit());
	}

	@Test
	public void insertingTwentyDollarBillShouldGiveMeTwoThousandCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.TWENTY);
		assertEquals(2000, vm.currentCredit());
	}

	@Test
	public void insertingFiftyDollarBillShouldGiveMeFiveThousandCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.FIFTY);
		assertEquals(5000, vm.currentCredit());
	}

	@Test
	public void insertingHundredDollarBillShouldGiveMeTenThousandCredits ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Bill.HUNDRED);
		assertEquals(10000, vm.currentCredit());
	}

	@Test
	public void emptyChange ()
	{
		VendingMachine vm = new VendingMachine();
		assertEquals(new Money[0], vm.takeChange());
		assertEquals(new Money[0], vm.takeChange());
	}

	@Test
	public void takingChangeShouldGiveMeBackMyCredit1 ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.PENNY);
		assertEquals(new Money[] { Coin.PENNY }, vm.takeChange());
	}

	@Test
	public void takingChangeShouldGiveMeBackMyCredit2 ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.DIME);
		vm.insertMoney(Coin.HALF);
		vm.insertMoney(Bill.TEN);
		List<Money> change = Arrays.asList(vm.takeChange());
		assertEquals(3, change.size());
		assertTrue(change.contains(Coin.DIME));
		assertTrue(change.contains(Coin.HALF));
		assertTrue(change.contains(Bill.TEN));
		//assertEquals(new Money[] { Coin.DIME, Coin.HALF, Bill.TEN }, vm.takeChange());
	}

	@Test
	public void takingChangeShouldResetCredit1 ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.PENNY);
		vm.takeChange();
		assertEquals(0, vm.currentCredit());
	}

	@Test
	public void takingChangeShouldResetCredit2 ()
	{
		VendingMachine vm = new VendingMachine();
		vm.insertMoney(Coin.DIME);
		vm.insertMoney(Coin.HALF);
		vm.insertMoney(Bill.TEN);
		vm.takeChange();
		assertEquals(0, vm.currentCredit());
	}

	@Test
	public void insertingUnsupportedMoneyShouldGiveMeNoCredit ()
	{
		VendingMachine vm = new VendingMachine(new Money[] { Coin.FULL });
		vm.insertMoney(Bill.TEN);
		assertEquals(0, vm.currentCredit());
	}

	@Test
	public void insertingUnsupportedMoneyShouldGoToChange ()
	{
		VendingMachine vm = new VendingMachine(new Money[] { Coin.FULL });
		vm.insertMoney(Bill.TEN);
		assertEquals(new Money[] { Bill.TEN }, vm.takeChange());
	}

	@Test
	public void buyingAnExistingProductWithSufficientMoney ()
	{
		Soda pop = new Soda();
		VendingMachine vm = new VendingMachine();
		vm.setPrice(Soda.class, 25);
		vm.loadProduct(pop);
		vm.insertMoney(Coin.QUARTER);
		assertSame(pop, vm.selectProduct(Soda.class));
		assertEquals(new Money[0], vm.takeChange());
	}

	@Test
	public void buyingAnExistingProductWithInsufficientMoney ()
	{
		Soda pop = new Soda();
		VendingMachine vm = new VendingMachine();
		vm.setPrice(Soda.class, 25);
		vm.loadProduct(pop);
		vm.insertMoney(Coin.DIME);
		assertNull(vm.selectProduct(Soda.class));
		assertEquals(new Money[] { Coin.DIME }, vm.takeChange());
	}

	@Test
	public void buyingAnInexistingProductWithSufficientMoney ()
	{
		VendingMachine vm = new VendingMachine();
		vm.setPrice(Soda.class, 25);
		vm.insertMoney(Coin.HALF);
		assertNull(vm.selectProduct(Soda.class));
		assertEquals(new Money[] { Coin.HALF }, vm.takeChange());
	}
}