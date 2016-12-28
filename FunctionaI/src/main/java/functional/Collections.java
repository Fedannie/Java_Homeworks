package functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public final class Collections {
    public static <Arg, Res> ArrayList<Res> map(Function1 <Arg, Res> f, Iterable a) {
        Iterator it = a.iterator();
        ArrayList<Res> answer = new ArrayList<Res>();
        while (it.hasNext()) {
            Object elem = it.next();
            answer.add(f.apply((Arg) elem));
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> filter(Predicate<Arg> p, Iterable a) {
        Iterator it = a.iterator();
        ArrayList<Arg> answer = new ArrayList<Arg>();
        while (it.hasNext()) {
            Object elem = it.next();
            if (p.apply((Arg) elem)) {
                answer.add((Arg) elem);
            }
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> takeWhile(Predicate<Arg> p, Iterable a) {
        Iterator it = a.iterator();
        ArrayList<Arg> answer = new ArrayList<Arg>();
        while (it.hasNext()) {
            Object elem = it.next();
            if (!p.apply((Arg) elem)) {
                return answer;
            }
            answer.add((Arg) elem);
        }
        return answer;
    }
    public static <Arg> ArrayList<Arg> takeUnless(Predicate<Arg> p, Iterable a) {
        return takeWhile((Predicate<Arg>) p.not(), a);
    }
    public static <Arg, Res> Res foldl(Iterable a, Function2<Arg, Res, Res> f, Res first) {
        Iterator it = a.iterator();
        Res answer = first;
        while (it.hasNext()) {
            Object elem = it.next();
            answer = f.apply((Arg) elem, answer);
        }
        return answer;
    }
    public static <Arg, Res> Res foldr(Iterable a, Function2 <Arg, Res, Res> f, Res first) {
        Iterator it = a.iterator();
        Res answer = first;
        ArrayList<Arg> list = new ArrayList<Arg>();
        while (it.hasNext()) {
            Object elem = it.next();
            list.add((Arg) elem);
        }
        ListIterator lit = list.listIterator();
        while (lit.hasNext()) {
            lit.next();
        }
        while (lit.hasPrevious()) {
            Object elem = lit.previous();
            answer = f.apply((Arg) elem, answer);
        }
        return answer;
    }

}
