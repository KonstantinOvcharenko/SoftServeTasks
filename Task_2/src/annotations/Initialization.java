package annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

// Create class with init method
public class Initialization {
	public static void setFields(Object instance, Class<?> clazz) throws IOException {
		Field[] fields = instance.getClass().getDeclaredFields();
		if (instance != null) {
			if (clazz.isAnnotationPresent(InitClass.class)) {
				Properties properties = new Properties();
				InputStream is = new FileInputStream("src/fields.properties");
				properties.load(is);

				for (Field field : fields) {
					if (field.isAnnotationPresent(InitField.class)) {
						InitField initfield = field.getAnnotation(InitField.class);
						field.setAccessible(true);
						try {
							if (field.getType() == int.class) {
								field.set(instance, strToInt(properties.getProperty(field.getName())));
							}
							field.set(instance, properties.getProperty(field.getName()));
						} catch (Exception e) {

						}
					}
				}

			}
		}
	}

	public static int strToInt(String value) {
		return Integer.parseInt(value);
	}
}
