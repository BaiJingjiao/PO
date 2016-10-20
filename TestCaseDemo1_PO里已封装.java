package demos.xhh;

import org.testng.annotations.Test;

import auxiliary_tools.Table;
import enums.bmp.TColumnBmp;
import pages.bmp.common.CommonPageBmp;
import pages.bmp.project_operation.FactoringBusinessProjectReleasePage;
import pages.bmp.project_operation.ProjectAuditingPage;
import pages.bmp.project_operation.ProjectManagementPage;
import utils.BaseAssert;
import utils.Helper;

public class TestCaseDemo1 {

	@Test()
	public void t1() {
		String status = "";
		/*
		 * 发布保理项目
		 */
		FactoringBusinessProjectReleasePage fac = new FactoringBusinessProjectReleasePage();
		fac.load();
		String projectName = fac.publishPersonalFacBusiProject(
				Helper.getTestDataFile("basic-factoring-business-project.json"));
		/*
		 * 审核
		 */
		ProjectAuditingPage audit = new ProjectAuditingPage();
		audit.load();
		audit.auditBasicFacBusiProject(projectName);
		/*
		 * 审核后添加检查点，检查项目管理页面，此项目投标状态应为"待开标"
		 */
		ProjectManagementPage m = new ProjectManagementPage();
		m.load();
		CommonPageBmp.search(projectName);
		status = Table.cell(projectName, TColumnBmp.投标状态_项目管理).getText();
		Helper.printStr(status);
		
		BaseAssert.assertTrue(status.trim().equals("待开标"));
	}
	
}
