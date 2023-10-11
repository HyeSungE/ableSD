package com.able.ableSD.DTO;

public class SolutionFile {
	private String solutionCategory;
	private String solutionId;
	private String solutionName;
	private String solutionPath;
	private String solutionSection;
	
	public SolutionFile() {
		super();
	}


	public SolutionFile(String solutionCategory, String solutionId, String solutionName, String solutionPath,
			String solutionSection) {
		super();
		this.solutionCategory = solutionCategory;
		this.solutionId = solutionId;
		this.solutionName = solutionName;
		this.solutionPath = solutionPath;
		this.solutionSection = solutionSection;
	}


	public String getSolutionCategory() {
		return solutionCategory;
	}

	public void setSolutionCategory(String solutionCategory) {
		this.solutionCategory = solutionCategory;
	}

	public String getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getSolutionPath() {
		return solutionPath;
	}

	public void setSolutionPath(String solutionPath) {
		this.solutionPath = solutionPath;
	}


	public String getSolutionSection() {
		return solutionSection;
	}


	public void setSolutionSection(String solutionSection) {
		this.solutionSection = solutionSection;
	}

	
}
