package com.micro.pmo.tools.utils;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.micro.pmo.tools.model.Column;
import com.micro.pmo.tools.model.Table;




public class DbMetaDataUtils {

	public static List<String> getAllTables() throws SQLException{	
		List<String> tableList = new ArrayList<String>();	
		DatabaseMetaData metaData = DBUtils.getConnection().getMetaData();
		ResultSet rs = metaData.getTables(DBUtils.getConnection().getCatalog(), "root", "", new String[]{"TABLE"});
		while(rs.next()) {
			tableList.add(rs.getString("TABLE_NAME"));
		}
		return tableList;
	}
	
	public static Table getTable(String tableName) throws SQLException {
		Table table = new Table();
		table.setTableName(tableName);
		table.setColumnList(getTableInfo(tableName));
		return table;
	}
	
	public static List<Column> getTableInfo(String tableName) throws SQLException {
		
		Map<String,String> columnExplains=new HashMap<String,String>();

		//获取表列的注释comments
		try{
			String sql=" SHOW FULL COLUMNS FROM "+tableName+"  ";
			PreparedStatement ps=DBUtils.getConnection().prepareStatement(sql);
			ResultSet rs1=ps.executeQuery();
			while(rs1.next()){
				columnExplains.put(rs1.getString("FIELD"), rs1.getString("COMMENT"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		Column column = null;
		List<Column> resultList = new ArrayList<Column>();
		//创建statement对象
		Statement stmt = DBUtils.getConnection().createStatement();
		//创建sql语句
		String sql = "select * from " + tableName;
		//执行sql
		ResultSet rs= stmt.executeQuery(sql);
		//处理结果	  
		ResultSetMetaData rsmd = rs.getMetaData() ;
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			column = new Column();
			// 列名
			column.setName(rsmd.getColumnName(i));
			// 类型
			column.setType(rsmd.getColumnTypeName(i).toLowerCase());
			// 注释
			column.setComments(columnExplains.get(column.getName()));
			// 长度
			column.setSize(rsmd.getColumnDisplaySize(i));
			// 精度
			column.setPrecision(rsmd.getPrecision(i));
			// 是否为空
			column.setNullable(rsmd.isNullable(i));
			
			// 字段在Model中的名称
			column.setColumnName(parseWords(column.getName()));
			
			column.setNameUp(StringUtils.upperCase(column.getName()));
			
			String fieldName = column.getColumnName();
			column.setColumnNameU(fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
			
			// 字段类型
			if (StringUtils.equals(column.getType(), "bigint")) {
				column.setColumnType("Long");
			} else if (StringUtils.equals(column.getType(), "int")) {
				column.setColumnType("Integer");
			} else if (StringUtils.equals(column.getType(), "varchar")) {
				column.setColumnType("String");
			} else if (StringUtils.equals(column.getType(), "char")) {
				column.setColumnType("String");
			} else if (StringUtils.equals(column.getType(), "datetime")) {
				column.setColumnType("Date");
			} else if (StringUtils.equals(column.getType(), "decimal")) {
				column.setColumnType("BigDecimal");
			} else if (StringUtils.equals(column.getType(), "text")) {
				column.setColumnType("String");
			} else if (StringUtils.equals(column.getType(), "float")) {
				column.setColumnType("float");
			}
			
			resultList.add(column);
		}
		
		return resultList;
	}
	
	
	public static void main(String[] args) throws SQLException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = formatter.format(new Date());
		Date date;
		try {
			date = formatter.parse(dateStr);
			System.out.println(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*List<String> tableList = null;
		
		// 所有表
		tableList = getAllTables();
		
		List<Column> list = getTableInfo(tableList.get(0));
		
		
		for(Column c : list) {
			System.out.println(c.toString());
		}*/
	}
	
	public static String parseWords(String orginalString) {
		String[] items = orginalString.split(StringUtil.UNDER_LINE);
		String result = "";
		for (int i = 0; i < items.length; i++) {
			items[i] = items[i].toLowerCase();
			if (i > 0) {
				result = result + StringUtils.capitalize(items[i]);
			} else {
				result = result + items[i];
			}
		}
		return result;
	}
}
