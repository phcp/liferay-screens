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
import org.json.JSONObject
import java.util.*

class ConverterDelegate {

    companion object {
        @JvmStatic
        fun initializeConverter() {
            converters[BlogPosting::class.java.name] = { it: Thing ->
                BlogPosting(
                    it["headline"] as? String,
                    it["alternativeHeadline"] as? String,
                    it["articleBody"] as? String,
                    it["creator"] as? Relation,
                    (it["dateCreated"] as? String)?.asDate())
            }

            converters[Collection::class.java.name] = { it: Thing ->
                val member = (it["member"] as? List<Relation>)?.mapNotNull {
                    graph[it.id]?.value
                }

                val totalItems = (it["totalItems"] as? Double)?.toInt()

                val nextPage = (it["view"] as Relation)["next"] as? String

                val pages = nextPage?.let(::Pages)

                Collection(member, totalItems, pages)
            }

            converters[Person::class.java.name] = { it: Thing ->
                val name = it["name"] as? String

                val email = it["email"] as? String

                val jobTitle = it["jobTitle"] as? String

                val birthDate = (it["birthDate"] as? String)?.asDate()

                val image = it["image"] as? String

                Person(name, email, jobTitle, birthDate, image)
            }

            converters[FormInstance::class.java.name] = { it: Thing ->
                val formInstanceId = it.id.toLong()

                val attributes = HashMap<String, Any>()
                attributes["dataType"] = Field.DataType.STRING
                attributes["readOnly"] = false
                attributes["type"] = Field.EditorType.TEXT
                attributes["required"] = false
                attributes["showLabel"] = true
                attributes["repeatable"] = false
                attributes["label"] = "TextField single"
                attributes["name"] = "TextFieldSingle"
                attributes["tip"] = "TextField hint"
                attributes["placeHolder"] = ""
                val stringField = StringField(attributes, Locale.ENGLISH, Locale.ENGLISH)

                val attributes2 = HashMap<String, Any>()
                attributes["dataType"] = Field.DataType.STRING
                attributes["readOnly"] = false
                attributes["type"] = Field.EditorType.TEXT_AREA
                attributes["required"] = false
                attributes["showLabel"] = true
                attributes["repeatable"] = false
                attributes["label"] = "TextField multiple"
                attributes["name"] = "TextFieldMultiple"
                attributes["tip"] = "TextField multiple hint"
                attributes["placeHolder"] = ""
                val stringField2 = StringField(attributes2, Locale.ENGLISH, Locale.ENGLISH)

                val fields = ArrayList<Field<*>>()
                fields.add(stringField)
                fields.add(stringField2)

                val ddmStructure = DDMStructure()

                val jsonObject = JSONObject()
                jsonObject.put("descriptionCurrentValue", "Basic fields form")
                jsonObject.put("structureId", "36579")
                jsonObject.put("nameCurrentValue", "Basic fields")

                ddmStructure.parse(jsonObject)
                ddmStructure.fields = fields

                FormInstance(formInstanceId, ddmStructure);
            }
        }
    }
}
