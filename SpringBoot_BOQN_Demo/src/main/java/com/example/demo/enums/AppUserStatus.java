/**
 * 
 */
package com.example.demo.enums;

/**
 * @author mahasarathi
 *
 */
public enum AppUserStatus {
	ACTIVE("ACTIVE"), DELETED("DELETED");

	private final String name;

	private AppUserStatus(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}
}
