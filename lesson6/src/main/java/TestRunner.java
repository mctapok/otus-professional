import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    static int methodsSuccess = 0;
    List<Method> methods = new ArrayList<>();
    List<Method> testMethods = new ArrayList<>();
    List<Method> beforeSuiteMethods = new ArrayList<>();
    List<Method> afterSuiteMethods = new ArrayList<>();

    public void execute(Class<?> testClass) {
        methods = Arrays.asList(testClass.getDeclaredMethods());

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            }
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethods.add(m);
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethods.add(m);
            }
        }
        if (beforeSuiteMethods.size() > 1 || afterSuiteMethods.size() > 1) {
            throw new RuntimeException("Only one BeforeSuite and AfterSuite method allowed");
        }

        beforeSuiteMethods.forEach(TestRunner::runMethod);
        testMethods.sort((m1, m2) -> {
            int m1Priority = m1.getAnnotation(Test.class).priority();
            int m2Priority = m2.getAnnotation(Test.class).priority();
            return Integer.compare(m2Priority, m1Priority);
        });
        for (Method method : testMethods) {
            runMethod(method);
        }
        afterSuiteMethods.forEach(TestRunner::runMethod);
        System.out.println(log(methodsSuccess, testMethods, beforeSuiteMethods, afterSuiteMethods));
    }

    private static void runMethod(Method method) {
        try {
            method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance());
            methodsSuccess += 1;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void execute(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            execute(testClass);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        }
    }

    public String log(int methodsSuccess,
                      List<Method> testMethod,
                      List<Method> beforeSuiteMethod,
                      List<Method> afterSuiteMethod) {
        int allTestMethods = testMethod.size() + beforeSuiteMethod.size() + afterSuiteMethod.size();
        return "\nMethods was testing: " + allTestMethods + "\nsuccessful: " +
                methodsSuccess + "\nunsuccessful: " + (allTestMethods - methodsSuccess);
    }
}
