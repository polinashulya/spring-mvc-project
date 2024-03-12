//package com.example.web.tag;
//
//import by.bsuir.web.controller.ControllerConstantsHolder;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.HashMap;
//import java.util.Map;
//
//final class ClassReflectionUtils {
//
//    private ClassReflectionUtils() {
//    }
//
//    /**
//     * Creates and returns a map of the names of public static final constants to
//     * their values, for the {@code ControllerConstantsHolder} class.
//     *
//     * @return {@code Map<String, Object>} from constant names to values
//     * @throws IllegalAccessException
//     * @throws IllegalArgumentException
//     */
//    public static Map<String, Object> getClassConstants()
//            throws IllegalArgumentException, IllegalAccessException {
//
//        final Map<String, Object> constantsMap = new HashMap<>();
//
//        final Class<ControllerConstantsHolder> clazz = ControllerConstantsHolder.class;
//
//        for (final Field field : clazz.getFields()) {
//            final int modifiers = field.getModifiers();
//            if (Modifier.isPublic(modifiers)
//                    && Modifier.isStatic(modifiers)
//                    && Modifier.isFinal(modifiers)) {
//                // null as argument because it's ignored when field is static
//                final Object value = field.get(null);
//                if (value != null) {
//                    constantsMap.put(field.getName(), value);
//                }
//            }
//        }
//        return constantsMap;
//    }
//
//}
