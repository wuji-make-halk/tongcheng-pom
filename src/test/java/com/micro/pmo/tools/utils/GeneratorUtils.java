package com.micro.pmo.tools.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import com.micro.pmo.tools.common.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class GeneratorUtils {

	public static void output(String ftlName, Map<String, Object> root,
			String outFile) throws Exception {
		try {
			File file = new File(outFile);
			if (file.exists())
				file.delete();

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			FileWriter out = new FileWriter(file);
			Template template = getTemplate(ftlName);
			template.process(root, out); // 模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Template getTemplate(String ftlName) throws Exception {
		try {
			Configuration cfg = new Configuration();
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File("/" + StringUtil
					.substringAfter(
							Thread.currentThread().getContextClassLoader().getResource("").toString(), "file:/") + "templates/"));
			Template temp = cfg.getTemplate(ftlName);
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getOutputPath(String fileName) {
		return "/" + StringUtil.substringBetween(Thread.currentThread().getContextClassLoader().getResource("").toString(), "file:/", Constants.PROJECT_PATH)
				+ Constants.PROJECT_PATH
				+ fileName;
	}
	
	public static String getTargetFilePath(String pkgPath, String clzName, String suffix) {
		String result = pkgPath.replace(StringUtil.DOT,
				StringUtil.FILE_SEPARATOR);
		result = result + StringUtil.FILE_SEPARATOR;
		result = result + clzName + StringUtil.DOT + suffix;
		if (clzName.endsWith("Test")) {// 单元测试类
			result = Constants.TEST_PATH + result;
		} /*else if ("xml".equalsIgnoreCase(suffix)) {
			// result = Constants.Mapper_PATH + result;
			result =  Constants.Mapper_PATH + clzName + StringUtil.DOT + suffix;
		}*/ else {
			result = Constants.SRC_PATH + result;// 普通类
		}
		return result;
	}
}
