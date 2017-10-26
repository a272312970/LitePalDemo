package com.example.pengshan.litepaldemo;

import java.io.Serializable;

public class GroupMemberBean implements Serializable {

	private String name;
	private String sortLetters;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	@Override
	public String toString() {
		return "GroupMemberBean{" +
				"name='" + name + '\'' +
				", sortLetters='" + sortLetters + '\'' +
				'}';
	}
}