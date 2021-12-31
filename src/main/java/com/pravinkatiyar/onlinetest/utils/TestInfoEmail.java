package com.pravinkatiyar.onlinetest.utils;

public class TestInfoEmail {
	private String candidateName; 
	private String quizName;
	private int maximumMarks;
	private int marksScored;
	private String result;
	public TestInfoEmail(String candidateName, String quizName, int maximumMarks, int marksScored, String result) {
		this.candidateName = candidateName;
		this.quizName = quizName;
		this.maximumMarks = maximumMarks;
		this.marksScored = marksScored;
		this.result = result;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public int getMaximumMarks() {
		return maximumMarks;
	}
	public void setMaximumMarks(int maximumMarks) {
		this.maximumMarks = maximumMarks;
	}
	public int getMarksScored() {
		return marksScored;
	}
	public void setMarksScored(int marksScored) {
		this.marksScored = marksScored;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	

	

}
