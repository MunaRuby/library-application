/*
    PriorityQueue.java: A Custom priority queue to model first in-first out behavior for
    book requests
 */

package utils;

import models.library.BookRequest;
import java.util.*;

public class PriorityQueue{
    private ArrayList<BookRequest> priorityQueue = new ArrayList<>();
    private Comparator<BookRequest> comparator;

    public PriorityQueue () {
        this(null);
    }

    public PriorityQueue (Comparator<BookRequest> comparator) {
        this.comparator = comparator;
    }

    /**
     * Adds a new book request to the priority queue
     * @param t
     * @return boolean
     */
    public boolean offer(BookRequest t) {
        boolean res = false;
        // if comparator is null, the we use the second implementation of first come first serve
        if (comparator == null) return priorityQueue.add(t);
        for (int i = 0; i < priorityQueue.size(); i++) {
            // check if the book names are the same then arrange requests with same books
            // together only if t is of higher priority. If T is of lower priority, then i goes down the queue
            if (priorityQueue.get(i).getBookName().equals(t.getBookName())) {
                if (t.getLender().getRole().compareTo(priorityQueue.get(i).getLender().getRole()) < 0) {
                    priorityQueue.add(i, t);
                    res= true;
                    break;
                }
            }
        }
        // if there's no request in the queue already, add a first
        if (priorityQueue.size() == 0) res = priorityQueue.add(t);
        if (!res) res = priorityQueue.add(t);

        // complementary sorting of the priority queue. Ensures the order of the queue
        priorityQueue.sort(comparator);
        return res;
    }

    public BookRequest poll () {
        return priorityQueue.remove(0);
    }

    public BookRequest peek() {
        return priorityQueue.get(0);
    }

    public int size() {
        return priorityQueue.size();
    }

    public boolean isEmpty () {
        return priorityQueue.isEmpty();
    }

    @Override
    public String toString() {
        return "priorityQueue = " + priorityQueue ;
    }
}
