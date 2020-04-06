package com.micro.pmo.tools.car;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class CarTypeUtils {

	 public final static String SCRIPT_PRE = "var rules = '';var document = {};document.createElement = function() {return {sheet: {insertRule: " +
	            "function(rule, i) {if (rules.length == 0) {rules = rule;} else {rules = rules + '|' + rule;}}}}};document.getElementsByTagName = " +
	            "function() {};document.querySelectorAll = function() {return {};};document.head = {};document.head.appendChild = function() " +
	            "{};var window = {};window.decodeURIComponent = decodeURIComponent;window.location = {};window.location.href = 'car.m.autohome.com.cn';";
	 
	    public final static Pattern CSS_PATTERN = Pattern.compile("\\.(.*)::before.*content:\"(.*)\".*");
	    @Test
	    public void testScript() throws Exception {
	        String url = "https://car.autohome.com.cn/config/series/692.html";
	        Connection.Response response = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).execute();
	        System.out.println(response.statusCode());
	        Document document = response.parse();
	        Elements scripts = document.select("script:containsData(insertRule)");
	 
	        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
	        Map<String, String> cssKeyValue = new HashMap<>();
	        System.out.println("------------css数据------------");
	        scripts.forEach(element -> {
	            String script = SCRIPT_PRE + element.html();
	            try {
	                engine.eval(script);
	            } catch (ScriptException e) {
	                e.printStackTrace();
	            }
	            String css = (String) engine.get("rules");
	            System.out.println(css);
	            for (String str : css.split("\\|")) {
	                Matcher cssMatcher = CSS_PATTERN.matcher(str);
	                if (cssMatcher.find()) {
	                    cssKeyValue.put("<span class='" + cssMatcher.group(1) + "'></span>", cssMatcher.group(2));
	                }
	            }
	        });
	        Elements contents = document.select("script:containsData(keyLink)");
	        String content = contents.html();
	        System.out.println("------------用css混淆的配置数据------------");
	        System.out.println(content);
	        //把混淆数据里的样式用上面解析的样式给替代
	        for(Map.Entry<String, String> entry : cssKeyValue.entrySet()) {
	            content = content.replaceAll(entry.getKey(), entry.getValue());
	        }
	        System.out.println("------------用css替换后的数据------------");
	        System.out.println(content);
	        engine.eval(content);
	        System.out.println("------------每个配置结果------------");
	        String keyLink = JSONObject.toJSONString(engine.get("keyLink"));
	        String config = JSONObject.toJSONString(engine.get("config"));
	        String option = JSONObject.toJSONString(engine.get("option"));
	        String bag = JSONObject.toJSONString(engine.get("bag"));
	        String color = JSONObject.toJSONString(engine.get("color"));
	        String innerColor = JSONObject.toJSONString(engine.get("innerColor"));
	        System.out.println(keyLink);
	        System.out.println(config);
	        System.out.println(option);
	        System.out.println(bag);
	        System.out.println(color);
	        System.out.println(innerColor);
	        //最后的数据，解析json就ok
	    }

}
