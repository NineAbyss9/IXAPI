
package org.NineAbyss9.block;

import org.NineAbyss9.event.EventContainer;
import org.NineAbyss9.event.ReturnEvent;

public class ReturnBlock<T> {
    private T result;
    public ReturnBlock(T resultIn) {
        this.result = resultIn;
    }

    public T get() {
        ReturnEvent<T> event = new ReturnEvent<>(result);
        EventContainer.post(event);
        return event.getResult();
    }

    public void setResult(T resultIn) {
        this.result = resultIn;
    }
}
