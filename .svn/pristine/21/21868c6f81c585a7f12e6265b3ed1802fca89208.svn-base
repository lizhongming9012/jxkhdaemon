package org.jxkh.mybatis.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
@MappedJdbcTypes(JdbcType.CHAR)
public class BooleanTypeCharConverter  extends BaseTypeHandler<Boolean>{

	@Override
	public Boolean getNullableResult(ResultSet arg0, String arg1)
			throws SQLException {
		
		return arg0.getString(arg1)=="Y" ? true:false;
	}

	@Override
	public Boolean getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		
		return arg0.getString(arg1)=="Y" ? true:false;
	}

	@Override
	public Boolean getNullableResult(CallableStatement arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return arg0.getString(arg1)=="Y" ? true:false;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1,
			Boolean arg2, JdbcType arg3) throws SQLException {
		arg0.setString(arg1, arg2?"Y":"N");
		
	}

}
