package com.panamby.backlogmanager.exception.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BacklogManagerGeneralErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	BacklogManagerGeneralDataErrorResponse data;
}
