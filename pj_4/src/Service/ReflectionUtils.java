package Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;




public class ReflectionUtils {
public static void setFieldValue(Object object,String fieldName,Object fieldValue) throws Exception {
	
	Field field = object.getClass().getDeclaredField(fieldName);
	field.setAccessible(true);
	field.set(object,fieldValue);
}

public static <t> Class<t> getSuperGenericType(Class class1)  {
	Class<t> type = null;
	ParameterizedType parameterizedType = (ParameterizedType) class1.getGenericSuperclass();
	Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
     String q = Arrays.toString(actualTypeArguments);
     String w = q.substring(7, q.length()-1);
	try {
		type = (Class<t>) Class.forName(w);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return type;
}
}
