package com.liferay.mobile.screens.ddm.form

import com.liferay.mobile.screens.ddl.model.*
import com.liferay.mobile.screens.ddl.model.Field.EditorType.*
import com.liferay.mobile.screens.ddm.form.model.CheckboxMultipleField
import com.liferay.mobile.screens.ddm.form.model.GridField
import com.liferay.mobile.screens.ddm.form.model.RepeatableField
import java.util.*

/**
 * @author Paulo Cruz
 */
class DDMFieldFactory {

    companion object {
        private var currentLocale: Locale = Locale.getDefault()
        private var defaultLocale: Locale = Locale.getDefault()

        fun setupLocales(currentLocale: Locale, defaultLocale: Locale) {
            this.currentLocale = currentLocale
            this.defaultLocale = defaultLocale
        }

        fun fromAttributes(attributes: Map<String, Any>): Field<*>? {
            val editorType = Field.EditorType.valueOf(attributes)

            return when (editorType) {
                CHECKBOX_MULTIPLE -> CheckboxMultipleField(attributes, currentLocale, defaultLocale)
                DATE -> DateField(attributes, currentLocale, defaultLocale)
                DECIMAL, INTEGER -> NumberField(attributes, currentLocale, defaultLocale)
                DOCUMENT -> DocumentField(attributes, currentLocale, defaultLocale)
                GRID -> GridField(attributes, currentLocale, defaultLocale)
                PARAGRAPH , TEXT-> StringField(attributes, currentLocale, defaultLocale)
                SELECT, RADIO -> SelectableOptionsField(attributes, currentLocale, defaultLocale)
                else -> null
            }
        }

        fun asRepeatableChild(attributes: Map<String, Any>): Field<*>? {
            return fromAttributes(attributes)?.let {
                RepeatableField(it)
            }
        }

    }

}


fun test() {
    DDMFieldFactory.setupLocales(Locale.getDefault(), Locale.getDefault())
    val field = DDMFieldFactory.fromAttributes(mapOf())
}