package com.rest.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends RuntimeException {

	private String resoureName;
	private String fieldName;
	private Integer fieldValue;

	public NoResourceFoundException(String resoureName, String fieldName, Integer fieldValue) {
		super(String.format(resoureName + " not present with " + fieldName + ": " + fieldValue));
		this.resoureName = resoureName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResoureName() {
		return resoureName;
	}

	public void setResoureName(String resoureName) {
		this.resoureName = resoureName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Integer fieldValue) {
		this.fieldValue = fieldValue;
	}
}
