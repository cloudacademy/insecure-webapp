package com.cloudacademy;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class VersionLogger implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEvent) {
        try {
            var applicationName = applicationEvent.getEnvironment().getProperty("application.name");
            var buildVersion = applicationEvent.getEnvironment().getProperty("build.version");
            var buildTimestamp = applicationEvent.getEnvironment().getProperty("build.timestamp");
    
            StringBuilder sb = new StringBuilder();
            sb.append("\n[Application info]");
            sb.append("\nApplication name : " + applicationName);
            sb.append("\nBuild version    : " + buildVersion);
            sb.append("\nBuild timestamp  : " + buildTimestamp);
    
            System.out.println("release version:" + sb.toString());            
        } catch (Exception e) {
            System.out.println("unable to extract build info");
        }
    }
  }