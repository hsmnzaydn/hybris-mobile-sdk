package com.felece.hybris_network_sdk.data.network.entities;

import java.util.List;

public class Entry {

    private List<MapElement> entry;

    public List<MapElement> getEntry() {
        return entry;
    }

    public void setEntry(List<MapElement> entry) {
        this.entry = entry;
    }

    public class MapElement {

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}


