package fr.imtmines.heroes.entity;

import java.util.HashMap;
import java.util.Map;

public class Items {

    public static Map<String, Item> ITEMS = new HashMap<>() {{
        put("caillou", new Item().setName("caillou"));
    }};

}
