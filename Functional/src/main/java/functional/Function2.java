package functional;

interface Function2 <Arg1, Arg2, Res>{
    Res apply(Arg1 x, Arg2 y);
    default <Comp> Function2<Arg1, Arg2, Comp> compose(Function1<? super Res, ? extends Comp> g) {
        return (x, y) -> g.apply(apply(x, y));
    }
    default Function1<Arg2, Res>  bind1(Arg1 first) {
        return second -> apply(first, second);
    }
    default Function1<Arg1, Res>  bind2(Arg2 second) {
        return first -> apply(first, second);
    }
    default Function1<Arg1, Function1<Arg2, Res>> curry() {
        return (Arg1 x) -> (Arg2 y) -> apply(x, y);
    }
}
