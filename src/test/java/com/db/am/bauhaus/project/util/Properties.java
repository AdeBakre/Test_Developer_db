package com.db.am.bauhaus.project.util;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class Properties {

    private EnvironmentVariables vars = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();

    public String getContentType() {
        return vars.getProperty("content.type");
    }

    public String getBaseUri() {
        return vars.getProperty("restservices.base.uri");
    }

    public String getMyUri(){ return vars.getProperty("sample.services.uri");}

    public String getAPIKey(){

        return vars.getProperty("api.key");
    }
}
