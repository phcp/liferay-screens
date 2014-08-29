/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
import UIKit

public class ForgotPasswordView_default: ForgotPasswordView {

	@IBOutlet var userNameIcon: UIImageView?
	@IBOutlet var userNameField: UITextField?
	@IBOutlet var requestPasswordButton: UIButton?

	// MARK: Overriden accessors

	override public func getUserName() -> String {
		return userNameField!.text
	}

	override public func setUserName(userName: String) {
		userNameField!.text = userName
	}

	override public func setAuthType(authTypeLabel: String) {
		LoginView_default.setStylesForAuthType(authTypeLabel, userNameField: userNameField, userNameIcon: userNameIcon)
	}

	// MARK: UITextFieldDelegate

	func textFieldDidBeginEditing(textField: UITextField!) {
		userNameField!.highlighted = (textField == userNameField)
	}

}