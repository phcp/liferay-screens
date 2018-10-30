package com.liferay.mobile.screens.ddm.form.service

import com.liferay.mobile.screens.ddl.model.DocumentField
import com.liferay.mobile.screens.ddl.model.DocumentRemoteFile
import java.io.InputStream

/**
 * @author Paulo Cruz
 */
interface IUploadService {

	fun uploadFile(thingId: String, operationId: String, field: DocumentField, inputStream: InputStream,
		onSuccess: (DocumentRemoteFile) -> Unit, onError: (Exception) -> Unit)

}