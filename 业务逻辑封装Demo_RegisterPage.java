	public String regUser(String mobile, String pwd) {
		mobileInput.input(mobile);
		pwdInput.input(pwd);
		authCodeInput.input(TestData.randomLetters(4)); //测试环境特殊设置，绕过图片验证码
		getDynamicCodeBtn.click();
		String error = registerErrorLable.getText();
		//填入的注册信息有误
		if(error.trim().length()>0) {
			Helper.log(error);
			return null;
		}
		waitLoadingOverlayDisappear(); //等待“动态码发送成功”提示框消失
		/*
		 * 从数据库获取动态验证码
		 */
		String dynamicCode = DBHelper.getRegistrationDynamicCode(mobile);
		System.out.println("dynamicCode: " + dynamicCode);
		dynamicCodeInput.input(dynamicCode);
		registerBtn.click();
		error = registerErrorLable.getText();
		//如：点击注册按钮后，“内部程序错误”
		if(error.trim().length()>0) {
			Helper.log(error);
			return null;
		}
		String uid = DBHelper.getUID(mobile);
		if(uid.equals("uid")) {
			return null;
		}
		System.out.println("uid: " + uid);
		return uid;
	}
