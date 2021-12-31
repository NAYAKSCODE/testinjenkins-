package com.crm.autodesk.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 
 * @author ASHUTOSH NAYAK
 *
 */

public class FileUtility {
	/**
	 * it is used to read data from commondata.properties file based on key which you pass as an argument
	 * @param key
	 * @return value
	 * @throws Throwable
	 */
public String getPropertyKeyValue(String key) throws Throwable
{
	FileInputStream fis = new FileInputStream("./data/commondata.properties");
	Properties pobj= new Properties();
	pobj.load(fis);
	String value = pobj.getProperty(key);
	return value;
	}
}
