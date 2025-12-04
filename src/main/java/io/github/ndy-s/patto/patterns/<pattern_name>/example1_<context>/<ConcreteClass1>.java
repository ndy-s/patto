package io.github.ndys.patto.patterns.<pattern_name>.example1_<context>;

public class ConcreteHandler extends <HandlerOrInterface> {
    @Override
    public void handle(Request request) {
        // handle or pass to next
        if (next != null) next.handle(request);
    }
}


