package com.ryanharter.auto.value.gson.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ResourceTest {
    @Test
    public void testResourceSerialization() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(SampleAdapterFactory.create())
                .create();

        Resource expected = getResource();
        String json = gson.toJson(expected);
        Resource actual = gson.fromJson(json, Resource.class);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResourceDeserialization() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(SampleAdapterFactory.create())
                .create();

        String json = "{\"id\":1,\"_embedded\":{\"embedded_resource\":{\"name\":\"test\"},\"embedded_list\":[{\"name\":\"test_list_1\"},{\"name\":\"test_list_2\"}]},\"_links\":{\"self\":{\"href\":\"self_link\"},\"embedded_list\":[{\"href\":\"test_list_1\"},{\"href\":\"test_list_1\"}]}}";

        Resource actual =gson.fromJson(json, Resource.class);
        Assert.assertEquals(1, actual.id());

        Assert.assertNotNull(actual.embeddedResource());
        Assert.assertNotNull(actual.embeddedResourceList());
        Assert.assertNotNull(actual.selfLink());
        Assert.assertNotNull(actual.embeddedResourceLinks());

        Assert.assertEquals("self_link", actual.selfLink().href());
        Assert.assertEquals(2, actual.embeddedResourceList().size());
        Assert.assertEquals(2, actual.embeddedResourceLinks().size());
    }

    private Resource getResource() {
        EmbeddedResource embeddedResource = EmbeddedResource.create("test");
        EmbeddedResource[] embeddedResourceList = {EmbeddedResource.create("test_list_1"), EmbeddedResource.create("test_list_2")};
        Link[] links = {Link.create("link_1"), Link.create("link_2")};

        return Resource.create(1, embeddedResource, Arrays.asList(embeddedResourceList), Link.create("self_link"), Arrays.asList(links));
    }
}
