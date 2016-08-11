package com.ryanharter.auto.value.gson.example;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class EmbeddedResource {

    public abstract String name();

    public static EmbeddedResource create(String test) {
        return new AutoValue_EmbeddedResource(test);
    }

    public static TypeAdapter<EmbeddedResource> typeAdapter(Gson gson) {
        return new AutoValue_EmbeddedResource.GsonTypeAdapter(gson);
    }
}
