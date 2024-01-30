/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.gradle.plugins.workspace.task;

import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;

import java.io.File;

import java.net.URL;

import java.util.Base64;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Drew Brokke
 */
public class GenerateBatchClientExtensionsTask extends DefaultTask {

	public GenerateBatchClientExtensionsTask() {
		_namespace = _project.getName();
	}

	@TaskAction
	public void doWork() throws Exception {
		URL serverURL = getServerURL();

		System.out.println("serverURL = " + serverURL);

		File outputDir = getOutputDir();

		System.out.println("outputDir = " + outputDir);

		System.out.println(
			"outputDir.getAbsolutePath() = " + outputDir.getAbsolutePath());

		String namespace = getNamespace();

		System.out.println("namespace = " + namespace);

		String token = getToken();

		if (token == null) {
			Base64.Encoder encoder = Base64.getEncoder();

			String userName = "test@liferay.com";
			String password = "test";

			String unencodedTokentString = String.format(
				"%s:%s", userName, password);

			setToken(encoder.encodeToString(unencodedTokentString.getBytes()));

			token = getToken();
		}

		System.out.println("token = " + token);
	}


	@Input
	@Optional
	public String getNamespace() {
		return GradleUtil.toString(_namespace);
	}

	@OutputDirectory
	public File getOutputDir() {
		return GradleUtil.toFile(_project, _outputDir);
	}

	@Input
	public URL getServerURL() {
		return GradleUtil.toURL(_serverURL);
	}

	@Input
	@Optional
	public String getToken() {
		return GradleUtil.toString(_token);
	}

	public void setNamespace(Object namespace) {
		_namespace = namespace;
	}

	public void setOutputDir(Object outputDir) {
		_outputDir = outputDir;
	}

	public void setServerURL(Object serverURL) {
		_serverURL = serverURL;
	}

	public void setToken(Object token) {
		_token = token;
	}

	private Object _namespace;
	private Object _outputDir = "client-extensions/batch-generated";
	private final Project _project = getProject();
	private Object _serverURL = "http://localhost:8080";
	private Object _token;

}