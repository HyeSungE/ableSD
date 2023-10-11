package com.able.ableSD;

public enum SolutionCode {
	XContact("BA12"), XCube("BA04"), TypeIX("BA02"), AbleReport("BA15"), AbleWallboard("BA08");

	private final String code;

	SolutionCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
