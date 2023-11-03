package com.able.ableSD.DTO;

import java.util.HashMap;
import java.util.Map;

public class SolutionCodeMap {
    private static SolutionCodeMap instance;
    private Map<String, String> valueMap;

    private SolutionCodeMap() {
        valueMap = new HashMap<>();
        valueMap.put(SolutionCodeConstants.XCONTACT_CODE, SolutionCodeConstants.XCONTACT_NAME);
        valueMap.put(SolutionCodeConstants.XCUBE_CODE, SolutionCodeConstants.XCUBE_NAME);
        valueMap.put(SolutionCodeConstants.TYPEIX_CODE, SolutionCodeConstants.TYPEIX_Name);
        valueMap.put(SolutionCodeConstants.REPORT_CODE, SolutionCodeConstants.REPORT_Name);
        valueMap.put(SolutionCodeConstants.WALLBOARD_CODE, SolutionCodeConstants.WALLBOARD_Name);
        valueMap.put(SolutionCodeConstants.SOFTPHONE_CODE, SolutionCodeConstants.SOFTPHONE_Name);
        valueMap.put(SolutionCodeConstants.SIPPHONE_CODE, SolutionCodeConstants.SIPPHONE_Name);
    }

    public static SolutionCodeMap getInstance() {
        if (instance == null) {
            instance = new SolutionCodeMap();
        }
        return instance;
    }

    public String getNameForCode(String code) {
        return valueMap.get(code);
    }
    
    public Map getMap() {
    	return valueMap;
    }
}
