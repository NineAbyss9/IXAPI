
package com.github.NineAbyss9.ix_api.util;

import net.minecraft.resources.ResourceLocation;
import org.NineAbyss9.annotation.doc.Message;

public class ResourceLocations {
    public ResourceLocations() {
    }

    public static String namespaceAndPath(@Message("Mod id") String namespace, String path)
    {
        return namespace + ":" + path;
    }

    public static ResourceLocation parse(String st) {
        return new ResourceLocation(st);
    }

    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }
}
