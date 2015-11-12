/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.showcase.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.faces.util.model.UploadedFile;


/**
 * @author  Neil Griffin
 */
@ViewScoped
@ManagedBean
public class HtmlInputFileModelBean implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 221172404377673109L;

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(HtmlInputFileModelBean.class);

	// Private Data Members
	private List<UploadedFile> uploadedFiles;

	@PostConstruct
	public void postConstruct() {
		this.uploadedFiles = new ArrayList<UploadedFile>();
	}

	@PreDestroy
	public void preDestroy() {

		for (UploadedFile uploadedFile : uploadedFiles) {

			try {
				uploadedFile.delete();
			}
			catch (IOException e) {
				logger.error(e);
			}
		}

		uploadedFiles = null;
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}
}
