package com.websocket.server;

public enum RedisNamespace {
    MOGUJIE_ANCHOR_NEW("mogujie:anchor:new","新的未爬取的用户列表"),
    MOGUJIE_ANCHOR_CRAWLED("mogujie:anchor:crawled","已爬取的用户列表"),
    MOGUJIE_ANCHOR_LIVE_STATUS("mogujie:anchor:live:status","淘宝主播的直播状态"),
    MOGUJIE_HISTORY_DATA("mogujie:history:data","淘宝直播历史数据");
    private String key;
    private String value;

    RedisNamespace(String key, String value) {
        this.key = key;
        this.value = value;
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

    public String getKey() {
        return key;
    }
}
