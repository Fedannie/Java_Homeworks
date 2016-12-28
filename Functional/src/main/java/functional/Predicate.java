package functional;

public interface Predicate <Arg> extends Function1 <Arg, Boolean>{
    default Predicate<Arg> or (Function1 <? super Arg, Boolean> x) {
        return t -> apply(t) || x.apply(t);
    }
    default Predicate<Arg> and (Function1 <? super Arg, Boolean> x) {
        return t -> apply(t) && x.apply(t);
    }
    default Predicate<Arg> not () {
        return t -> !apply(t);
    }
    Predicate<Object> ALWAYS_TRUE = s -> true;
    Predicate<Object> ALWAYS_FALSE = s -> false;
}
