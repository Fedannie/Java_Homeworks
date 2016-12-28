package functional;

import java.util.ArrayList;
import java.util.Iterator;

public final class Collections {
    public static <Arg, Res> ArrayList<Res> map(Function1 <? super Arg, ? extends Res> f, Iterable<Arg> a) {
        ArrayList<Res> answer = new ArrayList<>();
        for (Arg elem : a){
            answer.add(f.apply(elem));
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> filter(Predicate<? super Arg> p, Iterable<Arg> a) {
        ArrayList<Arg> answer = new ArrayList<>();
        for (Arg elem : a){
            if (p.apply(elem)) {
                answer.add(elem);
            }
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> takeWhile(Predicate<? super Arg> p, Iterable<Arg> a) {
        ArrayList<Arg> answer = new ArrayList<>();
        for (Arg elem : a){
            if (!p.apply(elem)) {
                return answer;
            }
            answer.add(elem);
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> takeUnless(Predicate<? super Arg> p, Iterable<Arg> a) {
        return takeWhile((Predicate<Arg>) p.not(), a);
    }
    public static <Arg, Res> Res foldl(Iterable<Arg> a, Function2<? super Arg, ? super Res, ? extends Res> f, Res first) {
        Res answer = first;
        for (Arg elem : a){
            answer = f.apply(elem, answer);
        }
        return answer;
    }
    public static <Arg, Res> Res foldr(Iterable<Arg> a, Function2 <? super Arg, ? super Res, ? extends Res> f, Res first) {
        return Collections.recursive(a.iterator(), f, first);
    }

    private static <Arg, Res> Res recursive(Iterator<Arg> it, Function2 <? super Arg, ? super Res, ? extends Res> f, Res first) {
        if(!it.hasNext()) {
            return first;
        }
        Arg arg = it.next();
        return f.apply(arg, recursive(it, f, first));
    }
}
