package org.openmrs.module.commonreports.reports;

import java.io.File;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.commonreports.ActivatedReportManager;
import org.openmrs.module.commonreports.CommonReportsConstants;
import org.openmrs.module.initializer.Domain;
import org.openmrs.module.initializer.api.InitializerService;
import org.openmrs.module.initializer.api.loaders.Loader;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.report.ReportData;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class OutpatientRecordBookManagerTest extends BaseModuleContextSensitiveTest {
	
	@Autowired
	private InitializerService iniz;
	
	@Autowired
	private ReportService rs;
	
	@Autowired
	private ReportDefinitionService rds;
	
	@Autowired
	@Qualifier("conceptService")
	private ConceptService cs;
	
	@Autowired
	@Qualifier(CommonReportsConstants.COMPONENT_REPORTMANAGER_OPDRECBOOK)
	private ActivatedReportManager manager;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet("org/openmrs/module/reporting/include/ReportTestDataset-openmrs-2.0.xml");
		
		String path = getClass().getClassLoader().getResource("testAppDataDir").getPath() + File.separator;
		System.setProperty("OPENMRS_APPLICATION_DATA_DIRECTORY", path);
		
		PersonAttributeType pat = Context.getPersonService()
		        .getPersonAttributeTypeByUuid("a0f5521c-dbbd-4c10-81b2-1b7ab18330df");
		pat.setForeignKey(Context.getConceptService().getConcept(3).getConceptId());
		Context.getPersonService().savePersonAttributeType(pat);
		
		for (Loader loader : iniz.getLoaders()) {
			if (loader.getDomainName().equals(Domain.JSON_KEY_VALUES.getName())) {
				loader.load();
			}
		}
	}
	
	@Test
	public void setupReport_shouldSetupOPDRecBook() {
		
		// replay
		ReportManagerUtil.setupReport(manager);
		
		// verif
		Assert.assertNotNull(rs.getReportDesignByUuid("9873e45d-f8a0-4682-be78-243b8c9b848c"));
	}
	
	@Test
	public void testReport() throws Exception {
		
		Context.getAdministrationService().setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_ADDRESS_TEMPLATE,
		    IOUtils.toString(getClass().getClassLoader().getResourceAsStream("addressTemplate.xml")));
		
		EvaluationContext context = new EvaluationContext();
		context.addParameterValue("startDate", DateUtil.parseDate("2017-09-01", "yyyy-MM-dd"));
		context.addParameterValue("endDate", DateUtil.parseDate("2017-09-30", "yyyy-MM-dd"));
		
		ReportDefinition rd = manager.constructReportDefinition();
		ReportData data = rds.evaluate(rd, context);
	}
}
