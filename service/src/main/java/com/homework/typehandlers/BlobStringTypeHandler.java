package com.homework.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author xuke
 * @create 2017-04-22 下午 18:31
 **/
@MappedJdbcTypes({JdbcType.BLOB,JdbcType.LONGVARBINARY})
public class BlobStringTypeHandler extends BaseTypeHandler<String> {
    //指定字符集
    private static final String DEFAULT_CHARSET = "utf-8";

    public BlobStringTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
//        ByteArrayInputStream bis;
//        try {
//            //把String转化成byte流
//           // bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("Blob Encoding Error!");
//        }
//        ps.setBinaryStream(i, bis, parameter.length());
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Blob blob = rs.getBlob(columnName);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            //把byte转化成string
            if(blob == null) return "";
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int index) throws SQLException
    {
        Blob blob = rs.getBlob(index);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            //把byte转化成string
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }
}
