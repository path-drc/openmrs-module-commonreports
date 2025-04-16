package org.openmrs.module.commonreports.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Cohort;
import org.openmrs.module.commonreports.reports.BaseModuleContextSensitiveMysqlBackedTest;
import org.openmrs.module.commonreports.ActivatedReportManager;
import org.openmrs.module.commonreports.CommonReportsConstants;
import org.openmrs.module.initializer.Domain;
import org.openmrs.module.initializer.api.InitializerService;
import org.openmrs.module.initializer.api.loaders.Loader;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.dataset.DataSetRow;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.report.ReportData;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;
import org.openmrs.module.reporting.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MSPPVaccinationReportManagerTest extends BaseModuleContextSensitiveMysqlBackedTest {
	
	public MSPPVaccinationReportManagerTest() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private InitializerService iniz;
	
	@Autowired
	private ReportService rs;
	
	@Autowired
	private ReportDefinitionService rds;
	
	@Autowired
	@Qualifier(CommonReportsConstants.COMPONENT_REPORTMANAGER_VACCINATION)
	private ActivatedReportManager manager;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet("org/openmrs/module/reporting/include/ReportTestDataset-openmrs-2.0.xml");
		executeDataSet("org/openmrs/module/commonreports/include/MSPPvaccinationTestDataset.xml");
		
		String path = getClass().getClassLoader().getResource("testAppDataDir").getPath() + File.separator;
		System.setProperty("OPENMRS_APPLICATION_DATA_DIRECTORY", path);
		
		for (Loader loader : iniz.getLoaders()) {
			if (loader.getDomainName().equals(Domain.JSON_KEY_VALUES.getName())) {
				loader.load();
			}
		}
	}
	
	@Test
	public void setupReport_shouldSetUpReport() {
		
		// replay
		ReportManagerUtil.setupReport(manager);
		
		// verify
		Assert.assertNotNull(rs.getReportDesignByUuid("ab8b5d26-8d13-4397-9690-0f107ad50adf"));
		
	}
	
	@Test
	public void testReport() throws Exception {
		
		EvaluationContext context = new EvaluationContext();
		context.addParameterValue("startDate", DateUtil.parseDate("2021-06-01", "yyyy-MM-dd"));
		context.addParameterValue("endDate", DateUtil.parseDate("2021-06-30", "yyyy-MM-dd"));
		
		ReportDefinition rd = manager.constructReportDefinition();
		ReportData data = rds.evaluate(rd, context);
		
		for (Iterator<DataSetRow> itr = data.getDataSets().get("MSPP Vaccination").iterator(); itr.hasNext();) {
			DataSetRow row = itr.next();
			// In CrossTabDataSet reports all rows and columns are in fact just columns of
			// one row
			Cohort _0mTo1yFemalesReceivedBCG = (Cohort) row
			        .getColumnValue("BCG Vaccination." + MSPPVaccinationReportManager.col1);
			assertNotNull(_0mTo1yFemalesReceivedBCG);
			assertEquals(1, _0mTo1yFemalesReceivedBCG.getSize());
			
			Cohort _0mTo1yMalesReceivedBCG = (Cohort) row
			        .getColumnValue("BCG Vaccination." + MSPPVaccinationReportManager.col3);
			assertNotNull(_0mTo1yMalesReceivedBCG);
			assertEquals(1, _0mTo1yMalesReceivedBCG.getSize());
			
			Cohort _0mTo1yFemalesReceivedPenta3 = (Cohort) row
			        .getColumnValue("Pentavalent Vaccination 3." + MSPPVaccinationReportManager.col1);
			assertNotNull(_0mTo1yFemalesReceivedPenta3);
			assertEquals(1, _0mTo1yFemalesReceivedPenta3.getSize());
			
			Cohort _prenatalFemalesReceivedPenta3 = (Cohort) row
			        .getColumnValue("Pentavalent Vaccination 3." + MSPPVaccinationReportManager.col5);
			assertNotNull(_prenatalFemalesReceivedPenta3);
			assertEquals(1, _prenatalFemalesReceivedPenta3.getSize());
			
			Cohort _malesReceivedPenta3 = (Cohort) row
			        .getColumnValue("Pentavalent Vaccination 3." + MSPPVaccinationReportManager.col4);
			assertNotNull(_malesReceivedPenta3);
			assertEquals(0, _malesReceivedPenta3.getSize());
			
			Cohort _prenatalFemalesReceivedOPV2 = (Cohort) row
			        .getColumnValue("Oral Polio Vaccination 2." + MSPPVaccinationReportManager.col5);
			assertNotNull(_prenatalFemalesReceivedOPV2);
			assertEquals(1, _prenatalFemalesReceivedOPV2.getSize());
			
			Cohort _femalesReceivedIPV = (Cohort) row
			        .getColumnValue("Polio Vaccine-Inactivated Vaccination." + MSPPVaccinationReportManager.col1);
			assertNotNull(_femalesReceivedIPV);
			assertEquals(1, _femalesReceivedIPV.getSize());
			
			Cohort _femalesReceivedRota2 = (Cohort) row
			        .getColumnValue("Rotavirus Vaccination 2." + MSPPVaccinationReportManager.col1);
			assertNotNull(_femalesReceivedRota2);
			assertEquals(1, _femalesReceivedRota2.getSize());
			
			Cohort _prenatalFemalesReceivedDT = (Cohort) row
			        .getColumnValue("Diphtheria Tetanus Vaccination 11." + MSPPVaccinationReportManager.col5);
			assertNotNull(_prenatalFemalesReceivedDT);
			assertEquals(1, _prenatalFemalesReceivedDT.getSize());
			
			Cohort _0mTo1yFemalesFullyVaccinated = (Cohort) row
			        .getColumnValue("Is the child fully vaccinated?." + MSPPVaccinationReportManager.col1);
			assertNotNull(_0mTo1yFemalesFullyVaccinated);
			assertEquals(1, _0mTo1yFemalesFullyVaccinated.getSize());
			
			Cohort _0mTo1yMalesFullyVaccinated = (Cohort) row
			        .getColumnValue("Is the child fully vaccinated?." + MSPPVaccinationReportManager.col3);
			assertNotNull(_0mTo1yMalesFullyVaccinated);
			assertEquals(1, _0mTo1yMalesFullyVaccinated.getSize());
			
			Cohort _prenatalFemalesFullyVaccinated = (Cohort) row
			        .getColumnValue("Is the child fully vaccinated?." + MSPPVaccinationReportManager.col5);
			assertNotNull(_prenatalFemalesFullyVaccinated);
			assertEquals(1, _prenatalFemalesFullyVaccinated.getSize());
		}
		
		for (Iterator<DataSetRow> itr = data.getDataSets().get("MSPP ECV").iterator(); itr.hasNext();) {
			DataSetRow row = itr.next();
			System.out.println(row);
			
			// In CrossTabDataSet reports all rows and columns are in fact just columns of
			// one row
			Cohort _0mTo1yFemalesECV = (Cohort) row.getColumnValue("ECV." + MSPPVaccinationReportManager.ecvCol1);
			assertNotNull(_0mTo1yFemalesECV);
			assertEquals(1, _0mTo1yFemalesECV.getSize());
			
			Cohort _1To2yFemalesECV = (Cohort) row.getColumnValue("ECV." + MSPPVaccinationReportManager.ecvCol2);
			assertNotNull(_1To2yFemalesECV);
			assertEquals(0, _1To2yFemalesECV.getSize());
			
			Cohort _0mTo1yMalesECV = (Cohort) row.getColumnValue("ECV." + MSPPVaccinationReportManager.ecvCol3);
			assertNotNull(_0mTo1yMalesECV);
			assertEquals(0, _0mTo1yMalesECV.getSize());
			
			Cohort _1To2yMalesECV = (Cohort) row.getColumnValue("ECV." + MSPPVaccinationReportManager.ecvCol4);
			assertNotNull(_1To2yMalesECV);
			assertEquals(0, _1To2yMalesECV.getSize());
		}
	}
	
}
