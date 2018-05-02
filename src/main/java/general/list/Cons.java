package general.list;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;

public class Cons<E> {
    private final E head;
    private final Cons<E> tail;

    public static <E> Cons<E> cons(E head, Cons<E> tail) {
        return new Cons<E>(head, tail);
    }

    public Cons(E head, Cons<E> tail) {
        this.head = Validate.notNull(head);;
        this.tail = tail;
    }

    public Cons<E> tail() {
        return tail;
    }

    public E head() {
        return head;
    }

    public static <E> Cons<E> from(E... items) {
        return from(Arrays.asList(items));
    }

    public static <E> Cons<E> from(List<E> items) {
        ListIterator<E> listIterator = items.listIterator(items.size());
        return from(listIterator);
    }

    private static <E> Cons<E> from(ListIterator<E> items) {
        Cons<E> head = null;
        while (items.hasPrevious()) {
            E prev = items.previous();
            head = new Cons<E>(prev, head);
        }
        return head;
    }

    public Cons<E> reverse() {
        return reverse(this, null);
    }

    private static <E> Cons<E> reverse(Cons<E> list, Cons<E> acc) {
        if (list == null) {
            return acc;
        } else {
            return reverse(list.tail, cons(list.head, acc));
        }
    }

    public Cons<E> append(Cons<E> list2) {
        return append(this, list2);
    }

    private static <E> Cons<E> append(Cons<E> list1, Cons<E> list2) {
        if (list1 == null) {
            return list2;
        }
        return cons(list1.head, append(list1.tail, list2));
    }

    public List<E> toList() {
        Cons<E> it = this;
        List<E> res = Lists.newLinkedList();
        while (it != null) {
            res.add(it.head);
            it = it.tail;
        }
        return res;
    }

    @Override
    public String toString() {
        return "[" + join(", ") + "]";
    }

    public String join(String joiner) {
        if (tail == null) {
            return head.toString();
        }

        return head.toString() + joiner + tail.join(joiner);
    }

    public Cons<E> filter(E e) {
        Cons<E> reverse = this.reverse();
        Cons<E> result = null;
        while (reverse != null) {
            E head = reverse.head;
            if (!head.equals(e)) {
                result = cons(head, result);
            }
            reverse = reverse.tail;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Cons) {
            @SuppressWarnings("unchecked")
            Cons<E> that = (Cons<E>) obj;
            return this.head.equals(that.head) && ObjectUtils.equals(this.tail, that.tail);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }
}