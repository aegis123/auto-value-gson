package com.ryanharter.auto.value.gson.example;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.ryanharter.auto.value.gson.HalEmbedded;
import com.ryanharter.auto.value.gson.HalLink;

import java.util.List;

@AutoValue
public abstract class Resource {
    public abstract int id();

    @HalEmbedded("embedded_resource")
    public abstract EmbeddedResource embeddedResource();

    @HalEmbedded("embedded_list")
    public abstract List<EmbeddedResource> embeddedResourceList();

    @HalLink("self")
    public abstract Link selfLink();

    @HalLink("embedded_list")
    public abstract List<Link> embeddedResourceLinks();

    public static Resource create(int id,
                                  EmbeddedResource embeddedResource,
                                  List<EmbeddedResource> embeddedResourceList,
                                  Link selfLink,
                                  List<Link> embeddedResourceLinks) {
        return new AutoValue_Resource(id, embeddedResource, embeddedResourceList, selfLink, embeddedResourceLinks);
    }

    public static TypeAdapter<Resource> typeAdapter(Gson gson) {
        return new AutoValue_Resource.GsonTypeAdapter(gson);
    }
}
