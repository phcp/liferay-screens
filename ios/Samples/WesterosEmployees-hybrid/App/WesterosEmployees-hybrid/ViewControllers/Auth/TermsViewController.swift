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
import LiferayScreens

class TermsViewController: CardViewController, AssetDisplayScreenletDelegate {

	// MARK: Outlets

	@IBOutlet weak var assetDisplayScreenlet: AssetDisplayScreenlet? {
		didSet {
			assetDisplayScreenlet?.delegate = self
		}
	}

	// MARK: Initializers

	convenience init() {
		self.init(nibName: "TermsViewController", bundle: nil)
	}

	// MARK: UIViewController

	override func viewWillAppear(_ animated: Bool) {

		//Login anonymous user
		SessionContext.loginWithBasic(
			username: LiferayServerContext.stringPropertyForKey("anonymousUsername"),
			password: LiferayServerContext.stringPropertyForKey("anonymousPassword"),
			userAttributes: [:])

		//Load article data into screenlet
        assetDisplayScreenlet?.assetEntryId = LiferayServerContext.longPropertyForKey("termsAndConditionsAssetEntryId")
		assetDisplayScreenlet?.load()
	}

	// MARK: AssetDisplayScreenletDelegate

	func screenlet(_ screenlet: AssetDisplayScreenlet, onAssetError error: NSError) {
		print("Couldn't load terms and conditions. Error: \(error)")
	}
}
