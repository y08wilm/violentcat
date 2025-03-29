/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Tail<X>
implements List<X> {
    protected Object[] values = DEFAULT_EMPTY_ARRAY;
    protected Tail<X> next;
    protected Tail<X> before;
    static final Object[] DEFAULT_EMPTY_ARRAY = new Object[5];

    @Override
    public int size() {
        int counter = this.values.length;
        Tail<X> nextTail = this.next;
        while (nextTail.next != null) {
            counter += 5;
            nextTail = nextTail.next;
        }
        return counter += this.calculateNonNullValues(nextTail.values);
    }

    @Override
    public boolean isEmpty() {
        return this.values[0] == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<X> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(X x) {
        Tail<X> lastTail = this.next;
        Tail<X> beforeTail = null;
        while (lastTail.next != null) {
            beforeTail = lastTail;
            lastTail = lastTail.next;
        }
        int lastTailSize = this.calculateNonNullValues(lastTail.values);
        if (lastTailSize < 5) {
            lastTail.values[lastTailSize] = x;
            return true;
        }
        Tail<X> tail = new Tail<X>();
        tail.before = beforeTail;
        tail.values[0] = x;
        lastTail.next = tail;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends X> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends X> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public X get(int index) {
        return null;
    }

    @Override
    public X set(int index, X element) {
        return null;
    }

    @Override
    public void add(int index, X element) {
    }

    @Override
    public X remove(int index) {
        if (index < 5) {
            this.values[index] = null;
            for (int i = 5; i > index; --i) {
                Object tempValue;
                this.values[i - 1] = tempValue = this.values[i - 1];
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<X> listIterator() {
        return null;
    }

    @Override
    public ListIterator<X> listIterator(int index) {
        return null;
    }

    @Override
    public List<X> subList(int fromIndex, int toIndex) {
        return null;
    }

    public Tail<X> last() {
        Tail<X> nextTail = this.next;
        while (nextTail.next != null) {
            nextTail = nextTail.next;
        }
        return nextTail;
    }

    protected int calculateNonNullValues(Object[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) continue;
            return i;
        }
        return 5;
    }
}

