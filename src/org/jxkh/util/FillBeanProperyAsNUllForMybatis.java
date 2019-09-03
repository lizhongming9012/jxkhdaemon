package org.jxkh.util;

import java.lang.reflect.Method;

public class FillBeanProperyAsNUllForMybatis {
	public static void fillNull(Object object) {
		Method[] methods = object.getClass().getDeclaredMethods();
		for (Method m : methods) {
			if (m.getName().startsWith("get")) {
				try {
					Class<?> returnType = m.getReturnType();
					if ("java.lang.String".equals(returnType.getName())) {

						String str = (String) m.invoke(object);
						if ("".equals(str)) {
							for (Method setm : methods) {
								String setname = setm.getName();
								if (setname.startsWith("set")
										&& setname.substring(3).equals(
												m.getName().substring(3))) {
									setm.invoke(object, new Object[] { null });

								}
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
