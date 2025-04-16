/*
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

package org.openmrs.module.commonreports.library;

import static org.openmrs.module.commonreports.CommonReportsConstants.MODULE_ARTIFACT_ID;

import java.util.Map;

import org.openmrs.module.commonreports.common.Helper;
import org.openmrs.module.commonreports.data.obs.definition.SqlObsDataDefinition;
import org.openmrs.module.reporting.data.obs.definition.ObsDataDefinition;
import org.openmrs.module.reporting.definition.library.BaseDefinitionLibrary;
import org.openmrs.module.reporting.definition.library.DocumentedDefinition;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ObsDataLibrary extends BaseDefinitionLibrary<ObsDataDefinition> {
	
	public static final String PREFIX = MODULE_ARTIFACT_ID + ".obsDataCalculation.";
	
	@Override
	public Class<? super ObsDataDefinition> getDefinitionType() {
		return ObsDataDefinition.class;
	}
	
	@Override
	public String getKeyPrefix() {
		return PREFIX;
	}
	
	@DocumentedDefinition("concept.id")
	public ObsDataDefinition getConceptId() {
		return sqlObsDataDefinition("conceptId.sql", null);
	}
	
	private ObsDataDefinition sqlObsDataDefinition(String resourceName, Replacements replacements) {
		String sql = Helper
		        .getStringFromResource("org/openmrs/module/" + MODULE_ARTIFACT_ID + "/sql/obsData/" + resourceName);
		if (replacements != null) {
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				sql = sql.replaceAll(":" + entry.getKey(), entry.getValue());
			}
		}
		
		SqlObsDataDefinition definition = new SqlObsDataDefinition();
		definition.setSql(sql);
		return definition;
	}
	
}
