package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    //rename the class function class -->compareTo /type(class)
    private Comparator<T> compareTo;

    public MaxArrayDeque(Comparator<T> c) {
        compareTo = c;

    }
    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T maxElement = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T testing = this.get(i);
            if (compareTo.compare(testing, maxElement) > 0) {
                maxElement = testing;
            }
        }
        return maxElement;


    }
    //Comparator is a unknown class,may contains functions
    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        T maxElement = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T testing = this.get(i);
            if (c.compare(testing, maxElement) > 0) {
                maxElement = testing;
            }
        }
        return maxElement;



    }

}
