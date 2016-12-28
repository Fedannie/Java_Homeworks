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
    final class ALWAYS_TRUE <Arg> implements Predicate <Arg> {
        public Boolean apply (Arg x) {
            return true;
        }
    }
    final class ALWAYS_FALSE <Arg> implements Predicate <Arg> {
        public Boolean apply (Arg x) {
            return false;
        }
    }
}
