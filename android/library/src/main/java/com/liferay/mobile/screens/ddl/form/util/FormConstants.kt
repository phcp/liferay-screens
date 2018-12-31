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

package com.liferay.mobile.screens.ddl.form.util

/**
 * @author Victor Oliveira
 */
class FormConstants {
	companion object {
		const val URL_TEMPLATE = "/o/api/form/%d?embedded=structure"
		const val DATA_PROVIDER_KEY: String = "data-provider"
		const val FROM_AUTOFILL_KEY: String = "from-autofill"
		const val NAME = "name"
		const val DESCRIPTION = "description"
		const val AVAILABLE_LANGUAGES = "availableLanguages"
		const val DEFAULT_LANGUAGE = "defaultLanguage"
		const val STRUCTURE = "structure"
		const val PAGES = "formPages"
		const val SUCCESS_PAGE = "successPage"
		const val HEADLINE = "headline"
		const val TEXT = "text"
		const val FIELDS = "fields"
		const val FIELD_VALUES = "fieldValues"
	}
}
