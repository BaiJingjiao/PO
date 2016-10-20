package demos;

import org.testng.annotations.Test;

import pages.xhh.RegisterPage;
import utils.BaseAssert;
import utils.TestData;

public class TestCaseDemo2 {
	
	String mobile = "136" + TestData.randomNum(8);
	String pwd = "123456";
	
	@Test
	public void t() {
		/*
		 * 打开注册页面，输入注册信息
		 */
		RegisterPage reg = new RegisterPage();
		reg.load();
		reg.mobileInput.input(mobile);
		reg.pwdInput.input(pwd);
		reg.authCodeInput.input(TestData.randomLetters(4));
		reg.getDynamicCodeBtn.click();
		/*
		 * 添加检查点，检查“动态码发送成功”提示框应出现
		 */
		BaseAssert.assertTrue(reg.loadingOverlay.isDisplayed());
		//等待提示框消失，以免后续运行的用例受影响
		reg.waitLoadingOverlayDisappear(); //等待“动态码发送成功”提示框消失
	}
}
