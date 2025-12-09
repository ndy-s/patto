package io.github.ndys.patto.patterns.pattern_name.example1_context;

public abstract class HandlerOrInterface {
    protected HandlerOrInterface next;

    public void setNext(<HandlerOrInterface> next) {
        this.next = next;
    }

    public abstract void handle(Request request);
}


