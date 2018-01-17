package com.kingthy.util;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * @author KingThy
 */
public class EnumConverter extends AbstractConverter
{
    private final Class<?> enumClass;
    
    public EnumConverter(Class<?> enumClass)
    {
        this(enumClass, null);
    }
    
    public EnumConverter(Class<?> enumClass, Object defaultValue)
    {
        super(defaultValue);
        this.enumClass = enumClass;
    }
    @Override
    protected Class<?> getDefaultType()
    {
        return this.enumClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Object convertToType(@SuppressWarnings("rawtypes") Class type, Object value)
    {
        String str = value.toString().trim();
        return Enum.valueOf(type, str);
    }
    @Override
    protected String convertToString(Object value)
    {
        return value.toString();
    }
}