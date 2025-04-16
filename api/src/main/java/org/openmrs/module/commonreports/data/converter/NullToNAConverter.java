/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.commonreports.data.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Converter to return a formated order
 */
public class NullToNAConverter implements DataConverter {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String naLabel;
	
	public NullToNAConverter(String naLabel) {
		this.naLabel = naLabel;
	}
	
	/**
	 * @return returns a naLabel string when the input is null
	 */
	@Override
	public Object convert(Object original) {
		
		if (original == null) {
			return naLabel;
		}
		
		return original;
	}
	
	@Override
	public Class<?> getInputDataType() {
		return Object.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return Object.class;
	}
	
}
