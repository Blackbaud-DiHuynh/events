package com.blackbaud.events.resources;

import com.blackbaud.events.api.DynamicRule;
import com.blackbaud.events.api.ResourcePaths;
import com.blackbaud.events.core.domain.DynamicRuleEntity;
import com.blackbaud.events.core.domain.DynamicRuleRepository;
import com.blackbaud.mapper.ApiEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Component
@Path(ResourcePaths.DYNAMIC_RULE_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class DynamicRuleResource {

    @Autowired
    DynamicRuleRepository dynamicRuleRepository;

    private ApiEntityMapper<DynamicRule, DynamicRuleEntity> converter = new ApiEntityMapper<>(DynamicRule.class, DynamicRuleEntity.class);

    @POST
    public DynamicRule create(DynamicRule rule) {
        DynamicRuleEntity created = dynamicRuleRepository.save(converter.toEntity(rule));
        return converter.toApi(created);
    }


    @GET
    @Path("{ticketId}")
    public List<DynamicRule> getRules(@PathParam("ticketId") Integer ticketId) {
        List<DynamicRuleEntity> entities = dynamicRuleRepository.findByTicketId(ticketId);
        return converter.toApiList(entities);
    }
}
