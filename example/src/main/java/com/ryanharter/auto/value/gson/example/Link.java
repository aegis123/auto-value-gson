package com.ryanharter.auto.value.gson.example;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Link {

    public abstract String href();

    public static Link create(String href) {
        return new AutoValue_Link(href);
    }
    public static TypeAdapter<Link> typeAdapter(Gson gson) {
        return new AutoValue_Link.GsonTypeAdapter(gson);
    }
}
