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

package com.liferay.mobile.screens.thingscreenlet.delegates

import com.liferay.mobile.screens.ddl.model.DDMStructure
import com.liferay.mobile.screens.ddl.model.Field
import com.liferay.mobile.screens.ddl.model.StringField
import com.liferay.mobile.screens.ddm.form.model.FormInstance
import com.liferay.mobile.screens.thingscreenlet.model.BlogPosting
import com.liferay.mobile.screens.thingscreenlet.model.Collection
import com.liferay.mobile.screens.thingscreenlet.model.Pages
import com.liferay.mobile.screens.thingscreenlet.model.Person
import com.liferay.mobile.sdk.apio.delegates.converters
import com.liferay.mobile.sdk.apio.extensions.asDate
import com.liferay.mobile.sdk.apio.graph
import com.liferay.mobile.sdk.apio.model.Relation
import com.liferay.mobile.sdk.apio.model.Thing
import com.liferay.mobile.sdk.apio.model.get
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ConverterDelegate {

    companion object {
        @JvmStatic
        fun initializeConverter() {

            converters[FormInstance::class.java.name] = { it: Thing ->

                val name = it["name"] as String
                val description = it["description"] as String

                val structureRelation = it.attributes["structure"] as Relation

                val structureDescription = structureRelation["description"] as String
                val structureName = structureRelation["name"] as String

                val attributes = mapOf(
                    "dataType" to Field.DataType.STRING.value,
                    "readOnly" to false,
                    "type" to Field.EditorType.TEXT.value,
                    "required" to false,
                    "showLabel" to true,
                    "repeatable" to false,
                    "label" to "TextField single",
                    "name" to "TextFieldSingle",
                    "tip" to "TextField hint",
                    "placeHolder" to ""
                    )

                val stringField = StringField(attributes, Locale.ENGLISH, Locale.ENGLISH)

                val attributes2 = mapOf(
                    "dataType" to Field.DataType.STRING.value,
                    "readOnly" to false,
                    "type" to Field.EditorType.TEXT_AREA.value,
                    "required" to false,
                    "showLabel" to true,
                    "repeatable" to false,
                    "label" to "TextField multiple",
                    "name" to "TextFieldMultiple",
                    "tip" to "TextField multiple hint",
                    "placeHolder" to ""
                )

                val stringField2 = StringField(attributes2, Locale.ENGLISH, Locale.ENGLISH)

                val fields = ArrayList<Field<*>>()
                fields.add(stringField)
                fields.add(stringField2)

                val ddmStructure = DDMStructure(structureName, structureDescription, fields)

                FormInstance(name, description, ddmStructure)
            }
            converters[BlogPosting::class.java.name] = BlogPosting.converter
            converters[Collection::class.java.name] = Collection.converter
            converters[Person::class.java.name] = Person.converter
        }
    }
}
