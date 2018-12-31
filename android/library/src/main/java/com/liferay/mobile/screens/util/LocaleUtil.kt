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
package com.liferay.mobile.screens.util

import java.util.*

/**
 * @author Paulo Cruz
 */
class LocaleUtil {

    companion object {
        @JvmStatic
        fun convertLanguageCodeToLocale(language: String): Locale {
            return language.replace("-", "_").let {
                Locale(it)
            }
        }

        @JvmStatic
        fun getLocaleFromAvailableLocales(availableLocales: List<Locale>, defaultLocale: Locale): Locale {
            val deviceLocale = Locale.getDefault()

            return deviceLocale.takeIf {
                availableLocales.contains(it)
            } ?: availableLocales.firstOrNull {
                it.language == deviceLocale.language
            } ?: availableLocales.firstOrNull {
                it.country == deviceLocale.country
            } ?: defaultLocale
        }
    }

}