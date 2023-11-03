package com.able.ableSD;

import com.able.ableSD.DTO.SolutionCodeConstants;

public enum SolutionCode {
	XContact(SolutionCodeConstants.XCONTACT_CODE), 
	XCube(SolutionCodeConstants.XCUBE_CODE), 
	TypeIX(SolutionCodeConstants.TYPEIX_CODE), 
	AbleReport(SolutionCodeConstants.REPORT_CODE), 
	AbleWallboard(SolutionCodeConstants.WALLBOARD_CODE),
	SOFTPHONE (SolutionCodeConstants.SOFTPHONE_CODE);
	//SIPPHON (SolutionCodeConstants.SIPPHONE_CODE);
	private final String code;

	SolutionCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	SolutionCode getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
