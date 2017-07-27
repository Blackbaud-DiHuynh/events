package com.blackbaud.events.client;

import com.blackbaud.events.api.DynamicRule;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.rest.client.CrudClient;

public class DynamicRuleClient extends CrudClient<DynamicRule, DynamicRuleClient> {

    public DynamicRuleClient(String baseUrl) {
        super(baseUrl, ResourcePaths.DYNAMIC_RULE_PATH, DynamicRule.class);
    }

}
