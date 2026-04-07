
package org.NineAbyss9.event;

public class ReturnEvent<T> extends Event {
    private T result;
    public ReturnEvent(T resultIn) {
        super();
        this.result = resultIn;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T resultIn) {
        this.result = resultIn;
    }
}
