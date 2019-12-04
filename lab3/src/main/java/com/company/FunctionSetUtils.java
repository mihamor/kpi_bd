package com.company;

import java.util.function.Function;

public class FunctionSetUtils {
    public static<T> PurelyFunctionalSet<T> empty() {
        return null;
    }

    public static <T>PurelyFunctionalSet<T> singletonSet(T val) {
        return null;
    }

    public static <T> PurelyFunctionalSet<T> union(PurelyFunctionalSet<T> s,
                                                   PurelyFunctionalSet<T> t) {
        return null;
    }

    public static <T> PurelyFunctionalSet<T> intersect(PurelyFunctionalSet<T> s,
                                                       PurelyFunctionalSet<T> t) {
        return null;
    }

    public static <T> PurelyFunctionalSet<T> diff(PurelyFunctionalSet<T> s,
                                                  PurelyFunctionalSet<T> t) {
        return null;
    }

    public static <T> PurelyFunctionalSet<T> filter(PurelyFunctionalSet<T> s,
                                                    Predicate<T> p) {
        return null;
    }

    public static <T> boolean forall(PurelyFunctionalSet<T> s,
                                     Predicate<Integer> p) {
        return false;
    }


    public static <T> boolean exists(PurelyFunctionalSet<T> s,
                                     Predicate<Integer> p) {
        return false;
    }

    public static <T, R> PurelyFunctionalSet<R> map(PurelyFunctionalSet<T> s,
                                                    Function<Integer, R> p) {
        return null;
    }

    private static boolean forAllHelper(PurelyFunctionalSet<Integer> s, Predicate<Integer> p, Integer current, Integer maxBound) {
        return false;
    }
}
