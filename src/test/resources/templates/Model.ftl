package ${model.packageName};

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * ${tableComment}Entity
 */
@Table(name = "${mapper.tableName}")
public class ${model.modelName} implements Serializable{

	private static final long serialVersionUID = 1L;
	
	<#if (model.columnList)??>
	<#list model.columnList as column>
	
	/**
	 * ${column.comments}
	 */
	private ${column.columnType} ${column.columnName};
	</#list>
	</#if>
	
	public ${model.modelName}(){
		super();
	}

	
	<#if model.tree>
	public ${model.modelName} getParent() {
		return parent;
	}

	public void setParent(${model.modelName} parent) {
		this.parent = parent;
	}
	</#if>
	
	<#if (model.columnList)??>
	<#list model.columnList as column>
	
	/**
	 * 获取${column.comments}
	 * 
	 * @return ${column.columnType} ${column.comments}
	 */
	public ${column.columnType} get${column.columnNameU}() {
		return ${column.columnName};
	}
	
	/**
	 * 设置${column.comments}
	 * 
	 * @param ${column.columnName} ${column.comments}
	 */
	public void set${column.columnNameU}(${column.columnType} ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
	</#list>
	</#if>
	
}