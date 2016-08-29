package com.boxiang.share.sample;

public class IntegralFacts {
	public IntegralFacts(IntegralBuilder builder) {
		this.register = builder.register;
		this.trade = builder.trade;
		this.comment = builder.comment;
	}

	private final boolean register;
	private final boolean trade;
	private final boolean comment;

	public int getNum(){
		int num=0;
		if(register)num+=30;
		return num;
	}
	@Override
	public String toString() {
		return "IntegralFacts [register=" + register + ", trade=" + trade + ", comment=" + comment + "]";
	}

	public static void main(String[] args) {
		System.out.println(new IntegralFacts.IntegralBuilder().build());
		System.out.println(new IntegralFacts.IntegralBuilder().register(true).build());
		System.out.println(new IntegralFacts.IntegralBuilder().register(true).trade(true).comment(true).build());
	}

	public static class IntegralBuilder {
		private boolean register = false;
		private boolean trade = false;
		private boolean comment = false;

		public IntegralBuilder() {

		}

		public IntegralBuilder register(boolean register) {
			this.register = register;
			return this;
		}

		public IntegralBuilder trade(boolean trade) {
			this.trade = trade;
			return this;
		}

		public IntegralBuilder comment(boolean comment) {
			this.comment = comment;
			return this;
		}

		public IntegralFacts build() {
			return new IntegralFacts(this);
		}
	}

}
