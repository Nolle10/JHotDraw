package org.jhotdraw.draw;

import java.util.*;

public class SPILocator {

    private static final Map<Class, ServiceLoader> LOADER = new HashMap<Class, ServiceLoader>();

    private SPILocator() {
    }

    public static <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = SPILocator.LOADER.get(service);

        boolean printStatement = false;

        if (loader == null) {
            loader = ServiceLoader.load(service);
            SPILocator.LOADER.put(service, loader);
            printStatement = true;
        }

        List<T> list = new ArrayList<T>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

        if (printStatement) {
            System.out.println("Found " + list.size() + " implementations for interface: " + service.getName());
        }

        return list;
    }
}
