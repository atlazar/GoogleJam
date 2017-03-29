package ru.altazar;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationRunner {


    public static void main(String[] args) throws Exception {

        ClassPath classPath = ClassPath.from(ApplicationRunner.class.getClassLoader());

        Map<String, ? extends Problem> problems = classPath
                .getTopLevelClassesRecursive("ru.altazar")
                .stream()
                .map(ClassInfo::load)
                .filter(ApplicationRunner::isConcreteProblem)
                .filter(ApplicationRunner::hasDefaultConstructor)
                .collect(Collectors.toMap(
                        aClass -> aClass.getSimpleName().toLowerCase(),
                        ApplicationRunner::instantiate
                ));

        if (args.length < 1) {
            System.err.println("Please specify problem to solve as first argument. Known problems are: " + problems.keySet());
            return;
        }

        Problem problem = problems.get(args[0]);
        if (problem == null) {
            System.err.println("Unknown problem " + args[0] + ". Known problems are: " + problems.keySet());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            problem.solve(reader, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Problem instantiate(Class<?> aClass) {
        try {
            return (Problem) aClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean hasDefaultConstructor(Class<?> aClass) {
        for (Constructor<?> constructor : aClass.getConstructors()) {
            if (constructor.getParameterCount() == 0 && Modifier.isPublic(constructor.getModifiers())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isConcreteProblem(Class<?> aClass) {
        return aClass != Problem.class && Problem.class.isAssignableFrom(aClass);
    }

}