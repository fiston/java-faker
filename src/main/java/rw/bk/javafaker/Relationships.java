package rw.bk.javafaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Relationships {
    private final Faker faker;

    protected Relationships(final Faker faker) {
        this.faker = faker;
    }

    public String direct() {
        return faker.resolve("relationship.familial.direct");
    }

    public String extended() {
        return faker.resolve("relationship.familial.extended");
    }

    public String inLaw() {
        return faker.resolve("relationship.in_law");
    }

    public String spouse() {
        return faker.resolve("relationship.spouse");
    }

    public String parent() {
        return faker.resolve("relationship.parent");
    }

    public String sibling() {
        return faker.resolve("relationship.sibling");
    }

    public String any() {
        try {
            Method[] methods = Relationships.class.getDeclaredMethods();

            // Filter out the 'any' method by name
            List<Method> validMethods = Arrays.stream(methods)
                    .filter(m -> !m.getName().equals("any"))
                    .filter(m -> m.getReturnType() == String.class)
                    .filter(m -> m.getParameterCount() == 0)
                    .toList();

            if (validMethods.isEmpty()) {
                throw new IllegalStateException("No valid methods found");
            }

            int index = faker.random().nextInt(validMethods.size());
            Method runMethod = validMethods.get(index);

            return (String) runMethod.invoke(this);

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke method: " + e.getMessage(), e);
        }
    }

}
