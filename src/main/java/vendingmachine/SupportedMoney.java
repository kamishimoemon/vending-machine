package vendingmachine;

public interface SupportedMoney
{
	boolean isSupported (Money money);

	public static final SupportedMoney ALL = new SupportedMoney() {
		@Override
		public boolean isSupported (Money money) {
			return true;
		}
	};

	public static SupportedMoney only (Money[] supportedMoney)
	{
		return new SupportedMoney() {
			@Override
			public boolean isSupported (Money money) {
				return java.util.Arrays.asList(supportedMoney).contains(money);
			}
		};
	}
}