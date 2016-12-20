package functional;

public interface Predicate <Arg> extends Function1 <Arg, Boolean>{
    default Predicate<Arg> or (Function1 <Arg, Boolean> x) {
        return t -> apply(t) || x.apply(t);
    }
    default Predicate<Arg> and (Function1 <Arg, Boolean> x) {
        return t -> apply(t) && x.apply(t);
    }
    default Predicate<Arg> not () {
        return t -> !apply(t);
    }
    final static Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        public Boolean apply(Object s) {
            return true;
        }
    };

    final static Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() {
        public Boolean apply(Object s) {
            return false;
        }
    };

}
