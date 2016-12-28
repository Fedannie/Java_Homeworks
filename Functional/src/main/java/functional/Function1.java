package functional;

public interface Function1 <Arg, Res>{
    Res apply (Arg x);
    default <Comp> Function1<Arg, Comp> compose(Function1<? super Res, ? extends Comp> f) {
        return t -> f.apply(apply(t));
    }
}
