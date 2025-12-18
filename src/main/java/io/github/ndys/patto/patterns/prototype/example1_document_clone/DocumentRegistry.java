package io.github.ndys.patto.patterns.prototype.example1_document_clone;

import java.util.HashMap;
import java.util.Map;

public class DocumentRegistry {

    private final Map<String, Prototype<Document>> prototypes = new HashMap<>();

    public void register(String key, Prototype<Document> prototype) {
        prototypes.put(key, prototype);
    }

    public Document create(String key) {
        return prototypes.get(key).clone();
    }

    
}
